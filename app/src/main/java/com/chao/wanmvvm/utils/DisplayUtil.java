package com.chao.wanmvvm.utils;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.WindowManager;
import com.chao.wanmvvm.App;

public class DisplayUtil {

    private static WindowManager windowManager;

    private static WindowManager getWindowManager() {
        if (windowManager == null) {
            windowManager = (WindowManager) App.Companion.getMInstance().getSystemService(Context.WINDOW_SERVICE);
        }
        return windowManager;
    }

    // 根据手机的分辨率将dp的单位转成px(像素)
    public static int dip2px(float dpValue) {
        final float scale = App.Companion.getMInstance().getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    // 根据手机的分辨率将px(像素)的单位转成dp
    public static int px2dip(float pxValue) {
        final float scale = App.Companion.getMInstance().getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

    // 将px值转换为sp值
    public static int px2sp(float pxValue) {
        final float fontScale = App.Companion.getMInstance().getResources().getDisplayMetrics().scaledDensity;
        return (int) (pxValue / fontScale + 0.5f);
    }

    // 将sp值转换为px值
    public static int sp2px(float spValue) {
        final float fontScale = App.Companion.getMInstance().getResources().getDisplayMetrics().scaledDensity;
        return (int) (spValue * fontScale + 0.5f);
    }

    // 屏幕宽度（像素）
    public static int getScreenWidth() {
        DisplayMetrics metric = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metric);
        return metric.widthPixels;
    }

    // 屏幕高度（像素）
    public static int getScreenHeight() {
        DisplayMetrics metric = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metric);
        return metric.heightPixels;
    }

    public static float getScreenDensity() {
        try {
            DisplayMetrics displayMetrics = new DisplayMetrics();
            getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
            return displayMetrics.density;
        } catch (Throwable e) {
            e.printStackTrace();
            return 1.0f;
        }
    }

    public static int getDensityDpi() {
        try {
            DisplayMetrics displayMetrics = new DisplayMetrics();
            getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
            return displayMetrics.densityDpi;
        } catch (Throwable e) {
            e.printStackTrace();
            return 320;
        }
    }
}
