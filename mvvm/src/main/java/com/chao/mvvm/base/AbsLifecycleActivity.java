package com.chao.mvvm.base;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import com.chao.mvvm.stateview.ErrorState;
import com.chao.mvvm.stateview.LoadingState;
import com.chao.mvvm.stateview.StateConstants;
import com.chao.mvvm.util.TUtil;
import com.tqzhang.stateview.stateview.BaseStateControl;

/**
 * Date: 2018/11/16 12:56
 * Author: hansyang
 * Description:
 */
public abstract class AbsLifecycleActivity<T extends AbsViewModel> extends BaseActivity {

    protected T mViewModel;

    public AbsLifecycleActivity(){

    }

    @Override
    public void initViews(Bundle savedInstanceState) {
        mViewModel = VMProviders(this,(Class<T>)TUtil.getInstance(this,0));
        dataObserver();
    }

    protected void dataObserver() {
    }

    private <T extends ViewModel> T VMProviders(AppCompatActivity fragment, Class<T> modeClass) {
        return (T) ViewModelProviders.of(fragment).get(modeClass);
    }

    @Override
    protected void onStateRefresh() {
        super.onStateRefresh();
        showLoading();
    }

    protected void showError(Class<? extends BaseStateControl> stateView) {
        showError(stateView, null);
    }

    protected void showError(Class<? extends BaseStateControl> stateView, Object tag) {
        mLoadManager.showStateView(stateView, tag);
    }

    protected void showSuccess() {
        mLoadManager.showSuccess();
    }

    protected void showLoading() {
        mLoadManager.showStateView(LoadingState.class);
    }

    protected Observer observer = new Observer<String>() {
        @Override
        public void onChanged(@Nullable String state) {
            if (!TextUtils.isEmpty(state)){
                if (StateConstants.ERROR_STATE.equals(state))
                    showError(ErrorState.class,StateConstants.ERROR_STATE);
                else if (StateConstants.NET_WORK_STATE.equals(state))
                    showError(ErrorState.class,StateConstants.NET_WORK_STATE);
                else if (StateConstants.LOADING_STATE.equals(state))
                    showLoading();
                else if (StateConstants.SUCCESS_STATE.equals(state))
                    showSuccess();
            }
        }
    };
}
