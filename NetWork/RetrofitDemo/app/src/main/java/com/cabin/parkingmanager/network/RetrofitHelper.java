package com.cabin.parkingmanager.network;


import com.cabin.parkingmanager.network.api.BaseApi;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * <pre>
 *     author : wenxin
 *     e-mail : wenxin2233@outlook.com
 *     time   : 2018/02/24
 *     desc   : Retrofit辅助类
 *     version: 1.0
 * </pre>
 */

public class RetrofitHelper {


    public static BaseApi getApi(){
        return createApi(BaseApi.class,ApiConstants.SERVER_URL);
    }
    public static BaseApi getScalarsApi(){
        return createScalarsApi(BaseApi.class,ApiConstants.SERVER_URL);
    }
    /**
     * 由于retrofit底层的实现是通过okhttp实现的，所以可以通过okHttp来设置一些连接参数
     * 初始化OKHttpClient,设置缓存,设置超时时间,构造头部
     */
    private static OkHttpClient getOkHttpClient() {
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient httpClient = new OkHttpClient.Builder()
                //.addNetworkInterceptor(new StethoInterceptor())
                //.addInterceptor(logging)
                //.addInterceptor(new HeaderInterceptor())
                .connectTimeout(10, TimeUnit.SECONDS)
                .writeTimeout(10, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .build();
        return httpClient;
    }

    /**
     * 添加Header
     */
    private static class HeaderInterceptor implements Interceptor {
        @Override
        public Response intercept(Chain chain) throws IOException {
            Request request;
            request = chain.request()
                    .newBuilder()
                    .header("USERNAME", "username")
                    .build();

            return chain.proceed(request);
        }
    }

    /**
     * 根据传入的baseUrl，和api创建retrofit
     */
    private static <T> T createApi(Class<T> clazz, String baseUrl) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(getOkHttpClient())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit.create(clazz);//实现接口,返回一个clazz接口的实例
    }
    /**
     * 根据传入的baseUrl，和api创建retrofit
     */
    private static <T> T createScalarsApi(Class<T> clazz, String baseUrl) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(getOkHttpClient())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                //.addConverterFactory(GsonConverterFactory.create())
                .addConverterFactory(ScalarsConverterFactory.create())
                .build();

        return retrofit.create(clazz);//实现接口,返回一个clazz接口的实例
    }

}
