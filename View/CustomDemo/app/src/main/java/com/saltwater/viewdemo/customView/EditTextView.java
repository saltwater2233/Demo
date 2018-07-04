package com.saltwater.viewdemo.customView;

import android.content.Context;
import android.graphics.Point;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * <pre>
 *     author : wenxin
 *     e-mail : wenxin2233@outlook.com
 *     time   : 2018/07/03
 *     desc   : 一个可以编辑、移动、放大缩小的TextView
 *     version: 1.0
 * </pre>
 */
public class EditTextView extends android.support.v7.widget.AppCompatTextView {
    int mOldX;//记录view的x坐标
    int mOldY;//记录view的y坐标
    int mNewX;
    int mNewY;

    float mOldDistance;//记录两指距离
    //记录view的宽高
    int mWidth;
    int mHeight;


    private Boolean mIsTranslate;//判断是否为单指平移操作
    private Boolean mIsScale;//判断是否为双指缩放操作

    public EditTextView(Context context) {
        super(context);
    }

    public EditTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public EditTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        //获取View相对布局的位置
        mNewX = (int) event.getRawX();
        mNewY = (int) event.getRawY();
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                //单点触控，设置图片可以平移、不能旋转和缩放
                mIsTranslate = true;
                mIsScale = false;

                //获取View相对宽度
                mWidth = getWidth();
                mHeight = getHeight();
                break;

            case MotionEvent.ACTION_POINTER_DOWN:
                //多点触控，设置图片不能平移
                mIsTranslate = false;
                //当手指个数为两个的时候，设置图片能够旋转和缩放
                if (event.getPointerCount() == 2) {
                    mIsScale = true;
                    mOldDistance = distanceBetweenFingers(event);
                }
                break;
            case MotionEvent.ACTION_POINTER_UP:
                mIsScale = false;
                break;
            case MotionEvent.ACTION_MOVE:
                if (mIsTranslate) {
                    translateView(mNewX, mNewY);
                }
                if (mIsScale) {
                    scaleView(event);
                }
                break;
            default:
                break;
        }
        //记录移动到的位置
        mOldX = mNewX;
        mOldY = mNewY;
        return true;
    }


    /**
     * 对View做平移操作
     *
     * @param x
     * @param y
     */
    private void translateView(int x, int y) {

        //view移动的距离
        int distanceX = x - mOldX;
        int distanceY = y - mOldY;

        //重新设置Vie位置
        setTranslationX(getTranslationX() + distanceX);
        setTranslationY(getTranslationY() + distanceY);
    }

    private void scaleView(MotionEvent event) {
        float distance = distanceBetweenFingers(event);
        float scaleRate = mOldDistance / distance;
        if (distance > 10f) {//当双指的距离大于10时，开始相应处理
            int scaleWidth = (int) (scaleRate * mWidth - mWidth);
            int scaleHeight = (int) (scaleRate * mHeight - mHeight);
            setHeight(scaleHeight);
            setWidth(scaleWidth);
        }
    }


    /**
     * 计算两个手指间的距离
     * 第一个点的坐标为(getX(0),getY(0)),第二个为(getX(1),getY(1))
     *
     * @param event
     */
    private float distanceBetweenFingers(MotionEvent event) {
        float x = event.getX(0) - event.getX(1);
        float y = event.getY(0) - event.getY(1);
        return (float) Math.sqrt(x * x + y * y);
    }
}
