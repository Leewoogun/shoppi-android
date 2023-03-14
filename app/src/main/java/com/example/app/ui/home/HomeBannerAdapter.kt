package com.example.app.ui.home

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.app.model.Banner
import com.example.app.GlideApp
import com.example.app.R
import com.example.app.databinding.ItemHomeBannerBinding
import com.example.app.ui.common.applyPriceFormat
import com.example.app.ui.common.loadImage
import java.text.DecimalFormat
import kotlin.math.roundToInt

/*
목록 데이터를 가져와서 이를 표시하는 뷰를 만드는데 사용하는
RecyclerView.Adapter 클래스의 서브 클래스
T : 레이아웃의 표현할 데이터의 타입
VH : RecyclerView.viewHoler 클래스의 서브 클래스를 만들어 사용
ListAdapter의 역할 : 데이터의 list를 받아서 0번째부터 순차적으로 viewHolder와 바인딩을함
Layout은 그대로 유지한채로 Data만 업데이트 <- ListAdapter
 */
class HomeBannerAdapter : ListAdapter<Banner, HomeBannerAdapter.HomeBannerViewHolder>(
    BannerDiffCallBack()
) {

    private lateinit var binding : ItemHomeBannerBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeBannerViewHolder {
        binding = ItemHomeBannerBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return HomeBannerViewHolder(binding)
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
    class HomeBannerViewHolder(private val binding: ItemHomeBannerBinding) : RecyclerView.ViewHolder(binding.root){

        fun bind(banner : Banner){
            // view와 바인딩을 해줌
            binding.banner = banner
            binding.executePendingBindings()

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
