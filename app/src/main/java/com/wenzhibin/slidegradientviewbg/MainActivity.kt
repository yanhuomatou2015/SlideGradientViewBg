package com.wenzhibin.slidegradientviewbg


import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.util.TypedValue
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.find
/**
 * Date：2020/11/17 0017  17:36
 * Describe:
 * Created by:yanhuomatou2015
 */
class MainActivity : AppCompatActivity() {
    lateinit var homeRvAdapter: HomeRvAdapter
    lateinit var rvHome: RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        rvHome = find(R.id.rv_home)
        //从上到下的列表视图
        rvHome.layoutManager = LinearLayoutManager(this)
        homeRvAdapter = HomeRvAdapter(this)
        rvHome.adapter = homeRvAdapter
        initData()

        //判断设备是否有虚拟按键，如果有增加paddingBottom=50dp
        if (StatusbarUtils.checkDeviceHasNavigationBar(this)) {
           rvHome.setPadding(0, 0, 0, 50.dp2px())
        }


    }

    //将dp转成像素
    fun Int.dp2px(): Int {
        return TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP,
            toFloat(), resources.displayMetrics).toInt()

    }
    val datas: ArrayList<String> = ArrayList<String>()
    var sum: Int = 0
    var distance: Int = 0
    var alpha = 55 //55代表不透明度  与xml布局设置的 android:background="#552E6BEC"保持一致
    private fun initData() {
        distance = 120.dp2px() //120=轮播图高度200dp-状态栏高度dp-标题栏高度50dp

        //设置状态栏颜色,一种方法是通过代码设置（建议用代码设置，兼容性更好）；第二种方法是在xml里去设置android:windowTranslucentStatus为true
        StatusbarUtils.statusBarColorTransparent(this)
        for (i in 0 until 15) {
            datas.add("附近粉店：" + i)
        }
        homeRvAdapter.setData(datas)

        rvHome.setOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
            }

            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                /**
                 * 颜色渐变的计算
                 */
                sum += dy
                if (sum > distance) {
                    alpha = 255  //255表示不透明
                } else { //按比例增加不透明度

                    alpha = sum * 200 / distance
                    alpha += 55
                }
                Log.e("home", "alpha:$alpha"+"  sum:$sum")
                ll_title_container.setBackgroundColor(Color.argb(alpha,0x2B,0x6C,0xEC))
            }
        })
    }
}
