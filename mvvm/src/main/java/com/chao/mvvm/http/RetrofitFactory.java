package com.chao.mvvm.http;

import android.content.Context;
import okhttp3.Cache;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import java.io.File;
import java.util.concurrent.TimeUnit;
import static com.chao.mvvm.util.TUtil.checkNotNull;

/**
 * Date: 2018/11/15 21:16
 * Author: hansyang
 * Description:
 */
public class RetrofitFactory {

    private static volatile RetrofitFactory sInstance = null;

    private static OkHttpClient sOkHttpClient;

    private static Retrofit sRetrofit;

    private static OkHttpClient.Builder sBuilder;

    private static String BASE_URL;

    private RetrofitFactory(){
    }

    public static RetrofitFactory getInstance(){
        if (sInstance == null){
            synchronized (RetrofitFactory.class){
                if (sInstance == null){
                    sInstance = new RetrofitFactory();
                }
            }
        }
        return sInstance;
    }

    public static void init(Context context,String baseUrl){
        new RetrofitFactory.Builder(context)
                .initOkHttp()
                .createRetrofit(baseUrl)
                .build();
    }

    public static class Builder {
        private OkHttpClient mOkHttpClient;

        private OkHttpClient.Builder mBuilder;

        private Retrofit mRetrofit;

        private Context mContext;

        public Builder(Context context) {
            this.mContext = context;
        }

        /**
         * create OkHttp 初始化OKHttpClient,设置缓存,设置超时时间,设置打印日志
         *
         * @return Builder
         */
        public Builder initOkHttp() {
            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor(new HttpLogger());
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            if (mBuilder == null) {
                synchronized (RetrofitFactory.class) {
                    if (mBuilder == null) {
                        Cache cache = new Cache(new File(mContext.getCacheDir(), "HttpCache"), 1024 * 1024 * 10);
                        mBuilder = new OkHttpClient.Builder()
                                .cache(cache)
                                .addInterceptor(interceptor)
                                .connectTimeout(30, TimeUnit.SECONDS)
                                .writeTimeout(30, TimeUnit.SECONDS)
                                .readTimeout(30, TimeUnit.SECONDS)
                        ;
                    }
                }
            }
            return this;
        }
        /**
         * 添加拦截器
         *
         * @param mInterceptor Interceptor
         * @return Builder
         */
        public Builder addInterceptor(Interceptor mInterceptor) {
            checkNotNull(mInterceptor);
            this.mBuilder.addNetworkInterceptor(mInterceptor);
            return this;
        }

        /**
         * create retrofit
         *
         * @param baseUrl baseUrl
         * @return Builder
         */
        public Builder createRetrofit(String baseUrl) {
            checkNotNull(baseUrl);
            Retrofit.Builder builder = new Retrofit.Builder()
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .baseUrl(baseUrl);
            BASE_URL = baseUrl;
            this.mOkHttpClient = mBuilder.build();
            this.mRetrofit = builder.client(mOkHttpClient)
                    .build();
            return this;
        }

        public void build() {
            RetrofitFactory.getInstance().build(this);
        }

    }

    private void build(Builder builder) {
        checkNotNull(builder);
        checkNotNull(builder.mBuilder);
        checkNotNull(builder.mOkHttpClient);
        checkNotNull(builder.mRetrofit);
        sBuilder = builder.mBuilder;
        sOkHttpClient = builder.mOkHttpClient;
        sRetrofit = builder.mRetrofit;
    }

    public <T> T create(Class<T> clazz){
        checkNotNull(clazz);
        checkNotNull(sRetrofit);
        return sRetrofit.create(clazz);
    }


}
