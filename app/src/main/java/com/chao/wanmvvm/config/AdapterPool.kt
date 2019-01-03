package com.chao.wanmvvm.config

import android.content.Context
import com.chao.wanmvvm.mvvm.model.bean.chapter.DatasItem
import com.chao.wanmvvm.mvvm.view.chapter.holder.ChapterItemHolder
import com.trecyclerview.adapter.DelegateAdapter.Builder
import com.trecyclerview.footview.FootViewHolder
import com.trecyclerview.headview.HeaderViewHolder
import com.trecyclerview.pojo.FootVo
import com.trecyclerview.pojo.HeaderVo
import com.trecyclerview.progressindicator.ProgressStyle

/**
 * @author：tqzhang on 18/8/3 16:25
 */
object AdapterPool {

    fun getChapterAdapter(context: Context): Builder<*> {
        return  Builder<Any>()
            .bind(DatasItem::class.java, ChapterItemHolder(context))
            .getAdapter(context, ProgressStyle.SysProgress)

    }
}

fun Builder<Any>.getAdapter(context: Context,mProgressStyle: Int): Builder<*>{
    return bind(HeaderVo::class.java, HeaderViewHolder(context, mProgressStyle))
        .bind(FootVo::class.java, FootViewHolder(context, mProgressStyle))
}

fun Builder<Any>.getNoHeadAdapter(context: Context,mProgressStyle: Int): Builder<*>{
    return bind(FootVo::class.java, FootViewHolder(context, mProgressStyle))
}

fun Builder<Any>.getNoFootAdapter(context: Context,mProgressStyle: Int): Builder<*>{
    return bind(HeaderVo::class.java, HeaderViewHolder(context, mProgressStyle))
}
