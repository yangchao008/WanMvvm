package com.chao.mvvm.baseui;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chao.mvvm.R;
import com.chao.mvvm.base.AbsLifecycleFragment;
import com.chao.mvvm.base.AbsViewModel;
import com.trecyclerview.adapter.ItemData;
import com.trecyclerview.listener.OnRefreshListener;
import java.util.Collection;
import java.util.List;

/**
 * Date: 2018/11/16 12:56
 * Author: hans yang
 * Description:
 */
public abstract class BaseListSimpleFragment<T extends AbsViewModel> extends AbsLifecycleFragment<T> implements OnRefreshListener {
    protected RecyclerView mRecyclerView;

    protected RelativeLayout mTitleBar;

    protected TextView mTitle;

    protected RecyclerView.LayoutManager layoutManager;

    protected BaseQuickAdapter mAdapter;

    protected String lastId = null;

    protected int page = 1;

    protected boolean isLoadMore = true;

    protected boolean isLoading = true;

    protected boolean isRefresh = false;

    protected ItemData oldItems;

    protected ItemData newItems;

    @Override
    public int getLayoutResId() {
        return R.layout.fragment_list_simple;
    }

    @Override
    public int getContentResId() {
        return R.id.content_layout;
    }

    @Override
    public void initView(Bundle state) {
        super.initView(state);
        mRecyclerView = getViewById(R.id.recycler_view);
        mTitleBar = getViewById(R.id.rl_title_bar);
        mTitle = getViewById(R.id.tv_title);
        oldItems = new ItemData();
        newItems = new ItemData();
        mAdapter = createAdapter();
        mAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                onLoadMore();
            }
        },mRecyclerView);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(createLayoutManager());
//        mRecyclerView.addOnRefreshListener(this);
        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                    if (mActivity != null) {
                        Glide.with(mActivity).resumeRequests();
                    }
                } else {
                    if (mActivity != null) {
                        Glide.with(mActivity).pauseRequests();
                    }
                }
            }
        });
    }

    @Override
    protected void lazyLoad() {
        isLoadMore = false;
        getRemoteData();
    }

    @Override
    protected void onStateRefresh() {
        super.onStateRefresh();
        onRefresh();
    }

    protected void setData(List<?> collection) {
        if (isLoadMore) {
            onLoadMoreSuccess(collection);
        } else {
            onRefreshSuccess(collection);
        }
    }

    @Override
    public void onRefresh() {
        lastId = null;
        isRefresh = true;
        isLoadMore = false;
        getRemoteData();
    }

    @Override
    public void onLoadMore() {
        isLoadMore = true;
        getLoadMoreData();
    }

//    protected void setBannerData(BannerListVo headAdList) {
//        newItems.add(headAdList);
//    }

    protected void onRefreshSuccess(Collection<?> collection) {
        newItems.addAll(collection);
        oldItems.clear();
        oldItems.addAll(newItems);
        mAdapter.setNewData(oldItems);
        if (collection.size() < 20)
            mAdapter.setEnableLoadMore(false);
        isRefresh = false;
    }

    protected void onLoadMoreSuccess(List<?> collection) {
        isLoading = true;
        isLoadMore = false;
        oldItems.addAll(collection);
        if (collection.size() < 20) {
            mAdapter.addData(collection);
            mAdapter.loadMoreEnd();
        } else {
            mAdapter.addData(collection);
            mAdapter.loadMoreComplete();
        }
    }

    /**
     * adapter
     *
     * @return DelegateAdapter
     */
    protected abstract BaseQuickAdapter createAdapter();

    /**
     * LayoutManager
     *
     * @return LayoutManager
     */
    protected abstract RecyclerView.LayoutManager createLayoutManager();


    protected void setTitle(String titleName) {
        mTitleBar.setVisibility(View.VISIBLE);
        mTitle.setText(titleName);
    }

    /**
     * 获取更多网络数据t
     */
    protected void getLoadMoreData() {

    }

}
