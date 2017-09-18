package com.aqinga.zhangqingling0918;

import android.app.Application;
import android.content.Context;
/**
 * Created by
 * 张庆龄
 * 1506A
 * Administrator
 * 2017/9/1810:38
 */

public class App extends Application {


    private static Context mContext;

    @Override
    public void onCreate() {
        super.onCreate();
        this.mContext = this;
        CrashHandler.getInstance().init(this);//初始化全局异常管理
    }

    public static Context getContext(){
        return mContext;
    }
}
