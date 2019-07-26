package com.chao.wanmvvm

import android.os.Bundle
import android.webkit.WebSettings
import android.webkit.WebView
import com.chao.mvvm.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_css.*

/**
 * Date: 2019/7/26 19:28
 * Author: hans yang
 * Description:
 */
class CssFragment : BaseFragment() {

    override fun getLayoutResId(): Int {
        return R.layout.fragment_css
    }

    override fun getContentResId(): Int {
        return com.chao.mvvm.R.id.content_layout
    }

    override fun initView(state: Bundle?) {
        val webView = getViewById<WebView>(R.id.webView)
        mLoadManager.showSuccess()

        val linkCss = "<link rel=\"stylesheet\" href=\"file:///android_asset/content.css\" type=\"text/css\">"
//        val str = "<p>如果用户选择允许，那么我们的程序可以正常走下面的拍照逻辑，如果选择拒绝，我们只能不使用摄像头了，一般的操作就是取消当次操作，当然在某些特殊场景下我们有一些特殊处理，详见后文。</p>\n" +
//                "<h3>1.2 权限收回</h3>\n" +
//                "<p>一个权限被用户允许后，还可以被收回，收回权限的用户操作一共有两种：</p>\n" +
//                "<h2>1.在应用信息-权限设置页面</h2>"

        val str = "<div id=\"container\">\n" +
                "            <div id=\"header\">\n" +
                "                <div id=\"title\"><span>我的个人网站</sapn></div>\n" +
                "                <div id=\"login_or_register\">\n" +
                "                        <a href=\"\">登录</a> | <a href=\"\">注册</a>\n" +
                "                </div>\n" +
                " \n" +
                "            </div>\n" +
                " \n" +
                "            <div class=\"nav\"></div>\n" +
                " \n" +
                "            <div id=\"main\">\n" +
                "                <div id=\"left\">\n" +
                "                </div>\n" +
                " \n" +
                "                <div id=\"vertical_nav\"></div>\n" +
                " \n" +
                "                <div id=\"right\">\n" +
                "                    <div id=\"article_one\" class=\"right_article\">\n" +
                "                        <span><a href=\"http://www.baidu.com\" target=\"_blank\">第一篇文章</a></span>\n" +
                "                    </div>\n" +
                " \n" +
                "                    <div class=\"article_nav\"></div>\n" +
                " \n" +
                "                    <div id=\"article_two\" class=\"right_article\">\n" +
                " \n" +
                "                        <span><a href=\"\">第二篇文章</a></span>\n" +
                "                    </div>\n" +
                " \n" +
                "                    <div class=\"article_nav\"></div>\n" +
                " \n" +
                "                    <div id=\"article_three\" class=\"right_article\">\n" +
                " \n" +
                "                        <span><a href=\"\">第三篇文章</a></span>\n" +
                "                    </div>\n" +
                " \n" +
                "                    <div class=\"article_nav\"></div>\n" +
                " \n" +
                "                    <div id=\"article_four\"class=\"right_article\">\n" +
                " \n" +
                "                        <span><a href=\"\">第四篇文章</a></span>\n" +
                "                    </div>\n" +
                " \n" +
                "                </div>\n" +
                "            </div>\n" +
                " \n" +
                "            <div class=\"nav\"></div>\n" +
                " \n" +
                "            <div id=\"footer\">\n" +
                "                <ul>\n" +
                "                    <li>网站首页</li>\n" +
                "                    <li>关于我们</li>\n" +
                "                    <li>联系方式</li>\n" +
                "                    <li>公司简介</li>\n" +
                "                </ul>\n" +
                "            </div>\n" +
                "        </div>\n" +
                " "
        val body = ("<html><header>" + linkCss + "</header>" + str
                + "</body></html>")
        webView?.loadDataWithBaseURL(linkCss, body, "text/html", "UTF-8", null)
        val webSettings = webView.settings
        webSettings.setSupportZoom(true)
        webSettings.javaScriptEnabled = true
        webSettings.builtInZoomControls = true //设置内置的缩放控件。
    }

    override fun onStateRefresh() {
    }


}