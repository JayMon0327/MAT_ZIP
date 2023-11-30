# [팀프로젝트] 맛.JAVA - 맛.ZIP
#### 💡 맛.ZIP은 “진짜 믿고 먹을 수 있는 맛집” 을 공유하는 플랫폼입니다.
* 맛.JAVA 팀은 맛집 탐방에 누구보다 진심인 사람들이 뭉친 팀입니다. 🍔
* 평소에 모두가 겪고 있던 부정한 광고, 믿을 수 없는 후기 속에서 소비자들이 믿고 방문할 수 있는 맛집을 모아 볼 수 있는 사이트의 필요성을 느꼈습니다.
* 그래서, 영수증 2회 이상 인증된 맛집만 등록되도록 해서 신뢰도 및 만족도가 높은 맛집만 선별하여 소비자에게 제공하는 목적으로 개발을 진행했습니다.
* 국내 운영 중인 맛집 추천 사이트, 대형 포털 지도 사이트의 사례 분석을 통해, 웹사이트 기능의 방향성을 "진정성 있는 맛집 공유"로 초점을 맞췄습니다.
* 맛집을 좋아하는 사람들 뿐만 아니라, 맛집을 좋아하는 사람들의 방문을 원하는 요식업계 사장님들도 타켓팅한 사장님 전용 구독 서비스 및 노출 배너 광고를 BM으로 설정했습니다.

<br>

## 1. 제작 기간
#### `2023년 4월 28일 ~ 6월 9일 (1개월)`

<br>

## 2. 사용 기술
### `Back-end`
* Java 8
* Spring Framework 5
* Junit5
* Maven
* Mybatis

### `Front-end`
* JSP
* JQuery 3.6.4
* BootStrap 4

### `DevOps`
* AWS EC2, S3, RDS, CloudFront, Route 53, ALB
* Tomcat 8.5
* MySQL 8.0.32

### `Collaboration`
* Git, Sourcetree 
* Slack 
* Notion
* Google Drive

### `External API`
* Toss Payments API
* Google chart API
* Naver AI Sentiment


<br>

## 3. 프로젝트 구조
```
🔻📁main
    🔻📁java
        🔻📁boss
            🔻📁controller
                BoardController.java
                Boss_memberController.java
                ChartController.java
                ComController.java
                PaymentController.java 
            🔻📁dao
                BoardDAO.java
                Boss_memberDAO.java
                ChartDAO.java
                ComDAO.java
                MemberDAO.java
                PaymentDAO.java
                boss_ReviewDAO.java
                🔻📁implementations
                    boss_ReviewDAOImpl.java
            🔻📁model
                BoardVO.java
                Boss_memberVO.java
                ChartVO.java
                ComVO.java
                LikesVO.java
                MemberVO.java
                PaymentResponse.java
                PaymentVO.java
                return_CustomerCountVO.java
                return_OrderCountVO.java
                return_OrderTotalVO.java
            🔻📁service
                MemberAndPaymentService.java
                ChartService.java
                PaymentService.java
                SentimentService.java
                boss_ReviewService.java
                🔻📁Implementations
                    boss_ReviewServiceImpl.java
    🔻📁resources
        🔻📁mapper
            BoardMapper.xml
            BossMapper.xml
            ChartMapper.xml
            Chart_reviewMapper.xml
            ComMapper.xml
            PaymentMapper.xml
    🔻📁webapp
        🔻📁WEB-INF
            🔻📁views
                🔻📁boss
                    board_detail.jsp
                    board_insert.jsp
                    board_delete.jsp
                    board_insertcom.jsp
                    board_list.jsp
                    board_search.jsp
                    board_update.jsp
                    tossFail.jsp
                    tossSuccess.jsp
        🔻📁boss
            boardInsert.jsp
            boardUpdate.jsp
            board_index.jsp
            boss_chart.jsp
            boss_login.jsp
            boss_member.jsp
            paymentTest.jsp
```
<br />

## 4. 기능 구현
* #### `[이후성] 사장 커뮤니티`
  * 토스 페이 API를 이용한 트랜잭션 구독결제 시스템과 예외처리
  * Ajax와 Restful 방식으로 게시판 게시글 생성, 수정, 삭제, 검색 기능, '좋아요' 기능과 페이징 기능
  * DB에서 데이터 선별, SQL문 로직작성, 구글 chart API로 매출차트 및 재방문 차트 구현
  * Clova API를 사용하여 손님들의 리뷰 데이터를 기반으로 감정분석 결과를 차트화
    
| <img src ="https://github.com/chujaeyeong/MAT_ZIP_readme_chujy/assets/123634960/97064c73-b97d-417d-9e33-54ca1a7a96b5" width="1150" height="750" /> | 
<br />
<img src="https://github.com/JayMon0327/Mat.zip_Boss/assets/124110982/92703863-36eb-43db-8eb2-43b1276caecc">


