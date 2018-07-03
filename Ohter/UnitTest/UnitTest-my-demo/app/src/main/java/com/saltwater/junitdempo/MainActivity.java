package com.saltwater.junitdempo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void testFunction(){
        EditText editText = findViewById(R.id.et_test);
        if (editText.length()==11){
            editText.setText("666");
        }
    }


}
