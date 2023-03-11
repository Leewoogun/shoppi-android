package com.example.app.repository

import com.example.app.model.HomeData

/*
데이터 소스로부터 데이터를 받는다.
 */
class HomeRepository(private val assetDataSource: HomeAssetDataSource) {

    fun getHomeData() : HomeData? {
        return assetDataSource.getHomeData()
    }
}