package com.awaker.imageloader

import android.graphics.drawable.Drawable
import android.widget.ImageView

import com.bumptech.glide.request.RequestListener

interface IImageLoader {

    fun load(imageView: ImageView, url: Int)

    fun load(imageView: ImageView, url: String, listener: RequestListener<Drawable>)

    fun load(imageView: ImageView, resId: Int, listener: RequestListener<Drawable>)

    fun loadThumb(imageView: ImageView, url: String)

    fun loadCropCircle(imageView: ImageView, url: String)

    fun loadCropCircle(imageView: ImageView, resId: Int)
}
