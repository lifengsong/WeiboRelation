package com.streestone.filecache

import android.util.LruCache

class FileCache <K,V>{

    val maxSize = 64 * 1024 * 1024
    val lruCache: LruCache<K,V> = LruCache(maxSize)

    fun put(k: K,v: V) {

    }

    fun get(k: K): V {
        TODO()
    }
}