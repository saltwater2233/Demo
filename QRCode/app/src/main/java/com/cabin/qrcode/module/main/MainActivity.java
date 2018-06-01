package com.cabin.qrcode.module.main;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.KeyEvent;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.cabin.qrcode.MyApplication;
import com.cabin.qrcode.R;
import com.cabin.qrcode.common.base.BaseActivity;
import com.cabin.qrcode.common.utils.QRCodeUtil;
import com.cabin.qrcode.common.utils.ToastUtil;

import butterknife.BindView;
import butterknife.OnClick;

/**
 *
 */
public class MainActivity extends BaseActivity implements MainContract.View {
    @BindView(R.id.img_code)
    ImageView imgCode;
    @BindView(R.id.tv_time_second)
    TextView tvTimeSecond;
    @BindView(R.id.ll_refresh)
    LinearLayout llRefresh;

    private static final String TAG = "MainActivity";
    private static final int Refresh = 0;
    private long lastTime = 0;
    private MainPresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mPresenter = new MainPresenter(this,this);
        mPresenter.refreshCode(imgCode);

        MyApplication.getInstance().setHandler(new Handler(){
            @Override
            public void handleMessage(Message msg) {
                switch (msg.what){
                    case Refresh:
                        mPresenter.refreshCode(imgCode);
                        break;
                    default:
                        break;
                }
            }
        });
    }



    @OnClick(R.id.ll_refresh)
    public void onClick() {
        mPresenter.refreshCode(imgCode);
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK){
            if (System.currentTimeMillis()-lastTime>2000){
                Toast.makeText(MainActivity.this,"再按一次退出程序",Toast.LENGTH_SHORT).show();
                lastTime=System.currentTimeMillis();
            }else{
                finish();
                System.exit(0);
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.finish();
    }


    @Override
    public void setCountText(String time) {
        tvTimeSecond.setText(time);
    }

    @Override
    public void showNetError() {
        ToastUtil.showShort(this,"网络请求错误");
    }

    @Override
    public void showGetError(String string) {
        ToastUtil.showShort(this,string);

    }

    @Override
    public void showQRCode(String dataString) {
        Bitmap bitmap = QRCodeUtil.createQRCodeWithLogo(dataString, 450,
                BitmapFactory.decodeResource(getResources(), R.drawable.logo));
        imgCode.setImageBitmap(bitmap);
    }
}
