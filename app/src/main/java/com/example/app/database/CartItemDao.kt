package com.example.app.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.app.model.CartItem

/*
데이터 베이스의 쿼리, 수정, 삭제, 삽입 등을 구조화함
 */

@Dao
interface CartItemDao {

    /*
    onConflict 속성 : 이미 추가되있는 데이터에서 일부만 변경하기 위해 사용
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(cartItem : CartItem)

    @Query("SELECT * FROM cart_item")
    suspend fun load() : List<CartItem>
}