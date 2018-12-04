package com.chao.wanmvvm.model

import com.chao.mvvm.base.AbsRepository
import com.chao.mvvm.event.LiveBus
import com.chao.mvvm.http.RetrofitFactory
import com.chao.wanmvvm.network.ApiService

/**
 * @author：tqzhang on 18/7/26 16:15
 */
open class BaseRepository : AbsRepository() {

    val apiService: ApiService by lazy {
        RetrofitFactory.getInstance().create(ApiService::class.java)
    }

//    init {
//        if (null == apiService) {
//            apiService = RetrofitFactory.getInstance().create(ApiService::class.java)
//        }
//    }

    protected fun sendData(eventKey: Any, t: Any) {
        sendData(eventKey, null, t)
    }

    protected fun sendData(eventKey: Any, tag: String?, t: Any) {
        LiveBus.getDefault().postEvent(eventKey, tag, t)
    }

    protected fun showPageState(eventKey: Any, state: String) {
        sendData(eventKey, state)
    }

    protected fun showPageState(eventKey: Any, tag: String, state: String) {
        sendData(eventKey, tag, state)
    }


}
