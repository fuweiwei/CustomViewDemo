package com.veer.custom.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.veer.custom.R;
import com.veer.custom.view.wave.DynamicWave;
import com.veer.custom.view.wave.WaveWater;

public class WaveWater2Activity extends AppCompatActivity {
    private DynamicWave waveWater;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wavewater2);
    }


}
