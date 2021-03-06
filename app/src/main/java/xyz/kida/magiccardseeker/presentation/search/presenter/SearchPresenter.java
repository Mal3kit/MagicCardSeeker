package xyz.kida.magiccardseeker.presentation.search.presenter;

import java.util.stream.Collectors;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableCompletableObserver;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;
import xyz.kida.magiccardseeker.data.api.models.MagicCardSearchResponse;
import xyz.kida.magiccardseeker.data.repository.MagicCardRepository;
import xyz.kida.magiccardseeker.presentation.mappers.MagicCardMapper;
import xyz.kida.magiccardseeker.presentation.model.MagicCardViewModel;
import xyz.kida.magiccardseeker.presentation.search.SearchContract;

public class SearchPresenter implements SearchContract.Presenter{

    private CompositeDisposable compositeDisposable;
    private MagicCardRepository repository;
    private SearchContract.View view;
    private MagicCardMapper mapper;

    public SearchPresenter(MagicCardRepository repository, MagicCardMapper mapper) {
        this.compositeDisposable = new CompositeDisposable();
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public void searchCards(String cardName) {
        compositeDisposable.clear();
        compositeDisposable.add(repository.getMagicCards(cardName)
                .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver<MagicCardSearchResponse>() {
                    @Override
                    public void onSuccess(MagicCardSearchResponse magicCardSearchResponse) {
                        view.showCards(magicCardSearchResponse.getCards()
                                .stream().map(card -> mapper.toViewModel(card)).collect(Collectors.toList()));
                    }

                    @Override
                    public void onError(Throwable e) {
                        System.out.println("Error : " + e.getMessage());
                    }
                }));
    }

    @Override
    public void addCardToCollection(MagicCardViewModel magicCardViewModel) {
        compositeDisposable.add(repository.addCardToCollection(magicCardViewModel)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableCompletableObserver() {
                    @Override
                    public void onComplete() {
                        view.onCardAddToCollection();
                    }

                    @Override
                    public void onError(Throwable e) {
                        System.out.println("Error : " + e.getMessage());
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
    public void attachView(SearchContract.View view) {
        this.view = view;
    }

    @Override
    public void cancelSubscription() {
        compositeDisposable.clear();
    }

    @Override
    public void detachView() {
        compositeDisposable.dispose();
        view = null;

    }
}
