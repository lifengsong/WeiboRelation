package com.streestone.weiborelation.adapter.recyclerview

import android.service.quicksettings.Tile
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.GridView
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.streestone.weiborelation.R
import com.streestone.weiborelation.adapter.VideoDisplayAdapter
import com.streestone.weiborelation.adapter.bean.VideoDetailInfo
import kotlinx.android.synthetic.main.category_video_title.view.*

class VideoCategoryAdapter(val data: List<MutileLayoutSupportBase>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return when (viewType) {
            R.layout.category_video_title -> CategoryVideoTitleVH(
                layoutInflater.inflate(
                    R.layout.category_video_title,
                    parent,
                    false
                )
            )
            R.layout.category_video_grid -> CategoryVideoNormalVH(
                layoutInflater.inflate(
                    R.layout.category_video_expaned_item,
                    parent,
                    false
                )
            )
            R.layout.category_video_expaned_item -> CategoryVideoExpandedVH(
                layoutInflater.inflate(
                    R.layout.category_video_expaned_item,
                    parent,
                    false
                )
            )
            else -> CategoryVideoTitleVH(
                layoutInflater.inflate(
                    R.layout.category_video_title,
                    parent,
                    false
                )
            )
        }
    }


    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = data[position]
        when (item) {
            is Title -> {
                (holder as CategoryVideoTitleVH).textView.text = item.title
            }
            is VideoGrid -> {
                (holder as CategoryVideoNormalVH).imageView.setImageResource(item.itemRes)
            }
            is SingleVideo -> {
                (holder as CategoryVideoExpandedVH).imageView.setImageResource(item.itemRes)
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        val item = data[position]
        return when (item) {
            is Title -> item.getLayout(item, position)
            is VideoGrid -> item.getLayout(item, position)
            is SingleVideo -> item.getLayout(item, position)
        }
    }

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)
        val layoutManager = recyclerView.layoutManager
        if (layoutManager is GridLayoutManager) {
            layoutManager.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
                override fun getSpanSize(position: Int): Int {
                    return when (data[position]) {
                        is Title -> 2
                        is VideoGrid -> 1
                        is SingleVideo -> 2
                    }
                }
            }
        }
    }
}

sealed class MutileLayoutSupportBase {
    open fun getLayout(dataItem: MutileLayoutSupportBase, position: Int): Int {
        return when (dataItem) {
            is Title -> R.layout.category_video_title
            is VideoGrid -> R.layout.category_video_grid
            is SingleVideo -> R.layout.category_video_expaned_item
        }
    }
}

class Title(val title: String) : MutileLayoutSupportBase() {}

class VideoGrid(val itemRes: Int) : MutileLayoutSupportBase() {}

class SingleVideo(val itemRes: Int) : MutileLayoutSupportBase() {}

class CategoryVideoVH(val view: View) : RecyclerView.ViewHolder(view) {
}

class CategoryVideoTitleVH : RecyclerView.ViewHolder {
    val textView: TextView

    constructor(itemView: View) : super(itemView) {
        textView = itemView.findViewById<TextView>(R.id.title)
    }
}

class CategoryVideoNormalVH : RecyclerView.ViewHolder {
    val imageView: ImageView

    constructor(itemView: View) : super(itemView) {
        imageView = itemView.findViewById<ImageView>(R.id.video_expaned)
    }
}

class CategoryVideoExpandedVH : RecyclerView.ViewHolder {
    val imageView: ImageView

    constructor(itemView: View) : super(itemView) {
        imageView = itemView.findViewById<ImageView>(R.id.video_expaned)
    }
}