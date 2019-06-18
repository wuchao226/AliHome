package com.wuc.alipay

import android.graphics.Color
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.appbar.AppBarLayout
import kotlinx.android.synthetic.main.activity_ali_home.*
import kotlinx.android.synthetic.main.include_open.*
import kotlinx.android.synthetic.main.include_toolbar_close.*
import kotlinx.android.synthetic.main.include_toolbar_open.*

/**
 * @author:     wuchao
 * @date:       2019-06-18 16:02
 * @desciption:
 */
class AliHomeActivity : AppCompatActivity(), AppBarLayout.OnOffsetChangedListener {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ali_home)
        app_bar.addOnOffsetChangedListener(this)
    }

    /**
     * 通过计算滑动的距离，逐渐改变透明度。
     */
    override fun onOffsetChanged(appBarLayout: AppBarLayout, verticalOffset: Int) {
        //垂直方向偏移量
        val offset = Math.abs(verticalOffset)
        //最大偏移距离
        val scrollRange = appBarLayout.totalScrollRange
        //当滑动没超过一半，展开状态下 toolbar 显示内容，更具收缩位置，改变透明值
        if (offset <= scrollRange / 2) {
            toolbar_open.visibility = View.VISIBLE
            toolbar_close.visibility = View.GONE
            //根据偏移百分比 计算透明值
            val scale: Float = offset.toFloat() / (scrollRange / 2)
            val alpha: Int = (255 * scale).toInt()
            bg_toolbar_open.setBackgroundColor(Color.argb(alpha, 25, 131, 209))
        }
        //当滑动超过一半，收缩状态下 toolbar 显示内容，根据收缩位置，改变透明值
        else {
            toolbar_open.visibility = View.GONE
            toolbar_close.visibility = View.VISIBLE
            val scale = (scrollRange - offset).toFloat() / (scrollRange / 2)
            val alpha = (255 * scale).toInt()
            bg_toolbar_close.setBackgroundColor(Color.argb(alpha, 25, 131, 209))
        }
        //根据百分比计算扫一扫布局透明值
        val scale = offset.toFloat() / scrollRange
        val alpha = (255 * scale).toInt()
        bg_content.setBackgroundColor(Color.argb(alpha, 25, 131, 209))
    }

    override fun onDestroy() {
        super.onDestroy()
        app_bar.removeOnOffsetChangedListener(this)
    }
}