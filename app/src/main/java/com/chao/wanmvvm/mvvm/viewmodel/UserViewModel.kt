package com.chao.wanmvvm.mvvm.viewmodel

import android.app.Application
import com.chao.mvvm.base.AbsViewModel
import com.chao.wanmvvm.mvvm.model.repository.UserRepository

/**
 * Date: 2019/4/11 20:16
 * Author: hans yang
 * Description:
 */
class UserViewModel(application : Application) : AbsViewModel<UserRepository>(application) {

    private fun login(userName: String, password: String){
        mRepository.login(userName,password)
    }

    private fun register(userName: String, password: String, rePassword: String){
        mRepository.register(userName,password,rePassword)
    }

    fun toRequest(userName: String,password: String,rePassword: String = ""){
        val isLogin = rePassword.isEmpty()
        if (isLogin){
            login(userName,password)
        }else{
            register(userName,password,rePassword)
        }
    }

}