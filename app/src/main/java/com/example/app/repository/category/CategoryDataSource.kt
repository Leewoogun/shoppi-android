package com.example.app.repository.category

import com.example.app.model.Category


/*
Category Repository에 데이터를 전달함
 */
interface CategoryDataSource {

    suspend fun getCategories() : List<Category>
}