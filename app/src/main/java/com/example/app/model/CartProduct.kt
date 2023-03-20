package com.example.app.model

/*
여러 클래스의 데이터 타입을 일치시켜줌
 */
sealed class CartProduct

data class CartHeader(
    val brandName : String,
) : CartProduct()

data class CartItem(
    val productId : String,
    val label : String,
    val price : Int,
    val brandName : String,
    val thumbnailImageUrl : String,
    val type : String,
    val amount : Int
) : CartProduct()