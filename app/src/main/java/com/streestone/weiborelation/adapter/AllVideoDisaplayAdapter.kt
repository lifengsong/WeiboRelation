package com.streestone.weiborelation.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.GridView
import android.widget.ImageView
import android.widget.TextView
import com.streestone.weiborelation.R
import com.streestone.weiborelation.adapter.bean.VideoDetailInfo
import java.util.*

class AllVideoDisplayAdapter(val allVideoInfos: SortedMap<String,List<VideoDetailInfo>>) : BaseAdapter() {

    override fun getItem(position: Int): List<VideoDetailInfo>? {
        return allVideoInfos[allVideoInfos.keys.toList()[position]]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getCount(): Int {
        return allVideoInfos.size
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val key = allVideoInfos.keys.toList()[position]
        val info = allVideoInfos[key]
        if (convertView == null) {
            val hold = AllViewHold()
            val itmeView = LayoutInflater.from(parent?.context).inflate(R.layout.all_video_list_item,parent,false)
            hold.time = itmeView.findViewById(R.id.video_time)
            hold.count = itmeView.findViewById(R.id.video_count)
            hold.videos = itmeView.findViewById(R.id.video_display_list) as GridView

            hold.run {
                time?.text = key
                count?.text = info?.size.toString()
                val adapter = info?.let { VideoDisplayAdapter(it) }
                videos?.adapter = adapter
               /* val newHeigh = videos?.layoutParams?.height?.times(((info?.size?.div(2))?.plus(1)!!))
                videos?.layoutParams?.height = newHeigh
                videos?.requestLayout()*/

            }
            itmeView.setTag(hold)
            return itmeView
        } else {
            val hold = convertView.getTag() as AllViewHold
            hold.run {
                time?.text = key
                count?.text = info?.size.toString()
                val adapter = info?.let { VideoDisplayAdapter(it) }
                videos?.adapter = adapter
                adapter?.notifyDataSetChanged()
                /*val newHeigh = videos?.layoutParams?.height?.times(((info?.size?.div(2))?.plus(1)!!))
                videos?.layoutParams?.height = newHeigh
                videos?.requestLayout()*/
            }
            return convertView
        }
    }
}

class AllViewHold {
    var time: TextView? = null
    var count: TextView? = null
    var videos: GridView? = null
}