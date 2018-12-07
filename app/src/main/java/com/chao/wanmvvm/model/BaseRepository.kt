package com.chao.wanmvvm.model

import com.chao.mvvm.base.AbsRepository
import com.chao.mvvm.event.LiveBus
import com.chao.mvvm.http.RetrofitFactory
import com.chao.mvvm.stateview.StateConstants
import com.chao.wanmvvm.network.ApiService

/**
 * @author：tqzhang on 18/7/26 16:15
 */
open class BaseRepository : AbsRepository() {

    val apiService: ApiService by lazy {
        RetrofitFactory.getInstance().create(ApiService::class.java)
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

    private fun showPageState(eventKey: Any, tag: String, state: String) {
        sendData(eventKey, tag, state)
    }

    fun sendSuccessData(eventKeyState: Any,eventKey: Any,t: Any) {
        showPageState(eventKeyState, StateConstants.SUCCESS_STATE)
        sendData(eventKey,t)
    }

    fun sendSuccessData(eventKeyState: Any,eventKey: Any, tag: String, t: Any) {
        showPageState(eventKeyState,tag, StateConstants.SUCCESS_STATE)
        sendData(eventKey,tag,t)
    }

}
