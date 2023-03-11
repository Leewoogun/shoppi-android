package com.example.app.repository

import com.example.app.model.HomeData

/*
데이터 소스는 여러 유형이 존재 할 수 있다. ex) 파일, 데이터베이스, 네트워크 통신 결과..
공통적으로 요청하는 것 : 원본 데이터
 */
interface HomeDataSource {

    fun getHomeData() : HomeData?
}