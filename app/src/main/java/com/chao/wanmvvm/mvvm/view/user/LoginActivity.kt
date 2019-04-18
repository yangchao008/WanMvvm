package com.chao.wanmvvm.mvvm.view.user

import android.os.Bundle
import com.chao.mvvm.base.NewAbsLifecycleActivity
import com.chao.wanmvvm.R
import com.chao.wanmvvm.mvvm.viewmodel.UserViewModel
import kotlinx.android.synthetic.main.activity_login.*

/**
 * Date: 2019/4/11 19:18
 * Author: hans yang
 * Description:
 */
class LoginActivity : NewAbsLifecycleActivity<UserViewModel>(){


    override fun getLayoutId(): Int {
        return R.layout.activity_login
    }

    override fun initViews(savedInstanceState: Bundle?) {
        super.initViews(savedInstanceState)

        btn_login.setOnClickListener {

        }

        btn_state.setOnClickListener {

        }
    }

}