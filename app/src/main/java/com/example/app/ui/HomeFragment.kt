package com.example.app.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.viewpager2.widget.ViewPager2
import com.example.app.*
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.google.gson.Gson

class HomeFragment : Fragment() {

    private val viewModel : HomeViewModel by viewModels()

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
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    // onViewCreated의 인자 view : onCreatedView에서 반환되는 View값
   override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val toolbarTitle = view.findViewById<TextView>(R.id.toolbar_home_title)
        val toolbarIcon = view.findViewById<ImageView>(R.id.toolbar_home_icon)
        val viewPager = view.findViewById<ViewPager2>(R.id.viewpager_home_banner)
        val viewPagerIndicator = view.findViewById<TabLayout>(R.id.viewpager_home_banner_indicator)


        val assetLoader = AssetLoader()
        val homeJsonString = assetLoader.getJsonString(requireContext(), "home.json" )
        Log.d("homeData", homeJsonString ?: "error")


        /*
        JSON 데이터 파싱
        중괄호 묶음 : jsonObject로 데이터 받기
        대괄호 묶음 : jsonArray로 데이터 받기
        */
        if (!homeJsonString.isNullOrEmpty()) {
            /*
            Gson 라이브러리 사용시 jsonObject, jsonArray로 json 데이터를 받지 않고
            바로 데이터 클래스 객체로 데이터를 받을 수 있다.
             */
            val gson = Gson()
            val homeData = gson.fromJson(homeJsonString, HomeData::class.java)

            // json 데이터를 key값으로 조회

            viewModel.title.observe(viewLifecycleOwner) { title ->
                toolbarTitle.text = title.text

                /*
                with인자 : Activity or Fragment
                load인자 : url 주소
                into인자 : 어떠한 리소스로 이미지 뷰에 할당할 것인가
                 */
                GlideApp.with(this)
                    .load(title.iconUrl)
                    .into(toolbarIcon)
            }

            viewModel.topBanners.observe(viewLifecycleOwner) { banners ->
                viewPager.adapter = HomeBannerAdapter().apply{
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


            viewPager.offscreenPageLimit = 3
            /*
            viewPager 슬라이드간 애니메이션을 구현하는 코드
            position : 사용자가 오른쪽으로 슬라이드하는지 왼쪽으로 슬라이드 하는지
             */
            viewPager.setPageTransformer { page, position ->
                // width값 위치조정
               page.translationX = position * -offset
            }
            /*
            TabLayout Indicator 구현
            인자가 interface인 경우 -> object로 구현 -> 필수 메소드 오버라이드 -> 메소드가 1개일 경우 람다식 가능
             */
            TabLayoutMediator(viewPagerIndicator, viewPager) { tab, position ->

            }.attach()
        }
   }
}
