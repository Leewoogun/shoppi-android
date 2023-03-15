package com.example.app.repository

import com.example.app.model.Category
import com.example.app.network.ApiClient


/*
CategoryDataSource 인터페이스 구현부
 */
class CategoryRemoteDataSource(private val apiClient: ApiClient) : CategoryDataSource {
    override suspend fun getCategories(): List<Category> {
        return apiClient.getCategories()
    }
}