package com.example.app

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView

/*
목록 데이터를 가져와서 이를 표시하는 뷰를 만드는데 사용하는
RecyclerView.Adapter 클래스의 서브 클래스
T : 레이아웃의 표현할 데이터의 타입
VH : RecyclerView.viewHoler 클래스의 서브 클래스를 만들어 사용
ListAdapter의 역할 : 데이터의 list를 받아서 0번째부터 순차적으로 viewHolder와 바인딩을함
Layout은 그대로 유지한채로 Data만 업데이트 <- ListAdapter
 */
class HomeBannerAdapter : ListAdapter<Banner, HomeBannerAdapter.HomeBannerViewHolder>(BannerDiffCallBack()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeBannerViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_home_banner, parent, false)
        return HomeBannerViewHolder(view)
    }

    override fun onBindViewHolder(holder: HomeBannerViewHolder, position: Int) {
        /*
        onCreateViewHolder가 호출된 이후에 viewHolder가 생성이 되면 holder가 인자로 전달
        holder의 데이터를 바인딩
        position을 전달하면 해당 데이터 타입의 position을 반환
         */
        holder.bind(getItem(position))

    }

    // view : Home banner에서 inflate 시킬 레이아웃웃
    class HomeBannerViewHolder(view : View) : RecyclerView.ViewHolder(view){

        private val bannerImageView = view.findViewById<ImageView>(R.id.iv_banner_image)
        private val bannerBadgeTextView = view.findViewById<TextView>(R.id.tv_banner_badge)
        private val bannerTitleTextView = view.findViewById<TextView>(R.id.tv_banner_title)
        private val bannerDetailThumbnailImageView = view.findViewById<ImageView>(R.id.iv_banner_detail_thumbnail)
        private val bannerDetailBrandLabelTextView = view.findViewById<TextView>(R.id.tv_banner_detail_brand_label)
        private val bannerDetailProductLabelTextView = view.findViewById<TextView>(R.id.tv_banner_detail_product_label)
        private val bannerDetailProductDiscountRateTextView = view.findViewById<TextView>(R.id.tv_banner_detail_product_discount_rate)
        private val bannerDetailProductDiscountPriceTextView = view.findViewById<TextView>(R.id.tv_banner_detail_product_discount_price)
        private val bannerDetailPriceTextView = view.findViewById<TextView>(R.id.tv_banner_detail_product_price)

        fun bind(banner : Banner){
            // view와 바인딩을 해줌
            // itemView : ViewHolder의 내부 View
            GlideApp.with(itemView)
                .load(banner.backgroundImageUrl)
                .into(bannerImageView)
        }
    }
}

class BannerDiffCallBack : DiffUtil.ItemCallback<Banner>(){
    override fun areItemsTheSame(oldItem: Banner, newItem: Banner): Boolean {
        /*
        기존의 객체와 새로운 객체를 비교할 때 어떠한 값을 식별자로 사용할지 알려줘야함
        두 객체가 같다면 2번째 콜백함수 호출
         */
        return oldItem.productDetail.productId == newItem.productDetail.productId

    }

    override fun areContentsTheSame(oldItem: Banner, newItem: Banner): Boolean {
        return oldItem == newItem
    }

}
