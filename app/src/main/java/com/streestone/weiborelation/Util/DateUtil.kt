package com.streestone.weiborelation.Util

import android.annotation.TargetApi
import android.icu.util.LocaleData
import android.os.Build
import androidx.annotation.RequiresApi
import java.time.LocalDate
import java.util.*
import java.util.stream.Stream
import kotlin.collections.ArrayList

@RequiresApi(Build.VERSION_CODES.O)
fun rangeTo(start: LocalDate, length: Int): Sequence<LocalDate> {
    var start = 0
    val date = LocalDate.now().minusDays(length.toLong())
    val dates = generateSequence(0) { it + 1 }.takeWhile { it <= length }.map { date.plusDays(it.toLong()) }
    return dates
}