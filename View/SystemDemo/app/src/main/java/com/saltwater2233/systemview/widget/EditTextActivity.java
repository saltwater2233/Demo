package com.saltwater2233.systemview.widget;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.RelativeLayout;

import com.saltwater2233.systemview.R;

public class EditTextActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_text);
        EditText editText  = findViewById(R.id.edit_demo);

        hidekeyboard();
    }


    /**
     * 点击空白位置 隐藏软键盘
     */
    private void hidekeyboard(){
        RelativeLayout relativeLayout  = findViewById(R.id.rl_container);
        relativeLayout.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if(null != EditTextActivity.this.getCurrentFocus()){
                    InputMethodManager mInputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);

                    return mInputMethodManager.hideSoftInputFromWindow(EditTextActivity.this.getCurrentFocus().getWindowToken(), 0);
                }
                return false;
            }
        });
    }
}