<br>


## 5. ERD 설계
<img src="https://user-images.githubusercontent.com/123634960/242927505-6d8c1885-fd63-41a2-84c7-c521fcce39e7.png">

<br>

## 5. 핵심 기능 설명 & 트러블 슈팅
#### [이후성] 사장 커뮤니티
<details>
  <summary>📌핵심 기능 설명</summary>
  
  ##### `1. 토스 API를 이용한 결제하기`
  * 먼저, JavaScript 코드에서는 'https://js.tosspayments.com/v1/payment' JavaScript 라이브러리를 사용하여 TossPayments 객체를 초기화합니다. 이 객체는 토스 결제 클라이언트 키인 clientKey를 인자로 받아, 결제 시스템과의 연결을 설정합니다.
	
  * 사용자가 '결제하기' 버튼을 클릭하면, jQuery를 이용한 이벤트 처리를 통해 requestPayment 함수가 호출됩니다. 이 함수 내부에서는 두 가지 주요 작업을 수행합니다: 첫째, jQuery의 $.ajax 메서드를 사용하여 사용자가 입력한 상점 ID(storeId)의 중복 여부를 서버에 비동기적으로 요청하고 응답을 처리합니다. 둘째, 중복되지 않는 storeId를 확인한 후, 랜덤으로 orderID를 생성합니다. 이렇게 생성된 orderID는 결제 요청 시 사용됩니다. 이 두 작업이 모두 성공적으로 이루어진 경우에만 결제 요청이 이루어집니다.
	
  * 결제 요청은 TossPayments API로 전송되며, 결제 정보를 담은 JavaScript 객체를 JSON.stringify 함수를 사용하여 JSON 문자열로 변환한 뒤 전송합니다. 이 때, Apache의 CloseableHttpClient와 HttpPost를 사용하여 HTTP 요청을 보내며, 요청 헤더를 setHeader 메서드로 설정하고, 요청 엔티티를 StringEntity 객체를 통해 설정합니다. 결제가 성공적으로 이루어지면, 결제 성공 URL로 이동하게 됩니다.
	
  * **‼결과‼** 결제하기 버튼 클릭 시 토스 결제 API와 연결되어 상점 ID의 중복 여부를 체크하고 결제를 진행합니다.	
