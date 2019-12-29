package com.streestone.filecache.imageloader

import android.content.res.Resources
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Log
import java.io.FileDescriptor

/*
*获取期望大小的Bitmap
*/
class ImageResizer {

    val TAG = "ImageResizer"

    /**
     * 通过资源ID加载指定大小的图片
     * @param res
     * @param resId 图片资源ID
     * @param reqWidth 图片的期望宽度
     * @param reqHeight 图片的期望高度
     * @return 返回期望大小的Bitmap
     */
    fun decodeSampledBitmapFromResource(
        res: Resources,
        resId: Int,
        reqWidth: Int,
        reqHeight: Int
    ): Bitmap {
        val options = BitmapFactory.Options()
        options.inJustDecodeBounds = true
        BitmapFactory.decodeResource(res, resId, options)

        options.inSampleSize = calculateInSampleSize(options, reqWidth, reqHeight)
        options.inJustDecodeBounds = false
        return BitmapFactory.decodeResource(res, resId, options)
    }

    /**
     * 通过文件fd加载指定大小的图片
     * @param fd 图片文件描述符
     * @param reqWidth 图片的期望宽度
     * @param reqHeight 图片的期望高度
     * @return 返回期望大小的Bitmap
     */
    fun decodeSampleBitmapFromFileDescriptor(
        fd: FileDescriptor,
        reqWidth: Int,
        reqHeight: Int
    ): Bitmap {
        val options = BitmapFactory.Options()
        options.inJustDecodeBounds = true
        BitmapFactory.decodeFileDescriptor(fd, null, options)
        options.inSampleSize = calculateInSampleSize(options, reqWidth, reqHeight)
        options.inJustDecodeBounds = false
        return BitmapFactory.decodeFileDescriptor(fd, null, options)
    }

    /**
     * 通过Byte数组加载指定大小的图片
     * @param res
     * @param data 图片的Byte数组表示
     * @param reqWidth 图片的期望宽度
     * @param reqHeight 图片的期望高度
     * @return 返回期望大小的Bitmap
     */
    fun decodeSampleBitmapFromBytes(data: ByteArray, reqWidth: Int, reqHeight: Int): Bitmap {
        val options = BitmapFactory.Options()
        options.inJustDecodeBounds = true
        BitmapFactory.decodeByteArray(data, 0, data.size, options)
        options.inJustDecodeBounds = true
        options.inSampleSize = calculateInSampleSize(options, reqWidth, reqHeight)
        return BitmapFactory.decodeByteArray(data, 0, data.size, options)
    }

    /**
     * 计算图片的缩放比例
     * @param options
     * @param reqWidth 图片的期望宽度
     * @param reqHeight 图片的期望高度
     * @return 返回期望缩放比例
     */
    fun calculateInSampleSize(options: BitmapFactory.Options, reqWidth: Int, reqHeight: Int): Int {
        if (reqWidth == 0 || reqHeight == 0) {
            return 1
        }

        val height = options.outHeight
        val width = options.outWidth
        Log.d(TAG, "origin, w=" + width + " h=" + height)
        var inSampleSize = 1

        if (height > reqHeight || width > reqWidth) {
            val halfHeight = height / 2
            val halfWidth = width / 2

            while ((halfHeight / inSampleSize) >= reqHeight && (halfWidth / inSampleSize) >= reqWidth) {
                inSampleSize *= 2
            }
        }

        Log.d(TAG, "origin, w=" + width + " h=" + height)
        return inSampleSize
    }
}