package com.saltwater.zhaoshanbangka_kotlin

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebViewClient
import kotlinx.android.synthetic.main.activity_web_view.*

class WebViewActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_web_view)
        val url = this.intent.getStringExtra("url")
        val phone = this.intent.extras.get("phone")
        val plate = this.intent.extras.get("plate")
        val postDate = "mobile=$phone&plate_num=$plate"

        wv.settings.javaScriptEnabled = true
        wv.settings.setSaveFormData(false)
        wv.settings.setSavePassword(false)
        wv.settings.setSupportZoom(false)
        wv.settings.javaScriptCanOpenWindowsAutomatically = true//设置允许js弹出alert对话框

        wv.postUrl(url, postDate.toByteArray())
        wv.webViewClient = WebViewClient()


    }
}
