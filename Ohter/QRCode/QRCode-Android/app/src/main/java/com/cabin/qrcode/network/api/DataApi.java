package com.cabin.qrcode.network.api;

import com.cabin.qrcode.network.entity.QRCodeEntity;

import io.reactivex.Observable;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * <pre>
 *     author : wenxin
 *     e-mail : wenxin2233@outlook.com
 *     time   : 2018/05/29
 *     desc   :
 *     version: 1.0
 * </pre>
 */
public interface DataApi {
    @POST("QRcode")
    Observable<QRCodeEntity> getQRCode();
}
