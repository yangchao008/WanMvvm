package com.chao.wanmvvm.view.chapter

import android.arch.lifecycle.Observer
import android.os.Bundle
import com.chao.mvvm.base.BaseFragment
import com.chao.mvvm.baseui.BaseViewPagerFragment
import com.chao.wanmvvm.R
import com.chao.wanmvvm.config.Constants
import com.chao.wanmvvm.model.bean.chapter.ChaptersResult
import com.chao.wanmvvm.viewmodel.ChapterViewModel

/**
 * Date: 2018/12/4 14:20
 * Author: hansyang
 * Description:
 */
class ChapterFragment : BaseViewPagerFragment<ChapterViewModel>(){

    override fun initView(state: Bundle?) {
        super.initView(state)
        setTitle(getString(R.string.wechat))
        getTabData()
    }

    override fun onStateRefresh() {
        super.onStateRefresh()
        getTabData()
    }

    private fun getTabData() {
        mViewModel.getChaptersData()
    }

    override fun dataObserver() {
        registerObserver(Constants.EVENT_KEY_CHAPTER,ChaptersResult::class.java)
            .observe(this, Observer<ChaptersResult> {
                when{
                    it?.data != null && it!!.data!!.isNotEmpty() -> setData(it!!)
                }
            })
    }

    private fun setData(result: ChaptersResult) {
        mArrTitles = arrayOfNulls<String>(result.data!!.size)
        result.data?.forEachIndexed { index, dataItem ->
            mArrTitles[index] = dataItem.name
            mFragments.add(ChapterListFragment.newInstance(dataItem.id.toString()))
        }
        setAdapter()
    }

    override fun createFragments(): MutableList<BaseFragment> {
        return mFragments
    }

    override fun getStateEventKey(): Any {
        return Constants.EVENT_KEY_CHAPTER_STATE
    }

    override fun createPageTitle(): Array<String> {
        return mArrTitles
    }

}