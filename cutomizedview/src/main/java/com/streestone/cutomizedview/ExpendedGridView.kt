package com.streestone.cutomizedview

import android.annotation.SuppressLint
import android.content.Context
import android.os.Build
import android.util.AttributeSet
import android.util.Log
import android.view.View
import android.widget.AbsListView.LayoutParams.MATCH_PARENT
import android.widget.GridView
import androidx.annotation.RequiresApi
import androidx.core.view.children

class ExpendedGridView : GridView {

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    constructor(context: Context):this(context,null)

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    constructor(context: Context,
                attributeSet: AttributeSet?): this(context,attributeSet!!,0)

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    constructor(
        context: Context,
        attributeSet: AttributeSet,
        defStyleAttr: Int) : this(context, attributeSet, defStyleAttr, 0) {

    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    constructor(
        context: Context,
        attributeSet: AttributeSet,
        defStyleAttr: Int,
        defStyleRes: Int
    ) : super(context, attributeSet, defStyleAttr, defStyleRes)

    @SuppressLint("Range")
    override fun measureChildren(widthMeasureSpec: Int, heightMeasureSpec: Int) {
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {

        //Log.d("FFFFFFFFFF",MeasureSpec.getMode(heightMeasureSpec).toString())

        val height = MeasureSpec.makeMeasureSpec(Int.MAX_VALUE shr 2,MeasureSpec.AT_MOST)

        super.onMeasure(widthMeasureSpec, height)
    }
}