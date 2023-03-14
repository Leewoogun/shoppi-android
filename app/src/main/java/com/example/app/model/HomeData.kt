package com.example.app.model

import com.google.gson.annotations.SerializedName

/*
Gson() 라이브러리를 사용하기 위해 JSON 데이터와 대응되는 객체를
만들어야함

json 데이터 key의 이름과 변수 이름이 같아야함 => SerializedName 사용
 */
data class HomeData(
    val title : Title,
    @SerializedName("top_banners") val topBanners : List<Banner>

)
