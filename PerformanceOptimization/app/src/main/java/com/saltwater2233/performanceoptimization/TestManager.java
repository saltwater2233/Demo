package com.saltwater2233.performanceoptimization;

import java.util.ArrayList;
import java.util.List;

/**
 * <pre>
 *     author : wenxin
 *     e-mail : wenxin2233@outlook.com
 *     time   : 2018/07/06
 *     desc   :
 *     version: 1.0
 * </pre>
 */
public class TestManager {
    private List<OnDataArrivedListener> mOnDataArrivedListeners = new ArrayList<>();

    private TestManager(){

    }
    private static class singletonHolder {
        public static final TestManager INSTANCE = new TestManager();
    }

    public static TestManager getInstance() {
        return singletonHolder.INSTANCE;
    }

    public synchronized void registerListener(OnDataArrivedListener listener){
        if (!mOnDataArrivedListeners.contains(listener)){
            mOnDataArrivedListeners.add(listener);
        }
    }

    public synchronized void unregisterListener(OnDataArrivedListener listener){
        mOnDataArrivedListeners.remove(listener);
    }

    public interface OnDataArrivedListener{
        public void onDataArrived(Object data);
    }

}

