package com.chao.wanmvvm.theme

/**
 * Date: 2018/11/19 18:32
 * Author: hans yang
 * Description:
 */
object TextSizeHelper{

    enum class TextSizeLevel{
        LEVEL_BASE5,
        LEVEL_BASE4,
        LEVEL_BASE3,
        LEVEL_BASE2,
        LEVEL_BASE,
        LEVEL_2,
        LEVEL_3,
        LEVEL_4,
        LEVEL_5,
        LEVEL_6,
        LEVEL_7,
        LEVEL_8,
        LEVEL_9,
        LEVEL_10
    }

    var currencyTextSize = 16

    val textSizeList = lazy {
        listOf(30,24,20,18,17,16,15,14,13,12,10)
    }

    fun getTextSize(level: TextSizeLevel) : Int{

        return when(level){
            TextSizeLevel.LEVEL_BASE5 -> currencyTextSize + 10
            TextSizeLevel.LEVEL_BASE4 -> currencyTextSize + 6
            TextSizeLevel.LEVEL_BASE3 -> currencyTextSize + 4
            TextSizeLevel.LEVEL_BASE2 -> currencyTextSize + 2
            TextSizeLevel.LEVEL_BASE -> currencyTextSize
            TextSizeLevel.LEVEL_2 -> currencyTextSize - 2
            TextSizeLevel.LEVEL_3 -> currencyTextSize - 4
            TextSizeLevel.LEVEL_4 -> currencyTextSize - 6
            TextSizeLevel.LEVEL_5 -> currencyTextSize - 7
            TextSizeLevel.LEVEL_6 -> currencyTextSize - 8
            TextSizeLevel.LEVEL_7 -> currencyTextSize - 9
            TextSizeLevel.LEVEL_8 -> currencyTextSize - 10
            TextSizeLevel.LEVEL_9 -> currencyTextSize - 11
            TextSizeLevel.LEVEL_10 -> currencyTextSize - 12
            else -> currencyTextSize
        }
    }
}