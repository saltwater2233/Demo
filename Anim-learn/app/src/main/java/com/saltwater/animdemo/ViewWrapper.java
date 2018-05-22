package com.saltwater.animdemo;

import android.view.View;

/**
 * <pre>
 *     author : wenxin
 *     e-mail : wenxin2233@outlook.com
 *     time   : 2018/05/21
 *     desc   :
 *     version: 1.0
 * </pre>
 */
public class ViewWrapper {
    private View mView;
    public ViewWrapper(View view){
        this.mView = view;
    }

    public int getWidth(){
        return mView.getLayoutParams().width;
    }
    public void setWidth(int width){
        mView.getLayoutParams().width= width;
        mView.requestLayout();
    }
}
