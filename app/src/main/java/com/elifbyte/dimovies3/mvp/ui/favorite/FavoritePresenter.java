package com.elifbyte.dimovies3.mvp.ui.favorite;

import com.androidnetworking.error.ANError;
import com.elifbyte.dimovies3.mvp.data.DataManager;
import com.elifbyte.dimovies3.mvp.data.db.model.Favorite;
import com.elifbyte.dimovies3.mvp.ui.base.BasePresenter;
import com.elifbyte.dimovies3.mvp.utils.rx.SchedulerProvider;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;

public class FavoritePresenter<V extends FavoriteMvpView>  extends BasePresenter<V> implements FavoriteMvpPresenter<V>{

    @Inject
    public FavoritePresenter(DataManager dataManager,
                             SchedulerProvider schedulerProvider,
                             CompositeDisposable compositeDisposable) {
        super(dataManager, schedulerProvider, compositeDisposable);
    }

    @Override
    public void onViewPrepared() {
        getMvpView().showLoading();
        getCompositeDisposable().add(getDataManager()
                .getAllFavorite()
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(new Consumer<List<Favorite>>() {
                    @Override
                    public void accept(List<Favorite> favoriteList) {
                        if (favoriteList != null) {
                            getMvpView().updatFavorite(favoriteList);
                        }
                        getMvpView().hideLoading();
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(@NonNull Throwable throwable) {
                        if (!isViewAttached()) {
                            return;
                        }

                        getMvpView().hideLoading();

                        // handle the error here
                        if (throwable instanceof ANError) {
                            ANError anError = (ANError) throwable;
                            handleApiError(anError);
                        }
                    }
                }));

    }


}
