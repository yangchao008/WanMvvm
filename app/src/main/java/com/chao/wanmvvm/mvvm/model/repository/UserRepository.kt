package com.chao.wanmvvm.mvvm.model.repository

import com.chao.mvvm.http.rx.RxSchedulers
import com.chao.mvvm.stateview.StateConstants
import com.chao.wanmvvm.config.Constants
import com.chao.wanmvvm.mvvm.model.BaseRepository
import com.chao.wanmvvm.mvvm.model.bean.user.LoginResult
import com.chao.wanmvvm.network.RxSubscriber

/**
 * Date: 2019/4/11 20:00
 * Author: hans yang
 * Description:
 */
class UserRepository : BaseRepository(){

    fun login(userName: String,password: String){

        addDisposable(apiService.login(userName,password)
            .compose(RxSchedulers.io_main())
            .subscribeWith(object : RxSubscriber<LoginResult>(){
                override fun onNoNetWork() {
                    showPageState(Constants.EVENT_KEY_LOGIN_STATE, StateConstants.NET_WORK_STATE)
                }

                override fun onSuccess(t: LoginResult) {
                    sendSuccessData(Constants.EVENT_KEY_LOGIN_STATE, Constants.EVENT_KEY_LOGIN,t)
                }

                override fun onFailure(msg: String?) {
                    showPageState(Constants.EVENT_KEY_LOGIN_STATE, StateConstants.ERROR_STATE)
                }

            }))
    }

    fun register(userName: String,password: String,rePassword: String){

        addDisposable(apiService.register(userName,password,rePassword)
            .compose(RxSchedulers.io_main())
            .subscribeWith(object : RxSubscriber<LoginResult>(){
                override fun onNoNetWork() {
                    showPageState(Constants.EVENT_KEY_REGISTER_STATE, StateConstants.NET_WORK_STATE)
                }

                override fun onSuccess(t: LoginResult) {
                    sendSuccessData(Constants.EVENT_KEY_REGISTER_STATE, Constants.EVENT_KEY_REGISTER,t)
                }

                override fun onFailure(msg: String?) {
                    showPageState(Constants.EVENT_KEY_REGISTER_STATE, StateConstants.ERROR_STATE)
                }

            }))
    }
}