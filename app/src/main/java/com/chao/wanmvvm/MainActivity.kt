package com.chao.wanmvvm

import android.os.Bundle
import com.chao.mvvm.base.BaseActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {

    override fun getLayoutId(): Int {
        return R.layout.activity_main
    }

    override fun initViews(savedInstanceState: Bundle?) {
        with(mNavigationBar){
        }
    }
}
