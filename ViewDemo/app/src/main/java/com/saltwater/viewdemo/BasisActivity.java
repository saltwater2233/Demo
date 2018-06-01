package com.saltwater.viewdemo;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.annotation.TargetApi;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class BasisActivity extends AppCompatActivity {
    Button btn_view;
    TextView tv_top, tv_left, tv_right, tv_bottom;
    TextView tv_width, tv_height;
    TextView tv_x, tv_y;
    TextView tv_translationX, tv_translationY;


    /**
     * @param savedInstanceState
     */
    @TargetApi(Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basis);

        btn_view = findViewById(R.id.btn_view);
        tv_top = findViewById(R.id.tv_top);
        tv_left = findViewById(R.id.tv_left);
        tv_right = findViewById(R.id.tv_right);
        tv_bottom = findViewById(R.id.tv_bottom);
        tv_width = findViewById(R.id.tv_width);
        tv_height = findViewById(R.id.tv_height);
        tv_x = findViewById(R.id.tv_x);
        tv_y = findViewById(R.id.tv_y);
        tv_translationX = findViewById(R.id.tv_translationX);
        tv_translationY = findViewById(R.id.tv_translationY);
        getDate();
        btn_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btn_view.animate().translationX(100)
                        .setUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                            @Override
                            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                                getDate();
                            }
                        })
                        .setDuration(5000)
                        .start();
            }
        });

    }

    private void getDate() {
        //直接在onCreate里是拿不到数据的，view的位置确定是在Measure和Layout之后才被赋予的，
        // 所以要在view完成上述两个步骤之后才能正确获取到相应的数值
        btn_view.post(new Runnable() {
            @Override
            public void run() {
                tv_top.setText("Top: " + btn_view.getTop());
                tv_left.setText("Left: " + btn_view.getLeft());
                tv_right.setText("Right: " + btn_view.getRight());
                tv_bottom.setText("Bottom: " + btn_view.getBottom());
                tv_width.setText("Width: " + btn_view.getWidth());
                tv_height.setText("Height " + btn_view.getHeight());
                //view左上角的坐标
                tv_x.setText("X: " + btn_view.getX());
                tv_y.setText("Y: " + btn_view.getY());
                //View左上角相对于父容器的偏移量
                tv_translationX.setText("translationX: " + btn_view.getTranslationX());
                tv_translationY.setText("translationY: " + btn_view.getTranslationY());
            }
        });
    }
}
