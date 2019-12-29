package com.streestone.weiborelation.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.streestone.weiborelation.adapter.bean.VideoDetailInfo

class RVVideoAdapter(val data: ArrayList<VideoDetailInfo>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        TODO()
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
    }
}

class CategoryVideoVH(val view: View) : RecyclerView.ViewHolder(view) {
}