package com.example.app.network

import com.example.app.model.Category
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import retrofit2.http.GET

// 어떤 주소와 통신을 할 것인가?
interface ApiClient {


    // URL 주소가 바뀌는 부분만 설정 (엔드포인트)
    @GET("categories.json")
    suspend fun getCategories() : List<Category>

    companion object{

        private val baseUrl = "https://shoppi-771e3-default-rtdb.asia-southeast1.firebasedatabase.app/"

        fun create() : ApiClient{
            val logger = HttpLoggingInterceptor().apply{
                level = HttpLoggingInterceptor.Level.BASIC
            }

            // addInterceptor : 네트워크 응답이 도착할 때 출력되는 메시지 format
            val client = OkHttpClient.Builder()
                .addInterceptor(logger)
                .build()

            /*
            baseUrl : Firebase의 기본 URL
            addConverterFactory : http 응답의 결과를 프로젝트에서 사용가능한 객체로 변환 방법 정의
            create : 통신할 Api
            */

            return Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(ApiClient::class.java)
        }
    }
}