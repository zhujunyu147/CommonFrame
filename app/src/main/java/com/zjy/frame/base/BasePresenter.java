package com.zjy.frame.base;

import com.zjy.frame.api.ApiRetrofit;
import com.zjy.frame.api.ApiServer;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class BasePresenter<V extends BaseView> {

    public V baseView;
    public CompositeDisposable compositeDisposable;


    protected ApiServer apiServer = ApiRetrofit.getInstance().getApiService();

    public BasePresenter(V baseView) {
        this.baseView = baseView;
    }

    public void addDisposable(Observable<?> observable, BaseObserver observer) {
        if (compositeDisposable == null) {
            compositeDisposable = new CompositeDisposable();
        }
        observable = observable.subscribeOn(Schedulers.io());
        observable = observable.observeOn(AndroidSchedulers.mainThread());
        Disposable disposable = observable.subscribeWith(observer);

        compositeDisposable.add(disposable);
    }


}
