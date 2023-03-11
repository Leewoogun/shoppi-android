package com.example.app

import android.content.Context
import android.util.Log

// JSON 포맷을 String으로 변환하는 클래스
class AssetLoader(private val context : Context) {

    // use 확장함수 실행 시 나타날 수 있는 exception을 처리하는 함수
    fun getJsonString(fileName : String) :String?{
        /*
        람다 식을 실행하고 예외가 발생한 경우 예외 객체를 반환 : Result<R>
         */
        return kotlin.runCatching {
            loadAsset(fileName)
        }.getOrNull()
    }

    private fun loadAsset(fileName : String) : String{
        /*
          context를 통해 어플리케이션 전역에서 사용할 수 있는 정보를 접근,
          resouce나 datebase와 같은 시스템 자원에 접근
          nullable 타입
          open 함수를 사용하여 얻는 객체 : InputStream
          InputStream 객체는 사용하고 해제해야한다.
        */
        return context.assets.open(fileName).use{ inputStream ->
            val size = inputStream.available()
            val bytes = ByteArray(size)
            inputStream.read(bytes)
            String(bytes)
        }
    }
}