package com.veer.custom.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.veer.custom.R;
import com.veer.custom.test.TestActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private Button button1;
    private Button button2;
    private Button button3;
    private Button button4;
    private Button button5;
    private Button button13;
    private Button button14;
    private Button button31;
    private Button button32;

    private Button buttonTest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button1 = (Button) findViewById(R.id.but1);
        button2 = (Button) findViewById(R.id.but2);
        button3 = (Button) findViewById(R.id.but3);
        button4 = (Button) findViewById(R.id.but4);
        button5 = (Button) findViewById(R.id.but5);
        button13 = (Button) findViewById(R.id.but13);
        button14 = (Button) findViewById(R.id.but14);
        button31 = (Button) findViewById(R.id.but31);
        button32 = (Button) findViewById(R.id.but32);
        buttonTest = (Button) findViewById(R.id.but_test);
        button1.setOnClickListener(this);
        button2.setOnClickListener(this);
        button3.setOnClickListener(this);
        button4.setOnClickListener(this);
        button5.setOnClickListener(this);
        button13.setOnClickListener(this);
        button14.setOnClickListener(this);
        button31.setOnClickListener(this);
        button32.setOnClickListener(this);
        buttonTest.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        Intent intent;
        switch (id){
            case R.id.but1:
                intent = new Intent(this,WebviewActivity.class);
                startActivity(intent);
                break;
            case R.id.but2:
                intent = new Intent(this,FillingviewActivity.class);
                startActivity(intent);
                break;
            case R.id.but3:
                intent = new Intent(this,WaveWaterActivity.class);
                startActivity(intent);
                break;
            case R.id.but4:
                intent = new Intent(this,WaveWater2Activity.class);
                startActivity(intent);
                break;
            case R.id.but5:
                intent = new Intent(this,WaveWater3Activity.class);
                startActivity(intent);
                break;
            case R.id.but13:
                intent = new Intent(this,JellyActivity.class);
                startActivity(intent);
                break;
            case R.id.but14:
                intent = new Intent(this,SwipeRefreshActivity.class);
                startActivity(intent);
                break;
            case R.id.but31:
                intent = new Intent(this,SmoothProgressActivity.class);
                startActivity(intent);
            case R.id.but32:
                intent = new Intent(this,RoundedRectProgressActivity.class);
                startActivity(intent);
                break;
            case R.id.but_test:
                intent = new Intent(this,TestActivity.class);
                startActivity(intent);
                break;
            default:
                break;
        }

    }
}
