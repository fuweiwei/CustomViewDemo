package com.veer.custom.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

import com.veer.custom.R;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private Button button1;
    private Button button2;
    private Button button3;
    private Button button4;
    private Button button5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button1 = (Button) findViewById(R.id.but1);
        button2 = (Button) findViewById(R.id.but2);
        button3 = (Button) findViewById(R.id.but3);
        button4 = (Button) findViewById(R.id.but4);
        button5 = (Button) findViewById(R.id.but5);
        button1.setOnClickListener(this);
        button2.setOnClickListener(this);
        button3.setOnClickListener(this);
        button4.setOnClickListener(this);
        button5.setOnClickListener(this);
//        setTitle("测试");
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id){
            case R.id.but1:
                Intent intent = new Intent(this,WebviewActivity.class);
                startActivity(intent);
                break;
            case R.id.but2:
                Intent intent2 = new Intent(this,FillingviewActivity.class);
                startActivity(intent2);
                break;
            case R.id.but3:
                Intent intent3 = new Intent(this,WaveWaterActivity.class);
                startActivity(intent3);
                break;
            case R.id.but4:
                Intent intent4 = new Intent(this,WaveWater2Activity.class);
                startActivity(intent4);
                break;
            case R.id.but5:
                Intent intent5 = new Intent(this,WaveWater3Activity.class);
                startActivity(intent5);
                break;
            default:
                break;
        }

    }
}