<!--   * [👉이미지로 전체 흐름 확인하기](https://user-images.githubusercontent.com/84839167/161939355-cac8c85a-0e30-429c-909a-fde92dd30b57.png) -->	
	
<br>	
	
  ##### `2. 사장 회원 등록`
  * 결제가 성공적으로 완료되면 다음 단계로 넘어가서, 상점 ID(storeId)와 세션의 user_id를 이용해 사장 멤버로의 회원 등록을 처리합니다. 이 과정은 jQuery와 AJAX를 사용하여 클라이언트에서 서버로 비동기적으로 요청을 보내는 방식으로 처리됩니다. 이 AJAX 요청은 특정 엔드포인트인 '/innerJoinAndInsert'로 전송되며, 서버에서는 이 요청을 받아 처리하게 됩니다.
	
  * 서버 사이드에서는 MemberAndPaymentService 클래스가 이 요청을 처리합니다. MemberAndPaymentService는 의존성 주입(Dependency Injection)을 통해 필요한 객체를 주입받습니다. 이 클래스는 회원 등록과 결제를 동시에 처리하는 트랜잭션을 관리합니다.
	
  * 클라이언트에서는 AJAX 요청의 성공 여부에 따라 콘솔에 성공 또는 실패 메시지를 출력합니다. 이때 성공 메시지가 출력되는 경우는 서버에서 회원 등록이 정상적으로 완료되었을 때이며, 그렇지 않은 경우 실패 메시지가 출력됩니다. 회원 등록 요청이 성공적으로 처리되면, 클라이언트에서는 이 storeId를 세션에서 제거합니다. 이를 통해 사용자의 인터페이스를 업데이트하고, 상점 ID를 재사용 가능하게 만듭니다.
	
  * **‼결과‼** 서버에서 회원 등록이 성공적으로 처리되면, 클라이언트 콘솔에 성공 메시지가 출력되고, 상점 ID는 세션에서 제거됩니다.
<!--   * [👉이미지로 전체 흐름 확인하기](https://user-images.githubusercontent.com/84839167/161939367-2daf8776-9865-45d0-94bf-3eb7ba5bf886.png) -->

<br>	
	
  ##### `3. 결제 내역 저장하기`
  * 결제가 성공적으로 완료된 후, 결제 내역은 서버로 전송됩니다. 이 정보는 /payment 엔드포인트로 전송되며, 서버는 이 정보를 받아 데이터베이스에 저장합니다. 이 때의 서버 사이드 처리는 PaymentService와 PaymentController 클래스에서 수행되며, 이들 클래스 역시 의존성 주입을 통해 필요한 객체를 주입받습니다.
	
  * PaymentVO 객체에는 주문 ID, 결제 금액, 주문 이름 등의 정보가 포함되어 있습니다. 이러한 정보는 서버로부터 받은 응답에서 추출하며, 이 과정에서 Apache의 HttpComponents 클라이언트를 사용합니다.
	
  * 클라이언트는 CloseableHttpClient 객체를 사용하여 서버로 HTTP 요청을 전송합니다. 이때 HttpPost 객체를 사용하여 요청 헤더와 엔티티를 설정합니다. 헤더는 클라이언트와 서버가 데이터를 어떻게 처리해야 할지에 대한 정보를 전달하고, 엔티티는 POST 요청을 통해 서버로 전송될 데이터를 담고 있습니다.
	
  * 요청이 서버로 전송된 후, 클라이언트는 CloseableHttpResponse 객체를 통해 서버로부터의 응답을 받습니다. 서버로부터 받은 응답은 EntityUtils의 toString 메서드를 사용하여 문자열로 변환합니다. 이 문자열은 JSON 형태로 되어 있으므로, JsonParser.parseString 메서드를 사용하여 JSON 문자열을 JsonObject로 파싱합니다.
	
  * 이렇게 파싱된 JsonObject에서, 주문 ID, 결제 금액, 주문 이름 등의 정보를 추출하여 PaymentVO 객체에 설정합니다. 이렇게 생성된 PaymentVO 객체는 서버에 보내집니다. 이를 통해 서버는 결제가 성공적으로 이루어졌음을 확인하고, 해당 결제 정보를 데이터베이스에 저장할 수 있습니다.
	
  * **‼결과‼** 결제 정보가 서버에 전달되고, 해당 정보가 데이터베이스에 성공적으로 저장됩니다.
<!--   * [👉이미지로 전체 흐름 확인하기](https://user-images.githubusercontent.com/84839167/161939367-2daf8776-9865-45d0-94bf-3eb7ba5bf886.png) -->

</details>
	
<details>
  <summary>⚽트러블 슈팅</summary>
	
  ##### `1. 토스 결제 API에서 결제 응답값이 null로 반환되는 문제`
  * 첫 번째 시도 : 클라이언트에서 결제 요청 값 설정 -> ❌비정상작동
    * 'PaymentKey, PaymentType, orderId, amount 값 모두 필요하다'고 웹 콘솔 에러 메시지가 나왔는데, 결국 PaymentKey와 PaymentType 값은 서버에서 설정해야 한다는 걸 알게 됐다. 이게 원인이었나보다 싶어서 orderId와 amount 값만 요청에 넣었는데, 결제는 되는데 결제 성공 페이지에서 결제 응답값을 여전히 못 받아왔다.
  * 두 번째 시도 : API 요청을 서버에서 처리 -> ⭕정상작동!

	클라이언트에서 API 요청을 처리하려니 보안 문제가 생긴다는 걸 알게 되었다. 그래서 결제 응답값을 JSON 형태로 받아오는 것도 서버에서 처리하도록 바꿨다. 서비스 레이어를 분리하고, Secret Key를 Base64로 인코딩해서 요청을 보내니까 정상적으로 동작했다. 예외 처리도 try-catch 구문을 써서 해결했고, 요청이 성공하면 200이라는 HTTP OK 신호가 오는 것을 이용했다. 그리고 응답 헤더에 'Content-Type: application/json'을 써서 JSON으로 파싱된 값을 클라이언트에 전달하니까 응답정보도 잘 표시됐다.

	이번 오류를 통해서 다양한 것을 배울 수 있었다. 클라이언트에서 API 요청을 처리하는 것은 보안 문제가 있으니 서버에서 처리하는 것이 좋다는 것, 서비스 레이어를 분리하는 방법, 예외 처리하는 방법, Secret Key를 Base64로 인코딩하는 방법, 그리고 'Content-Type: application/json' 헤더를 사용해서 JSON으로 파싱된 값을 클라이언트에 전달하는 방법 등을 알게 되었다.
	
	```
	String encodedSecretKey = Base64.getEncoder().encodeToString((secretKey + ":").getBytes()); Base64인코딩 하는 코드
	```
	
<br>
	
  ##### `2. SQL 쿼리문 syntax 에러`
  * 첫 번째 발견 : mapper에서 쿼리문 작성 후 실행 -> ❌syntax 에러 발생
    * Mysql 문법에 맞지 않아 에러가 발생했었다. 쿼리문 작성에서 잘못됐다고 판단하였고, 확인해본 결과 부등호와 같은 특수 문자 때문에 XML 파싱 오류가 발생하는 것을 확인하였다. 이를 해결하기 위해 `<![CDATA[]]>`를 이용해 해당 부분을 감싸 처리하였으나, 이후 MySQL에서는 다시 문법 오류가 발생했다.
  * 해결 방법 : XML 엔티티 사용 -> ⭕정상작동!

	XML 문법에 따르면, <, >와 같은 특수 문자는 태그를 의미하기에 이들을 문자 그대로 사용하면 문제가 발생한다. 따라서 이런 문자를 사용할 때는 XML 엔티티를 사용해야 하며, <는 `&it;`로, >는 `&gt;`로 변경하였다. 이러한 변경을 통해 기능이 정상적으로 작동하였다.

	이번 오류를 통해 SQL 쿼리문을 작성하는 과정에서 XML 오류를 어떻게 해결할 수 있는지, 또 XML에서 특수 문자를 어떻게 처리하는지에 대해 배울 수 있었다. 
	
<br>	
	
 ##### `3. AI 감정분석 API 사용시 null 에러`
감정 분석 API를 통해 리뷰 데이터의 분석을 요청했을 때, 응답값 중 '중립'만 반환되는 문제가 발생했다. 분석 대상인 'content'가 '???' 형태 혹은 null로 응답 받아지는 것이 원인이었다.
  * 첫 번째 시도 : 함수 내부에서 여러 ajax 요청을 동시에 보내는 것이 문제라고 판단해서 promise 문법을 사용해 순차적 비동기 처리를 시도 -> ❌비정상작동
    
  * 두 번째 시도 : JSON 형식으로 요청이 되지 않아 발생하는 문제인지 확인하기 위해 JSON.stringify를 사용하여 정상적으로 JSON 요청을 보냄 -> ❌비정상작동
  * 세 번째 시도 : 리뷰 데이터 자체가 DB에서 UTF-8로 인코딩되지 않아 발생하는 문제인지 확인하기 위해 데이터를 utf8mb4로 인코딩 -> ❌비정상작동
  * 네 번째 시도 : 스프링 프레임워크에서 DB와 연결 시 UTF-8 설정이 안 되어 있는 문제인지 확인하기 위해 spring의 root-context.xml 파일을 확인 -> ❌비정상작동
  * 다섯 번째 시도 : API 요청을 보낼 때 인코딩/디코딩 문제인지 확인하기 위해 클라이언트에서 요청을 인코딩하여 보내고, 서버에서 디코딩하여 받은 후 서버에서 API 요청을 보냄 -> ❌비정상작동
  * 여섯 번째 시도 : 서버에서 API 요청을 보낼 때 base64로 인코딩하고 처리 후 클라이언트로 전달하였고, 클라이언트 측에서 디코딩하여 출력 -> ❌비정상작동
  * 일곱 번째 시도 : HttpEntity 객체 사용 및 ContentType에 UTF-8 명시 -> ⭕정상 작동!

	네이버 감정분석 API 공식문서를 보면 요청 헤더에 UTF-8을 명시하라고 되어있다. 이를 적용해 보니 정상적으로 요청이 이루어졌다.
	
	```
	headers.setContentType(new MediaType("application", "json", StandardCharsets.UTF_8));
	```

	이번 문제를 통해 API 요청을 보낼 때 인코딩과 헤더 설정의 중요성을 깨닫게 되었다. 또한, 공식문서를 주의 깊게 읽는 것의 중요성도 다시 한번 인지하게 되었다.
	
* 추가 에러 발생
   * 두 번째 문제점 : API 요청과 응답이 정상적으로 이루어지고, 긍정, 부정, 중립 응답도 정상적으로 받아오는 상황에서, 응답받은 페이지에서 'content'를 화면에 표시할 때 '???' 형태로 표시되는 문제가 발생
원인 분석 결과, 서비스 파일과 응답 타입 및 인코딩 형식을 맞추는 것이 아니라, 클라이언트 측과 맞추어야 했다. 감정 분석 API의 다른 오류 해결을 위해 코드를 수정하는 과정에서 이 부분을 놓치게 된 것으로 파악되었다.
	
   * 해결방법 : 컨트롤러 매핑에서 받아오는 타입을 클라이언트 측과 맞추어주니, 'content'가 정상적으로 출력되는 것을 확인할 수 있었다.
	
	```
	@GetMapping(value = "/analyze/{storeId}", produces = "application/json; charset=UTF-8")
	```

     이를 통해, 클라이언트와 서버 간 데이터 교환 시 데이터 형식과 인코딩 방식의 중요성을 재인식하게 되었다. 또한, API를 사용하면서 생기는 다양한 문제에 대응하기 위해서는 깊은 이해와 정확한 문제 파악 능력이 필요하다는 것을 깨달았다.

</details>	

