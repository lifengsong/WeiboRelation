package com.streestone.cutomizedview

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Typeface
import android.os.Build
import android.os.Message
import android.util.AttributeSet
import android.util.Log
import android.widget.ImageView
import androidx.annotation.RequiresApi
import cn.hutool.core.date.DateTime
import java.lang.StringBuilder
import java.time.LocalDate
import java.time.LocalTime
import java.util.*

class ClockPanelView : ImageView {

    private val TAG = ClockPanelView::class.java.simpleName

    private val paint = Paint()
    private var hour = Int.MAX_VALUE
    private var minute = Int.MAX_VALUE
    private var second = Int.MAX_VALUE
    private val handler = object : android.os.Handler() {
        override fun handleMessage(msg: Message) {
            invalidate()
        }
    }

    constructor(context: Context) : this(context, null) {}

    constructor(context: Context, attributeSet: AttributeSet?) : this(context, attributeSet, 0) {}

    constructor(context: Context, attributeSet: AttributeSet?, defStyleAtrr: Int) : super(
        context,
        attributeSet,
        defStyleAtrr
    ) {
        init()
    }

    private fun init() {
        paint.isAntiAlias = true
        paint.strokeWidth = 5.toFloat()
        paint.setColor(Color.RED)
        paint.style = Paint.Style.STROKE
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        drawClockPanel(canvas)
        drawCenter(canvas)
        drawTimePoint(canvas)
        handler.sendEmptyMessageDelayed(0,1000)
    }

    private fun drawClockPanel(canvas: Canvas?) {
        val radius = Math.min(width, height).toFloat() / 2
        canvas?.drawCircle(width.toFloat() / 2, height.toFloat() / 2, radius, paint)
        val stoneTime = radius / 5
        val normalTime = radius / 7
        val startX = left + width.toFloat() / 2
        val startY = top + height.toFloat() / 2 - radius

        IntRange(0, 11).forEach {
            if (it % 3 == 0) {
                canvas?.drawLine(startX, startY, startX, startY + stoneTime, paint)
            } else {
                canvas?.drawLine(startX, startY, startX, startY + normalTime, paint)
            }
            canvas?.rotate(30.toFloat(), width.toFloat() / 2, height.toFloat() / 2)
        }
    }

    private fun drawCenter(canvas: Canvas?) {
        paint.style = Paint.Style.FILL
        canvas?.drawCircle(width.toFloat() / 2, height.toFloat() / 2, 20.toFloat(), paint)
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    private fun drawTimePoint(canvas: Canvas?) {
        paint.style = Paint.Style.STROKE
        val radius = Math.min(width, height) / 2
        val hWidth = 8
        val hLength = radius * 0.6
        val mWidth = 5
        val mLenght = hLength * 1.2
        val sWidth = 3
        val sLenght = mLenght * 1.2

        val time = DateTime(System.currentTimeMillis())
        val hour = time.hour(true)
        val minute = time.minute()
        val second = time.second()

        Log.d(TAG, "hour:" + hour + "minute:" + minute + "second:" + second)

        if (true) {
            paint.strokeWidth = sWidth.toFloat()
            val degree = 360 * second / 60
            canvas?.save()
            canvas?.rotate(degree.toFloat(),width.toFloat() / 2, height.toFloat() / 2)
            canvas?.drawLine(
                width.toFloat() / 2,
                height.toFloat() / 2,
                width.toFloat() / 2,
                (height.toFloat() - sLenght).toFloat(),
                paint
            )
            canvas?.restore()
        }

        if (true) {
            paint.strokeWidth = mWidth.toFloat()
            this.minute = minute
            val degree = 360 * minute / 60
            canvas?.save()
            canvas?.rotate(degree.toFloat(),width.toFloat() / 2, height.toFloat() / 2)
            canvas?.drawLine(
                width.toFloat() / 2,
                height.toFloat() / 2,
                width.toFloat() / 2,
                (height.toFloat() / 2 - mLenght).toFloat(),
                paint
            )
            canvas?.restore()
        }

        if (true) {
            paint.strokeWidth = hWidth.toFloat()
            this.hour = hour
            val degree = 360 * (hour % 12) / 12
            canvas?.save()
            canvas?.rotate(degree.toFloat(),width.toFloat() / 2, height.toFloat() / 2)
            canvas?.drawLine(
                width.toFloat() / 2,
                height.toFloat() / 2,
                width.toFloat() / 2,
                (height.toFloat() / 2 - hLength).toFloat(),
                paint
            )
            canvas?.restore()
        }

        paint.textSize = 80.toFloat()
        paint.typeface = Typeface.MONOSPACE

        val timeString = String.format("%02d:%02d:%02d",hour % 12,minute,second)
        val textOffset = paint.measureText(timeString) / 2
        val textStart = width / 2 - textOffset
        val textEnd = width / 2 + textOffset
        val y = height / 2 + radius / 2
        canvas?.drawText(timeString,0,timeString.length,textStart.toFloat(),y.toFloat(),paint)

        paint.style = Paint.Style.FILL
        val arcTop = height.toFloat() / 2 - radius.toFloat()
        canvas?.drawArc(left.toFloat(),arcTop,
            (left + width).toFloat(),arcTop + radius * 2,0.toFloat(),30.toFloat(),true,paint)
        paint.style = Paint.Style.STROKE
        //canvas?.drawArc()
    }
}