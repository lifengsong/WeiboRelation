package com.streestone.cutomizedview

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.*
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable

/**
 * 写一个drawble转BitMap的方法
 */
@SuppressLint("NewApi")
fun drawableToBitmap(drawable: Drawable): Bitmap {
    if (drawable is BitmapDrawable) {
        return drawable.getBitmap()
    }
    val w = drawable.getIntrinsicWidth()
    val h = drawable.getIntrinsicHeight()
    val bitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888)
    val canvas = Canvas(bitmap)
    drawable.setBounds(0, 0, w, h)
    drawable.draw(canvas)
    return bitmap
}

/**
 * 绘制文字到图片上
 */
fun drawTextToBitmap(drawable: Drawable,text: String, xStart: Float, yEnd: Float): Bitmap {
    val bitmap = drawableToBitmap(drawable).copy(Bitmap.Config.ARGB_8888, true)
    val canvas = Canvas(bitmap)
    val p = Paint();
    p.setColor(Color.RED)//设置画笔颜色
    p.setAntiAlias(true)//抗锯齿
    p.setTextSize(20.toFloat())//设置字体大小
    p.typeface = Typeface.MONOSPACE
    canvas.drawText(text, xStart, yEnd, p)
    return bitmap
}

/**
 * 将px值转换为dip或dp值
 * @param pxValue
 * @return
 */
fun px2dip(context: Context, pxValue: Float): Int {
    val scale = context.resources.displayMetrics.density
    return (pxValue / scale + 0.5f).toInt()
}

/**
 * 将dip或dp值转换为px值
 *
 * @param dipValue
 * @return
 */
fun dip2px(context: Context, dipValue: Float): Int {
    val scale = context.resources.displayMetrics.density
    return (dipValue * scale + 0.5f).toInt()
}

/**
 * 将px值转换为sp值
 * @param pxValue
 * @return
 */
fun px2sp(context: Context, pxValue: Float): Int {
    val fontScale = context.resources.displayMetrics.scaledDensity
    return (pxValue / fontScale + 0.5f).toInt()
}

/**
 * 将sp值转换为px值
 * @param spValue
 * @return
 */
fun sp2px(context: Context, spValue: Float): Int {
    val fontScale = context.resources.displayMetrics.scaledDensity
    return (spValue * fontScale + 0.5f).toInt()
}

/**
 * 返回屏幕宽度单位是像素
 * @param context
 * @return
 */
fun displayWidth(context: Context): Int {
    return context.resources.displayMetrics.widthPixels
}

/**
 * 返回屏幕高度单位是像素
 * @param context
 * @return
 */
fun displayHeight(context: Context): Int {
    return context.resources.displayMetrics.heightPixels
}

/**
 * 返回屏幕宽度单位是dip
 * @param context
 * @return
 */
fun displayDipWidth(context: Context): Int {
    return px2dip(context,context.resources.displayMetrics.widthPixels.toFloat())
}

/**
 * 返回屏幕高度单位是dip
 * @param context
 * @return
 */
fun displayDipHeight(context: Context): Int {
    return px2dip(context,context.resources.displayMetrics.heightPixels.toFloat())
}
