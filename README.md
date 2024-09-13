스프링부트를 이용한 사이트 만들기 프로젝트
<br><br>
프로젝트 목적<br>
: 정보를 공유하고 이에 대해 함께 이야기할 수 있는 공간을 만듦<br>
<br><br>
주요 목표<br>
: 검색이 가능한 정보 게시판<br>
  평점에 따라 구성되는 추천 게시판<br>
  사진을 업로드 할 수 있는 자유 게시판이 있는 사이트 만들기<br>
<br><br>
<사용 기술><br>
백엔드<br>
-Spring Boot<br>
-Spring Web<br>
-Spring Security<br>
-Lombok<br>
-Mybatis<br>
<br><br>
프론트엔드<br>
-JSP<br>
-AJAX (일부 기능)<br>
-JSON<br>
-부트스트랩<br>
<br>

ERD<br>
https://blog.kakaocdn.net/dn/3q5Bm/btsJBPzQr0O/xsVkcoSUNnyEMoTtDZXuO1/img.png<br>
<br>
블로그<br>
https://myblog7363.tistory.com/
<br>
<br>
주요 기능별 코드 정리

카카오 로그인 연동
https://github.com/kongsh621/site/blob/369af88ba91c923a85af395007afa474e830b1e0/java/com/example/project01/controller/MemberController.java?plain=1#L203-L309

아이디 유효성 검사
https://github.com/kongsh621/site/blob/e0184abeafc19c8aebd3138c41ea4871c598086d/java/com/example/project01/controller/MemberController.java?plain=1#L92-L112
https://github.com/kongsh621/site/blob/e0184abeafc19c8aebd3138c41ea4871c598086d/java/com/example/project01/service/UserService.java?plain=1#L26-L30
https://github.com/kongsh621/site/blob/e0184abeafc19c8aebd3138c41ea4871c598086d/java/com/example/project01/service/UserService.java?plain=1#L94-L103

회원가입 미작성 상태로 focusout시 알림 메시지
https://github.com/kongsh621/site/blob/85166c38b687c93cbfb77936f59c7d9e427f32b9/js/register.js?plain=1#L29-L181

BCryptPasswordEncoder 주입하여 비밀번호 암호화
https://github.com/kongsh621/site/blob/b2eda5e0f9771ffdd866530d9ed36c31209781c9/java/com/example/project01/service/UserService.java?plain=1#L106-L116
BCryptPasswordEncoder 빈 등록 및 시큐리티 설정파일
https://github.com/kongsh621/site/blob/b2eda5e0f9771ffdd866530d9ed36c31209781c9/java/com/example/project01/Config/SecurityConfig.java?plain=1#L17-L35

JavaMailSender를 이용한 메일인증 기능
https://github.com/kongsh621/site/blob/85166c38b687c93cbfb77936f59c7d9e427f32b9/java/com/example/project01/service/MailServiceImpl.java?plain=1#L16-L71

버튼을 이용해 type별로 작품 분류 쿼리문
https://github.com/kongsh621/site/blob/85166c38b687c93cbfb77936f59c7d9e427f32b9/mapper/InfoBoardMapper.xml?plain=1#L67-L77

테이블 조인을 통한 관심 목록 가져오기
https://github.com/kongsh621/site/blob/85166c38b687c93cbfb77936f59c7d9e427f32b9/mapper/InfoBoardMapper.xml?plain=1#L122-L135

평점 기능
https://github.com/kongsh621/site/blob/85166c38b687c93cbfb77936f59c7d9e427f32b9/java/com/example/project01/controller/InfoBoardController.java?plain=1#L188-L244

첨부파일 저장
https://github.com/kongsh621/site/blob/85166c38b687c93cbfb77936f59c7d9e427f32b9/java/com/example/project01/controller/BoardController.java?plain=1#L65-L80

쿠키에 아이디 저장 기능
https://github.com/kongsh621/site/blob/85166c38b687c93cbfb77936f59c7d9e427f32b9/js/login.js?plain=1#L31-L55
https://github.com/kongsh621/site/blob/7615e54371b6e3e3641f3470657e7d0189329672/java/com/example/project01/controller/MemberController.java?plain=1#L150-L158

로그인 후 그전 주소로 돌아감
https://github.com/kongsh621/site/blob/7615e54371b6e3e3641f3470657e7d0189329672/js/login.js?plain=1#L57-L92
https://github.com/kongsh621/site/blob/7615e54371b6e3e3641f3470657e7d0189329672/java/com/example/project01/controller/MemberController.java?plain=1#L183-L195
