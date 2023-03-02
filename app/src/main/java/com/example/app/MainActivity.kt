package com.example.app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.google.android.material.bottomnavigation.BottomNavigationView

private const val TAG = "MainActivity"

// 액티비티 : 사용자와 상호작용하는 UI
// inflate : Layout의 ID를 받아 Layout 구현부를 widget으로 바꾸는 과정
class MainActivity : AppCompatActivity() {
    // 데이터를 초기화하는 작업
    // Layout을 inflate 하는 과정
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.d(TAG, "onCreate")

        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.navigation_main)
        bottomNavigationView.itemIconTintList = null
    }

    // on으로 시작하는 메서드(name convention) : 어떠한 이벤트를 알려주는 메서드
    // 콜백 함수 : 어떠한 이벤트가 발생하고 호출되는 함수
    override fun onRestart() {
        super.onRestart()
        Log.d(TAG, "onRestart")
    }

    override fun onStart(){
        super.onStart()
        Log.d(TAG, "onStart")
    }

    override fun onResume(){
        super.onResume()
        Log.d(TAG, "onResume")
    }

    override fun onPause(){
        super.onPause()
        Log.d(TAG, "onPause")
    }

    override fun onStop(){
        super.onStop()
        Log.d(TAG, "onStop")
    }

    override fun onDestroy(){
        super.onDestroy()
        Log.d(TAG, "onDestroy")
    }

}