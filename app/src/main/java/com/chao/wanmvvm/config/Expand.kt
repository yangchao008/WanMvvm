package zqx.rj.com.mvvm.common

import android.content.Context
import android.content.Intent
import android.support.v4.content.ContextCompat
import android.text.Html
import android.text.Html.FROM_HTML_MODE_COMPACT
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.chad.library.adapter.base.BaseViewHolder

/**
 * author：  HyZhan
 * created： 2018/10/11 16:33
 * desc：    扩展方法
 */

//扩展属性
var TextView.leftMargin:Int
    get():Int {
        return (layoutParams as ViewGroup.MarginLayoutParams).leftMargin
    }
    set(value) {
        (layoutParams as ViewGroup.MarginLayoutParams).leftMargin=value
    }

fun EditText.str(): String {
    return this.text.toString()
}

// 关闭软键盘
fun View.hideKeyboard() {
    val imm: InputMethodManager = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.hideSoftInputFromWindow(this.windowToken, 0)
}

// 显示软键盘
fun View.showKeyboard() {
    val imm: InputMethodManager = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    this.requestFocus()
    imm.showSoftInput(this, 0)
}

// 将 html 代码转化 为 string
// Android N（API level 24.）废弃了Html.fromHtml(String)
fun String.toHtml(): String {
    return if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
        Html.fromHtml(this, FROM_HTML_MODE_COMPACT).toString()
    } else {
        Html.fromHtml(this).toString()
    }
}

fun Context.showShortToast(msg: String) = Toast.makeText(this,msg,Toast.LENGTH_SHORT).show()

fun Context.showLongToast(msg: String) = Toast.makeText(this,msg,Toast.LENGTH_LONG).show()

fun Context.getMyColor(colorId: Int) = ContextCompat.getColor(this, colorId)

fun BaseViewHolder.setTexts(texts: List<Pair<Int,String>>){
    if (texts == null)
        return throw Exception()

    texts.forEach {
        setText(it.first,it.second)
    }
}

fun Context.startToActivity(c: Class<*>){
    startActivity(Intent(this,c))
}

//fun ImageView.loadUrl(context: Context, url: String) {
//
//    val options = RequestOptions()
//            .placeholder(R.drawable.ic_logo)
//            .error(R.drawable.ic_logo)
//            .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
//            .override(150, 200)
//
//    Glide.with(context)
//            .load(url)
//            .apply(options)
//            .into(this)
//}

