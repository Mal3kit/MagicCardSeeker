package xyz.kida.magiccardseeker.presentation.collection.fragment;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import xyz.kida.magiccardseeker.R;
import xyz.kida.magiccardseeker.data.di.FakeDI;
import xyz.kida.magiccardseeker.presentation.collection.CollectionContract;
import xyz.kida.magiccardseeker.presentation.collection.adapter.CollectionCardAdapter;
import xyz.kida.magiccardseeker.presentation.collection.model.CollectionListener;
import xyz.kida.magiccardseeker.presentation.collection.presenter.CollectionPresenter;
import xyz.kida.magiccardseeker.presentation.mappers.MagicCardEntityMapper;
import xyz.kida.magiccardseeker.presentation.model.MagicCardViewModel;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class CollectionFragment extends Fragment implements CollectionContract.View, CollectionListener {

    @BindView(R.id.collection_fragment_recycler_view)
    RecyclerView recyclerView;

    private View rootView;
    private CollectionCardAdapter collectionCardAdapter;
    private CollectionContract.Presenter presenter;

    public CollectionFragment() {

    }

    public static CollectionFragment newInstance() {
        return new CollectionFragment();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        rootView = inflater.inflate(R.layout.fragment_collection, container, false);
        ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        configureRecyclerView();

        presenter = new CollectionPresenter(FakeDI.getMagicCardRepository(), new MagicCardEntityMapper());
        presenter.attachView(this);
        presenter.getCollection();
    }

    private void configureRecyclerView() {
        collectionCardAdapter = new CollectionCardAdapter(this);
        recyclerView.setAdapter(collectionCardAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    @Override
    public void displayCollection(List<MagicCardViewModel> magicCardViewModels) {
        collectionCardAdapter.bindViewModels(magicCardViewModels);
    }

    @Override
    public void onCardRemovedFromCollection() {


    }

    @Override
    public void onDeleteCardFromCollection(String cardId) {
        presenter.deleteCardFromCollection(cardId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        presenter.detachView();
    }
}
