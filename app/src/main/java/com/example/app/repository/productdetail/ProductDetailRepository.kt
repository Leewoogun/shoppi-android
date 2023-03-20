package com.example.app.repository.productdetail

import com.example.app.model.Product

class ProductDetailRepository(private val remoteDataSource : ProductDetailDataSource) {
    suspend fun getProductDetail(productId : String) : Product{
        return remoteDataSource.getProductDetail(productId)
    }
}
