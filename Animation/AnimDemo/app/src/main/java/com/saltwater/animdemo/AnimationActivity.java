package com.saltwater.animdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.view.animation.ScaleAnimation;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * View动画(视图动画)
 */
public class AnimationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animation);

        Button btn_translate = findViewById(R.id.btn_translate);
        Button btn_rotation = findViewById(R.id.btn_rotation);
        Button btn_alpha = findViewById(R.id.btn_alpha);
        Button btn_scale = findViewById(R.id.btn_scale);

        loadAnimation(btn_translate,btn_rotation,btn_alpha);
        createAnimation(btn_scale);


        /*给recyclerView的item添加动画*/
        RecyclerView recyclerView = findViewById(R.id.rv_fruit);
        List nameList = new ArrayList();
        nameList.add("layoutAnimation");
        nameList.add("layoutAnimation");
        nameList.add("layoutAnimation");
        nameList.add("layoutAnimation");

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new FruitAdapter(nameList));
        //layoutAnimation(recyclerView);

    }

    /*xml加载*/
    private void loadAnimation(View translateView,View rotationView,View alphaView){
        Animation translate = AnimationUtils.loadAnimation(this,R.anim.translate);
        translateView.setAnimation(translate);

        /*未改变View的真实布局属性值*/
        translateView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(AnimationActivity.this,"click!",Toast.LENGTH_SHORT).show();
            }
        });

        Animation rotation = AnimationUtils.loadAnimation(this,R.anim.rotation);
        rotationView.setAnimation(rotation);

        Animation alpha = AnimationUtils.loadAnimation(this,R.anim.alpha);
        alphaView.setAnimation(alpha);
    }

    /*代码创建*/
    private void createAnimation(View view){
        Animation scale = new ScaleAnimation(0,0,300f,300f);
        scale.setDuration(1000);
        view.setAnimation(scale);
    }

    /*一般通过xml里的layoutAnimation属性指定，也可以通过代码指定*/
    private void layoutAnimation(RecyclerView recyclerView){
        Animation layoutAnimation = AnimationUtils.loadAnimation(this,R.anim.alpha);
        LayoutAnimationController layoutAnimationController = new LayoutAnimationController(layoutAnimation);
        layoutAnimationController.setDelay(0.5f);
        layoutAnimationController.setOrder(LayoutAnimationController.ORDER_NORMAL);
        recyclerView.setLayoutAnimation(layoutAnimationController);

    }
}
