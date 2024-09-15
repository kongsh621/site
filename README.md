스프링부트를 이용한 사이트 만들기 프로젝트
<br><br>
프로젝트 목적<br>
: 작품에 대한 정보를 얻고 이에 대해 함께 이야기할 수 있는 공간을 만듦<br>
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
주요 기능별 코드 정리<br>

카카오 로그인 연동<br>
https://github.com/kongsh621/site/blob/01a0019281c21831915ee641323c65c305cd47db/java/com/example/project01/controller/MemberController.java?plain=1#L173-L251<br><br>

아이디 유효성 검사<br>
https://github.com/kongsh621/site/blob/01a0019281c21831915ee641323c65c305cd47db/java/com/example/project01/controller/MemberController.java?plain=1#L82-L113<br>
https://github.com/kongsh621/site/blob/e0184abeafc19c8aebd3138c41ea4871c598086d/java/com/example/project01/service/UserService.java?plain=1#L26-L30<br>
https://github.com/kongsh621/site/blob/e0184abeafc19c8aebd3138c41ea4871c598086d/java/com/example/project01/service/UserService.java?plain=1#L94-L103<br>

회원가입 미작성 상태로 focusout시 알림 메시지<br>
https://github.com/kongsh621/site/blob/85166c38b687c93cbfb77936f59c7d9e427f32b9/js/register.js?plain=1#L29-L181<br>

BCryptPasswordEncoder 주입하여 비밀번호 암호화<br>
https://github.com/kongsh621/site/blob/748f36c4ecdfec0ab081aea1550a864fc19cd399/java/com/example/project01/service/UserService.java?plain=1#L21-L22<br>
https://github.com/kongsh621/site/blob/748f36c4ecdfec0ab081aea1550a864fc19cd399/java/com/example/project01/service/UserService.java?plain=1#L94-L101<br>
https://github.com/kongsh621/site/blob/748f36c4ecdfec0ab081aea1550a864fc19cd399/java/com/example/project01/service/UserService.java?plain=1#L64-L80<br>

BCryptPasswordEncoder 빈 등록 및 시큐리티 설정파일<br>
https://github.com/kongsh621/site/blob/b2eda5e0f9771ffdd866530d9ed36c31209781c9/java/com/example/project01/Config/SecurityConfig.java?plain=1#L17-L35<br>

JavaMailSender를 이용한 메일인증 기능<br>
https://github.com/kongsh621/site/blob/85166c38b687c93cbfb77936f59c7d9e427f32b9/java/com/example/project01/service/MailServiceImpl.java?plain=1#L16-L71<br>

평점 기능<br>
https://github.com/kongsh621/site/blob/61034ca8936824f59d038150a7b97b5d53a1d1f7/java/com/example/project01/controller/InfoBoardController.java?plain=1#L173-L219<br>
첨부파일 저장<br>
https://github.com/kongsh621/site/blob/61034ca8936824f59d038150a7b97b5d53a1d1f7/java/com/example/project01/controller/BoardController.java?plain=1#L69-L91<br>

쿠키에 아이디 저장 기능<br>
https://github.com/kongsh621/site/blob/85166c38b687c93cbfb77936f59c7d9e427f32b9/js/login.js?plain=1#L31-L55<br>
https://github.com/kongsh621/site/blob/01a0019281c21831915ee641323c65c305cd47db/java/com/example/project01/controller/MemberController.java?plain=1#L129-L137<br>

로그인 후 그전 주소로 돌아감<br>
https://github.com/kongsh621/site/blob/7615e54371b6e3e3641f3470657e7d0189329672/js/login.js?plain=1#L57-L92<br>
https://github.com/kongsh621/site/blob/01a0019281c21831915ee641323c65c305cd47db/java/com/example/project01/controller/MemberController.java?plain=1#L161-L171<br>

