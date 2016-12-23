package com.veer.custom.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.veer.custom.R;
import com.veer.custom.view.MyProgressView;

public class SmoothProgressActivity extends AppCompatActivity {
    private MyProgressView myProgressView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_smooth_progress);
        myProgressView = (MyProgressView) findViewById(R.id.view);
        myProgressView.setIsStart(true);
    }


}
