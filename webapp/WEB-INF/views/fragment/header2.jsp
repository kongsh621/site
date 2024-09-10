<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
        <meta name="description" content="" />
        <meta name="author" content="" />
        <title>Simple Sidebar - Start Bootstrap Template</title>
        <!-- Favicon-->
        <link rel="icon" type="image/x-icon" href="../assets/favicon1.ico" />
        <!-- 드롭다운 버튼 -->
        <link href="../css/dropdown.css" rel="stylesheet" />
        <!-- Core theme CSS (includes Bootstrap)-->
        <link href="../css/styles1.css" rel="stylesheet" />
        <!-- Bootstrap icons-->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.5.0/font/bootstrap-icons.css" rel="stylesheet" />
        
        <!-- Custom fonts for this template -->
        <link href="../vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
        <link href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i" rel="stylesheet">
        
         <!-- Custom styles for this template -->
        <link href="../css/sb-admin-2.min.css" rel="stylesheet">


        <!-- jQuery를 CDN을 통해 로드하기 --> 
        <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
       
    
        <link href="../css/button.css" rel="stylesheet">
    </head>
    <body>
        <div class="d-flex" id="wrapper">
            <!-- Sidebar-->
            <div class="border-end bg-white" id="sidebar-wrapper">
                <div class="sidebar-heading border-bottom bg-light"><strong>뭐 볼래?</strong></div>
                <div class="list-group list-group-flush">
                    <a class="list-group-item list-group-item-action list-group-item-light p-3" href="/index">추천</a>
                    <a class="list-group-item list-group-item-action list-group-item-light p-3" href="../infoboard/?page=1">정보 게시판</a>
                    <a class="list-group-item list-group-item-action list-group-item-light p-3" href="/list">자유 게시판</a>
                </div>
            </div>
            <!-- Page content wrapper-->
            <div id="page-content-wrapper">
                <!-- Top navigation-->
                <nav class="navbar navbar-expand-lg navbar-light bg-light border-bottom">
                    <div class="container-fluid">
                        <button class="btn btn-primary" id="sidebarToggle">메뉴</button>
                        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation"><span class="navbar-toggler-icon"></span></button>
                        <div class="collapse navbar-collapse" id="navbarSupportedContent">
                            <ul class="navbar-nav ms-auto mt-2 mt-lg-0">
                                <li class="nav-item active"><a class="nav-link" href="/index"><strong>뭐 볼래?</strong></a></li>
                                <!-- <li class="nav-item"><a class="nav-link" href="#!">Link</a></li> -->
                                <!-- 로그인 전 -->
                            <c:choose>
                                <c:when test="${empty loginSession}">
                                    <li class="nav-item dropdown">
                                        <a class="nav-link dropdown-toggle" id="navbarDropdown" role="button" data-bs-toggle="dropdown" aria-haspopup="true" aria-expanded="false">내 정보</a>
                                        <div id="nav-content" class="dropdown-menu dropdown-menu-end" aria-labelledby="navbarDropdown">
                                            <a class="dropdown-item" href="../member/save"><button>회원가입</button></a>
                                            <a class="dropdown-item" href="../member/login" id="loginBt"><button id="loginBt">로그인</button></a>
                                        </div>
                                    </li>
                                </c:when>
                                <c:otherwise>
                                    <!-- 로그인 후 -->
                                    <li class="nav-item dropdown">
                                        <a class="nav-link dropdown-toggle" id="navbarDropdown" role="button" data-bs-toggle="dropdown" aria-haspopup="true" aria-expanded="false">${loginSession.nickname}</a>
                                        <div class="dropdown-menu dropdown-menu-end" aria-labelledby="navbarDropdown">
                                            <a class="dropdown-item" href="/logout" id="loinBt">로그아웃</a>
                                            <div class="dropdown-divider"></div>
                                            <a class="dropdown-item" href="../mypage/">내 정보</a>
                                            <a class="dropdown-item" href="../mypage/mypost">내가 쓴 글</a>
                                            <a class="dropdown-item" href="../mypage/mycomment">내가 쓴 댓글</a>
                                            <a class="dropdown-item" href="../infoboard/favorites">관심 목록</a>
                                        </div>
                                    </li>
                                </c:otherwise>
                            </c:choose>
                            </ul>
                        </div>
                    </div>
                </nav>


    </body>
</html>
