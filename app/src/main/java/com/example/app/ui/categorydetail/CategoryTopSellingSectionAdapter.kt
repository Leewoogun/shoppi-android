package com.example.app.ui.categorydetail

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.app.databinding.ItemCategoryTopSellingSectionBinding
import com.example.app.model.TopSelling


class CategoryTopSellingSectionAdapter : ListAdapter<TopSelling, CategoryTopSellingSectionAdapter.TopSellingSectionViewHolder>(
    TopSellingDiffCallBack()
){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TopSellingSectionViewHolder {
        val binding = ItemCategoryTopSellingSectionBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TopSellingSectionViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TopSellingSectionViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class TopSellingSectionViewHolder(private val binding : ItemCategoryTopSellingSectionBinding) : RecyclerView.ViewHolder(binding.root){

        private val nestedAdapter = CategoryTopSellingItemAdapter()

        init{
            binding.rvCategorySection.adapter = nestedAdapter
        }

        fun bind(topSelling : TopSelling){
            binding.title = topSelling.title
            nestedAdapter.submitList(topSelling.categories)
            binding.executePendingBindings()
        }
    }
}

class TopSellingDiffCallBack : DiffUtil.ItemCallback<TopSelling>(){
    // id 값이 동일한가?
    override fun areItemsTheSame(oldItem: TopSelling, newItem: TopSelling): Boolean {
        return oldItem.title.text == newItem.title.text
    }

    // 모든 값이 동일한가?
    override fun areContentsTheSame(oldItem: TopSelling, newItem: TopSelling): Boolean {
        return oldItem == newItem
    }

}


