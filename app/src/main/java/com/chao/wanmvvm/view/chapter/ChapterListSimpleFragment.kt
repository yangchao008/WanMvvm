package com.chao.wanmvvm.view.chapter

import android.arch.lifecycle.Observer
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.chao.mvvm.baseui.BaseListSimpleFragment
import com.chao.wanmvvm.R
import com.chao.wanmvvm.config.Constants
import com.chao.wanmvvm.model.bean.chapter.ChapterListResult
import com.chao.wanmvvm.model.bean.chapter.DatasItem
import com.chao.wanmvvm.viewmodel.ChapterViewModel
import zqx.rj.com.mvvm.common.showShortToast
import zqx.rj.com.mvvm.common.toHtml

/**
 * Date: 2018/12/4 14:53
 * Author: hansyang
 * Description:
 */
class ChapterListSimpleFragment : BaseListSimpleFragment<ChapterViewModel>(){

    companion object {
        private const val CHAPTER_ID = "chapter_id"

        fun newInstance(chapterId: String):ChapterListSimpleFragment{
            val fragment = ChapterListSimpleFragment()
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

    override fun createAdapter(): BaseQuickAdapter<DatasItem,BaseViewHolder> {
        var adapter = MyAdapter(R.layout.article_item)
        adapter.setOnItemClickListener { adapter, view, position ->
            activity!!.showShortToast("点击 = $position")
        }
        return adapter
    }

    override fun getRemoteData() {
        mViewModel.getChapterList(chapterId,page)
    }

    override fun getLoadMoreData() {
        page++
        getRemoteData()
    }

    inner class MyAdapter(resId: Int) : BaseQuickAdapter<DatasItem, BaseViewHolder>(resId){
        override fun convert(helper: BaseViewHolder?, item: DatasItem?) {
            helper?.let {
                it.setText(R.id.mTvAuthor, item?.author)
                it.setText(R.id.mTvTitle, item?.title?.toHtml())
                it.setText(R.id.mTvCategory, category(item))
                it.setText(R.id.mTvTime, item?.niceDate)
                it.setImageResource(R.id.mIvLove, isCollect(item))
                it.addOnClickListener(R.id.mIvLove)
            }
        }

        private fun category(article: DatasItem?): String {
            return article?.let { "${it.superChapterName}/${it.chapterName}" } ?: ""
        }

        private fun isCollect(article: DatasItem?): Int {
            return article?.let {
                if (it.collect) R.drawable.ic_collected else R.drawable.ic_collect
            } ?: R.drawable.ic_collect
        }
    }

}