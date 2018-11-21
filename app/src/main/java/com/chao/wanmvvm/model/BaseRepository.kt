package com.chao.wanmvvm.model

import com.chao.mvvm.base.AbsRepository
import com.chao.mvvm.event.LiveBus
import com.chao.mvvm.http.RetrofitFactory
import com.chao.wanmvvm.network.ApiService

/**
 * @author：tqzhang on 18/7/26 16:15
 */
class BaseRepository : AbsRepository() {

    var apiService: ApiService? = null

    init {
        if (null == apiService) {
            apiService = RetrofitFactory.getInstance().create(ApiService::class.java)
        }
    }

    private fun sendData(eventKey: Any, t: Any) {
        sendData(eventKey, null, t)
    }

    private fun sendData(eventKey: Any, tag: String?, t: Any) {
        LiveBus.getDefault().postEvent(eventKey, tag, t)
    }

    fun showPageState(eventKey: Any, state: String) {
        sendData(eventKey, state)
    }

    fun showPageState(eventKey: Any, tag: String, state: String) {
        sendData(eventKey, tag, state)
    }


}
