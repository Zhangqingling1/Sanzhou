package com.aqinga.zhangqingling0918;

import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

/**
 * Created by
 * 张庆龄
 * 1506A
 * Administrator
 * 2017/9/1810:05
 */

public class MyActivity extends AppCompatActivity {

    private ImageView image;
    private String tu;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.myactivity);
        //通过传过来的值再给image赋值
        Intent intent = getIntent();
        tu = intent.getStringExtra("image");
        image = (ImageView) findViewById(R.id.image_view2);
        Glide.with(this).load(tu).into(image);

    }
    //点击按钮实现动画
    public void rotateyAnimRun(View view)
    {
        ObjectAnimator
                .ofFloat(image, "rotationX", 0.0F, 360.0F)
                .setDuration(500)
                .start();
    }
}
