package com.saltwater.viewdemo.scroll;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.saltwater.viewdemo.R;

public class ScrollActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scroll);
        final TextView textView = findViewById(R.id.tv_content);
        Button btn_scrollTo = findViewById(R.id.btn_scrollTo);
        Button btn_scrollBy = findViewById(R.id.btn_scrollBy);
        btn_scrollTo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                textView.scrollTo(100,100);
            }
        });
        btn_scrollBy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                textView.scrollBy(-100,-100);
            }
        });
        findViewById(R.id.btn_anim).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                textView.animate().translationX(100).translationY(100).setDuration(1000).start();
            }
        });

        findViewById(R.id.btn_layoutParams).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ViewGroup.MarginLayoutParams params = (ViewGroup.MarginLayoutParams) textView.getLayoutParams();
                params.height+=100;
                params.leftMargin+=100;
                textView.setLayoutParams(params);
            }
        });

    }
}
