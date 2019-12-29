package com.streestone.weiborelation

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.streestone.cutomizedview.StarLayout
import com.streestone.weiborelation.ui.main.MainFragment
import kotlinx.android.synthetic.main.relation_layout.*
import java.util.*
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit
import kotlin.math.log

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        if (savedInstanceState == null) {
            /*supportFragmentManager.beginTransaction()
                .replace(R.id.container, MainFragment.newInstance())
                .commitNow()*/
        }
       /* Executors.newScheduledThreadPool(1).schedule({
            Log.d("TTTTTTTTTTT","Thread:" + Thread.currentThread().name)
            //name.setText("张子枫")
        },3000,TimeUnit.MILLISECONDS)*/

        //initView()
    }

    private fun initView() {
        center.setOnClickListener {
            startActivity(Intent(this,VideoListActivity::class.java))
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
    }

    override fun onResume() {
        super.onResume()
    }
}
