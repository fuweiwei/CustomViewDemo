package com.veer.custom.view;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.os.Handler;
import android.os.Message;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.View;

import com.veer.custom.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

/**
 *水波纹效果
 * Created by fww on 16/10/31.
 */
public class WaveWater extends View
{

    private Context context;
    private int mViewWidth;
    private int mViewHeight;


    private float mLevelLine;

    //波浪的高宽带
    private float mWaveHeight = 20;
    private float mWaveWidth = 160;

    private float mLeftSide;

    private float mMoveLen;

    public static final float SPEED = 7f;

    private List<Point> mPointsList;
    private Paint mPaint;
    private Path mWavePath;
    private boolean isMeasured = false;

    private Timer timer;
    private MyTimerTask mTask;
    Handler updateHandler = new Handler()
    {

        @Override
        public void handleMessage(Message msg)
        {

            mMoveLen += SPEED;

            if (mLevelLine < 0)
                mLevelLine = 0;
            mLeftSide += SPEED;

            for (int i = 0; i < mPointsList.size(); i++)
            {
                mPointsList.get(i).setX(mPointsList.get(i).getX() + SPEED);
                switch (i % 4)
                {
                    case 0:
                    case 2:
                        mPointsList.get(i).setY(mLevelLine);
                        break;
                    case 1:
                        mPointsList.get(i).setY(mLevelLine + mWaveHeight);
                        break;
                    case 3:
                        mPointsList.get(i).setY(mLevelLine - mWaveHeight);
                        break;
                }
            }
            if (mMoveLen >= mWaveWidth)
            {

                mMoveLen = 0;
                resetPoints();
            }
            invalidate();
        }

    };


    private void resetPoints()
    {
        mLeftSide = -mWaveWidth;
        for (int i = 0; i < mPointsList.size(); i++)
        {
            mPointsList.get(i).setX(i * mWaveWidth / 4 - mWaveWidth);
        }
    }

    public WaveWater(Context context)
    {
        super(context);
        this.context = context;
        init();
    }

    public WaveWater(Context context, AttributeSet attrs)
    {
        super(context, attrs);
        this.context = context;
        init();
    }

    public WaveWater(Context context, AttributeSet attrs, int defStyle)
    {
        super(context, attrs, defStyle);
        this.context = context;
        init();
    }

    private void init()
    {
        mPointsList = new ArrayList<Point>();
        timer = new Timer();

        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setColor(ContextCompat.getColor(context,R.color.orange_nomal));

        mWavePath = new Path();
    }

    @Override
    public void onWindowFocusChanged(boolean hasWindowFocus)
    {
        super.onWindowFocusChanged(hasWindowFocus);
        //  start();
    }

    public void start()
    {
        if (mTask != null)
        {
            mTask.cancel();
            mTask = null;
        }
        mTask = new MyTimerTask(updateHandler);
        timer.schedule(mTask, 0, 10);
    }
    public void stop()
    {
        if (mTask != null)
        {
            mTask.cancel();
            mTask = null;
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec)
    {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        if (!isMeasured) {

            isMeasured = true;
            mViewHeight = getMeasuredHeight();


            int[] location = new int[2];
            this.getLocationInWindow(location);

            DisplayMetrics metric = new DisplayMetrics();
            ((Activity)context).getWindowManager().getDefaultDisplay().getMetrics(metric);

            mViewWidth = metric.widthPixels;
            mLevelLine = this.getBottom()+50;

            mLeftSide = -mWaveWidth;

            int n = (int) Math.round(mViewWidth / mWaveWidth + 0.5);

            for (int i = 0; i < (4 * n + 5); i++)
            {
                float x = i * mWaveWidth / 4 - mWaveWidth;
                float y = 0;
                switch (i % 4)
                {
                    case 0:
                    case 2:
                        y = mLevelLine;
                        break;
                    case 1:
                        y = mLevelLine + mWaveHeight;
                        break;
                    case 3:
                        y = mLevelLine - mWaveHeight;
                        break;
                }
                mPointsList.add(new Point(x, y));
            }
        }
    }

    @Override
    protected void onDraw(Canvas canvas)
    {
        //重置
        mWavePath.reset();
        int i = 0;
        mWavePath.moveTo(mPointsList.get(0).getX(), mPointsList.get(0).getY());
        for (; i < mPointsList.size() - 2; i = i + 2)
        {
            //贝尔塞曲线
            mWavePath.quadTo(mPointsList.get(i + 1).getX(),
                    mPointsList.get(i + 1).getY(), mPointsList.get(i + 2)
                            .getX(), mPointsList.get(i + 2).getY());
        }
        mWavePath.lineTo(mPointsList.get(i).getX(), mViewHeight);
        mWavePath.lineTo(mLeftSide, mViewHeight);
        mWavePath.close();
        canvas.drawPath(mWavePath, mPaint);

    }

    class MyTimerTask extends TimerTask
    {
        Handler handler;

        public MyTimerTask(Handler handler)
        {
            this.handler = handler;
        }

        @Override
        public void run()
        {
            handler.sendMessage(handler.obtainMessage());
        }

    }

    class Point
    {
        private float x;
        private float y;

        public float getX()
        {
            return x;
        }

        public void setX(float x)
        {
            this.x = x;
        }

        public float getY()
        {
            return y;
        }

        public void setY(float y)
        {
            this.y = y;
        }

        public Point(float x, float y)
        {
            this.x = x;
            this.y = y;
        }

    }

}
