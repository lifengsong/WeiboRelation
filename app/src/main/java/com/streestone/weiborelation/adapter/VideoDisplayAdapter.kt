package com.streestone.weiborelation.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.streestone.weiborelation.R
import com.streestone.weiborelation.adapter.bean.VideoDetailInfo

class VideoDisplayAdapter(val videoInfos: List<VideoDetailInfo>) : BaseAdapter() {

    override fun getItem(position: Int): Any {
        return videoInfos[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getCount(): Int {
        return videoInfos.size
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val info = videoInfos.get(position)
        if (convertView == null) {
            val hold = ViewHold()
            val itmeView = LayoutInflater.from(parent?.context).inflate(R.layout.video_list_itme,parent,false)
            hold.videoTumb = itmeView.findViewById(R.id.video_tumb)
            hold.timeAndSize = itmeView.findViewById(R.id.video_time_length_and_size)
            hold.name = itmeView.findViewById(R.id.video_name)
            hold.start = itmeView.findViewById(R.id.video_start)
            hold.run {
                videoTumb?.setImageResource(info.bitmapRes)
                timeAndSize?.setText(info.time)
                name?.setText(info.name)
            }
            itmeView.setTag(hold)
            return itmeView
        } else {
            val hold = convertView.getTag() as ViewHold
            hold.run {
                videoTumb?.setImageResource(info.bitmapRes)
                timeAndSize?.setText(info.time)
                name?.setText(info.name)
            }
            return convertView
        }
    }
}

class ViewHold {
    var videoTumb: ImageView? = null
    var timeAndSize: TextView? = null
    var name: TextView? = null
    var start: ImageView? = null
}