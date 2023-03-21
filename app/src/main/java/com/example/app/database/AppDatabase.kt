package com.example.app.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.app.model.CartItem

/*
version = 1 : 데이터베이스의 schema 버전,
Room은 데이터베이스 스키마가 변경될 떄마다 새로운 버전 번호를 할당해야함
version = 1은 첫 번째 데이터베이스 버전이라는 의미
스키마를 변경(새로운 테이블 추가, 컬럼 수정)하면 version 값을 2로 바꿔야함함 */
@Database(entities = [CartItem::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun cartItemDao() : CartItemDao
}