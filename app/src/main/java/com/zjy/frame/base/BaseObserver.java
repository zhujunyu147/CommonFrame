package com.zjy.frame.base;

import android.util.Log;

import com.google.gson.JsonParseException;

import org.json.JSONException;

import java.net.ConnectException;
import java.net.UnknownHostException;
import java.text.ParseException;

import io.reactivex.observers.DisposableObserver;
import retrofit2.HttpException;

public abstract class BaseObserver<T> extends DisposableObserver<T> {
    protected BaseView view;

    private boolean isShowDialog;

    public BaseObserver(BaseView view) {
        this.view = view;
    }

    @Override
    protected void onStart() {
        Log.e("BaseObserver","onStart");
        super.onStart();
    }

    @Override
    public void onNext(T t) {
        Log.e("BaseObserver","onNext");
        onSuccess(t);
    }

    @Override
    public void onError(Throwable e) {
        Log.e("BaseObserver","onError");
        if (view != null && isShowDialog) {
            view.hideLoading();
        }
        BaseException baseException = null;

        if (e != null) {

            if (e instanceof BaseException) {
                baseException = (BaseException) e;
                //回调到view层 处理
                if (view != null) {
                    view.onErrorCode(new BaseModel(baseException.getErrorCode(), baseException.getErrorMsg()));
                } else {
                    onError(baseException.getErrorMsg());
                }
            } else {

                if (e instanceof HttpException) {
                    baseException = new BaseException(BaseException.BAD_NETWORK_MSG, e, BaseException.BAD_NETWORK);
                } else if (e instanceof ConnectException || e instanceof UnknownHostException) {
                    baseException = new BaseException(BaseException.CONNECT_ERROR_MSG, e, BaseException.CONNECT_ERROR);
                } else if (e instanceof JsonParseException
                        || e instanceof JSONException
                        || e instanceof ParseException) {
                    baseException = new BaseException(BaseException.PARSE_ERROR_MSG, e, BaseException.PARSE_ERROR);
                } else {
                    baseException = new BaseException(BaseException.OTHER_MSG, e, BaseException.OTHER);
                }

            }

        } else {
            baseException = new BaseException(BaseException.OTHER_MSG, e, BaseException.OTHER);
        }
        onError(baseException.getErrorMsg());

    }

    @Override
    public void onComplete() {
        Log.e("BaseObserver","onComplete");
    }

    public abstract void onSuccess(T o);

    public abstract void onError(String msg);

}
