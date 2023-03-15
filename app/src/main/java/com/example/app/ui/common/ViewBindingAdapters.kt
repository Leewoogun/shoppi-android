package com.example.app.ui.common

import android.view.View
import androidx.databinding.BindingAdapter



/*
view의 활성/비활성화 기능

View.GONE을 사용하는 이유는 Layout의 목적으로만 쓰는게 아니기 때문이다.
 */
@BindingAdapter("isVisible")
fun updateVisibility(view: View, isVisible : Boolean){
    view.visibility = if (isVisible) View.VISIBLE else View.GONE
}