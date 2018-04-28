package com.cabin.parkingmanager.network.api;


import com.cabin.parkingmanager.network.entity.InfoEntity;
import com.cabin.parkingmanager.network.entity.UploadEntity;

import java.util.Map;

import io.reactivex.Observable;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.PartMap;
import retrofit2.http.Query;

/**
 * <pre>
 *     author : wenxin
 *     e-mail : wenxin2233@outlook.com
 *     time   : 2018/02/24
 *     desc   :
 *     version: 1.0
 * </pre>
 */

public interface BaseApi {

    @Multipart
    @POST("uploadImg")
    Observable<ResponseBody> upload(@Part MultipartBody.Part img, @PartMap Map<String, RequestBody> args);

    @GET("getWhiteByPlateNum")
    Observable<InfoEntity> getInfo(@Query("platenum") String palteNumber);
}
