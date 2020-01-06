package xyz.kida.magiccardseeker.presentation.search;

import io.reactivex.disposables.CompositeDisposable;

public class SearchPresenter {

    private CompositeDisposable compositeDisposable;

    public SearchPresenter() {
        this.compositeDisposable = new CompositeDisposable();
    }
}
