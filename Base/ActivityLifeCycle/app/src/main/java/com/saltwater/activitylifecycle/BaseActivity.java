package com.saltwater.activitylifecycle;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

/**
 * <pre>
 *     author : wenxin
 *     e-mail : wenxin2233@outlook.com
 *     time   : 2018/04/02
 *     desc   :
 *     version: 1.0
 * </pre>
 */

public class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        Log.d("logUtil",this.getLocalClassName()+": onCreate");
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onStart() {
        Log.d("logUtil",this.getLocalClassName()+": onStart");
        super.onStart();
    }

    @Override
    protected void onResume() {
        Log.d("logUtil",this.getLocalClassName()+": onResume");

        super.onResume();
    }


    @Override
    protected void onPause() {
        Log.d("logUtil",this.getLocalClassName()+": onPause");

        super.onPause();
    }

    @Override
    protected void onStop() {
        Log.d("logUtil",this.getLocalClassName()+": onStop");
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        Log.d("logUtil",this.getLocalClassName()+": onDestroy");
        super.onDestroy();
    }

    @Override
    protected void onRestart() {
        Log.d("logUtil",this.getLocalClassName()+": onRestart");
        super.onRestart();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        Log.d("logUtil",this.getLocalClassName()+": onSaveInstanceState");
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        Log.d("logUtil",this.getLocalClassName()+": onRestoreInstanceState");
        super.onRestoreInstanceState(savedInstanceState);
    }
}
