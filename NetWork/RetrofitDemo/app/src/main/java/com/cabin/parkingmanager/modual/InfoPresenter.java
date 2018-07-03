package com.cabin.parkingmanager.modual;



import android.content.Context;
import android.support.v7.app.AlertDialog;

import com.cabin.parkingmanager.R;
import com.cabin.parkingmanager.common.utils.DialogUtil;
import com.cabin.parkingmanager.common.utils.ToastUtil;
import com.cabin.parkingmanager.network.RetrofitHelper;
import com.cabin.parkingmanager.network.entity.InfoDataEntity;
import com.cabin.parkingmanager.network.entity.InfoEntity;
import com.google.gson.Gson;
import com.trello.rxlifecycle2.LifecycleProvider;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * <pre>
 *     author : wenxin
 *     e-mail : wenxin2233@outlook.com
 *     time   : 2018/04/25
 *     desc   :
 *     version: 1.0
 * </pre>
 */
public class InfoPresenter implements InfoContract.Presenter {

    private InfoContract.View mView;
    private LifecycleProvider mProvider;

    public InfoPresenter(InfoContract.View view,LifecycleProvider provider){
        mView = view;
        mProvider = provider;
    }


    @Override
    public void loadDate(Context context) {

        final AlertDialog alertDialog = DialogUtil.getLoadingDialog(context, R.layout.widget_loading);
        RetrofitHelper.getApi()
                .getInfo(mView.getPlateNumber())
                .compose(mProvider.<InfoEntity>bindToLifecycle())
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<InfoEntity>() {
                    @Override
                    public void accept(InfoEntity infoEntity) throws Exception {
                        alertDialog.cancel();
                        if(infoEntity.getCode().equals("00")){
                            InfoDataEntity infoDataEntity = new Gson().fromJson(infoEntity.getData(),InfoDataEntity.class);
                            mView.setSuccessDate(infoDataEntity);
                        }else {
                            //ToastUtil.showShort(mView.getContext(),infoEntity.getMsg());
                            mView.setEmptyDate();
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        alertDialog.cancel();
                        ToastUtil.showShort(mView.getContext(),"网络错误,请稍后重试");
                        mView.setNetError();
                    }
                });
    }
}
