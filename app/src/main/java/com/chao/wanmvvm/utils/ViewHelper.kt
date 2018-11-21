package com.hankkin.reading.utils

import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationSet
import android.view.animation.RotateAnimation
import android.view.animation.ScaleAnimation


/**
 * Created by huanghaijie on 2018/7/10.
 */
object ViewHelper {

    /**
     * 抖动动画
     */
    fun startShakeAnim(view: View) {
        val scale = ScaleAnimation(0.95f, 1.05f, 0.95f, 1.05f)
        val rotate = RotateAnimation(-0.5f, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f)
        scale.duration = 800
        rotate.duration = 80
        rotate.repeatMode = Animation.REVERSE
        rotate.repeatCount = 5
        val animSet = AnimationSet(false)
        animSet.addAnimation(scale)
        animSet.addAnimation(rotate)
        view.startAnimation(animSet)
        animSet.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationRepeat(animation: Animation?) {
            }

            override fun onAnimationEnd(animation: Animation?) {
                animSet.cancel()
            }

            override fun onAnimationStart(animation: Animation?) {
            }
        })
    }




}