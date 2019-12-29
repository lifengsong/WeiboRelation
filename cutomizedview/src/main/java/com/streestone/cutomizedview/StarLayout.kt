package com.streestone.cutomizedview

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.icu.text.CaseMap
import android.util.AttributeSet
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.children
import com.awaker.imageloader.ImageLoader
import de.hdodenhof.circleimageview.CircleImageView
import kotlin.math.cos
import kotlin.math.log
import kotlin.math.sin

class StarLayout : ConstraintLayout {
    val TAG = StarLayout::class.java.simpleName
    val paint = Paint(Paint.ANTI_ALIAS_FLAG)
    var childCount: Int? = null

    private val hPadding = 20
    private val vPading = hPadding * 2
    private val centerRadius = 100

    val starBeans = ArrayList<StarBean>()

    constructor(context: Context) : this(context, null) {}

    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0) {}

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        setWillNotDraw(false)
        paint.setColor(Color.RED)
        paint.strokeWidth = 5.0f
    }

    override fun dispatchDraw(canvas: Canvas?) {
        super.dispatchDraw(canvas)

    }
    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        childCount = getChildCount()
        Log.d(TAG,"ChildCount:" + childCount)

        if (childCount == 0 || childCount == 1)
            return
        val centerView = children.first() as CircleImageView
        val secondView = children.last() as CircleImageView
        val layoutParams = secondView.layoutParams as ConstraintLayout.LayoutParams
        val radius = centerView.width / 2.0f

        val textView = TextView(context)
        //textView.text =

        val startrPoint = Pair(
            centerView.x + radius + cos(layoutParams.circleAngle) * radius,
            centerView.y + radius - sin(layoutParams.circleAngle) * radius
        )

        val endPoint = Pair(
            secondView.x + radius - cos(layoutParams.circleAngle) * radius,
            secondView.y + radius + sin(layoutParams.circleAngle) * radius
        )

        canvas?.drawLine(
            startrPoint.first,
            startrPoint.second,
            endPoint.first,
            endPoint.second,
            paint
        )
    }

    fun setDatasource(starBeans: ArrayList<StarBean>) {
        if (starBeans.size == 0) {
            return
        }
        this.starBeans.addAll(starBeans)

        invalidate()
    }

    private fun layoutChildren() {
        val starBean = starBeans[0]
        val centerView = CircleImageView(context)
        this.starBeans.forEach {
        }
    }
}



