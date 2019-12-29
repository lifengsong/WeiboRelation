package com.streestone.weiborelation.adapter.bean

data class AllVideoDetailInfo(
    val time: String,
    val count: Int,
    val map: HashMap<String, ArrayList<VideoDetailInfo>>
)