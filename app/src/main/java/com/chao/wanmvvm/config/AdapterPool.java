package com.chao.wanmvvm.config;

import android.content.Context;
import com.chao.wanmvvm.model.bean.chapter.DatasItem;
import com.chao.wanmvvm.view.chapter.holder.ChapterItemHolder;
import com.trecyclerview.adapter.DelegateAdapter;
import com.trecyclerview.footview.FootViewHolder;
import com.trecyclerview.headview.HeaderViewHolder;
import com.trecyclerview.pojo.FootVo;
import com.trecyclerview.pojo.HeaderVo;
import com.trecyclerview.progressindicator.ProgressStyle;

/**
 * @author：tqzhang on 18/8/3 16:25
 */
public class AdapterPool {

    private static AdapterPool adapterPool;

    public static AdapterPool newInstance() {
        if (adapterPool == null) {
            synchronized (AdapterPool.class) {
                if (adapterPool == null) {
                    adapterPool = new AdapterPool();
                }
            }
        }
        return adapterPool;
    }

    public DelegateAdapter getNoHeadAdapter(DelegateAdapter.Builder builder, Context context) {
        return builder
                .bind(FootVo.class, new FootViewHolder(context, ProgressStyle.Pacman))
                .build();
    }

    public DelegateAdapter.Builder getNoFootAdapter(DelegateAdapter.Builder builder, Context context, int mProgressStyle) {
        return builder
                .bind(HeaderVo.class, new HeaderViewHolder(context, mProgressStyle));
    }

    public DelegateAdapter.Builder getAdapter(DelegateAdapter.Builder builder, Context context, int mProgressStyle) {
        return builder.bind(HeaderVo.class, new HeaderViewHolder(context, mProgressStyle))
                .bind(FootVo.class, new FootViewHolder(context,mProgressStyle));
    }

    public DelegateAdapter.Builder getChapterAdapter(Context context) {
        return getAdapter(new DelegateAdapter.Builder<>()
                .bind(DatasItem.class, new ChapterItemHolder(context)), context,ProgressStyle.SysProgress);
    }
}
