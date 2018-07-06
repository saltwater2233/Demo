package com.saltwater2233.performanceoptimization;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewStub;

public class MainActivity extends AppCompatActivity implements TestManager.OnDataArrivedListener {
    private static Context sContext;
    private static View sView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sContext = this;//会造成内存泄漏
        sView = new View(this);//也会造成内存泄漏

        //由于缺少解注册的操作所以会引起内存泄露
        TestManager.getInstance().registerListener(this);

        ///使用ViewStub优化布局
        findViewById(R.id.btn_show_net_error).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startAnimator(view);

                ViewStub viewStub =findViewById(R.id.stub_import);
                if (viewStub!=null){//注意ViewStub引用后就被替换了
                    viewStub.setVisibility(View.VISIBLE);
                }
            }
        });

        //制造一个ANR，使用adb pull/data/anr/traces.txt. 查看原因
        findViewById(R.id.btn_make_anr).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SystemClock.sleep(30*1000);
            }
        });
    }

    @Override
    public void onDataArrived(Object data) {

    }

    //无限循环的animator导致内存泄漏
    private void startAnimator(View view){
        ObjectAnimator animator = ObjectAnimator.ofFloat(view,"rotation",0,360).setDuration(300);
        animator.setRepeatCount(ValueAnimator.INFINITE);
        animator.start();
    }
}
