package com.veer.custom;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.veer.custom.view.wave.WaveWater;

public class WaveWaterActivity extends AppCompatActivity {
    private WaveWater waveWater;
    private Button button_start,button_stop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wavewater);
        waveWater = (WaveWater) findViewById(R.id.view);
        button_start = (Button) findViewById(R.id.start);
        button_stop = (Button) findViewById(R.id.stop);
        button_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                waveWater.start();
            }
        });
        button_stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                waveWater.stop();
            }
        });
    }


}
