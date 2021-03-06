package com.saltwater.animdemo;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btn_animation = findViewById(R.id.btn_animation);
        btn_animation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,AnimationActivity.class));
            }
        }); 

        Button btn_animator = findViewById(R.id.btn_animator);
        btn_animator.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,AnimatorActivity.class));
            }
        });


        ImageView imageView = findViewById(R.id.img);
        ObjectAnimator rightFold = ObjectAnimator.ofFloat(imageView,"translationZ",0,90);
        rightFold.setDuration(2000);
        rightFold.start();
    }
}
