package com.example.app.repository

import com.example.app.AssetLoader
import com.example.app.model.HomeData
import com.google.gson.Gson

class HomeAssetDataSource(private val assetLoader: AssetLoader) : HomeDataSource {
    /*
      Gson 라이브러리 사용시 jsonObject, jsonArray로 json 데이터를 받지 않고
      바로 데이터 클래스 객체로 데이터를 받을 수 있다.
     */
    private val gson = Gson()

    override fun getHomeData(): HomeData? {
        return assetLoader.getJsonString("home.json")?.let { homeJsonString ->
            gson.fromJson(homeJsonString, HomeData::class.java)
        }
    }
}