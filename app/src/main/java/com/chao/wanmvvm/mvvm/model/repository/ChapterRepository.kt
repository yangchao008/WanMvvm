package com.chao.wanmvvm.mvvm.model.repository

import com.chao.mvvm.http.rx.RxSchedulers
import com.chao.mvvm.stateview.StateConstants
import com.chao.wanmvvm.config.Constants
import com.chao.wanmvvm.mvvm.model.BaseRepository
import com.chao.wanmvvm.mvvm.model.bean.chapter.ChapterListResult
import com.chao.wanmvvm.mvvm.model.bean.chapter.ChaptersResult
import com.chao.wanmvvm.network.RxSubscriber

/**
 * Date: 2018/12/4 12:32
 * Author: hans yang
 * Description:
 */
class ChapterRepository : BaseRepository(){

    fun loadChaptersData(){
        addDisposable(apiService.getChapters()
            .compose(RxSchedulers.io_main())
            .subscribeWith(object : RxSubscriber<ChaptersResult>(){
                override fun onNoNetWork() {
                    showPageState(Constants.EVENT_KEY_CHAPTER_STATE, StateConstants.NET_WORK_STATE)
                }

                override fun onSuccess(t: ChaptersResult) {
                    sendSuccessData(Constants.EVENT_KEY_CHAPTER_STATE,Constants.EVENT_KEY_CHAPTER,t)
                }

                override fun onFailure(msg: String?) {
                    showPageState(Constants.EVENT_KEY_CHAPTER_STATE, StateConstants.ERROR_STATE)
                }

            }))

    }

    fun loadChapterList(chapterId : String, page : Int){
        addDisposable(apiService.getChapterList(chapterId,page)
            .compose(RxSchedulers.io_main())
            .subscribeWith(object : RxSubscriber<ChapterListResult>(){
                override fun onNoNetWork() {
                    showPageState(Constants.EVENT_KEY_CHAPTER_LIST_STATE, StateConstants.NET_WORK_STATE)
                }

                override fun onSuccess(t: ChapterListResult) {
                    sendSuccessData(Constants.EVENT_KEY_CHAPTER_LIST_STATE,Constants.EVENT_KEY_CHAPTER_LIST,chapterId,t)
                }

                override fun onFailure(msg: String?) {
                    showPageState(Constants.EVENT_KEY_CHAPTER_LIST_STATE, StateConstants.ERROR_STATE)
                }
            }))
    }
}