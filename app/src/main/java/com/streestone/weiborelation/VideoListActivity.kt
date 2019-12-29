package com.streestone.weiborelation

import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.MotionEvent
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.tabs.TabLayout
import com.streestone.cutomizedview.displayHeight
import com.streestone.weiborelation.adapter.recyclerview.*
import kotlinx.android.synthetic.main.video_list_activity.*

class VideoListActivity : AppCompatActivity() {

    var isRecyclerScroll = false
    var lastPos = 0
    var scrollToPosition = 0;
    var canScroll = true;

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.video_list_activity)
        val view = findViewById(android.R.id.content) as ViewGroup
        view.setBackgroundColor(Color.TRANSPARENT)

        //initData()
        init()
        initListener()
        Log.d("TAG","height:" + displayHeight(this) + "statusBar:" +
                (supportActionBar?.height ?: 0) + "tab:" + video_title.height )
    }

    @RequiresApi(Build.VERSION_CODES.O)
    /*  private fun initData() {
          val bitMap = BitmapFactory.decodeResource(resources, R.mipmap.liu_yi_fei)
          val map = TreeMap<String, Int>()
          val videos = TreeMap<String, List<VideoDetailInfo>>()
          rangeTo(LocalDate.now(), 10).forEach {
              map.put(
                  it.format(DateTimeFormatter.ISO_DATE).toString(),
                  Random().nextInt(5) + 1
              )
          }
          map.forEach { t, u ->
              val infos = generateSequence(0) { it + 1 }.takeWhile { it <= u }
                  .map { VideoDetailInfo(bitMap, t, bitMap.byteCount.toString(), "刘亦菲") }.toList()
              videos.put(t, infos)
          }
          video_list.adapter = AllVideoDisplayAdapter(videos)
      }
  */
    private fun init() {
        val map = LinkedHashMap<String, List<MutileLayoutSupportBase>>()
        video_title.tabMode = TabLayout.MODE_SCROLLABLE
        IntRange(1, 10).forEach {
            val data = productList()
            val tab = video_title.newTab().setText(it.toString() + "Test")
            video_title?.addTab(tab)
            map.put(it.toString(), data)
        }

        val layoutManager = LinearLayoutManager(this).also {
            it.orientation = LinearLayoutManager.VERTICAL
        }


        video_list.adapter = AllCategoryVideoAdapter(map, this)
        video_list.layoutManager = layoutManager

    }

    private fun initListener() {
        video_title.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabReselected(p0: TabLayout.Tab?) {
            }

            override fun onTabUnselected(p0: TabLayout.Tab?) {
            }

            override fun onTabSelected(p0: TabLayout.Tab?) {
                isRecyclerScroll = false
                //video_list.smoothScrollToPosition(p0?.position!!)
                moveToPosition(
                    video_list.layoutManager as LinearLayoutManager,
                    video_list,
                    p0?.position!!
                )
            }
        })

        video_list.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                if (isRecyclerScroll) {
                    //第一个可见的view的位置，即tablayou需定位的位置
                    val position =
                        (video_list.layoutManager as LinearLayoutManager).findFirstVisibleItemPosition();
                    if (lastPos != position) {
                        video_title.setScrollPosition(position, 0F, true);
                    }
                    lastPos = position;
                }
            }
        })

        video_list.setOnTouchListener { v, event ->
            if (event.getAction() == MotionEvent.ACTION_DOWN) {
                isRecyclerScroll = true;
            }
            return@setOnTouchListener false;
        }
    }

    private fun moveToPosition(
        manager: LinearLayoutManager,
        recyclerView: RecyclerView,
        position: Int
    ) {
        // 第一个可见的view的位置
        val firstItem = manager.findFirstVisibleItemPosition();
        // 最后一个可见的view的位置
        val lastItem = manager.findLastVisibleItemPosition();
        if (position <= firstItem) {
            // 如果跳转位置firstItem 之前(滑出屏幕的情况)，就smoothScrollToPosition可以直接跳转，
            recyclerView.smoothScrollToPosition(position);
        } else if (position <= lastItem) {
            // 跳转位置在firstItem 之后，lastItem 之间（显示在当前屏幕），smoothScrollBy来滑动到指定位置
            val top = recyclerView.getChildAt(position - firstItem).getTop();
            recyclerView.smoothScrollBy(0, top);
        } else {
            // 如果要跳转的位置在lastItem 之后，则先调用smoothScrollToPosition将要跳转的位置滚动到可见位置
            // 再通过onScrollStateChanged控制再次调用当前moveToPosition方法，执行上一个判断中的方法
            recyclerView.smoothScrollToPosition(position);
            scrollToPosition = position;
            canScroll = true;
        }
    }

    private fun productList(): ArrayList<MutileLayoutSupportBase> {
        val data = ArrayList<MutileLayoutSupportBase>()
        val rawData = ArrayList<MutileLayoutSupportBase>()
        data.add(Title("Test"))
        IntRange(1, 4).forEach {
            rawData.add(VideoGrid(R.mipmap.liu_yi_fei))
        }
        data.addAll(rawData)
        data.add(SingleVideo(R.mipmap.liu_yi_fei))
        return data
    }
}