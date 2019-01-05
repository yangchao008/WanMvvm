package com.chao.wanmvvm.mvvm.model

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

    private fun sendData(eventKey: Any, t: Any, tag: String?= null) {
        LiveBus.getDefault().postEvent(eventKey, tag, t)
    }

    fun showPageState(eventKey: Any, state: String, tag: String?= null) {
        sendData(eventKey, state, tag)
    }

    fun sendSuccessData(eventKeyState: Any,eventKey: Any, t: Any, tag: String?= null) {
        showPageState(eventKeyState, StateConstants.SUCCESS_STATE,tag)
        sendData(eventKey,t,tag)
    }

//    fun showPageState(eventKeyState: Any, state: String = StateConstants.SUCCESS_STATE, tag: String?= null,
//                      eventKey: Any?= null,t: Any?= null) {
//        sendData(eventKeyState, state, tag)
//        eventKey?.run {
//            sendData(eventKey!!,t!!,tag)
//        }
//    }

}
