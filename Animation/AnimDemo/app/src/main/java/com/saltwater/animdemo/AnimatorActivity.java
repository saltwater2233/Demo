package com.saltwater.animdemo;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ArgbEvaluator;
import android.animation.IntEvaluator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class AnimatorActivity extends AppCompatActivity {
    private ValueAnimator mValueAnimator;
    private AnimatorSet mAnimatorSet;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animator);
        Button btn_xml = findViewById(R.id.btn_xml);
        Button btn_objectAnimator = findViewById(R.id.btn_objectAnimator);
        Button btn_valueAnimator = findViewById(R.id.btn_valueAnimator);
        Button btn_animatorSet = findViewById(R.id.btn_animatorSet);
        Button btn_customValue = findViewById(R.id.btn_customValue);


        loadAnimator(btn_xml);
        objectAnimator(btn_objectAnimator);
        valueAnimator(btn_valueAnimator);
        animatorSet(btn_animatorSet);
        wrapperAnimate(btn_customValue);
    }

    /*从xml加载,注意放在animator目录下面*/
    private void loadAnimator(View view){
        mAnimatorSet = (AnimatorSet) AnimatorInflater.loadAnimator(this , R.animator.test_animator);
        mAnimatorSet.setTarget(view);
        mAnimatorSet.start();
    }

    /*ObjectAnimator方式*/
    private void objectAnimator(View view){
        ObjectAnimator.ofFloat(view,"translationX",600).setDuration(2000).start();
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(AnimatorActivity.this,"click!",Toast.LENGTH_SHORT).show();
            }
        });
    }


    /*ValueAnimation方式*/
    private void valueAnimator(View view){
        mValueAnimator = ObjectAnimator.ofInt(view,"backgroundColor",/*red*/0xffff8080,/*blue*/0xff8080ff);
        mValueAnimator.setDuration(2000);
        mValueAnimator.setEvaluator(new ArgbEvaluator());//设置颜色估值器
        mValueAnimator.setRepeatCount(ValueAnimator.INFINITE);//无限循环的要注意手动取消，不然会内存泄漏
        mValueAnimator.setRepeatMode(ValueAnimator.REVERSE);
        mValueAnimator.start();

    }

    /*AnimatorSet*/
    private void animatorSet(View view){
        AnimatorSet animationSet = new AnimatorSet();
        animationSet.playTogether(
                ObjectAnimator.ofFloat(view,"translationX",200),
                ObjectAnimator.ofFloat(view,"rotation",0,360,1),
                ObjectAnimator.ofFloat(view,"scaleX",1,1.5f),
                ObjectAnimator.ofFloat(view,"scaleY",1,1.5f),
                ObjectAnimator.ofFloat(view,"alpha",1,0,1)
        );
        animationSet.setDuration(3*1000).start();

        /*属性动画监听*/
        animationSet.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationStart(Animator animation) {
                super.onAnimationStart(animation);
                Toast.makeText(AnimatorActivity.this,"Animator启动！",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                Toast.makeText(AnimatorActivity.this,"Animator结束!",Toast.LENGTH_SHORT).show();

            }
        });
    }


    /*通过ViewWrapper改变属性*/
    private void wrapperAnimate(View view){
        ViewWrapper viewWrapper = new ViewWrapper(view);
        ObjectAnimator.ofInt(viewWrapper,"width",1000).setDuration(2000).start();

    }

    /*通过监听ValueAnimator进度改变属性*/
    private void performAnimate(final View view, final int start, final int end){
        ValueAnimator customValueAnimator = ValueAnimator.ofInt(1,100);
        customValueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            IntEvaluator intEvaluator = new IntEvaluator();
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                //获取当前动画的进度值，范围在1-100之间
                int currentValue = (int) valueAnimator.getAnimatedValue();
                Log.d("TAG", "current value: " + currentValue);

                //获得当前进度占整个动画过程的比例，浮点型，0-1之间
                float fraction = valueAnimator.getAnimatedFraction();
                view.getLayoutParams().width = intEvaluator.evaluate(fraction,start,end);
                view.requestLayout();

            }
        });
        customValueAnimator.setDuration(5000).start();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mAnimatorSet.cancel();
        mValueAnimator.cancel();
    }
}
