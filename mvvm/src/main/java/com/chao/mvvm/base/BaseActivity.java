package com.chao.mvvm.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import com.tqzhang.stateview.core.LoadManager;
import com.tqzhang.stateview.stateview.BaseStateControl;

/**
 * Date: 2018/11/16 12:49
 * Author: hans yang
 * Description:
 */
public abstract class BaseActivity extends AppCompatActivity {
    
    protected LoadManager mLoadManager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //状态栏
        initStatusBar();
        //设置布局内容
        setContentView(getLayoutId());
        mLoadManager = new LoadManager.Builder()
                .setViewParams(this)
                .setListener(new BaseStateControl.OnRefreshListener() {
                    @Override
                    public void onRefresh(View view) {
                        onStateRefresh();
                    }
                }).build();
        //初始化控件
        initViews(savedInstanceState);
        //初始化ToolBar
        initToolBar();
    }

    /**
     *
     */
    protected  void onStateRefresh(){

    }

    protected void initStatusBar() {

    }

    /**
     * 设置布局layout
     *
     * @return
     */
    public abstract int getLayoutId();

    /**
     * 初始化views
     *
     * @param savedInstanceState
     */
    public abstract void initViews(Bundle savedInstanceState);

    /**
     * 初始化toolbar
     */
    protected void initToolBar() {
        //doSomething
    }

    /**
     * 显示进度条
     */
    public void showProgressBar() {
    }

    /**
     * 隐藏进度条
     */
    public void hideProgressBar() {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
