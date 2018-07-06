package com.saltwater2233.performanceoptimization;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * <pre>
 *     author : wenxin
 *     e-mail : wenxin2233@outlook.com
 *     time   : 2018/07/06
 *     desc   : 绘制优化
 *     version: 1.0
 * </pre>
 */
public class CustomView extends View {
    public CustomView(Context context) {
        super(context);
    }

    public CustomView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint paint = new Paint();//1.不要在onDraw里创建局部对象，因为onDraw方法可能会被频繁调用，这样就会在一瞬间产生大量的临时对象

        //2.也不要做耗时操作，会造成View绘制不流畅
        for (int i=0;i<10000;i++){

        }
    }
}
