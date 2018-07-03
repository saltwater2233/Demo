package com.cabin.qrcode.network;


import com.cabin.qrcode.network.api.DataApi;

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

    public static DataApi getApi(){
        return createApi(DataApi.class,ApiConstants.BASE_URL);
    }


    /**
     * 由于retrofit底层的实现是通过okhttp实现的，所以可以通过okHttp来设置一些连接参数
     * 初始化OKHttpClient,设置缓存,设置超时时间,构造头部
     */
    private static OkHttpClient getOkHttpClient() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient httpClient = new OkHttpClient.Builder()
                //.addNetworkInterceptor(new StethoInterceptor())
                //.addInterceptor(new HeaderInterceptor())
                //.addInterceptor(interceptor)
                .connectTimeout(30, TimeUnit.SECONDS)
                .writeTimeout(20, TimeUnit.SECONDS)
                .readTimeout(20, TimeUnit.SECONDS)
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

}
