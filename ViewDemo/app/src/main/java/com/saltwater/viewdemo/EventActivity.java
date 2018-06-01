package com.saltwater.viewdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.ViewConfiguration;
import android.widget.TextView;
import android.widget.Toast;

public class EventActivity extends AppCompatActivity {
    VelocityTracker velocityTracker;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event);
        TextView textView = findViewById(R.id.tv_touchslop);
        textView.setText("TouchSloup: "+ ViewConfiguration.get(this).getScaledTouchSlop()+"pixels");
    }


    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        Log.d("Event","EventActivity:dispatchTouchEvent");
        return super.dispatchTouchEvent(ev);
    }




    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.d("Event","EventActivity:onTouchEvent");
        velocityTracker = VelocityTracker.obtain();
        velocityTracker.addMovement(event);
        velocityTracker.computeCurrentVelocity(1000);
        int xVelocity = (int) velocityTracker.getXVelocity();
        int yVelocity = (int) velocityTracker.getYVelocity();
        Log.d("Event","xVelocity: "+xVelocity+" yVelocity: "+yVelocity);

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                Log.d("Event","ACTION_DOWN ");
                Toast.makeText(this, "ACTION_DOWN", Toast.LENGTH_SHORT).show();
                break;
            case MotionEvent.ACTION_MOVE:
                Log.d("Event","ACTION_MOVE ");

                Toast.makeText(this, "ACTION_MOVE", Toast.LENGTH_SHORT).show();
                break;
            case MotionEvent.ACTION_UP:
                Log.d("Event","ACTION_UP");
                Toast.makeText(this, "ACTION_UP", Toast.LENGTH_SHORT).show();
                break;
        }

        return true;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        velocityTracker.clear();
        velocityTracker.recycle();
    }


}
