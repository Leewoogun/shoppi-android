package com.example.app.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/*
여러 클래스의 데이터 타입을 일치시켜줌
 */
sealed class CartProduct

data class CartHeader(
    val brandName : String,
) : CartProduct()

/*
https://developer.android.com/training/data-storage/room?hl=ko 참조
cart_item의 테이블 생성
 */
@Entity(
    // 테이블명을 클래스 이름과 다르게 생성
    tableName = "cart_item"
)

/*
각 프로퍼티가 table의 column으로 대응이 됨
 */
data class CartItem(
    @PrimaryKey @ColumnInfo(name = "product_id") val productId : String,
    val label : String,
    val price : Int,
    @ColumnInfo(name = "brand_name") val brandName : String,
    @ColumnInfo(name = "thumbnail_image_url") val thumbnailImageUrl : String,
    val type : String,
    val amount : Int
) : CartProduct()