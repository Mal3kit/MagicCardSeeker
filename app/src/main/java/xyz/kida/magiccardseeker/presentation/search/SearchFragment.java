package xyz.kida.magiccardseeker.presentation.search;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableObserver;
import xyz.kida.magiccardseeker.CardStreams;
import xyz.kida.magiccardseeker.R;
import xyz.kida.magiccardseeker.data.api.models.MagicCard;
import xyz.kida.magiccardseeker.presentation.search.adapter.SearchAdapter;
import xyz.kida.magiccardseeker.presentation.search.adapter.SearchAdapterListener;

public class SearchFragment extends Fragment implements SearchAdapterListener {

    @BindView(R.id.fragment_search_recycler_view)
    RecyclerView recyclerView;

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
        this.configureRecyclerView();
        this.httpRequestRetrofit();
        return view;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.disposeWhenDestroy();
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

    private void httpRequestRetrofit() {
        this.disposable = CardStreams.streamGetCards("Nissa,")
                .subscribeWith(new DisposableObserver<List<MagicCard>>() {
                    @Override
                    public void onNext(List<MagicCard> magicCards) {
                        updateUI(magicCards);
                    }

                    @Override
                    public void onError(Throwable e) {

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

    private void updateUI(List<MagicCard> cards) {
        this.cards.clear();
        this.cards.addAll(cards);
        adapter.notifyDataSetChanged();
    }

}
