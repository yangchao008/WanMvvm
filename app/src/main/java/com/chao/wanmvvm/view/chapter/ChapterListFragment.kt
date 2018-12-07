package com.chao.wanmvvm.view.chapter

import android.arch.lifecycle.Observer
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import com.chao.mvvm.baseui.BaseListFragment
import com.chao.wanmvvm.config.AdapterPool
import com.chao.wanmvvm.config.Constants
import com.chao.wanmvvm.model.bean.chapter.ChapterListResult
import com.chao.wanmvvm.viewmodel.ChapterViewModel
import com.trecyclerview.adapter.DelegateAdapter
import zqx.rj.com.mvvm.common.showShortToast

/**
 * Date: 2018/12/4 14:53
 * Author: hansyang
 * Description:
 */
class ChapterListFragment : BaseListFragment<ChapterViewModel>(){

    companion object {
        private const val CHAPTER_ID = "chapter_id"

        fun newInstance(chapterId: String):ChapterListFragment{
            val fragment = ChapterListFragment()
            var bundle = Bundle()
            bundle.putString(CHAPTER_ID,chapterId)
            fragment.arguments = bundle
            return fragment
        }
    }
    private var chapterId : String =""

    override fun createLayoutManager(): RecyclerView.LayoutManager {
        return LinearLayoutManager(activity)
    }

    override fun getStateEventKey(): Any {
        return Constants.EVENT_KEY_CHAPTER_LIST_STATE
    }

    override fun getStateEventTag(): String {
        return chapterId
    }

    override fun dataObserver() {
        arguments?.apply {
            chapterId = arguments!!.getString(CHAPTER_ID)
        }
        registerObserver(Constants.EVENT_KEY_CHAPTER_LIST,chapterId,ChapterListResult::class.java)
            .observe(this, Observer<ChapterListResult>{
                    when{
                        it?.data?.datas != null && it?.data?.datas!!.isNotEmpty() -> setData(it?.data.datas)
                    }
            })
    }

    override fun createAdapter(): DelegateAdapter {
        return AdapterPool.newInstance()
            .getChapterAdapter(activity!!)
            .setOnItemClickListener { view, i, any ->
                activity!!.showShortToast("click item = $i")
            }
            .build()
    }

    override fun getRemoteData() {
        mViewModel.getChapterList(chapterId,page)
    }

    override fun getLoadMoreData() {
        page++
        getRemoteData()
    }

}