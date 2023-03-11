package com.example.app.ui.common

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.app.AssetLoader
import com.example.app.repository.HomeAssetDataSource
import com.example.app.repository.HomeRepository
import com.example.app.ui.home.HomeViewModel

/*
viewModelProvider를 구현하는 클래스
 */
class ViewModelFactory(private val context : Context): ViewModelProvider.Factory {
    // viewModel 클래스의 서브 클래스 여야만 한다.
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HomeViewModel::class.java)){
            val repository = HomeRepository(HomeAssetDataSource(AssetLoader(context)))
            return HomeViewModel(repository) as T
        } else{
            throw IllegalArgumentException("Failed to create  Viewmodel : ${modelClass.name}")
        }
    }
}