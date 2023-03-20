package com.example.app.ui.cart

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.app.databinding.ItemCartSectionBinding
import com.example.app.databinding.ItemCartSectionHeaderBinding
import com.example.app.model.CartHeader
import com.example.app.model.CartItem
import com.example.app.model.CartProduct
import okhttp3.internal.notify


private const val VIEW_TYPE_HEADER = 0
private const val VIEW_TYPE_ITEM = 0
/*
아이템의 목록을 관리하는 adapter
 */
class CartAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>(){

    private val cartProducts = mutableListOf<CartProduct>()


    /*
    viewHolder를 생성하는 시점
    getItemViewType의 리턴값이 viewType의 인자로 알려주게됨
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return when(viewType){
            VIEW_TYPE_HEADER -> HeaderViewHolder(ItemCartSectionHeaderBinding.inflate(inflater, parent, false))
            VIEW_TYPE_ITEM -> ItemViewHolder(ItemCartSectionBinding.inflate(inflater, parent, false))
            else -> throw IllegalArgumentException("ViewHolder 생성 실패")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(holder){
            is HeaderViewHolder ->{
                val item = cartProducts[position] as CartHeader
                holder.bind(item)
            }
            is ItemViewHolder -> {
                val item = cartProducts[position] as CartItem
                holder.bind(item)
            }
        }
    }

    /*
    데이터 타입을 mapping
    position에 따라서 데이터 타입이 header일수도 있고, item일수도 있다.
     */
    override fun getItemViewType(position: Int): Int {
        return when (cartProducts[position]){
            is CartHeader -> VIEW_TYPE_HEADER
            is CartItem -> VIEW_TYPE_ITEM
        }

    }

    override fun getItemCount(): Int {
        return cartProducts.size
    }

    fun submitHeaderAndItemList(items : List<CartItem>){
        val itemGroups = items.groupBy { it.brandName }
        val products = mutableListOf<CartProduct>()
        itemGroups.entries.forEach { entry ->
            val header = CartHeader(entry.key)
            products.add(header)
            products.addAll(entry.value)
        }
        cartProducts.addAll(products)
        notifyItemRangeInserted(cartProducts.size, products.size)
    }

    class HeaderViewHolder(private val binding : ItemCartSectionHeaderBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(header : CartHeader){
            binding.header = header
            binding.executePendingBindings()
        }
    }

    class ItemViewHolder(private val binding : ItemCartSectionBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(item : CartItem){
            binding.item = item
            binding.executePendingBindings()
        }
    }
}