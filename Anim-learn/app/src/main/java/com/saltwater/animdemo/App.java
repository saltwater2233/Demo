package com.saltwater.animdemo;

import android.app.Application;

import com.squareup.leakcanary.LeakCanary;

/**
 * <pre>
 *     author : wenxin
 *     e-mail : wenxin2233@outlook.com
 *     time   : 2018/05/22
 *     desc   :
 *     version: 1.0
 * </pre>
 */
public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        LeakCanary.install(this);
    }
}
