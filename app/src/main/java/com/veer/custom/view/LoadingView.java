package com.veer.custom.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import com.veer.custom.R;

/**
 * 填充view
 * Created by fww
 * date 16/4/25.
 */
public class LoadingView extends View {
    private Bitmap bitmap;
    private Paint paint1;
    private Paint paint2;
    private Paint paint3;
    private Context context;
    private  int y;
    public LoadingView(Context context) {
        this(context, null);
    }

    public LoadingView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public LoadingView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        paint1 = new Paint();
        paint1.setColor(context.getResources().getColor(R.color.orange_nomal));
        paint2 = new Paint();
        paint2.setColor(context.getResources().getColor(R.color.gray));
        paint3 = new Paint();
        paint3.setColor(context.getResources().getColor(R.color.gray_bg));
        Bitmap bitmap1 = BitmapFactory.decodeResource(context.getResources(), R.mipmap.loading_0000);
        bitmap = bitmap1.extractAlpha();// 获取一个透明图片
        y = bitmap.getHeight();//初始化y轴坐标
    }
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec)
    {
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        int width;
        int height ;
        if (widthMode == MeasureSpec.EXACTLY)
        {
            width = widthSize;
        } else
        {
            float textWidth = bitmap.getWidth();
            int desired = (int) (getPaddingLeft() + textWidth + getPaddingRight());
            width = desired;
        }

        if (heightMode == MeasureSpec.EXACTLY)
        {
            height = heightSize;
        } else
        {
            float textHeight = bitmap.getHeight();
            int desired = (int) (getPaddingTop() + textHeight + getPaddingBottom());
            height = desired;
        }



        setMeasuredDimension(width, height);
    }

    @Override
    protected void onDraw(Canvas canvas)
    {
//      canvas.drawColor(context.getResources().getColor(R.color.hr_gray_bg));
        canvas.drawBitmap(bitmap, 0, 0, paint2);
        canvas.save();
        //裁剪y`
        canvas.clipRect(0, y+0, bitmap.getWidth()+0,
                bitmap.getHeight()+0);
        canvas.drawBitmap(bitmap, 0, 0, paint1);
        canvas.restore();
    }
    public void updateView(int i){
        y=i;
        postInvalidate();

    }
}
