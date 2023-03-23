이글은 Udemy 안드로이드 IVY님의 쇼핑앱 강의를 듣고 정리하는 글 입니다.

# 기능 요구 사항

## UI

### 1. 스플래시 화면
![image](https://user-images.githubusercontent.com/91789276/227198772-6d445176-9dd8-447c-9cd2-e36891f43f69.png)

앱이 시작할 때 보여주는 스플래시 화면을 구현한다.

구현 방법 (APi level 30 이하를 기준)

1. drawable 파일에서 스플래시 화면을 디자인한다.
2. splash 화면을 사용할 activity를 생성한다.
3. Theme 파일에서 디자인한 splash 화면을 등록한다.
4. AndroidManifest에서 <activity>를 추가해 splash 액티비티가 첫 화면으로 나오게 지정한다.

### 바텀 네비게이션
![image](https://user-images.githubusercontent.com/91789276/227198991-a10d9f91-b408-487c-88f9-5b97965d5bb6.png)

바텀 네비게이션을 활용하여 홈, 카테고리, 장바구니 3개의 화면을 바텀 네비게이션 icon을 클릭 시 화면이 이동하도록 구현한다.

0. dependencies에 네이게이션 라이브러리를 추가한다.
1. menu 파일을 만들어 네비게이션에 활용할 icon과 title을 정의한다.
2. 네비게이션 파일에서 화면 간의 이동 관계를 정의한다. (드래그로 화면 간의 이동을 표현할 수 있음)
3. main 레이아웃에서 FragmentContainerView, BottomNavigationView를 정의한다.
4. main으로 사용할 activity에서 findNavController() 메소드를 통해 navController 객체를 생성한다.

### ViewModel, LiveData 활용
- UI를 업데이트 하기 위해 필요한 데이터와 UI를 연결시켜주는 ViewModel 클래스를 활용한다.
- ViewModel은 Data Layer의 repository 클래스에서 데이터를 받아오며 받아온 데이터를 LivaData<> 형태로 변환한다.
- Fragment에서 viewModel 생성은 ViewModelProviderFactory 클래스를 상속받은 ViewModelFactory 클래스에서 create 메서드를 통해 생성한다.

### Home 화면
![image](https://user-images.githubusercontent.com/91789276/227199123-05833c6c-705c-4a69-9961-11289d488436.png)

JSON 데이터를 가져와서 Home Fragment를 구현한다. 이 때 ViewPager2를 활용하여 여러 개의 banner를 스와핑할 수 있도록 한다.
이미지 url을 가져오는 라이브러리는 Glide 라이브러리를 사용한다.

1. viewPager, GSON 라이브러리를 dependencies에 추가한다.
2. fragment에 그릴 layout을 정의한다. 이 때 TabLayout을 활용해 스와이핑 시 어느 view를 가르키는지 확인한다.
3. JSON 데이터를 data class를 활용하여 객체로 만든다.
4. ListAdapter를 상속 받은 adapter를 구현한다. DiffUtil을 활용하여 JSON 데이터로 만든 객체를 비교하여 업데이트 여부를 확인한다.
5. Observer 클래스를 활용하여 adapter를 fragment로 연결시킨다.

### Category 화면 
![image](https://user-images.githubusercontent.com/91789276/227199234-2aaa5357-ac02-46bc-af79-b4a853d665f9.png)

Category 구현부터는 Firebase에 데이터를 저장하고 retrofit2 라이브러리를 활용한 REST API를 통해 서버로부터 데이터를 받아온다.
네트워크 통신은 데이터의 크기가 크면 시간이 오래걸릴 수 있기 때문에 코루틴을 활용하여 메인스레드가 아닌 백그라운드 스레드에서 데이터 통신을 구현한다.

1. Layout을 정의한다. 레이아웃을 재사용하기 위해 recyclerView로 구현한다. Recycler의 layoutManager는 GridLayout으로 지정한다.
2. 리사이클러뷰의 어댑터를 구현한다.
3. fragment에 어댑터를 할당한다.
4. 카테고리에서 한 카테고리를 클릭 시 카테고리 디테일 화면으로 넘어가게 하는 기능을 layout의 onClick 메소드로 추가한다.

### Category Detail 화면
![image](https://user-images.githubusercontent.com/91789276/227199495-f4322385-62e8-4a80-8031-9500ab33d188.png)

1. 인기 상품에 대한 Layout과 상품 상세 설명에 대한 Layout를 따로 정의한다. RecyclerView로 구현하며 인기 상품의 orientation은 horizontal로 정의한다.
2. 인기 상품 recyclerView의 어댑터와 상품 상세 설명 recyclerView의 어댑터를 정의한다.
3. fragment에 어댑터를 할당한다. 이 때 2개 이상의 어댑터를 하나로 정의하기 위해 concatAdapter를 활용한다.
4. Appbar 영역의 뒤로 가기 버튼 기능을 구현한다.

### Cart 화면
장바구니 화면에서는 Home화면 또는 Category Detail화면에서 상품에 장바구니 담기를 클릭하면 Cart화면에서 어떤 상품을 담았는지 표시한다.
앞선 화면들의 구성은 Firebase로 데이터를 받아왔지만 Cart화면에서는 Room 데이터베이스를 활용하여 데이터를 받아온다. 이는 오프라인 또는 앱이 종료되었을 때도 데이터를 저장하고 있어야하기 때문이다. 
![image](https://user-images.githubusercontent.com/91789276/227207174-a875b88b-ceb2-4e2f-a6b8-01e3ac2fbe27.png)
![image](https://user-images.githubusercontent.com/91789276/227207237-c5338697-6e1d-4f09-a024-ad05c4fbb4d5.png)


0. Room dependencies를 추가한다.
1. Cart화면을 구성할 Layout을 구현한다. (RecyclerView)
2. 리사이클러뷰 어댑터를 구현하고 cart fragment에 할당한다.
3. MaterialAlertDialogBuilder를 활용해 장바구니 버튼을 누르면 알림 문구가 나오도록 구현한다.
4. AppDatabase라는 추상 클래스를 만들어 데이터베이스를 만든다. 
5. 데이터베이스에 데이터를 insert하거나 Query하는 DAO Interface를 구현한다.
6. Cart repository에서 데이터베이스 데이터를 할당한다.




