package com.example.app.ui.common

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.example.app.GlideApp

@BindingAdapter("imageUrl")
fun loadImage(view: ImageView, imageUrl: String?) {
    /*
      with인자 : Activity or Fragment
      load인자 : url 주소
      into인자 : 어떠한 리소스로 이미지 뷰에 할당할 것인가
     */
    if (!imageUrl.isNullOrEmpty()) {
        GlideApp.with(view)
            .load(imageUrl)
            .into(view)
    }
}