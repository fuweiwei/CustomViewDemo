package com.veer.custom.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.veer.custom.R;


/**
 * 带百分比文字进度条
 * @author Veer
 * @email 276412667@qq.com
 * @date 18/9/11
 */

public class RoundedRectProgressBar extends View {

    /**
     * 画笔
     */
    private Paint mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    /**
     * 背景颜色
     */
    private int mBackColor = Color.GRAY;
    /**
     * 进度条颜色
     */
    private int mBarColor = Color.GREEN;
    /**
     * 文字颜色
     */
    private int mTextColor = Color.WHITE;
    /**
     * 进度
     */
    private int mProgress = 0;

    /**
     * 圆角半径
     */
    private int mRadius;


    public RoundedRectProgressBar(Context context) {
        this(context,null);
    }

    public RoundedRectProgressBar(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public RoundedRectProgressBar(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        //获取自定义属性
        TypedArray array = context.getTheme().obtainStyledAttributes(attrs, R.styleable.RoundedRectProgressBar,defStyleAttr,0);
        int count = array.getIndexCount();
        for(int i=0;i<count;i++){
            int attr = array.getIndex(i);
            switch (attr){
                case R.styleable.RoundedRectProgressBar_backColor:
                    mBackColor = array.getColor(attr, Color.GRAY);

                    break;
                case R.styleable.RoundedRectProgressBar_barColor:
                    mBarColor = array.getColor(attr, Color.GREEN);

                    break;
                case R.styleable.RoundedRectProgressBar_textColor:
                    mTextColor = array.getColor(attr, Color.WHITE);

                    break;
                default:
                    break;
            }

        }
        array.recycle();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        mRadius = this.getMeasuredHeight()/5;

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        //画背景
        mPaint.setColor(mBackColor);
        mPaint.setStyle(Paint.Style.FILL);
        canvas.drawRoundRect(new RectF(0,0,this.getMeasuredWidth(),this.getMeasuredHeight()),mRadius,mRadius,mPaint);
        //画进度条
        mPaint.setColor(mBarColor);
        mPaint.setStyle(Paint.Style.FILL);
        canvas.drawRoundRect(new RectF(0,0,this.getMeasuredWidth()*mProgress/100f,this.getMeasuredHeight()),mRadius,mRadius,mPaint);
        //进度
        mPaint.setColor(mTextColor);
        mPaint.setTextSize(this.getMeasuredHeight() / 1.2f);
        String text = "" + mProgress + "%";
        float x = this.getMeasuredWidth() * mProgress / 100 - mPaint.measureText(text) - 10;
        float y = this.getMeasuredHeight() / 2f - mPaint.getFontMetrics().ascent / 2f - mPaint.getFontMetrics().descent / 2f;
        canvas.drawText(text, x, y, mPaint);


    }
    /***
     * 设置进度条进度, 外部调用
     * @date  18/9/11 下午4:34
     * @author  Veer
     * @param  progress
    */
    public void setProgress(int progress) {
        if (progress > 100) {
            this.mProgress = 100;
        } else if (progress < 0) {
            this.mProgress = 0;
        } else {
            this.mProgress = progress;
        }
        postInvalidate();
    }
}
