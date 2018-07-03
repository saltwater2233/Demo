package com.saltwater.zhaoshanbangka_kotlin

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn_start.setOnClickListener{
            val intent = Intent()
            intent.putExtra("url",edit_url.text)
            intent.putExtra("phone",edit_phone.text)
            intent.putExtra("plate",edit_plate.text)
            intent.setClass(this,WebViewActivity::class.java)
            startActivity(intent)
        }
    }
}
