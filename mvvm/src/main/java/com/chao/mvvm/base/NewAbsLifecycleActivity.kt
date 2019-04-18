package com.chao.mvvm.base

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.text.TextUtils
import com.chao.mvvm.stateview.ErrorState
import com.chao.mvvm.stateview.LoadingState
import com.chao.mvvm.stateview.StateConstants
import com.chao.mvvm.util.TUtil
import com.tqzhang.stateview.stateview.BaseStateControl

/**
 * Date: 2019/4/11 20:37
 * Author: hans yang
 * Description:
 */
abstract class NewAbsLifecycleActivity<T : AbsViewModel<*>> : BaseActivity() {

    lateinit var mViewModel: T

    override fun initViews(savedInstanceState: Bundle?) {
        mViewModel = VMProviders(this, TUtil.getInstance<Any>(this, 0) as Class<T>)
        dataObserver()
    }

    fun dataObserver() {}

    private fun <T : ViewModel> VMProviders(fragment: AppCompatActivity, modeClass: Class<T>): T {
        return ViewModelProviders.of(fragment).get(modeClass)
    }

    protected override fun onStateRefresh() {
        super.onStateRefresh()
        showLoading()
    }

    protected fun showError(stateView: Class<out BaseStateControl>) {
        showError(stateView, null)
    }

    private fun showError(stateView: Class<out BaseStateControl>, tag: Any?) {
        mLoadManager.showStateView(stateView, tag)
    }

    private fun showSuccess() {
        mLoadManager.showSuccess()
    }

    private fun showLoading() {
        mLoadManager.showStateView(LoadingState::class.java)
    }

    protected var observer: Observer<*> = Observer<String> { state ->
        if (!TextUtils.isEmpty(state)) {
            when (state) {
                StateConstants.ERROR_STATE -> showError(ErrorState::class.java, StateConstants.ERROR_STATE)
                StateConstants.NET_WORK_STATE -> showError(ErrorState::class.java, StateConstants.NET_WORK_STATE)
                StateConstants.LOADING_STATE -> showLoading()
                StateConstants.SUCCESS_STATE -> showSuccess()
            }
        }
    }
}