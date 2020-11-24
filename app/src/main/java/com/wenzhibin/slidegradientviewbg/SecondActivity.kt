package com.wenzhibin.slidegradientviewbg

import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.util.TypedValue
import android.widget.ScrollView
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_second.*
import android.widget.ImageView
import android.widget.LinearLayout





class SecondActivity : AppCompatActivity() {

    lateinit var scrollView: ScrollView

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)
        scrollView =findViewById(R.id.myscrollview)

        if (StatusbarUtils.checkDeviceHasNavigationBar(this)) {
            scrollView.setPadding(0, 0, 0, 50.dp2px())
        }
        StatusbarUtils.statusBarColorTransparent(this)

        initData()

    }

    fun Int.dp2px(): Int {
        return TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP,
            toFloat(), resources.displayMetrics).toInt()

    }

    var distance: Int = 0
    var alpha = 66 //66代表不透明度  与xml布局设置的 android:background="#66EC240A" 保持一致
    @RequiresApi(Build.VERSION_CODES.M)
    private fun initData() {
        distance = 120.dp2px()//把120dp转成实际的像素距离(120dp对应手机的多少像素)
        ll_group.removeAllViews()
        for (i in 0 until 6) {//给LinearLayout添加子控件
            val imageView = ImageView(this)
            val lp = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,200.dp2px())
            lp.setMargins(0, 0, 0, 10.dp2px())
            imageView.layoutParams = lp
            imageView.scaleType=ImageView.ScaleType.FIT_XY
            when(i%2){
                0->imageView.setImageResource(R.mipmap.banner_f)
                else->imageView.setImageResource(R.mipmap.banner_e)

            }

            ll_group.addView(imageView)
        }

        scrollView.setOnScrollChangeListener { v, scrollX, scrollY, oldScrollX, oldScrollY ->

            /**
             * 颜色渐变的计算
             */
            if (scrollY > distance) {
                alpha = 255  //255表示不透明
            } else { //按比例增加不透明度
                alpha = scrollY * 184 / distance
                alpha += 66
            }
            Log.e("second", "alpha:$alpha"+"  scrollY:$scrollY")
            ll_head_container.setBackgroundColor(Color.argb(alpha,0xEC,0x24,0x0A))

        }

    }

}