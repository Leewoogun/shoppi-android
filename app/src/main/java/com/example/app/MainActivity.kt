package com.example.app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
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

        // find 혹은 get으로 시작하는 method는 찾고자 하는 객체가 없을수도 있으므로 nullable함
       val navController = supportFragmentManager.findFragmentById(R.id.container_main)?.findNavController()
       navController?.let{
           // 바텀 navigation view와 NavHostFragment를 연결
           // 바텀 navigation을 클릭했을 때 화면 이동을 처리해주는 controller
           bottomNavigationView.setupWithNavController(it)
       }
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