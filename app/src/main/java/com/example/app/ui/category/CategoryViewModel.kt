package com.example.app.ui.category

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.app.model.Category
import com.example.app.repository.category.CategoryRepository
import kotlinx.coroutines.launch

/*
CategoryRepository에 데이터 요청
 */
class CategoryViewModel(private val categoryRepository: CategoryRepository) : ViewModel(){

    private val _items = MutableLiveData<List<Category>>()
    val items : LiveData<List<Category>> = _items

    private val _openCategoryEvent = MutableLiveData<Category>()
    val openCategoryEvent : LiveData<Category> = _openCategoryEvent


    init{
        loadCategory()
    }

    // view에서 호출
    fun openCategoryDetail(category : Category){
        _openCategoryEvent.value = category
    }


    private fun loadCategory(){
        // TODO repository 데이터 요청 (네트워크 통신으로)
        viewModelScope.launch {
            val categories = categoryRepository.getCategories()
            _items.value = categories
        }
    }

}