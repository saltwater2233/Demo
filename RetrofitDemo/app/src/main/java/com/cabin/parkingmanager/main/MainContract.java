package com.cabin.parkingmanager.main;

import android.content.Context;
import android.view.View;

import com.cabin.parkingmanager.common.base.BasePresenter;
import com.cabin.parkingmanager.common.base.BaseView;

/**
 * <pre>
 *     author : wenxin
 *     e-mail : wenxin2233@outlook.com
 *     time   : 2018/04/24
 *     desc   :
 *     version: 1.0
 * </pre>
 */
public class MainContract {
    interface Presenter extends BasePresenter {
        void initView();
        void startInfoActivity();
        void takePhoto();
        void handleRequestCode(int resultCode);
        void deletePhoto(android.view.View view);
        void upLoad();
    }

    interface View extends BaseView {
        void initKeyBoard();
        void clear();
        String getPlateNumber();
        String getDescribe();
        void setPhotoVisibility(Boolean visibility);
    }
}
