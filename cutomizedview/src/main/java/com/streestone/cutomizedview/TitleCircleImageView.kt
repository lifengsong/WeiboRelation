package com.streestone.cutomizedview

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.*
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.util.Log
import com.bumptech.glide.load.resource.drawable.DrawableDecoderCompat
import de.hdodenhof.circleimageview.CircleImageView
import kotlin.math.min
import kotlin.math.sqrt


class TitleCircleImageView : CircleImageView {
    val paint = Paint(Paint.ANTI_ALIAS_FLAG)
    var title: String = "佚名"
    val TAG = TitleCircleImageView::class.java.simpleName
    private var textIsDraw = false

    constructor(context: Context) : this(context, null) {}

    constructor(context: Context, attr: AttributeSet?) : this(context, attr!!, 0) {}

    constructor(context: Context, attr: AttributeSet, defStyleAttr: Int) : super(
        context,
        attr,
        defStyleAttr
    ) {
        paint.setColor(Color.RED)
        paint.textSize = 3.0f
        val typedArray =
            context.obtainStyledAttributes(attr, R.styleable.TitleCircleImageView, defStyleAttr, 0)
        if (typedArray.hasValue(R.styleable.TitleCircleImageView_tciv_title))
            title = typedArray.getString(R.styleable.TitleCircleImageView_tciv_title).toString()
        typedArray.recycle()
        textIsDraw = false
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        if (!textIsDraw) {
            textIsDraw = true
            drawText()
        }
        /*title?.let {
            canvas?.drawText(
                it,
                0,
                it.length - 1,
                baseLine.first.toFloat(),
                baseLine.second.toFloat(),
                paint
            )
        }*/
    }

    private fun drawText() {
        val xStart = min(width,height) / 2
        val yEnd = height / 3 * 2
        val len = title?.length
        val baseLine = Pair(
            xStart,
            yEnd
        )

        Log.d(TAG,"baseLine:" + baseLine.first + "++" + baseLine.second + title)
        val textWidth = Paint().measureText(title) / 2
        setImageBitmap(drawTextToBitmap(drawable,title,baseLine.first.toFloat() - textWidth,baseLine.second.toFloat()))
    }
}
