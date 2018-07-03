package com.saltwater.baseprojectmvp.module.location;

import com.saltwater.baseprojectmvp.common.base.BasePresenter;
import com.saltwater.baseprojectmvp.common.base.BaseView;

/**
 * <pre>
 *     author : wenxin
 *     e-mail : wenxin2233@outlook.com
 *     time   : 2018/03/22
 *     desc   :
 *     version: 1.0
 * </pre>
 */

public interface UpdateContract {

    interface presenter extends BasePresenter{
        void loadUpdate();
    }

    interface View extends BaseView{
        void showMsg(String msg);
    }

}
