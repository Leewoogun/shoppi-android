package com.example.app.repository.productdetail

import com.example.app.model.Product

interface ProductDetailDataSource {

    suspend fun getProductDetail(productId : String) : Product
}