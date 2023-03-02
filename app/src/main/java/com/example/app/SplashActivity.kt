package com.example.app

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState : Bundle?){
        super.onCreate(savedInstanceState)
        // 액티비티 간의 이동 할 때 Intent 객체를 사용 (현재 액티비티, 이동할 액티비티)
        // 클래스 참조 : 클래스::class.java
        startActivity(Intent(this, MainActivity::class.java))
        // splash 액티비티 종료
        finish()
    }

}