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
        <title>Shop Homepage - Start Bootstrap Template</title>
        <!-- Favicon-->
        <link rel="icon" type="image/x-icon" href="assets/favicon.ico" />
        <!-- Bootstrap icons-->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.5.0/font/bootstrap-icons.css" rel="stylesheet" />
        <!-- Core theme CSS (includes Bootstrap)-->
        <link href="css/styles.css" rel="stylesheet" />
    </head>
    <body>
        <!-- Navigation-->
        <nav class="navbar navbar-expand-lg navbar-light bg-light">
            <div class="container px-4 px-lg-5">
                <a class="navbar-brand" href="/index"><strong>뭐 볼래?</strong></a>
                <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation"><span class="navbar-toggler-icon"></span></button>
                <div class="collapse navbar-collapse" id="navbarSupportedContent">
                    <ul class="navbar-nav me-auto mb-2 mb-lg-0 ms-lg-4">
                        <li class="nav-item"><a class="nav-link active" aria-current="page" href="/infoboard/">정보 게시판</a></li>
                        <li class="nav-item"><a class="nav-link" href="/list">자유 게시판</a></li>
                    <c:choose>
                        <c:when test="${empty loginSession}">
                            <li class="nav-item dropdown"> <!-- 로그인 전 -->
                                <a class="nav-link dropdown-toggle" id="navbarDropdown" href="/member/login" role="button" data-bs-toggle="dropdown" aria-expanded="false">내 정보</a>
                                <ul class="dropdown-menu" aria-labelledby="navbarDropdown">
                                    <li><a class="dropdown-item" href="../member/save">회원가입</a></li>
                                    <li><a class="dropdown-item" href="../member/login" id="">로그인</a></li>
                                </ul>
                            </li>
                        </c:when>
                        <c:otherwise>
                            <li class="nav-item dropdown"> <!-- 로그인 상태 -->
                                <a class="nav-link dropdown-toggle" id="navbarDropdown" href="/member/login" role="button" data-bs-toggle="dropdown" aria-expanded="false">${loginSession.nickname}</a>
                                <ul class="dropdown-menu" aria-labelledby="navbarDropdown">
                                    <li><a class="dropdown-item" href="/logout">로그아웃</a></li>
                                    <li><hr class="dropdown-divider" /></li>
                                    <li><a class="dropdown-item" href="../mypage/">내 정보</a></li>
                                    <li><a class="dropdown-item" href="../mypage/mypost">내가 쓴 글</a></li>
                                    <li><a class="dropdown-item" href="../mypage/mycomment">내가 쓴 댓글</a></li>
                                </ul>
                            </li>
                        </c:otherwise>
                    </c:choose>
                    </ul>
                    <form class="d-flex">
                        <button class="btn btn-outline-dark">
                            <i><a href="/infoboard/favorites" style="text-decoration: none; color: black;">관심 목록</a></i>
                            <span class="badge bg-dark text-white ms-1 rounded-pill"></span>
                        </button>
                    </form>
                </div>
            </div>
        </nav>
        <!-- Header-->
        <header class="bg-dark py-5">
            <div class="container px-4 px-lg-5 my-5">
                <div class="text-center text-white">
                    <h1 class="display-4 fw-bolder">뭐 볼래?</h1>
                    <p class="lead fw-normal text-white-50 mb-0">드라마 영화 정보 공유</p>
                </div>
            </div>
        </header>
        <!-- Begin Page Content -->
        <div class="container-fluid">

            <!-- DataTales Example -->
            <div class="card shadow mb-4">

                <section class="py-4">
                    <div class="container px-4">
                        <div class="row row-cols-1 row-cols-sm-2 row-cols-md-3 row-cols-lg-4 g-4" id="board-container">
                            <c:forEach var="board" items="${list}">
                                <div class="col">
                                    <div class="card h-100">
                                        <!-- Product image -->
                                        <img class="card-img-top" src="<c:url value='../img/info/${board.title}.jpg'/>" alt="${board.title}" />
                                        <!-- Product details -->
                                        <div class="card-body text-center">
                                            <!-- Product name -->
                                            <h5 class="fw-bolder">${board.title}</h5>
                                            <!-- Product reviews -->
                                            <div class="d-flex justify-content-center small text-warning mb-2">
                                                ${board.total_rate}
                                            </div>
                                            <!-- 방영연도 추가하기 -->
                                            <p class="text-muted">${board.regdate}</p>
                                        </div>
                                        <!-- Product actions -->
                                        <div class="card-footer p-4 pt-0 border-top-0 bg-transparent text-center">
                                            <a class="btn btn-outline-dark" href="../infoboard/readinfo?id=${board.id}">더보기</a>
                                        </div>
                                    </div>
                                </div>
                            </c:forEach>
                        </div>
                    </div>
                </section>
        <!-- Footer-->
        <footer class="py-5 bg-dark">
            <div class="container"><p class="m-0 text-center text-white">공지윤</p></div>
        </footer>
        <!-- Bootstrap core JS-->
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>
        <!-- Core theme JS-->
        <script src="js/scripts.js"></script>
        <!-- 로그인 리다이렉트 URL -->
        <script src="../js/login.js"></script>

    </body>
</html>
