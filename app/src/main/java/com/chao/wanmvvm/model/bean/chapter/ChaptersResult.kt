package com.chao.wanmvvm.model.bean.chapter


import com.google.gson.annotations.SerializedName

data class ChaptersResult(@SerializedName("data")
                          val data: List<DataItem>?,
                          @SerializedName("errorCode")
                          val errorCode: Int = 0,
                          @SerializedName("errorMsg")
                          val errorMsg: String = "")

data class DataItem(@SerializedName("visible")
                    val visible: Int = 0,
                    @SerializedName("name")
                    val name: String = "",
                    @SerializedName("userControlSetTop")
                    val userControlSetTop: Boolean = false,
                    @SerializedName("id")
                    val id: Int = 0,
                    @SerializedName("courseId")
                    val courseId: Int = 0,
                    @SerializedName("parentChapterId")
                    val parentChapterId: Int = 0,
                    @SerializedName("order")
                    val order: Int = 0)


