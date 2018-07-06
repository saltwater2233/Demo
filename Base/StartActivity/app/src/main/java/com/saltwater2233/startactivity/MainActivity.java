package com.saltwater2233.startactivity;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private Context mContext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mContext = this;

        //显式启动
        findViewById(R.id.btn_start).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SecondActivity.actionStart(MainActivity.this);
            }
        });

        //隐式启动
        findViewById(R.id.btn_start2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent("com.saltwater2233.startactivity.test");//只能指定一个action
                intent.addCategory("com.saltwater2233.startactivity.test");//intent可以添加多个Category
                //intent.addCategory("不存在的Category");//如果添加了一个没在IntentFilter里标注的Category，会报错

                PackageManager packageManager = mContext.getPackageManager();
                ComponentName componentName = intent.resolveActivity(packageManager);
                if (componentName==null){
                    //如果没有，那么就去应用市场去找找看
                    Uri marketUri = Uri.parse("market://search?q=需要打开的应用名");//打开应用市场，搜索应用
                    Intent marketIntent = new Intent(Intent.ACTION_VIEW).setData(marketUri);
                    if (marketIntent.resolveActivity(packageManager) != null) {
                        mContext.startActivity(marketIntent);
                    } else {
                        Log.d("Error", "Market client not available.");
                    }
                }else {
                    startActivity(intent);
                }



            }
        });

        findViewById(R.id.btn_start3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW);

                intent.setData(Uri.parse("http://www.baidu.com"));//打开网址,http://得加，不然报错
//                intent.setData(Uri.parse("tel:1232333"));//拨号程序
//                intent.setData(Uri.parse("geo:39.899533,116.036476"));//打开地图定位
//                intent.setDataAndType(Uri.parse("file:///sdcard/media.mp4"), "video/*");//播放视频

//                intent.putExtra("sms_body", "信息内容...");//发送短信
//                intent.setType("vnd.android-dir/mms-sms");


                startActivity(intent);
            }
        });
    }
}
