이글은 Udemy 안드로이드 IVY님의 쇼핑앱 강의를 듣고 정리하는 글 입니다.

# 기능 요구 사항

## UI

### 1. 스플래시 화면
앱이 시작할 때 보여주는 스플래시 화면을 구현한다.

구현 방법 (APi level 30 이하를 기준)

1. drawable 파일에서 스플래시 화면을 디자인한다.
2. splash 화면을 사용할 activity를 생성한다.
3. Theme 파일에서 디자인한 splash 화면을 등록한다.
4. AndroidManifest에서 <activity>를 추가해 splash 액티비티가 첫 화면으로 나오게 지정한다.

### 바텀 네비게이션
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
JSON 데이터를 가져와서 Home Fragment를 구현한다. 이 때 ViewPager2를 활용하여 여러 개의 banner를 스와핑할 수 있도록 한다.
이미지 url을 가져오는 라이브러리는 Glide 라이브러리를 사용한다.

1. viewPager, GSON 라이브러리를 dependencies에 추가한다.
2. fragment에 그릴 layout을 정의한다. 이 때 TabLayout을 활용해 스와이핑 시 어느 view를 가르키는지 확인한다.
3. JSON 데이터를 data class를 활용하여 객체로 만든다.
4. ListAdapter를 상속 받은 adapter를 구현한다. DiffUtil을 활용하여 JSON 데이터로 만든 객체를 비교하여 업데이트 여부를 확인한다.
5. Observer 클래스를 활용하여 adapter를 fragment로 연결시킨다.

### Category 화면 
Category 구현부터는 Firebase에 데이터를 저장하고 retrofit2 라이브러리를 활용한 REST API를 통해 서버로부터 데이터를 받아온다.
네트워크 통신은 데이터의 크기가 크면 시간이 오래걸릴 수 있기 때문에 코루틴을 활용하여 메인스레드가 아닌 백그라운드 스레드에서 데이터 통신을 구현한다.

1. Layout을 정의한다. 레이아웃을 재활용하기 위해 recyclerView로 구현한다. 
2. 리사이클러뷰의 어댑터를 구현한다.
3. fragment에 어댑터를 할당한다.
4. 카테고리에서 한 카테고리를 클릭 시 카테고리 디테일 화면으로 넘어가게 하는 기능을 layout의 onClick 메소드로 추가한다.
5. 추후 추가 예





