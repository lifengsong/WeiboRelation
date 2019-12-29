package com.streestone.filecache.imageloader

import android.content.Context
import android.os.Handler
import android.os.Looper
import android.os.Message
import java.util.concurrent.LinkedBlockingQueue
import java.util.concurrent.ThreadFactory
import java.util.concurrent.ThreadPoolExecutor
import java.util.concurrent.TimeUnit
import java.util.concurrent.atomic.AtomicInteger

class ImageLoader {
    companion object {
        private val TAG = "ImageLoader"
        val MESSAGE_POST_RESULT = 1

        //线程池配置参数
        private val CPU_COUNT = Runtime.getRuntime().availableProcessors()
        private val CORE_POOL_SIZE = CPU_COUNT + 1
        private val MAXIMUM_POOL_SIZE = CPU_COUNT * 2 + 1
        private val KEEP_ALIVE = 10L

        private val TAG_KEY_URI = "imageloader_uri"
        private val DISK_CACHE_SIZE = 1024 * 1024 * 50
        private val IO_BUFFER_SIZE = 8 * 1024
        private val DISK_CACHE_INDEX = 0

        private val sThreadFactory = object : ThreadFactory {
            private val mCount = AtomicInteger(1)

            override fun newThread(r: Runnable): Thread {
                return Thread(r,"ImageLoader#" + mCount.getAndIncrement())
            }
        }

        val THREAD_POOL_EXECUTOR = ThreadPoolExecutor(
            CORE_POOL_SIZE,
            MAXIMUM_POOL_SIZE,
            KEEP_ALIVE,
            TimeUnit.SECONDS,LinkedBlockingQueue<Runnable>(),
            sThreadFactory
        )
    }

    private var mIsDiskLruCacheCreated = false
    private val mMainHandler = object :Handler(Looper.getMainLooper()) {
        override fun handleMessage(msg: Message) {

        }
    }

    private lateinit var context: Context

}