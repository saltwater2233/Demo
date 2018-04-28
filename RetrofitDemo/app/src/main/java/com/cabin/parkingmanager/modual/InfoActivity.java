package com.cabin.parkingmanager.modual;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.cabin.parkingmanager.R;
import com.cabin.parkingmanager.common.base.BaseActivity;
import com.cabin.parkingmanager.common.utils.ImgLoadUtil;
import com.cabin.parkingmanager.network.entity.InfoDataEntity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class InfoActivity extends BaseActivity implements InfoContract.View {
    @BindView(R.id.tv_info_name)
    TextView mtTvInfoName;
    @BindView(R.id.tv_info_phone)
    TextView mTvInfoPhone;
    @BindView(R.id.tv_info_dept)
    TextView mTvInfoDept;
    @BindView(R.id.tv_info_plateNumber)
    TextView mTvInfoPlateNumber;
    @BindView(R.id.ll_content)
    LinearLayout mLlContent;
    @BindView(R.id.img_empty_logo)
    ImageView mImgEmptyLogo;
    @BindView(R.id.tv_empty_hint)
    TextView mTvEmptyHint;
    @BindView(R.id.rl_empty)
    RelativeLayout mRlEmpty;


    private InfoPresenter mPresenter;
    private static final String PLATE_NUMBER = "plateNumber";

    public static void startActivity(Context context, String plateNumber) {
        Intent intent = new Intent(context, InfoActivity.class);
        intent.putExtra(PLATE_NUMBER, plateNumber);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);
        ButterKnife.bind(this);
        setActionBar();

        mPresenter = new InfoPresenter(this, this);
        mPresenter.loadDate(this);
    }

    /**
     * @return 获取到MainActivity传过来的车牌号
     */
    @Override
    public String getPlateNumber() {
        return getIntent().getStringExtra(PLATE_NUMBER);
    }

    @Override
    public Context getContext() {
        return this;
    }

    @Override
    public void setSuccessDate(InfoDataEntity infoDataEntity) {
        mtTvInfoName.setText(infoDataEntity.getRealname());
        mTvInfoPhone.setText(infoDataEntity.getPhone());
        mTvInfoDept.setText(infoDataEntity.getDept());
        mTvInfoPlateNumber.setText(infoDataEntity.getPlatenum());
        mLlContent.setVisibility(View.VISIBLE);
    }

    @Override
    public void setEmptyDate() {
        ImgLoadUtil.loadImage(this,R.drawable.ic_info_empty,mImgEmptyLogo);
        mTvEmptyHint.setText(getPlateNumber()+"是外部车辆");
        mRlEmpty.setVisibility(View.VISIBLE);
    }

    @Override
    public void setNetError() {
        ImgLoadUtil.loadImage(this,R.drawable.ic_info_error,mImgEmptyLogo);
        mTvEmptyHint.setText("加载失败，请检查网络连接状况");
        mRlEmpty.setVisibility(View.VISIBLE);
    }


    /**
     * 显示返回按钮
     */
    @Override
    public void setActionBar() {
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setHomeButtonEnabled(true);
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setTitle("车辆信息");
        }
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @OnClick(R.id.tv_info_phone)
    public void onClick() {
    }
}
