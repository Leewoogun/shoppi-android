package com.example.app.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.viewpager2.widget.ViewPager2
import com.example.app.*
import com.example.app.databinding.FragmentHomeBinding
import com.example.app.ui.common.ViewModelFactory
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class HomeFragment : Fragment() {

    private val viewModel : HomeViewModel by viewModels { ViewModelFactory(requireContext()) }
    private lateinit var binding : FragmentHomeBinding
    // Fragment는 setContentView()가 없기 때문에 직접 객체화 시켜서 메모리에 올려야한다.
    // inflate(resource: Int, root: ViewGroup?, attachToRoot: Boolean)
    //
    //- resource : View를 만들고 싶은 레이아웃 파일의 id
    //- root: 생성될 View의 parent를 명시
    //- attachToRoot: true 로 설정해 줄 경우 root의 자식 View로 자동으로 추가, false 일 경우 root는 생성되는 View의 layoutParam을 생성하는데만 사용
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    // onViewCreated의 인자 view : onCreatedView에서 반환되는 View값
   override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.lifecycleOwner = viewLifecycleOwner
        setToolbar()

        setTopBanners()
   }
    private fun setToolbar() {
        viewModel.title.observe(viewLifecycleOwner) { title ->
            binding.title = title
        }
    }

    private fun setTopBanners() {
        with(binding.viewpagerHomeBanner) {
            adapter = HomeBannerAdapter().apply {
                viewModel.topBanners.observe(viewLifecycleOwner) { banners ->
                    /*
                sumbitList() 메서드는 새로운 데이터 목록을 RecyclerView에 표시하기 위해
                호출된다.
                 */
                    submitList(banners)
                }
            }
            val pageWidth = resources.getDimension(R.dimen.viewpager_item_width)
            val pageMargin = resources.getDimension(R.dimen.viewpager_item_margin)
            // 디바이스 가로 길이 - 한 페이지 가로 길이 - 페이지 간 간격
            val screenWidth = resources.displayMetrics.widthPixels
            val offset = screenWidth - pageWidth - pageMargin


            offscreenPageLimit = 3
            /*
            viewPager 슬라이드간 애니메이션을 구현하는 코드
            position : 사용자가 오른쪽으로 슬라이드하는지 왼쪽으로 슬라이드 하는지
             */
            setPageTransformer { page, position ->
                // width값 위치조정
                page.translationX = position * -offset
            }
            /*
            TabLayout Indicator 구현
            인자가 interface인 경우 -> object로 구현 -> 필수 메소드 오버라이드 -> 메소드가 1개일 경우 람다식 가능
             */
            TabLayoutMediator(binding.viewpagerHomeBannerIndicator, this) { tab, position ->

            }.attach()
        }
    }
}
