package com.wenzhibin.slidegradientviewbg

import android.content.Context
import android.content.Intent
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.daimajia.slider.library.SliderLayout
import com.daimajia.slider.library.SliderTypes.TextSliderView

import org.jetbrains.anko.find
/**
 * Date：2020/11/17 0017  17:36
 * Describe:
 * Created by:yanhuomatou2015
 */
class HomeRvAdapter(val context: Context) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    companion object { //定义常量
        val TYPE_ONE = 0
        val TYPE_TWO = 1
    }


    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val viewType = getItemViewType(position)
        when (viewType) {
            TYPE_ONE -> (holder as OneHolder).bindData("---------------广告轮播图-------------")
            TYPE_TWO -> (holder as TwoHolder).bindData(mDatas[position - 1])
        }    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        when (viewType) {
            TYPE_ONE -> return OneHolder(View.inflate(context, R.layout.item_banner, null))
            TYPE_TWO -> return TwoHolder(View.inflate(context, R.layout.item_seller, null))
            else ->return OneHolder(View.inflate(context, R.layout.item_banner, null))
        }    }



    /**
     * 不同position对应不同类型
     */
    override fun getItemViewType(position: Int): Int {
        if (position == 0) {
            return TYPE_ONE
        } else {
            return TYPE_TWO
        }
    }


    var mDatas: ArrayList<String> = ArrayList()

    fun setData(data: ArrayList<String>) {
        this.mDatas = data
        notifyDataSetChanged()
    }



    override fun getItemCount(): Int {
        if (mDatas.size > 0) {
            return mDatas.size + 1
        } else {
            return 0

        }
    }



    var url_maps: HashMap<String, Int> = HashMap()
    inner class OneHolder(item: View) : RecyclerView.ViewHolder(item) {
        val slideLayout: SliderLayout

        init {
            slideLayout = item.findViewById(R.id.slider) as SliderLayout
        }

        fun bindData(data: String) {
            if (url_maps.size == 0) {


                url_maps.put("有梦想谁都了不起", R.mipmap.banner_a)
                url_maps.put("打瞌睡的可爱少年", R.mipmap.banner_b)
                url_maps.put("github猫",R.mipmap.banner_c )
                url_maps.put("复仇者联盟",R.mipmap.banner_d)

                for ((key, value) in url_maps) {
                    var textSlideView: TextSliderView = TextSliderView(context)
                    textSlideView.description(key).image(value)
                    slideLayout.addSlider(textSlideView)
                }
            }
        }
    }

    inner class TwoHolder(item: View) : RecyclerView.ViewHolder(item) {
        val tvTitle: TextView
        val ivLogo: ImageView
        val rbScore: RatingBar
        val tvSale: TextView
        val tvSendPrice: TextView
        val tvDistance: TextView

        init {
            tvTitle = item.find(R.id.tv_title)
            ivLogo = item.find(R.id.seller_logo)
            rbScore = item.find(R.id.ratingBar)

            tvSale = item.find(R.id.tv_home_sale)
            tvSendPrice = item.find(R.id.tv_home_send_price)
            tvDistance = item.find(R.id.tv_home_distance)
            item.setOnClickListener{
                  val intent: Intent =Intent(context,SecondActivity::class.java)
                   context.startActivity(intent)

            }

        }


        fun bindData(string: String) {

        }

    }
}
