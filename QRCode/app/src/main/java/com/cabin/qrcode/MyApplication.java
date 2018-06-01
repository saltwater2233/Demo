package com.cabin.qrcode;

import android.app.Application;
import android.os.Handler;

import com.facebook.stetho.Stetho;
import com.squareup.leakcanary.LeakCanary;



import cn.jpush.android.api.JPushInterface;


public class MyApplication extends Application {
    private static MyApplication mContext;
    private Handler handler ;
    @Override
    public void onCreate() {
        super.onCreate();
        mContext = this;
        init();
    }

    public Handler getHandler() {
        return handler;
    }

    public void setHandler(Handler handler) {
        this.handler = handler;
    }


    private void init() {
        //初始化Leak内存泄露检测工具
        LeakCanary.install(this);
        //初始化Stetho调试工具
        Stetho.initialize(Stetho.newInitializerBuilder(this)
                .enableDumpapp(Stetho.defaultDumperPluginsProvider(this))
                .enableWebKitInspector(Stetho.defaultInspectorModulesProvider(this))
                .build());
        JPushInterface.setDebugMode(true); // 设置开启日志,发布时请关闭日志
        JPushInterface.init(this); // 初始化 JPush
        JPushInterface.setAlias(this,0,"MingZhouLi");

    }

    public static MyApplication getInstance() {
        return mContext;
    }

}