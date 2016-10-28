package com.veer.custom.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ScrollView;

import com.veer.custom.R;

/**
 * 下拉填充可滚动动画
 * Created by fww
 * date 16/10/25.
 */

public class PullDownFillingView extends ScrollView {
    private static final String TAG = "PullDownFillingView";
    // 实际的padding的距离与界面上偏移距离的比例
    private final static int RATIO = 1;
    private float startY;
    private float endY;
    private float moveY;
    private LinearLayout innerLayout;
    private LinearLayout headView;
    private int headContentWidth;
    private int headContentHeight;
    private LoadingView loadingView;
    public PullDownFillingView(Context context) {
        super(context);
        init(context);
    }

    public PullDownFillingView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public PullDownFillingView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }
    private void init(Context context) {
        LayoutInflater inflater = LayoutInflater.from(context);
        innerLayout = new LinearLayout(context);
        innerLayout.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT));
        innerLayout.setOrientation(LinearLayout.VERTICAL);
        innerLayout.setFocusable(false);
        innerLayout.setClickable(false);
        innerLayout.setDescendantFocusability(ViewGroup.FOCUS_BLOCK_DESCENDANTS);
        headView = (LinearLayout) inflater.inflate(R.layout.scroll_filling_head,
                null);
        loadingView = (LoadingView) headView.findViewById(R.id.loadingview);
        measureView(headView);
        headContentHeight = headView.getMeasuredHeight();
        headContentWidth = headView.getMeasuredWidth();
        headView.setPadding(0, - headContentHeight, 0, 0);
        headView.invalidate();
        innerLayout.addView(headView);
        addView(innerLayout);
    }
    private void measureView(View child) {
        ViewGroup.LayoutParams p = child.getLayoutParams();
        if (p == null) {
            p = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.FILL_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT);
        }
        int childWidthSpec = ViewGroup.getChildMeasureSpec(0, 0 + 0, p.width);
        int lpHeight = p.height;
        int childHeightSpec;
        if (lpHeight > 0) {
            childHeightSpec = MeasureSpec.makeMeasureSpec(lpHeight,
                    MeasureSpec.EXACTLY);
        } else {
            childHeightSpec = MeasureSpec.makeMeasureSpec(0,
                    MeasureSpec.UNSPECIFIED);
        }
        child.measure(childWidthSpec, childHeightSpec);
    }
    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                startY = event.getY();
                Log.v(TAG,"------startY:"+startY);
                break;
            case MotionEvent.ACTION_UP:
                endY = event.getY();
                Log.v(TAG,"------endY:"+endY);
                    headView.setPadding(0, -1 * headContentHeight, 0, 0);
                break;
            case MotionEvent.ACTION_MOVE:
                float tempY = event.getY();
                moveY =tempY-startY;
                Log.v(TAG,"------moveY:"+moveY);
                    if(moveY>0){
                        headView.setPadding(0, (int) (moveY / RATIO - headContentHeight), 0, 0);
                    }
                loadingView.updateView((int) ( headContentHeight-moveY+60));
                break;

        }

        return super.dispatchTouchEvent(event);
    }
    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                Log.v(TAG,"onTouchEvent------ACTION_DOWN:");
                break;
            case MotionEvent.ACTION_UP:
                Log.v(TAG,"onTouchEvent------ACTION_UP:");
                break;
            case MotionEvent.ACTION_MOVE:
                Log.v(TAG,"onTouchEvent------ACTION_MOVE:");

                break;

        }
        return super.onTouchEvent(ev);
    }
    public void addChild(View child) {
        innerLayout.addView(child);
    }

    public void addChild(View child, int position) {
        innerLayout.addView(child, position);
    }
}
