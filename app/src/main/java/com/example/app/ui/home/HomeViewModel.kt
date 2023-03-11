package com.example.app.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.app.Banner
import com.example.app.model.Title
import com.example.app.repository.HomeRepository

/*
ViewModel 클래스는 수명 주기를 고려하여 UI 관련 데이터를 저장하고 관리하도록 설계되었습니다.
ViewModel 클래스를 사용하면 화면 회전과 같이 구성을 변경할 때도 데이터를 유지할 수 있습니다
repository -> viewModel (레퍼지토리에 데이터 요청)
 */
class HomeViewModel(private val homeRepository: HomeRepository) : ViewModel() {

    /*
    _title이 할당이 될 때 LiveData로 변환
    */
    private val _title = MutableLiveData<Title>()
    val title : LiveData<Title> = _title

    private val _topBanners = MutableLiveData<List<Banner>>()
    val topBanners : LiveData<List<Banner>> = _topBanners

    init{
        loadHomeData()
    }

    private fun loadHomeData(){
        // TODO Data Layer - Repository에 요청
        val homeData = homeRepository.getHomeData()
        homeData?.let{homeData ->
            _title.value = homeData.title
            _topBanners.value = homeData.topBanners
        }
    }
}
