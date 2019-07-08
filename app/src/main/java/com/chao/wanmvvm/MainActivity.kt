package com.chao.wanmvvm

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import com.ashokvarma.bottomnavigation.BottomNavigationBar
import com.ashokvarma.bottomnavigation.BottomNavigationItem
import com.chao.mvvm.base.BaseActivity
import com.chao.mvvm.base.BlankFragment
import com.chao.wanmvvm.mvvm.view.chapter.ChapterFragment
import kotlinx.android.synthetic.main.activity_main.*

/**
 * Date: 2018/11/19 18:32
 * Author: hans yang
 * Description:
 */
class MainActivity : BaseActivity() {

    val mFragments = listOf(
        BlankFragment(),
        ChapterFragment(),
        BlankFragment(),
        BlankFragment(),
        BlankFragment())

    override fun getLayoutId(): Int {

        return R.layout.activity_main
    }

    override fun initViews(savedInstanceState: Bundle?) {
        mLoadManager.showSuccess()
        mNestedViewPager.apply {
            adapter = MyFragmentPagerAdapter(supportFragmentManager)
            currentItem = 0
            isLocked = true
            offscreenPageLimit = mFragments.size
        }
        initBottomNavigationBar()
    }

    private fun initBottomNavigationBar() {
        mNavigationBar.apply{
            setMode(BottomNavigationBar.MODE_FIXED)
            addItem(BottomNavigationItem(R.mipmap.ic_home, getString(R.string.home)))
            addItem(BottomNavigationItem(R.mipmap.ic_wechat, getString(R.string.wechat)))
            addItem(BottomNavigationItem(R.mipmap.ic_system, getString(R.string.system)))
            addItem(BottomNavigationItem(R.mipmap.ic_navigation, getString(R.string.navigation)))
            addItem(BottomNavigationItem(R.mipmap.ic_project, getString(R.string.project)))

            // 设置底部 BottomBar 默认选中 home
            setFirstSelectedPosition(0)
            // 初始化
            initialise()

            setTabSelectedListener(object : BottomNavigationBar.OnTabSelectedListener {
                override fun onTabReselected(position: Int) {}
                override fun onTabUnselected(position: Int) {}

                override fun onTabSelected(position: Int) {
                    mNestedViewPager.currentItem = position
                }
            })
        }
    }

    inner class MyFragmentPagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm){
        override fun getItem(p0: Int): Fragment {
            return mFragments[p0]
        }

        override fun getCount(): Int {
            return mFragments.size
        }

    }
}
