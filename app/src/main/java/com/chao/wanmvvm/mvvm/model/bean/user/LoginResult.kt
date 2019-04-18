package com.chao.wanmvvm.mvvm.model.bean.user

import com.google.gson.annotations.SerializedName

/**
 * Date: 2019/4/11 19:57
 * Author: hans yang
 * Description:
 */
data class LoginResult(@SerializedName("code")
                       val code: Int = 0)