package com.example.app.ui.productdetail

import android.content.DialogInterface
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.app.R
import com.example.app.common.KEY_PRODUCT_ID
import com.example.app.databinding.FragmentProductDetailBinding
import com.example.app.ui.common.EventObserver
import com.example.app.ui.common.ViewModelFactory
import com.google.android.material.dialog.MaterialAlertDialogBuilder


class ProductDetailFragment : Fragment() {
    private val viewModel : ProductDetailViewModel by viewModels { ViewModelFactory(requireContext()) }
    private lateinit var binding : FragmentProductDetailBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentProductDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel
        setNavigation()
        requireArguments().getString(KEY_PRODUCT_ID)?.let{ productId ->
            setLayout(productId)
        }
        setAddCart()
    }

    private fun setNavigation(){
        binding.toolbarProductDetail.setNavigationOnClickListener {
            findNavController().navigateUp()
        }
    }

    private fun setLayout(productId : String){
        viewModel.loadProductDetail(productId)
        val detailImageAdapter = ProductDescriptionAdapter()
        binding.rvProductDetail.adapter = detailImageAdapter
        viewModel.product.observe(viewLifecycleOwner){ product ->
            binding.product = product
            detailImageAdapter.submitList(product.description)
        }
    }

    private fun setAddCart(){
        /*
        MaterialAlertDialogBuilder는 안드로이드 Material Design 라이브러리에서 제공하는 대화 상자 빌더 클래스 중 하나입니다.
        MaterialAlertDialogBuilder를 사용하여 경고, 정보, 확인 대화 상자 등 다양한 유형의 대화 상자를 만들 수 있습니다.
         */
        viewModel.addCartEvent.observe(viewLifecycleOwner, EventObserver{
            MaterialAlertDialogBuilder(requireContext())
                .setTitle("장바구니에 상품이 담겼습니다.")
                .setPositiveButton("확인") { dialog, which ->

                }
                .show()
        })
    }
}