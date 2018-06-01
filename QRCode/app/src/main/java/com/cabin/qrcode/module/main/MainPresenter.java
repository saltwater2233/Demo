package com.cabin.qrcode.module.main;

import android.util.Log;
import android.widget.ImageView;

import com.cabin.qrcode.common.utils.LogUtil;
import com.cabin.qrcode.network.ApiConstants;
import com.cabin.qrcode.network.RetrofitHelper;
import com.cabin.qrcode.network.entity.QRCodeEntity;
import com.trello.rxlifecycle2.LifecycleProvider;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * <pre>
 *     author : wenxin
 *     e-mail : wenxin2233@outlook.com
 *     time   : 2018/05/29
 *     desc   :
 *     version: 1.0
 * </pre>
 */
public class MainPresenter implements MainContract.Presenter {
    private MainContract.View mView;
    private LifecycleProvider mProvider;
    private static final String TAG = "MainPresenter";

    private int time;//倒计时时间
    private CompositeDisposable mCompositeDisposable;
    public MainPresenter(MainContract.View view,LifecycleProvider provider){
        this.mView = view;
        this.mProvider = provider;
        mCompositeDisposable = new CompositeDisposable();
    }

    @Override
    public void start() {

    }

    @Override
    public void refreshCode(final ImageView imageView) {
        getQRCode(imageView);

        time = 60;
        mCompositeDisposable.clear();
        Disposable disposable = Observable.interval(1, TimeUnit.SECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Long>() {
                    @Override
                    public void accept(Long aLong) throws Exception {
                        if (time>0){
                            Log.d(TAG,time+"");
                            mView.setCountText(time+"");
                            time--;
                        }else {
                            refreshCode(imageView);
                        }
                    }
                });
        mCompositeDisposable.add(disposable);
    }

    @Override
    public void getQRCode(final ImageView imageView) {
        RetrofitHelper.getApi()
                .getQRCode()
                .compose(mProvider.<QRCodeEntity>bindToLifecycle())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<QRCodeEntity>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(QRCodeEntity qrCodeEntity) {
                        if (qrCodeEntity.getResultCode().equals("00")){
                            mView.showQRCode(ApiConstants.SHARE_URL + qrCodeEntity.getData());

                        }else {
                            mView.showGetError(qrCodeEntity.getMessage());
                        }
                    }
                    @Override
                    public void onError(Throwable e) {
                        mView.showNetError();
                        LogUtil.d(TAG,e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }


    @Override
    public void finish() {
        mCompositeDisposable.clear();
    }

}
