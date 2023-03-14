package com.example.app.ui.common

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import androidx.databinding.BindingConversion

@BindingConversion

// ColorDrawable : #을 포함한 String 값을 Drawable 객체로 변환
fun convertToColorDrawable(color: String): Drawable {
    return ColorDrawable(Color.parseColor(color))
}