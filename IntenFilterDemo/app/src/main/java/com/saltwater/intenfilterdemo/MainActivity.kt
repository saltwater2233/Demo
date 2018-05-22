package com.saltwater.intenfilterdemo

import android.content.Intent
import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var intent = Intent("test_action")
        intent.setDataAndType(Uri.parse("http://cctv"),"video/mpeg")
        intent.resolveActivity()
        startActivity(intent)

    }
}
