package com.chao.wanmvvm.config

/**
 * Date: 2018/11/16 12:56
 * Author: hans yang
 * Description:
 */
object URL {

    const val BASE_URL = "https://www.wanandroid.com/"

    private const val WX_ARTICLE = "wxarticle/"
    const val HOME_CHAPTER = "${WX_ARTICLE}chapters/json"
    const val HOME_CHAPTER_LIST = "${WX_ARTICLE}list/{chapterId}/{page}/json"

    private const val USER = "user/"
    const val USER_LOGIN = "${USER}login"
    const val USER_REGISTER = "${USER}register"

}
