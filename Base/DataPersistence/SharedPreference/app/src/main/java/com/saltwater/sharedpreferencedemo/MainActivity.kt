package com.saltwater.sharedpreferencedemo

import android.content.Context
import android.content.SharedPreferences
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        saved()
        loaded()
    }
    fun saved(){
        val sharedPreferences = getSharedPreferences("user", Context.MODE_PRIVATE).edit()
        sharedPreferences.putString("name","test")
        sharedPreferences.commit()
    }

    fun loaded(){
        val sharedPreferences = getSharedPreferences("user", Context.MODE_PRIVATE)
        Log.d("sharedPreference",sharedPreferences.getString("name",""))
    }
}
