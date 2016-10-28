package com.veer.custom.view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.webkit.WebView;

/**
 * 描述
 * Created by fww
 * date 16/10/28.
 */

public class MyWebView extends WebView {
    private static final String TAG = "MyWebView";
    public MyWebView(Context context) {
        super(context);
    }

    public MyWebView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyWebView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                Log.v(TAG,"dispatchTouchEvent------ACTION_DOWN:");
                break;
            case MotionEvent.ACTION_UP:
                Log.v(TAG,"dispatchTouchEvent------ACTION_UP:");
                break;
            case MotionEvent.ACTION_MOVE:
                Log.v(TAG,"dispatchTouchEvent------ACTION_MOVE:");

                break;

        }
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
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
        return super.onTouchEvent(event);
    }
}
