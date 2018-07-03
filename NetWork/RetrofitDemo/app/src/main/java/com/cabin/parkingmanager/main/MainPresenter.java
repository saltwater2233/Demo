package com.cabin.parkingmanager.main;


import android.Manifest;
import android.app.Activity;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.view.View;

import com.cabin.parkingmanager.R;
import com.cabin.parkingmanager.common.utils.DialogUtil;
import com.cabin.parkingmanager.common.utils.LogUtil;
import com.cabin.parkingmanager.common.utils.MediaUtil;
import com.cabin.parkingmanager.common.utils.PermissionUtil;
import com.cabin.parkingmanager.common.utils.StringUtil;
import com.cabin.parkingmanager.common.utils.ToastUtil;
import com.cabin.parkingmanager.modual.InfoActivity;
import com.cabin.parkingmanager.network.RetrofitHelper;
import com.cabin.parkingmanager.network.entity.UploadEntity;
import com.google.gson.Gson;
import com.trello.rxlifecycle2.LifecycleProvider;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;

/**
 * <pre>
 *     author : wenxin
 *     e-mail : wenxin2233@outlook.com
 *     time   : 2018/04/24
 *     desc   :
 *     version: 1.0
 * </pre>
 */
public class MainPresenter implements MainContract.Presenter {
    private static final int TAKE_CAMERA_PHOTO = 0;
    private static final int CROP_PHOTO = 1;

    private MainContract.View mView;
    private LifecycleProvider mProvider;
    AlertDialog mAlertDialog ;

    public MainPresenter(MainContract.View view, LifecycleProvider provider) {
        mView = view;
        mProvider = provider;
    }

    /**
     * 初始化View
     */
    @Override
    public void initView() {
        mView.initKeyBoard();
    }

    /**
     * 启动车辆信息Activity
     */
    @Override
    public void startInfoActivity() {
        String plateNumber = mView.getPlateNumber();
        if (StringUtil.isEmpty(plateNumber)) {
            ToastUtil.showShort(mView.getContext(), "车牌号不能为空");
            return;
        }
        if (plateNumber.length() < 7){
            ToastUtil.showShort(mView.getContext(), "车牌号错误");
            return;
        }
        InfoActivity.startActivity(mView.getContext(), plateNumber);
    }

    /**
     * 获取权限后拍照
     */
    @Override
    public void takePhoto() {
        PermissionUtil.getPermissionAll((Activity) mView.getContext(), new PermissionUtil.PermissionsResultListener() {
            @Override
            public void onSuccessful() {
                MediaUtil.takeCameraPhoto((Activity) mView.getContext(), TAKE_CAMERA_PHOTO);
            }

            @Override
            public void onFailure() {
                ToastUtil.showShort(mView.getContext(), "获取权限失败");
            }
        }, Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE);
    }


    /**
     * 处理拍照返回
     */
    @Override
    public void handleRequestCode(int requestCode) {
        switch (requestCode) {
            case TAKE_CAMERA_PHOTO:
                mView.setPhotoVisibility(true);
                //MediaUtil.cropPhoto((Activity) mView.getContext(), CROP_PHOTO);// 照片剪裁
                break;
            case CROP_PHOTO:
                mView.setPhotoVisibility(true);
                break;
            default:
                break;
        }
    }

    @Override
    public void deletePhoto(View view) {
        mView.setPhotoVisibility(false);
        ToastUtil.showSnackBarShort(view, "已删除照片", new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mView.setPhotoVisibility(true);
            }
        });
    }


    @Override
    public void upLoad() {
        String plateNumber = mView.getPlateNumber();
        File file = MediaUtil.getFile(mView.getContext());
        if (file == null) {
            ToastUtil.showShort(mView.getContext(), "未获取照片");
            return;
        }

        if (StringUtil.isEmpty(plateNumber)) {
            ToastUtil.showShort(mView.getContext(), "车牌号不能为空");
            return;
        }
        if (plateNumber.length() < 7){
            ToastUtil.showShort(mView.getContext(), "车牌号错误");
            return;
        }

        DialogUtil.getConfirmDialog(mView.getContext(), null, "是否上传照片?", "是", "否",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        mAlertDialog = DialogUtil.getLoadingDialog(mView.getContext(), R.layout.widget_upload_loading);
                        doUpload();

//                        io.reactivex.Observable.timer(5, TimeUnit.SECONDS)
//                                .compose(mProvider.<Long>bindToLifecycle())
//                                .observeOn(AndroidSchedulers.mainThread())
//                                .subscribe(new Consumer<Long>() {
//                                    @Override
//                                    public void accept(Long aLong) throws Exception {
//
//                                    }
//                                });
                    }
                }, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
    }

    private void doUpload() {
        String plateNumber = mView.getPlateNumber();
        String describe = mView.getDescribe();
        File file = MediaUtil.getFile(mView.getContext());
        MediaUtil.compressBitmapToFile(MediaUtil.getBitmap(mView.getContext()),file);
        final RequestBody requestBody = RequestBody.create(MediaType.parse("multipart/form-data"), file);
        MultipartBody.Part body = MultipartBody.Part.createFormData("image", file.getName(), requestBody);

        Map<String, RequestBody> params = new HashMap<>();
        params.put("platenum", RequestBody.create(null, plateNumber));
        params.put("description", RequestBody.create(null, describe));


        RetrofitHelper.getScalarsApi()
                .upload(body, params)
                .compose(mProvider.<ResponseBody>bindToLifecycle())
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<ResponseBody>() {
                    @Override
                    public void accept(ResponseBody responseBody) throws Exception {
                        mAlertDialog.cancel();
                        try {
                            Gson gson = new Gson();
                            UploadEntity uploadEntity = gson.fromJson(new String(responseBody.bytes()), UploadEntity.class);
                            if (uploadEntity.getCode().equals("00")) {
                                ToastUtil.showShort(mView.getContext(), "上传成功");
                                mView.clear();
                            } else {
                                ToastUtil.showShort(mView.getContext(), uploadEntity.getMsg());
                            }
                        } catch (Exception e) {
                            LogUtil.e(e.toString());
                            ToastUtil.showShort(mView.getContext(), "获取数据失败");
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        mAlertDialog.cancel();
                        LogUtil.e(throwable.toString());
                        ToastUtil.showShort(mView.getContext(), "网络连接错误，请稍后重试");
                    }
                });
    }
}
