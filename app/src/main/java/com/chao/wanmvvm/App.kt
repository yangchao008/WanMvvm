package com.chao.wanmvvm

import android.app.Application
import android.content.ComponentCallbacks2
import android.content.Context
import android.support.multidex.MultiDex
import com.bumptech.glide.Glide
import com.chao.mvvm.http.RetrofitFactory
import com.chao.mvvm.stateview.ErrorState
import com.chao.mvvm.stateview.LoadingState
import com.chao.wanmvvm.config.URL
import com.raizlabs.android.dbflow.config.FlowManager
import com.tqzhang.stateview.core.LoadState

/**
 * Date: 2018/11/15 21:16
 * Author: hans yang
 * Description:
 */
class App : Application(), ComponentCallbacks2 {

    override fun attachBaseContext(base: Context) {
        super.attachBaseContext(base)
        MultiDex.install(this)
    }

    override fun onCreate() {
        super.onCreate()
        FlowManager.init(this)
        mInstance = this
        RetrofitFactory.Builder(this)
            .initOkHttp()
            .createRetrofit(URL.BASE_URL)
            .build()
        LoadState.Builder()
            .register(ErrorState())
            .register(LoadingState())
            .setDefaultCallback(LoadingState::class.java)
            .build()
    }

    override fun onTrimMemory(level: Int) {
        super.onTrimMemory(level)
        if (level == ComponentCallbacks2.TRIM_MEMORY_UI_HIDDEN) {
            Glide.get(this).clearMemory()
        }
        Glide.get(this).trimMemory(level)
    }

    override fun onLowMemory() {
        super.onLowMemory()
        Glide.get(this).clearMemory()
    }

    companion object {
        var mInstance: App? = null

        fun instance(): App = mInstance!!
    }
}
