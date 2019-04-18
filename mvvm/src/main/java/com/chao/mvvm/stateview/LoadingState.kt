package com.chao.mvvm.stateview

import com.chao.mvvm.R
import com.tqzhang.stateview.stateview.BaseStateControl

/**
 * @author：tqzhang  on 18/7/16 15:07
 */
class LoadingState : BaseStateControl() {
    override fun onCreateView(): Int {
        return R.layout.loading_view
    }

    override fun isVisible(): Boolean {
        return super.isVisible()
    }

}
