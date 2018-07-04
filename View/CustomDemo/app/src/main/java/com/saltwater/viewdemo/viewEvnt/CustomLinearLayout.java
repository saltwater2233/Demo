package com.saltwater.viewdemo.viewEvnt;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.LinearLayout;

/**
 * <pre>
 *     author : wenxin
 *     e-mail : wenxin2233@outlook.com
 *     time   : 2018/05/25
 *     desc   : 测试事件分发
 *     version: 1.0
 * </pre>
 */
public class CustomLinearLayout extends LinearLayout {
    public CustomLinearLayout(Context context) {
        super(context);
    }

    public CustomLinearLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomLinearLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        Log.d("Event","CustomLinearLayout:dispatchTouchEvent");
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        Log.d("Event","CustomLinearLayout:onInterceptTouchEvent");

        return super.onInterceptTouchEvent(ev);//默认是false
        //return true;//如果拦截，ViewGroup里面的view收不到时间序列
    }

    /**只用onInterceptTouchEvent就进行了拦截，才会执行这个方法
     * @param event
     * @return
     */
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.d("Event","CustomLinearLayout:onTouchEvent");
        return super.onTouchEvent(event);//默认是false
        //return true;//如果返回true，表示被事件被消费了
    }
}
