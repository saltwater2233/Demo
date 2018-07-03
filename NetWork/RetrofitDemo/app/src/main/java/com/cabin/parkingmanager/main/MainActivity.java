package com.cabin.parkingmanager.main;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.inputmethodservice.KeyboardView;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.cabin.parkingmanager.R;
import com.cabin.parkingmanager.common.utils.CarKeyboardUtil;
import com.cabin.parkingmanager.common.utils.MediaUtil;
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends RxAppCompatActivity implements MainContract.View {
    @BindView(R.id.edit_query)
    EditText mEditQuery;
    @BindView(R.id.keyboard_view)
    KeyboardView mKeyboardView;
    @BindView(R.id.image_photo)
    ImageView mImagePhoto;
    @BindView(R.id.edit_describe)
    EditText mEditDescribe;
    @BindView(R.id.btn_upload)
    Button mBtnUpload;
    @BindView(R.id.img_search)
    ImageView mImgSearch;
    @BindView(R.id.ll_background)
    LinearLayout mLlBackground;
    @BindView(R.id.img_delete)
    ImageView mImgDelete;

    private MainPresenter mPresenter;
    private CarKeyboardUtil keyboardUtil;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        mPresenter = new MainPresenter(this, this);
        mPresenter.initView();
    }


    @OnClick({R.id.img_search, R.id.image_photo, R.id.ll_background, R.id.btn_upload, R.id.img_delete})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.img_search:
                mPresenter.startInfoActivity();
                break;
            case R.id.image_photo:
                keyboardUtil.hideKeyboard();
                mPresenter.takePhoto();
                break;
            case R.id.ll_background:
                keyboardUtil.hideKeyboard();
                keyboardUtil.hideSystemKeyBroad();
                break;
            case R.id.btn_upload:
                mPresenter.upLoad();
                break;
            case R.id.img_delete:
                mPresenter.deletePhoto(view);
                break;
        }
    }


    /**
     * 初始化车牌键盘
     */
    @Override
    public void initKeyBoard() {
        keyboardUtil = new CarKeyboardUtil(this, mEditQuery, mKeyboardView);
        mEditQuery.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                keyboardUtil.hideSystemKeyBroad();
                keyboardUtil.hideSoftInputMethod();
                if (!keyboardUtil.isShow()) {
                    keyboardUtil.showKeyboard();
                }

                return true;
            }
        });
        mEditQuery.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (mEditQuery.getText().toString().length()==8){
                    keyboardUtil.hideKeyboard();
                }
            }
        });


    }

    @Override
    public void clear() {
        mImagePhoto.setImageResource(R.drawable.ic_main_add);
        mImagePhoto.setEnabled(true);
        mImgDelete.setVisibility(View.GONE);
        mEditQuery.setText("");
        mEditDescribe.setText("");

    }


    @Override
    public String getPlateNumber() {
        return mEditQuery.getText().toString();
    }

    @Override
    public String getDescribe() {
        return mEditDescribe.getText().toString();
    }


    @Override
    public void setPhotoVisibility(Boolean visibility) {
        if (visibility) {
            Bitmap bitmap = MediaUtil.getBitmap(this);
            mImagePhoto.setImageBitmap(bitmap);
            mImagePhoto.setEnabled(false);
            mImgDelete.setVisibility(View.VISIBLE);
        } else {
            mImagePhoto.setImageResource(R.drawable.ic_main_add);
            mImagePhoto.setEnabled(true);
            mImgDelete.setVisibility(View.GONE);
        }
    }

    /**
     * 使用home代替back
     */
    @Override
    public void onBackPressed() {
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.addCategory(Intent.CATEGORY_HOME);
        startActivity(intent);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode != RESULT_OK) {
            return;
        }
        mPresenter.handleRequestCode(requestCode);
    }


    @Override
    public Context getContext() {
        return this;
    }
}
