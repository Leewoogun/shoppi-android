package com.example.app.repository.category

import com.example.app.model.Category


/*
Category 객체를 받아오는 repository
REST API에서 get 함수를 호출함
CategoryDataSource에 데이터를 요청
 */
class CategoryRepository(private val remoteDataSource: CategoryRemoteDataSource) {

    // 멈추고 다시 시작할 수 있는 함수수
   suspend fun getCategories() : List<Category>{
        return remoteDataSource.getCategories()
    }
}