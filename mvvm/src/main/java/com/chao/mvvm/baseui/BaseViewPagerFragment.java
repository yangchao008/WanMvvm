package com.chao.mvvm.baseui;

import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.chao.mvvm.R;
import com.chao.mvvm.adapter.ViewPagerAdapter;
import com.chao.mvvm.base.AbsLifecycleFragment;
import com.chao.mvvm.base.AbsViewModel;
import com.chao.mvvm.base.BaseFragment;
import com.chao.mvvm.stateview.LoadingState;
import com.chao.mvvm.widget.NestedViewPager;
import com.flyco.tablayout.SlidingTabLayout;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Date: 2018/11/16 12:56
 * Author: hansyang
 * Description:
 */
public abstract class BaseViewPagerFragment<T extends AbsViewModel> extends AbsLifecycleFragment<T> {

    protected SlidingTabLayout mTabLayout;

    protected NestedViewPager mViewPager;

    protected RelativeLayout mTitleBar;

    protected TextView mTitle;

    protected ViewPagerAdapter adapter;

    protected List<BaseFragment> mFragments;

    protected List<String> mTitles;

    protected String[] mArrTitles;

    @Override
    public int getLayoutResId() {
        return R.layout.fragment_viewpager;
    }

    @Override
    public int getContentResId(){
        return R.id.content_layout;
    }

    @Override
    public void initView(Bundle state) {
        super.initView(state);
        mLoadManager.showStateView(LoadingState.class);
        mTabLayout = getViewById(R.id.tab_layout);
        mViewPager = getViewById(R.id.view_pager);
        mTitleBar = getViewById(R.id.rl_title_bar);
        mTitle = getViewById(R.id.tv_title);
        mTitles = new ArrayList<>();
        mFragments = new ArrayList<>();
    }

    /**
     * init adapter
     */
    protected void setAdapter() {
        mTitles.addAll(Arrays.asList(createPageTitle()));
        adapter = new ViewPagerAdapter(getChildFragmentManager(), createFragments(), mTitles);
        mViewPager.setAdapter(adapter);
        mViewPager.setOffscreenPageLimit(mTitles.size());
        mTabLayout.setViewPager(mViewPager, mTitles.toArray(createPageTitle()));
    }

    /**
     * create ViewPager title
     *
     * @return String[]
     */
    protected abstract String[] createPageTitle();

    /**
     * create Fragment
     *
     * @return List<BaseFragment>
     */
    protected abstract List<BaseFragment> createFragments();


    /**
     * set title
     * @param titleName
     */
    protected void setTitle(String titleName) {
        mTitleBar.setVisibility(View.VISIBLE);
        mTitle.setText(titleName);
    }
}
