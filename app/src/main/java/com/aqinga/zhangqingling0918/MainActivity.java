package com.aqinga.zhangqingling0918;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;

import com.google.gson.Gson;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.io.IOException;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity implements XRecyclerView.LoadingListener {

    private XRecyclerView recyclerview;
    private MyAdapter adapter;
    private CrashHandler crashHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Thread.setDefaultUncaughtExceptionHandler(crashHandler);
        recyclerview = (XRecyclerView) findViewById(R.id.recyclerview);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        recyclerview.setLayoutManager(manager);
        recyclerview.setLoadingListener(this);
        init();
    }
    public void init(){
        OkHttpUtils.sendOkHttpRequest("https://news-at.zhihu.com/api/4/news/latest", new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String json = response.body().string();
                Gson gson = new Gson();
                Bean fromJson = gson.fromJson(json, Bean.class);
                final List<Bean.StoriesBean> list = fromJson.getStories();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        adapter = new MyAdapter(list,MainActivity.this);
                        recyclerview.setAdapter(adapter);
                    }
                });
            }
        });
    }

    @Override
    public void onRefresh() {
        init();
        adapter.notifyDataSetChanged();
        recyclerview.refreshComplete();
    }

    @Override
    public void onLoadMore() {
        init();
        adapter.notifyDataSetChanged();
        recyclerview.loadMoreComplete();
    }
}
