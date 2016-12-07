package com.veer.custom.ui;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.veer.custom.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SwipeRefreshActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener {
    private static final int REFRESH_UI = 100;
    private SwipeRefreshLayout swipeRefreshLayout;
    private ListView listView;
    private List<String> datas = new ArrayList<>();
    private ArrayAdapter<String> arrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_swiperefresh);
        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipe_ly);
        listView = (ListView) findViewById(R.id.listView);
        datas.addAll(Arrays.asList("赵云","张飞","关羽","马超","刘备","魏延"));
        swipeRefreshLayout.setOnRefreshListener(this);
        arrayAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,datas);
        listView.setAdapter(arrayAdapter);
    }


    @Override
    public void onRefresh() {
        mHandler.sendEmptyMessageDelayed(REFRESH_UI, 2000);
    }
    private Handler mHandler = new Handler()
    {
        public void handleMessage(android.os.Message msg)
        {
            switch (msg.what)
            {
                case REFRESH_UI:
                    datas.addAll(Arrays.asList("曹植", "曹操", "郭嘉"));
                    //刷新adapter
                    arrayAdapter.notifyDataSetChanged();
                    //关闭刷新动画
                    swipeRefreshLayout.setRefreshing(false);
                    break;

            }
        };
    };
}
