package com.example.app.ui.categorydetail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.app.model.Promotion
import com.example.app.model.TopSelling
import com.example.app.repository.categorydetail.CategoryDetailRepository
import kotlinx.coroutines.launch

class CategoryDetailViewModel(private val categoryDetailRepository: CategoryDetailRepository) : ViewModel() {
    private val _topSelling = MutableLiveData<TopSelling>()
    val topSelling : LiveData<TopSelling> = _topSelling

    private val _promotion = MutableLiveData<Promotion>()
    val promotion : LiveData<Promotion> = _promotion

    init{
        loadCategoryDetail()
    }

    private fun loadCategoryDetail(){
        viewModelScope.launch {
            val categoryDetail = categoryDetailRepository.getCategoryDetail()
            _topSelling.value = categoryDetail.topSelling
            _promotion.value = categoryDetail.promotion
        }
    }

}