package com.streestone.weiborelation.adapter.recyclerview

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.streestone.weiborelation.R
import kotlinx.android.synthetic.main.video_list_activity.*

class AllCategoryVideoAdapter(val resData: Map<String,List<MutileLayoutSupportBase>>,val context: Context) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return AllCategoryVideoHV(LayoutInflater.from(parent.context).inflate(R.layout.category_video_category_display,parent,false))

    }

    override fun getItemCount(): Int {
        return resData.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val view = (holder as AllCategoryVideoHV).recyclerView
        view.layoutManager = GridLayoutManager(context,2)
        view.adapter = resData.get(resData.keys.toList()[position])?.let { VideoCategoryAdapter(it) }
    }
}

class AllCategoryVideoHV: RecyclerView.ViewHolder {
    val recyclerView: RecyclerView
    constructor(itemView: View): super(itemView) {
        recyclerView = itemView.findViewById<RecyclerView>(R.id.category_display)
    }
}
