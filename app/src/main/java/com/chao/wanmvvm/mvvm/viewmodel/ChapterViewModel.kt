package com.chao.wanmvvm.mvvm.viewmodel

import android.app.Application
import com.chao.mvvm.base.AbsViewModel
import com.chao.wanmvvm.mvvm.model.repository.ChapterRepository

/**
 * Date: 2018/12/4 13:04
 * Author: hans yang
 * Description:
 */
class ChapterViewModel(application : Application) : AbsViewModel<ChapterRepository>(application){

    fun getChaptersData(){
        mRepository.loadChaptersData()
    }

    fun getChapterList(chapterId : String, page : Int){
        checkNotNull(chapterId)
        mRepository.loadChapterList(chapterId,page)
    }
}