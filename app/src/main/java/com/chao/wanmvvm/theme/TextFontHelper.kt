package com.chao.wanmvvm.theme

import android.graphics.Typeface
import com.chao.wanmvvm.App

/**
 * Date: 2018/11/19 18:32
 * Author: hans yang
 * Description:
 */
object TextFontHelper{

    enum class TextFontLevel{
        LEVEL_BASE5,
        LEVEL_BASE4,
        LEVEL_BASE3,
        LEVEL_BASE2,
        LEVEL_BASE,
    }

    fun getTextFont(level: TextFontLevel) : Typeface{
        val typeface = Typeface.createFromAsset(App.instance().assets, "huawencaiyunv.TTF")
        var resources = App.instance().resources
        return typeface
//        return when(level){
//            TextFontLevel.LEVEL_BASE5 -> currencyTextSize + 10
//            TextFontLevel.LEVEL_BASE4 -> currencyTextSize + 6
//            TextFontLevel.LEVEL_BASE3 -> currencyTextSize + 4
//            TextFontLevel.LEVEL_BASE2 -> currencyTextSize + 2
//            TextFontLevel.LEVEL_BASE -> currencyTextSize
//            else -> currencyTextSize
//        }
    }
}