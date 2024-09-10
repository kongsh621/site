<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ko">
<head>
<%@ include file="/WEB-INF/views/fragment/header2.jsp" %>
    <meta charset="UTF-8">
    <title>Log in</title>
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
    <meta name="description" content="" />
    <meta name="author" content="" />
  
</head>

<body class="d-flex flex-column min-vh-100">

    <main class="login-form">
        <div class="container">
            <div class="row justify-content-center">
                <div class="col-md-6">
                    <div class="card">
                        <div class="card-header">로그인</div>
                        <div class="card-body">
                            <form action="/member/login" method="Post" id="loginForm">
                                <div class="form-group row">
                                    <label for="email_address" class="col-md-4 col-form-label text-md-right">아이디</label>
                                    <div class="col-md-6">
                                        <input type="text" id="email_address" class="form-control" name="email" placeholder="example@naver.com" required autofocus>
                                    </div>
                                </div>
    
                                <div class="form-group row">
                                    <label for="password" class="col-md-4 col-form-label text-md-right">비밀번호</label>
                                    <div class="col-md-6">
                                        <input type="password" id="password" class="form-control" name="password" placeholder="비밀번호"required>
                                    </div>
                                </div>
    
                                <div class="form-group row">
                                    <div class="col-md-6 offset-md-4">
                                        <div class="checkbox">
                                            <label>
                                                <input type="checkbox" name="saveId"> 아이디 저장
                                            </label>
                                        </div>
                                    </div>
                                </div>
    
                                <div class="form-group row">
                                    <div class="col-md-6 offset-md-4">
                                    <button type="submit" class="btn btn-primary">
                                        로그인
                                    </button>
                                    <a href="https://kauth.kakao.com/oauth/authorize?response_type=code&client_id=e879f28762d8052b2499d5b104b296aa&redirect_uri=http://localhost:8080/member/auth/kakao/callback"><img src="<c:url value='/img/kakao_login_medium_narrow.png'/>"
                                        style="width:130px; height:40px;" alt="카카오 로그인"></a>
                                    </div>
                                </div>
                                <div class="col-md-6 offset-md-4">
                                    <a href="/member/idfind" class="btn btn-link">
                                        아이디/비밀번호 찾기
                                    </a>
                                </div>
    
                                <div class="col-md-8 offset-md-4">
    
                                </div>
    
                        </div>
                        </form>
                            <c:if test="${param.error != null}">
                                <p style="color:red;">Invalid username or password</p>
                            </c:if>
                    </div>
                </div>
            </div>
        </div>
        </div>
    
    </main>
    

       <!-- 헤더 마무리 -->
    </div>
</div>
<%@ include file="/WEB-INF/views/fragment/footer2.jsp" %>
 
    </body>
</html>
