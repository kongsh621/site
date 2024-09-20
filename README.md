
스프링부트를 이용한 사이트 만들기 프로젝트<br>
*이 레파지토리는 프로젝트를 마무리할 때 사용하기 시작하여 거의 완성본으로 시작하였습니다.
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

-카카오 로그인 연동<br>
https://github.com/kongsh621/site/blob/01a0019281c21831915ee641323c65c305cd47db/java/com/example/project01/controller/MemberController.java?plain=1#L173-L251<br><br>

-로그인, 쿠키에 아이디 저장, 기존 경로 저장<br>
https://github.com/kongsh621/site/blob/a23b64d4ce6271059d85f94801f379fc784b70c0/java/com/example/project01/controller/MemberController.java?plain=1#L122-L171<br>
https://github.com/kongsh621/site/blob/85166c38b687c93cbfb77936f59c7d9e427f32b9/js/login.js?plain=1#L31-L55<br>
쿠키<br>
https://github.com/kongsh621/site/blob/7615e54371b6e3e3641f3470657e7d0189329672/js/login.js?plain=1#L57-L92<br>
기존 경로<br><br>
-조회수 집계<br>
https://github.com/kongsh621/site/blob/c75df270134031b8b407785016eec3f146920c28/java/com/example/project01/controller/BoardController.java?plain=1#L152-L191<br>
-아이디 유효성 검사<br>
https://github.com/kongsh621/site/blob/e0184abeafc19c8aebd3138c41ea4871c598086d/java/com/example/project01/service/UserService.java?plain=1#L26-L30<br>
https://github.com/kongsh621/site/blob/bce467289d5faff26ab2f21bdcd0fd020bedcf3e/java/com/example/project01/service/UserService.java?plain=1#L80-L89

-회원가입 입력 내용 검사<br>
https://github.com/kongsh621/site/blob/85166c38b687c93cbfb77936f59c7d9e427f32b9/js/register.js?plain=1#L29-L181<br>

-비밀번호 암호화와 로그인 인증 로직<br>
https://github.com/kongsh621/site/blob/bce467289d5faff26ab2f21bdcd0fd020bedcf3e/java/com/example/project01/service/UserService.java?plain=1#L97-L109<br>
https://github.com/kongsh621/site/blob/bce467289d5faff26ab2f21bdcd0fd020bedcf3e/java/com/example/project01/service/UserService.java?plain=1#L64-L78<br>

-JavaMailSender를 이용한 메일인증 기능<br>
https://github.com/kongsh621/site/blob/85166c38b687c93cbfb77936f59c7d9e427f32b9/java/com/example/project01/service/MailServiceImpl.java?plain=1#L16-L71<br>
-찜 기능<br>
https://github.com/kongsh621/site/blob/c75df270134031b8b407785016eec3f146920c28/java/com/example/project01/controller/InfoBoardController.java?plain=1#L288-L345<br>
-평점 기능<br>
https://github.com/kongsh621/site/blob/61034ca8936824f59d038150a7b97b5d53a1d1f7/java/com/example/project01/controller/InfoBoardController.java?plain=1#L173-L219<br>
-첨부파일 저장<br>
https://github.com/kongsh621/site/blob/1a2ba765d6e058cc60db04e5139b75a84ccfd03e/java/com/example/project01/controller/BoardController.java?plain=1#L58-L98
