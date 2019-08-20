package com.zjy.frame.detail.presenter;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Environment;
import android.support.v4.content.FileProvider;
import android.text.format.Time;
import android.util.Log;
import android.view.View;
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

public class DetailPresenter extends BasePresenter<DetailView> {

    public DetailPresenter(DetailView baseView) {
        super(baseView);
    }

    public void getScrollShot(final ScrollView mScrollView) {

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
//                mUri = Uri.fromFile(file);

                emitter.onComplete();
            }
        });


        addDisposable(oble, new BaseObserver<Uri>(baseView) {
            @Override
            public void onSuccess(Uri mUri) {
                baseView.shotSuccess(mUri);
            }

            @Override
            public void onError(String msg) {

            }
        });


    }


    public void getCommonShot() {


        Observable<Uri> oble = Observable.create(new ObservableOnSubscribe<Uri>() {
            @Override
            public void subscribe(ObservableEmitter<Uri> emitter) throws Exception {
                Activity activity = (Activity) baseView.getContext();
                View decorview = activity.getWindow().getDecorView();
                decorview.setDrawingCacheEnabled(true);
                decorview.buildDrawingCache();
                Bitmap bitmap = Bitmap.createBitmap(decorview.getDrawingCache());
                Bitmap waterSignBitmap = createBitmap(bitmap, "@ Honeywell空气检测仪App", true);

                decorview.setDrawingCacheEnabled(false);
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
//                Uri uri = Uri.fromFile(file);
                String s = baseView.getContext().getPackageName() + ".fileprovider";

                Uri photoOutputUri = FileProvider.getUriForFile(baseView.getContext(), s, file);

                emitter.onNext(photoOutputUri);
            }
        });

        addDisposable(oble, new BaseObserver<Uri>(baseView) {
            @Override
            public void onSuccess(Uri mUri) {
                Log.e("DetailPresenter", "onSuccess" + mUri);
                baseView.shotSuccess(mUri);
            }

            @Override
            public void onError(String msg) {
                Log.e("DetailPresenter", "onError" + msg);
            }
        });

    }

    // 给图片添加水印
    private Bitmap createBitmap(Bitmap src, String string, boolean isWhite) {

        Time t = new Time();
        t.setToNow();
        int w = src.getWidth();
        int h = src.getHeight();
        String mstrTitle = string;
        Bitmap bmpTemp = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bmpTemp);
        Paint paint = new Paint();
        canvas.drawBitmap(src, 0, 0, paint);

        Rect rect = new Rect(0, h - 200, w, h);//画一个矩形

        String familyName = "宋体";
        Typeface font = Typeface.create(familyName, Typeface.BOLD);
        if (isWhite) {
            paint.setColor(baseView.getContext().getResources().getColor(R.color.text_color1));
        } else {
            paint.setColor(baseView.getContext().getResources().getColor(R.color.text_color1));
        }

        paint.setTypeface(font);
        paint.setTextSize(30);
        paint.setTextAlign(Paint.Align.CENTER);


        Paint.FontMetrics fontMetrics = paint.getFontMetrics();
        float top = fontMetrics.top;//为基线到字体上边框的距离,即上图中的top
        float bottom = fontMetrics.bottom;//为基线到字体下边框的距离,即上图中的bottom
        int baseLineY = (int) (rect.centerY() - top / 2 - bottom / 2);//基线中间点的y轴计算公式
        //文字的位置
        canvas.drawText(mstrTitle, rect.centerX(), baseLineY, paint);
        canvas.save(Canvas.ALL_SAVE_FLAG);
        canvas.restore();
        return bmpTemp;
    }


}
