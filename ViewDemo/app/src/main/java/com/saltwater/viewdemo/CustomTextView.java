package com.saltwater.viewdemo;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

/**
 * <pre>
 *     author : wenxin
 *     e-mail : wenxin2233@outlook.com
 *     time   : 2018/05/24
 *     desc   :
 *     version: 1.0
 * </pre>
 */
public class CustomTextView extends android.support.v7.widget.AppCompatTextView {
    int oldX, oldY;

    public CustomTextView(Context context) {
        super(context);
    }

    public CustomTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        Log.d("Event","CustomTextView:dispatchTouchEvent");
        return super.dispatchTouchEvent(event);
    }


    /**view没有onInterceptTouchEvent方法，直接传递到onTouchEvent方法
     * @param event
     * @return
     */
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.d("Event","CustomTextView:onTouchEvent");
        //获取相对布局的位置
        int x = (int) event.getRawX();
        int y = (int) event.getRawY();
        switch (event.getAction()) {
            case MotionEvent.ACTION_MOVE:
                Log.d("ScrollView","X: "+x+"Y: "+y);
                //原来的位置（getTranslationX()）加上偏移距离（x - oldX）
                setTranslationX(getTranslationX()+x - oldX);
                setTranslationY(getTranslationY()+y - oldY);
                break;
            default:
                break;
        }
        oldX = x;
        oldY = y;
        return true;
    }

    /**click在序列最后，事件前面被消费了就传递不过来
     * @param l
     */
    @Override
    public void setOnClickListener(@Nullable OnClickListener l) {
        Log.d("Event","CustomTextView:setOnClickListener");
        super.setOnClickListener(l);
    }


}
