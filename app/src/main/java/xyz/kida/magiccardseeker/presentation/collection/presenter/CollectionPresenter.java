package xyz.kida.magiccardseeker.presentation.collection.presenter;

import java.util.List;
import java.util.stream.Collectors;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableCompletableObserver;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subscribers.ResourceSubscriber;
import xyz.kida.magiccardseeker.data.entity.MagicCardEntity;
import xyz.kida.magiccardseeker.data.repository.MagicCardRepository;
import xyz.kida.magiccardseeker.presentation.collection.CollectionContract;
import xyz.kida.magiccardseeker.presentation.mappers.MagicCardEntityMapper;

public class CollectionPresenter implements CollectionContract.Presenter {

    private MagicCardRepository repository;
    private CollectionContract.View view;
    private CompositeDisposable compositeDisposable;
    private MagicCardEntityMapper mapper;

    public CollectionPresenter(MagicCardRepository repository, MagicCardEntityMapper mapper) {
        this.repository = repository;
        this.compositeDisposable = new CompositeDisposable();
        this.mapper = mapper;
    }

    @Override
    public void attachView(CollectionContract.View view) {
        this.view = view;
    }

    @Override
    public void getCollection() {
        compositeDisposable.add(repository.loadCollection()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new ResourceSubscriber<List<MagicCardEntity>>() {

                    @Override
                    public void onNext(List<MagicCardEntity> magicCardEntities) {
                        view.displayCollection(magicCardEntities
                                .stream()
                                .map(entity -> mapper.toViewModel(entity))
                                .collect(Collectors.toList()));
                    }

                    @Override
                    public void onError(Throwable t) {
                        System.out.println("Error : " + t.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                }));
    }

    @Override
    public void deleteCardFromCollection(String cardId) {
        compositeDisposable.add(repository.deleteCardFromCollection(cardId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableCompletableObserver() {
                    @Override
                    public void onComplete() {
                        view.onCardRemovedFromCollection();
                    }

                    @Override
                    public void onError(Throwable e) {
                        System.out.println("Error : " + e.getMessage());
                    }
                }));
    }

    @Override
    public void detachView() {
        compositeDisposable.dispose();
        view = null;

    }
}
