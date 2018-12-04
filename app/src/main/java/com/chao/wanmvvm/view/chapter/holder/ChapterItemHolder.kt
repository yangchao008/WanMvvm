package com.chao.wanmvvm.view.chapter.holder

import android.content.Context
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.chao.wanmvvm.R
import com.chao.wanmvvm.model.bean.chapter.DatasItem
import com.chao.wanmvvm.utils.DisplayUtil
import com.trecyclerview.holder.AbsHolder
import com.trecyclerview.holder.AbsItemHolder
import zqx.rj.com.mvvm.common.toHtml

/**
 * @author：tqzhang on 18/6/27 19:14
 */
class ChapterItemHolder(context: Context) : AbsItemHolder<DatasItem, ChapterItemHolder.ViewHolder>(context) {

    private val commonWidth: Int by lazy {
        ((DisplayUtil.getScreenWidth().toFloat() - 30 * DisplayUtil.getScreenDensity()) / 2).toInt()
    }

    override fun getLayoutResId(): Int {
        return R.layout.article_item
    }

    override fun createViewHolder(view: View): ViewHolder {
        return ViewHolder(view)
    }


    override fun onBindViewHolder(holder: ChapterItemHolder.ViewHolder, item: DatasItem) {
        holder?.apply {
            mTvAuthor.text = item?.author
            mTvTitle.text = item?.title?.toHtml()
            mTvCategory.text = category(item)
            mTvTime.text = item?.niceDate
            mIvLove.setImageResource(isCollect(item))
        }
    }

    inner class ViewHolder internal constructor(itemView: View) : AbsHolder(itemView) {
        val mTvAuthor: TextView = getViewById(R.id.mTvAuthor)
        val mTvTitle: TextView = getViewById(R.id.mTvTitle)
        val mTvCategory: TextView = getViewById(R.id.mTvCategory)
        val mTvTime: TextView = getViewById(R.id.mTvTime)
        val mIvLove: ImageView = getViewById(R.id.mIvLove)

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
