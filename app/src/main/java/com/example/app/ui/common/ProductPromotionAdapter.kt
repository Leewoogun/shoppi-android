package com.example.app.ui.common

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.app.databinding.ItemCategoryPromotionBinding
import com.example.app.model.Product

class ProductPromotionAdapter(private val clickListener : ProductClickListener) : ListAdapter<Product, ProductPromotionAdapter.ProductPromotionViewHolder>(
    ProductDiffCallback()
) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductPromotionViewHolder {
        val binding = ItemCategoryPromotionBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ProductPromotionViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ProductPromotionViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class ProductPromotionViewHolder(private val binding: ItemCategoryPromotionBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(product : Product){
            binding.clickListener = clickListener
            binding.product = product
            binding.executePendingBindings()
        }
    }

    class ProductDiffCallback : DiffUtil.ItemCallback<Product>() {
        override fun areItemsTheSame(oldItem: Product, newItem: Product): Boolean {
            return oldItem.productId == newItem.productId
        }

        override fun areContentsTheSame(oldItem: Product, newItem: Product): Boolean {
            return oldItem == newItem
        }
    }
}