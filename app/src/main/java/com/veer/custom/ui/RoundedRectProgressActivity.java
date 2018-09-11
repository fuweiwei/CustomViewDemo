package com.veer.custom.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.veer.custom.R;
import com.veer.custom.view.RoundedRectProgressBar;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/***
 * RoundedRectProgress
 * @author Veer
 * @email  276412667@qq.com
 * @date   18/9/11
 */
public class RoundedRectProgressActivity extends AppCompatActivity {
    private RoundedRectProgressBar rectProgressBar;
    private int progress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rounded_rect_progress);
        rectProgressBar = (RoundedRectProgressBar) findViewById(R.id.view);
        reset();

    }

    /**
     * 进度条从头到尾跑一次
     */
    private void reset() {

        final ScheduledExecutorService executorService = Executors.newScheduledThreadPool(1);
        executorService.scheduleWithFixedDelay(new Runnable() {
            @Override
            public void run() {
                rectProgressBar.setProgress(progress);
                progress++;
                if (progress > 100) {
                    executorService.shutdown();
                }

            }
        },0,50, TimeUnit.MILLISECONDS);




    }
}
