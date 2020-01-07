package xyz.kida.magiccardseeker.presentation.search;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.stream.Collectors;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableObserver;
import xyz.kida.magiccardseeker.CardStreams;
import xyz.kida.magiccardseeker.R;
import xyz.kida.magiccardseeker.data.api.models.MagicCard;
import xyz.kida.magiccardseeker.data.api.models.MagicCardSearchResponse;
import xyz.kida.magiccardseeker.presentation.search.adapter.SearchAdapter;
import xyz.kida.magiccardseeker.presentation.search.adapter.SearchAdapterListener;

public class SearchFragment extends Fragment implements SearchAdapterListener {

    @BindView(R.id.fragment_search_recycler_view)
    RecyclerView recyclerView;

    @BindView(R.id.search_view)
    SearchView searchView;

    private List<MagicCard> cards;
    private Disposable disposable;
    private SearchAdapter adapter;

    public SearchFragment() {
        // Required empty public constructor
    }

    public static SearchFragment newInstance() {
        SearchFragment fragment = new SearchFragment();
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_search, container, false);
        ButterKnife.bind(this, view);
        this.configureSearchView();
        this.configureRecyclerView();
        return view;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.disposeWhenDestroy();
    }

    private void configureSearchView() {
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            private Timer timer = new Timer();

            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if (newText.length() == 0) {

                } else {
                    timer.cancel();
                    timer = new Timer();
                    int sleep = 1000;
                    if (newText.length() == 1) {
                        sleep = 50000;
                    } else if (newText.length() <= 3) {
                        sleep = 5000;
                    } else if (newText.length() <= 5) {
                        sleep = 3000;
                    }
                    timer.schedule(new TimerTask() {
                        @Override
                        public void run() {
                            httpRequestRetrofit(newText);
                        }
                    }, sleep);
                }
                return true;
            }
        });
    }

    private void configureRecyclerView() {
        this.cards = new ArrayList<>();
        this.adapter = new SearchAdapter(this.cards, Glide.with(this), this);
        this.recyclerView.setAdapter(adapter);
        this.recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));


    }

    @Override
    public void onClickDeleteButton(int position) {
        MagicCard card = adapter.getMagicCard(position);
        Toast.makeText(getContext(),"Trying to delete card " + card.getName(), Toast.LENGTH_SHORT).show();
    }

    private void httpRequestRetrofit(String cardName) {
        this.disposable = CardStreams.streamGetCards(cardName)
                .subscribeWith(new DisposableObserver<MagicCardSearchResponse>() {
                    @Override
                    public void onNext(MagicCardSearchResponse magicCards) {
                        updateUI(magicCards);
                    }


                    @Override
                    public void onError(Throwable e) {
                        System.out.println("error : " +e.getMessage());

                    }

                    @Override
                    public void onComplete() {
                        System.out.println("Complete ! lol");
                    }
                });
    }

    private void disposeWhenDestroy() {
        if (this.disposable != null && !this.disposable.isDisposed()) {
            this.disposable.dispose();
        }
    }

    private void updateUI(MagicCardSearchResponse magicCards) {
        this.cards.clear();
        cards.addAll(magicCards.getCards()
                .stream()
                .filter(c -> c.getImageUrl() != null)
                .collect(Collectors.toList()));
        adapter.notifyDataSetChanged();
    }

}
