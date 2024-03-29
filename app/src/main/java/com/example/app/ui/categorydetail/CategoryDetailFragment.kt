package com.example.app.ui.categorydetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.ConcatAdapter
import com.example.app.common.KEY_CATEGORY_ID
import com.example.app.common.KEY_CATEGORY_LABEL
import com.example.app.databinding.FragmentCategoryDetailBinding
import com.example.app.ui.common.ProductClickListener
import com.example.app.ui.common.ProductPromotionAdapter
import com.example.app.ui.common.ViewModelFactory

class CategoryDetailFragment : Fragment(), ProductClickListener {

    private lateinit var binding : FragmentCategoryDetailBinding
    // ViewModelFactory에 viewModel 생성을 위임함
    private val viewModel : CategoryDetailViewModel by viewModels {ViewModelFactory(requireContext())}

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCategoryDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.lifecycleOwner = viewLifecycleOwner

        /*
        함수 또는 메서드가 올바른 인수를 받았는지 검증하는데 사용용
        bundle 객체에 접근
        */
        val categoryId = requireArguments().getString(KEY_CATEGORY_ID)

        setToolbar()
        setListAdapter()
    }

    private fun setToolbar(){
        val categoryLabel = requireArguments().getString(KEY_CATEGORY_LABEL)
        binding.toolbarCategoryDetail.title = categoryLabel
    }

    private fun setListAdapter(){
        /*
        concatAdapter : 여러개의 어댑터를 하나로 모을 수 있음
        여러 가지 view 타입을 하나의 어댑터로 배치할 수 있음
         */
        val topSellingSectionAdapter = CategoryTopSellingSectionAdapter()
        val titleAdapter = SectionTitleAdapter()
        val promotionAdapter = ProductPromotionAdapter(this)
        binding.rvCategoryDetail.adapter = ConcatAdapter(topSellingSectionAdapter, titleAdapter, promotionAdapter)
        viewModel.topSelling.observe(viewLifecycleOwner){ topSelling ->
            topSellingSectionAdapter.submitList(listOf(topSelling))
        }
        viewModel.promotion.observe(viewLifecycleOwner){ promotion ->
            titleAdapter.submitList(listOf(promotion.title))
            promotionAdapter.submitList(promotion.items)
        }

    }

    override fun onProductClick(productId: String) {
        TODO("Not yet implemented")
    }
}