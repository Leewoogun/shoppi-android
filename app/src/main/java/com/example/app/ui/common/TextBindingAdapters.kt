package com.example.app.ui.common

import android.graphics.Paint
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.example.app.R
import java.text.DecimalFormat
import kotlin.math.roundToInt

@BindingAdapter("priceAmount")
fun applyPriceFormat(view: TextView, price: Int) {
    val decimalFormat = DecimalFormat("#,###")
    view.text = view.context.getString(R.string.unit_discount_currency, decimalFormat.format(price))
}

@BindingAdapter("priceAmount", "discountRate")
fun applyPriceDiscountRate(view: TextView, price: Int, discountRate: Int) {
    val discountPrice = (((100 - discountRate) / 100.0) * price).roundToInt()
    applyPriceFormat(view, discountPrice)
}

@BindingAdapter("priceAmount", "strikeThrough")
fun applyPriceAndStrikeStyle(view : TextView, price : Int, strikeThrough : Boolean){
    applyPriceFormat(view, price)
    /*
    취소선을 나타내는 속성
     */
    if (strikeThrough){
        view.paintFlags = Paint.STRIKE_THRU_TEXT_FLAG
    }
}