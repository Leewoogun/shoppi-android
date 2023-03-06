package com.example.app

import android.os.Bundle
import android.view.LayoutInflater
import android.view.SurfaceControl
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController

class HomeFragment : Fragment() {

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

        val button = view.findViewById<Button>(R.id.btn_enter_product_detail)
        button.setOnClickListener {

            /*
             FragmentManager는 앱 프래그먼트에서 프래그먼트를 추가, 삭제 또는 교체하고 백 스택에 추가하는 등의 작업을 실행하는 클래스
             HostActivity에 Fragment를 참조해야므로 parentFragmentManager
                     SurfaceControl.Transaction : Fragment의 추가 삭제 교체를 요청
            val transaction = parentFragmentManager.beginTransaction()
            // add 메소드를 사용하여 container view에 ProductDetailFragment 추가
            transaction.add(R.id.container_main, ProductDetailFragment())
            transaction.commit()
            */

            // navigation component를 이용하여 화면 전환
            // navigate의 인자로는 action값의 id를 참조한다.
            findNavController().navigate(R.id.action_home_to_product_detail)

        }

    }
}