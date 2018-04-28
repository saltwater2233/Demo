package com.cabin.parkingmanager.modual;

import android.content.Context;

import com.cabin.parkingmanager.common.base.BasePresenter;
import com.cabin.parkingmanager.common.base.BaseView;
import com.cabin.parkingmanager.network.entity.InfoDataEntity;

/**
 * <pre>
 *     author : wenxin
 *     e-mail : wenxin2233@outlook.com
 *     time   : 2018/04/25
 *     desc   :
 *     version: 1.0
 * </pre>
 */
public class InfoContract {
    interface View extends BaseView {
        Context getContext();
        void setActionBar();//设置显示返回按钮
        String getPlateNumber();//获取车牌号码
        void setSuccessDate(InfoDataEntity infoDataEntity);//设置获取白名单车辆数据
        void setEmptyDate();//设置外来车辆数据
        void setNetError();//设置网络错误数据


    }

    interface Presenter extends BasePresenter {
        void loadDate(Context context);

    }


}
