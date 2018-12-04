package com.chao.wanmvvm.model.bean.chapter


import com.google.gson.annotations.SerializedName

data class DatasItem(@SerializedName("superChapterName")
                     val superChapterName: String = "",
                     @SerializedName("publishTime")
                     val publishTime: Long = 0,
                     @SerializedName("visible")
                     val visible: Int = 0,
                     @SerializedName("niceDate")
                     val niceDate: String = "",
                     @SerializedName("projectLink")
                     val projectLink: String = "",
                     @SerializedName("author")
                     val author: String = "",
                     @SerializedName("zan")
                     val zan: Int = 0,
                     @SerializedName("origin")
                     val origin: String = "",
                     @SerializedName("chapterName")
                     val chapterName: String = "",
                     @SerializedName("link")
                     val link: String = "",
                     @SerializedName("title")
                     val title: String = "",
                     @SerializedName("type")
                     val type: Int = 0,
                     @SerializedName("userId")
                     val userId: Int = 0,
                     @SerializedName("tags")
                     val tags: List<TagsItem>?,
                     @SerializedName("apkLink")
                     val apkLink: String = "",
                     @SerializedName("envelopePic")
                     val envelopePic: String = "",
                     @SerializedName("chapterId")
                     val chapterId: Int = 0,
                     @SerializedName("superChapterId")
                     val superChapterId: Int = 0,
                     @SerializedName("id")
                     val id: Int = 0,
                     @SerializedName("fresh")
                     val fresh: Boolean = false,
                     @SerializedName("collect")
                     val collect: Boolean = false,
                     @SerializedName("courseId")
                     val courseId: Int = 0,
                     @SerializedName("desc")
                     val desc: String = "")


data class ChapterListResult(@SerializedName("data")
                             val data: Data,
                             @SerializedName("errorCode")
                             val errorCode: Int = 0,
                             @SerializedName("errorMsg")
                             val errorMsg: String = "")


data class TagsItem(@SerializedName("name")
                    val name: String = "",
                    @SerializedName("url")
                    val url: String = "")


data class Data(@SerializedName("over")
                val over: Boolean = false,
                @SerializedName("pageCount")
                val pageCount: Int = 0,
                @SerializedName("total")
                val total: Int = 0,
                @SerializedName("curPage")
                val curPage: Int = 0,
                @SerializedName("offset")
                val offset: Int = 0,
                @SerializedName("size")
                val size: Int = 0,
                @SerializedName("datas")
                val datas: List<DatasItem>?)


