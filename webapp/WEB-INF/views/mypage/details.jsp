<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title>My Page</title>
    <%@ include file="/WEB-INF/views/fragment/header2.jsp" %>
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
    <meta name="description" content="" />
    <meta name="author" content="" />
    <script src="https://code.jquery.com/jquery.min.js"></script>
    <script src="//maxcdn.bootstrapcdn.com/bootstrap/latest/js/bootstrap.min.js">
    // <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>

</head>

<body>
 
    <div id="page-content-wrapper">
        <div class="container-fluid">
            <div class="col-sm-11 page">
                    <div class="row justify-content-center">
                        <div class="col-md-8">
                            <div class="card">
                              <div class="card-header">마이페이지</div>
                                <div class="card-body">

                                    <c:if test="${empty kakaoLogin}">
                                        <div class="form-group row">
                                            <label for="email" class="col-md-4 col-form-label text-md-right">아이디:</label>
                                            <div class="col-md-6">
                                                <input type="text" readonly class="form-control-plaintext" id="email" value="${member.email}">
                                            </div>
                                        </div>
                                    </c:if>

                                        <div class="form-group row">
                                            <input type="hidden" id="id" value="${member.id}">
                                            <label for="name" class="col-md-4 col-form-label text-md-right">이름:</label>
                                            <div class="col-md-6">
                                                <input type="text" readonly class="form-control-plaintext" id="name" value="${member.name}">
                                            </div>
                                        </div>

                                        <div class="form-group row">
                                            <label for="nickname" class="col-md-4 col-form-label text-md-right">닉네임:</label>
                                            <div class="col-md-6">
                                                <input type="text" readonly class="form-control-plaintext" id="nickname" value="${member.nickname}">
                                            </div>
                                        </div>

                                        <div class="form-group row">
                                            <label for="age" class="col-md-4 col-form-label text-md-right">나이:</label>
                                            <div class="col-md-6">
                                                <input type="text" readonly class="form-control-plaintext" id="age" value="${member.age}">
                                            </div>
                                        </div>

                                        <div class="form-group row">
                                            <label for="regdate" class="col-md-4 col-form-label text-md-right">가입일:</label>
                                            <div class="col-md-6">
                                                <input type="text" readonly class="form-control-plaintext" id="regdate" value="${member.regdate}">
                                            </div>
                                        </div>

                                        <div class="form-group row">
                                            <label for="updatedate" class="col-md-4 col-form-label text-md-right">최근 수정일:</label>
                                            <div class="col-md-6">
                                                <input type="text" readonly class="form-control-plaintext" id="updatedate" value="${member.updatedate}">
                                            </div>
                                        </div>
                                        <c:if test="${not empty kakaoLogin}">
                                            <div class="col-md-10 offset-md-3 row">
                                                <div class="col-md-3">
                                                    <a href="/member/update"><button class="btn btn-primary">회원정보 변경</button></a>
                                                </div>
                                                <div class="col-md-3">
                                                    <button class="btn btn-primary" id="unlinkButton">연동 탈퇴</button>
                                                    <input type="hidden" value="${Access_Token}" id="Access_Token">
                                                </div>
                                            </div>
                                        </c:if>

                                        <c:if test="${empty kakaoLogin}">
                                            
                                            <div class="col-md-10 offset-md-2 row">
                                                <div class="col-md-3">
                                                    <a href="/member/update"><button class="btn btn-primary">회원정보 변경</button></a>
                                                </div>

                                                <div class="col-md-3">
                                                    <a href="/mypage/setpass"><button class="btn btn-primary">비밀번호 변경</button></a>
                                                </div>
                                                <div class="col-md-3">
                                                    <a href="/member/delete?id=${member.id}"><button class="btn btn-primary" id="delete">회원 탈퇴</button></a>
                                                </div>
                                            </div>
                                        </c:if>
                                </div>
                              </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<!-- 헤더 마무리 -->
    </div>
</div>

<%@ include file="/WEB-INF/views/fragment/footer2.jsp" %>
<!-- 카카오 연동 아이디 탈퇴  -->
<script src="https://t1.kakaocdn.net/kakao_js_sdk/2.7.2/kakao.min.js"
integrity="sha384-TiCUE00h649CAMonG018J2ujOgDKW/kVWlChEuu4jK2vxfAAD0eZxzCKakxg55G4" crossorigin="anonymous"></script>
<script src="${pageContext.request.contextPath}/js/deleteKakao.js"></script>
    </body>
</html>
