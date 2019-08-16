package com.zjy.frame.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RadialGradient;
import android.graphics.Shader;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;

import com.zjy.frame.R;

/**
 * Created by zhujunyu on 2017/2/16.
 */

public class EnvironmentView extends View {

    private Context context;
    //圆心
    private float centerX;
    private float centerY;

    private Paint roundPaint;
    private Paint roundInnerPaint;
    private Paint homePaint;
    private Paint romePaint;

    private int diameterOuter;
    private int diameterInside;
    private int radius;
    public static int DISTANCE_OUT_IN;

    private String homeName;
    private String romeName;

    Shader mRadialGradient = null;

    private float[] position = new float[3];


    public EnvironmentView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        initView();
    }

    public EnvironmentView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        initView();
    }

    public EnvironmentView(Context context) {
        super(context);
        this.context = context;
        initView();
    }

    private void initView() {


        homePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        homePaint.setTextSize(40);
        homePaint.setStrokeWidth(1);
        homePaint.setColor(Color.WHITE);
        homePaint.setTextAlign(Paint.Align.CENTER);


        romePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        romePaint.setTextSize(30);
        romePaint.setStrokeWidth(1);
        romePaint.setColor(Color.WHITE);
        romePaint.setTextAlign(Paint.Align.CENTER);


        roundInnerPaint = new Paint();
        roundInnerPaint.setAntiAlias(true);
        roundInnerPaint.setStyle(Paint.Style.FILL);
        roundInnerPaint.setColor(Color.WHITE);
        DISTANCE_OUT_IN = (int) (getFontHeight(homePaint) + getFontHeight(romePaint)) * 3 / 2;
        Log.e("homePaint", "" + getFontHeight(homePaint));
        Log.e("romePaint", "" + getFontHeight(romePaint));
        roundPaint = new Paint();
        roundPaint.setAntiAlias(true);
//        roundPaint.setStyle(Paint.Style.STROKE);
//        roundPaint.setStrokeWidth(DISTANCE_OUT_IN);

//        roundPaint.setColor(Color.LTGRAY);
//        roundPaint.setAlpha(166);

        getCenter();
        //1.圆心X坐标2.Y坐标3.半径 4.颜色数组 5.相对位置数组，可为null 6.渲染器平铺模式

        float p = (float)radius / (float) diameterInside;

        position[0] = 0.1f;
        position[1] = p;
        position[2] = 1.0f;
        mRadialGradient = new RadialGradient(centerX, centerY, diameterInside, new int[]{Color.WHITE, Color.LTGRAY, getResources().getColor(R.color.chart_green_color_transparent)}, position, Shader.TileMode.CLAMP);
        roundPaint.setShader(mRadialGradient);
    }

    public void setColor(int color,int transparentColor) {
        mRadialGradient = new RadialGradient(centerX, centerY, diameterInside, new int[]{Color.WHITE, color,transparentColor}, position, Shader.TileMode.CLAMP);
        getPaint().setShader(mRadialGradient);
        invalidate();
    }

    public void setHomeRoomName(String homeName, String romeName) {
        this.homeName = homeName;
        this.romeName = romeName;
        this.invalidate();
    }


    public Paint getPaint() {
        return roundPaint;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawCircle(centerX, centerY, diameterInside, roundPaint);//外圆
        canvas.drawCircle(centerX, centerY, radius, roundInnerPaint);//内圆
        if (TextUtils.isEmpty(homeName) || TextUtils.isEmpty(romeName)) {
            return;
        }
        canvas.drawText(homeName, centerX, getBaseLine(homePaint, DISTANCE_OUT_IN / 3), homePaint);
        canvas.drawText(romeName, centerX, getBaseLine(romePaint, DISTANCE_OUT_IN / 2 + (int) getFontHeight(romePaint)), romePaint);
    }

    private void getCenter() {
        WindowManager wm = (WindowManager) context
                .getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics dm = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(dm);

        centerX = dm.widthPixels / 2;
        centerY = centerX + DISTANCE_OUT_IN;
        diameterInside = dm.widthPixels / 2 + DISTANCE_OUT_IN;
        radius = dm.widthPixels / 2;
    }

    private int Dp2Px(int dp) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dp * scale + 0.5f);
    }

    /**
     * 获取文字的底部线
     */
    public float getBaseLine(Paint textPaint, int targetY) {
        Paint.FontMetrics fontMetrics = textPaint.getFontMetrics();
        float textBaseY = targetY + (fontMetrics.descent - fontMetrics.ascent) / 2 - fontMetrics.descent;
        return textBaseY;
    }

    public float getFontHeight(Paint textPaint) {
        Paint.FontMetrics fontMetrics = textPaint.getFontMetrics();
        float height = fontMetrics.descent - fontMetrics.ascent;
        return height;
    }

}
