package com.chao.wanmvvm.network

import com.chao.wanmvvm.config.URL
import com.chao.wanmvvm.mvvm.model.bean.chapter.ChapterListResult
import com.chao.wanmvvm.mvvm.model.bean.chapter.ChaptersResult
import io.reactivex.Flowable
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * Date: 2018/11/16 12:56
 * Author: hans yang
 * Description:
 */
interface ApiService{

    @GET(URL.HOME_CHAPTER)
    fun getChapters()
            : Flowable<ChaptersResult>

    @GET(URL.HOME_CHAPTER_LIST)
    fun getChapterList(@Path("chapterId") chapterId : String,
                       @Path("page") page : Int)
            : Flowable<ChapterListResult>

}
