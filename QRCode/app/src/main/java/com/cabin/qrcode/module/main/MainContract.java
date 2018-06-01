package com.cabin.qrcode.module.main;

import android.widget.ImageView;

import com.cabin.qrcode.common.base.BasePresenter;
import com.cabin.qrcode.common.base.BaseView;

/**
 * <pre>
 *     author : wenxin
 *     e-mail : wenxin2233@outlook.com
 *     time   : 2018/05/29
 *     desc   :
 *     version: 1.0
 * </pre>
 */
public class MainContract {
    interface View extends BaseView{
        void setCountText(String content);
        void showNetError();
        void showGetError(String content);
        void showQRCode(String dataString);
    }

    interface Presenter extends BasePresenter{
        void refreshCode(ImageView imageView);
        void getQRCode(ImageView imageView);

        void finish();

    }
}
