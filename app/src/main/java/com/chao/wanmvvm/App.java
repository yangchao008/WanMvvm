package com.chao.wanmvvm;

import android.app.Application;
import android.content.ComponentCallbacks2;
import android.content.Context;
import android.support.multidex.MultiDex;
import com.bumptech.glide.Glide;
import com.chao.mvvm.stateview.ErrorState;
import com.chao.mvvm.stateview.LoadingState;
import com.tqzhang.stateview.core.LoadState;

/**
 * @author：tqzhang on 18/4/19 17:57
 */
public class App extends Application implements ComponentCallbacks2 {
    public static App mInstance;

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }


    public static App instance() {
        return mInstance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
        new com.chao.http.HttpHelper.Builder(this)
                .initOkHttp()
                .createRetrofit(com.code.mvvm.config.URL.BASE_URL)
                .build();
        new LoadState.Builder()
                .register(new ErrorState())
                .register(new LoadingState())
                .setDefaultCallback(LoadingState.class)
                .build();
    }


    @Override
    public void onTrimMemory(int level) {
        super.onTrimMemory(level);
        if (level == TRIM_MEMORY_UI_HIDDEN) {
            Glide.get(this).clearMemory();
        }
        Glide.get(this).trimMemory(level);
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        Glide.get(this).clearMemory();
    }
}
