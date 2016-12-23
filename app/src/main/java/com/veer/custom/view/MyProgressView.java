package com.veer.custom.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;

/**
 * 描述
 * Created by fww
 * date 16/12/21.
 */

public class MyProgressView extends View {

    private Context mContext;
    private WindowManager mWindowManager;

    public MyProgressView(Context context) {
        this(context, null);
    }

    public MyProgressView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MyProgressView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mContext = context;
        init();
    }
    private int millisecond = 1000;
    private float maxProgressSize = 60 * millisecond;//总进度是60
    private float eachProgressWidth = 0;
    private Paint progressPaint;

    private void init(){
        //设置每一刻度的宽度
        DisplayMetrics dm = new DisplayMetrics();
        mWindowManager = (WindowManager)mContext.getSystemService(Context.WINDOW_SERVICE);
        mWindowManager.getDefaultDisplay().getMetrics(dm);
        eachProgressWidth = dm.widthPixels / (maxProgressSize * 0.3f);
        //进度条的背景颜色
        setBackgroundColor(Color.parseColor("#19000000"));
        //进度条的前景颜色,画笔
        progressPaint = new Paint();
        progressPaint.setStyle(Paint.Style.FILL);
        progressPaint.setColor(Color.parseColor("#1482de"));

    }
    private long initTime = -1;//上一次刷新完成后的时间
    private boolean isStart = false;
    private float countWidth = 0;//进度条进度的进程，每次调用invalidate（）都刷新一次
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);


        if (!isStart){
            canvas.drawRect(0,0,countWidth,getMeasuredHeight(),progressPaint);
            return;
        }
        if (initTime == -1){
            initTime =  System.currentTimeMillis();
            canvas.drawRect(0,0,countWidth,getMeasuredHeight(),progressPaint);
            invalidate();
            return;
        }
        //这次刷新的时间，用于与上一次刷新完成的时间作差得出进度条需要增加的进度
        long thisTime = System.currentTimeMillis();
        countWidth += eachProgressWidth * (thisTime - initTime) * 1.0f;
        if (countWidth > getMeasuredWidth()){
            countWidth = getMeasuredWidth();
        }
        Log.d("MyProgressView", "onDraw() called with: " + "countWidth = [" + countWidth + "]");
        canvas.drawRect(0,0,countWidth,getMeasuredHeight(),progressPaint);

        //如果都了最大长度，就不再调用invalidate();了
        if (countWidth < getMeasuredWidth()  && isStart){
            initTime = System.currentTimeMillis();
            invalidate();
        }else{
            countWidth = 0;
            initTime = -1;
            isStart = false;
        }


    }
    //开始或暂停进度条进度刷新
    public void setIsStart(boolean isStart) {
        if (isStart == this.isStart)
            return;
        this.isStart = isStart;
        if (isStart) {
            initTime = -1;
            invalidate();
        }
    }
    //重置进度条
    public void reset() {
        countWidth = 0;
        initTime = -1;
        isStart = false;
        invalidate();
    }
}
