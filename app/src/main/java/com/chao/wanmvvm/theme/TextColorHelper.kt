package com.chao.wanmvvm.theme

import com.chao.wanmvvm.App
import com.chao.wanmvvm.R

/**
 * Date: 2018/11/19 18:32
 * Author: hansyang
 * Description:
 */
object TextColorHelper{

    enum class TextColorLevel{
        LEVEL_NIGHT,
        LEVEL_BASE,
        LEVEL_2,
        LEVEL_3,
        LEVEL_4,
        LEVEL_5,
        LEVEL_6
    }

    fun getTextColor(level: TextColorLevel) : Int{

        var resources = App.instance().resources
        return when(level){
            TextColorLevel.LEVEL_NIGHT -> resources.getColor(R.color.color_aaaaaa)
            TextColorLevel.LEVEL_BASE -> resources.getColor(R.color.black_000000)
            TextColorLevel.LEVEL_2 -> resources.getColor(R.color.color_333333)
            TextColorLevel.LEVEL_3 -> resources.getColor(R.color.color_666666)
            TextColorLevel.LEVEL_4 -> resources.getColor(R.color.color_999999)
            TextColorLevel.LEVEL_5 -> resources.getColor(R.color.color_aaaaaa)
            TextColorLevel.LEVEL_6 -> resources.getColor(R.color.black_e8e8e8)
            else -> resources.getColor(R.color.color_333333)
        }
    }
}