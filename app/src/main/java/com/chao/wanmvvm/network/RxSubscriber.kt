package com.chao.wanmvvm.network

import com.chao.mvvm.util.NetworkUtils
import com.chao.wanmvvm.App
import com.google.gson.JsonParseException
import io.reactivex.subscribers.DisposableSubscriber
import org.json.JSONException
import retrofit2.HttpException
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

/**
 * @author tqzhang
 */
abstract class RxSubscriber<T> : DisposableSubscriber<T>() {

    override fun onStart() {
        super.onStart()
        showLoading()
        if (!NetworkUtils.isNetworkAvailable(App.instance())) {
            onNoNetWork()
            cancel()
            return
        }
    }

    override fun onComplete() {

    }

    fun showLoading() {

    }

    fun onNoNetWork() {

    }

    override fun onError(e: Throwable) {
        var message: String? = null
        if (e is UnknownHostException) {
            message = "没有网络"
        } else if (e is HttpException) {
            message = "网络错误"
        } else if (e is SocketTimeoutException) {
            message = "网络连接超时"
        } else if (e is JsonParseException || e is JSONException) {
            message = "解析错误"
        } else if (e is ConnectException) {
            message = "连接失败"
        } else if (e is ServerException) {
            message = e.message
        }
        onFailure(message)
    }

    override fun onNext(t: T) {
        onSuccess(t)
    }

    /**
     * success
     *
     * @param t
     */
    abstract fun onSuccess(t: T)

    /**
     * failure
     *
     * @param msg
     */
    abstract fun onFailure(msg: String?)
}
