package com.streestone.cutomizedview

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.os.Build
import android.util.AttributeSet
import android.view.View
import androidx.annotation.RequiresApi
import kotlin.random.Random

class PiaChartView : View {

    private val paint = Paint()

    constructor(context: Context) : this(context, null) {}

    constructor(context: Context, attributeSet: AttributeSet?) : this(context, attributeSet, 0) {}

    constructor(context: Context, attributeSet: AttributeSet?, defStyleAtrr: Int) : super(
        context,
        attributeSet,
        defStyleAtrr
    ) {
    }

    private fun init() {
        paint.isAntiAlias = true
        paint.setColor(Color.RED)
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        drawArc(canvas)
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    private fun drawArc(canvas: Canvas?) {
        val rawData = IntArray(3) {
            java.util.Random().nextInt(50) + 20
        }
        val radius = Math.min(width, height) * 0.4
        val offset = 10
        val firstDegree = (rawData[0].toFloat() / rawData.sum().toFloat()) * 360
        canvas?.drawArc(
            (left + width / 2 - radius - offset).toFloat(),
            (top + height / 2 - radius - offset).toFloat(),
            (left + width / 2 + radius - offset).toFloat(),
            (top + height / 2 + radius - offset).toFloat(),
            -180.toFloat(),
            firstDegree, true, paint
        )

        val secondDegree = (rawData[1].toFloat() / rawData.sum().toFloat()) * 360
        paint.setColor(Color.RED)
        canvas?.drawArc(
            (left + width / 2 - radius).toFloat(),
            (top + height / 2 - radius).toFloat(),
            (left + width / 2 + radius).toFloat(),
            (top + height / 2 + radius).toFloat(),
            -180.toFloat() + firstDegree,
            secondDegree, true, paint
        )

        val thridDegree = (rawData[2].toFloat() / rawData.sum().toFloat()) * 360
        paint.setColor(Color.YELLOW)
        canvas?.drawArc(
            (left + width / 2 - radius).toFloat(),
            (top + height / 2 - radius).toFloat(),
            (left + width / 2 + radius).toFloat(),
            (top + height / 2 + radius).toFloat(),
            -180.toFloat() + firstDegree + secondDegree,
            360 - firstDegree - secondDegree, true, paint
        )

    }


}