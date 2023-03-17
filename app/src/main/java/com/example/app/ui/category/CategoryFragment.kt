package com.example.app.ui.category

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.app.R
import com.example.app.databinding.FragmentCategoryBinding
import com.example.app.ui.common.ViewModelFactory

class CategoryFragment : Fragment() {

    private val viewModel : CategoryViewModel by viewModels{ ViewModelFactory(requireContext()) }
    private lateinit var binding : FragmentCategoryBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCategoryBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val categoryAdapter = CategoryAdapter(viewModel)
        binding.rvCategoryList.adapter = categoryAdapter
        viewModel.items.observe(viewLifecycleOwner){
            categoryAdapter.submitList(it)
            Log.d("category", "카테고리 바인딩 완료")
        }

        viewModel.openCategoryEvent.observe(viewLifecycleOwner){
            openCategoryDetail(it.categoryId, it.label)
        }
    }

    /*
    bundle 객체 : key : value 쌍으로 데이터를 저장할 수 있는 구조
     */
    private fun openCategoryDetail(categoryId : String, categoryLabel : String){
        // 화면 이동 : UI의 상태가 변화되었다. => ViewModel에서 호출
        findNavController().navigate(R.id.action_category_to_category_detail,bundleOf(
            "category_id" to categoryId,
            "category_label" to categoryLabel
        ))
    }
}