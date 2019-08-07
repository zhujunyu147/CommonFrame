package com.zjy.frame.detail.presenter;

import android.content.Intent;
import android.graphics.Bitmap;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Environment;
import android.util.Log;
import android.widget.ScrollView;

import com.zjy.frame.R;
import com.zjy.frame.base.BaseObserver;
import com.zjy.frame.base.BasePresenter;
import com.zjy.frame.detail.view.DetailView;
import com.zjy.frame.utils.ScreenUtil;

import java.io.File;
import java.io.FileOutputStream;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class DetailPresenter extends BasePresenter<DetailView> {
    private Uri mUri;

    public DetailPresenter(DetailView baseView) {
        super(baseView);
    }

    private void getScrollShot(final ScrollView mScrollView) {










        Observable<String> oble = Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> emitter) throws Exception {

                Bitmap bitmap;
                bitmap = ScreenUtil.shotScrollView(mScrollView, baseView.getContext());
                Bitmap waterSignBitmap = ScreenUtil.createBitmap(baseView.getContext(), bitmap, "@ Honeywell空气检测仪App", false);
                String SavePath = Environment.getExternalStorageDirectory().getPath() + "/IAQ/ShareImage";
                File path = new File(SavePath);
                String filepath = SavePath + "/ScreenShort.png";
                File file = new File(filepath);
                if (file.exists()) {
                    file.delete();
                }
                if (!path.exists()) {
                    path.mkdirs();
                }
                if (!file.exists()) {
                    file.createNewFile();
                }
                FileOutputStream fos = new FileOutputStream(file);
                waterSignBitmap.compress(Bitmap.CompressFormat.PNG, 90, fos);
                fos.flush();
                fos.close();
                MediaScannerConnection.scanFile(baseView.getContext(), new String[]{filepath}, null, null);
                mUri = Uri.fromFile(file);
                emitter.onComplete();
            }
        });

        Observer<String> oser = new Observer<String>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(String s) {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {
                baseView.shotSuccess(mUri);
            }
        };
        oble.subscribe(oser);


        addDisposable(oble, new BaseObserver(baseView) {
            @Override
            public void onSuccess(Object o) {

            }

            @Override
            public void onError(String msg) {

            }
        });




    }




    private void getCommonShot() {

    }


}
