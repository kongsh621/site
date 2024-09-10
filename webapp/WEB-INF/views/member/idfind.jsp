<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html lang="en">
    <head><%@ include file="/WEB-INF/views/fragment/header2.jsp" %>


<meta charset="utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
<meta name="description" content="" />
<meta name="author" content="" />
    </head>

    <body>
        <main class="login-form">
            <div class="container">
                <div class="row justify-content-center">
                    <div class="col-md-6">
                        <div class="card">
                            <div class="card-header">
                                <div class="row">
                                    <div class="col-md-6 text-center">
                                        <a href="/member/idfind">아이디 찾기</a>
                                    </div>
                                    <div class="col-md-6 text-center">
                                        <a class= "col-sm-push-9" href="/member/passfind">비밀번호 찾기</a>
                                    </div>
                                </div>
                            </div>
                            <div class="card-body">
<c:choose>
    <c:when test="${empty idCheck}">
                                <form action="/member/idfind" method="Post">
                                    <div class="form-group row">
                                        <label for="name" class="col-md-4 col-form-label text-md-right">이름</label>
                                        <div class="col-md-6">
                                            <input type="text" id="name" class="form-control" name="name" placeholder="이름"required>
                                        </div>
                                    </div>
                                    <div class="col-md-6 offset-md-4">
                                        <button type="submit" class="btn btn-primary">
                                            찾기
                                        </button>
                                    </div>
                                </form>
    </c:when>
    <c:otherwise>
                                    <div class="form-group row">
                                        <div class="col-md-10">
                                            아이디는 ${idCheck}입니다.
                                        </div>
                                    </div>
                                    <div class="col-md-6 offset-md-4">
                                        <a href="/member/login"><button class="btn btn-primary">로그인</button></a>
                                    </div>
                                <div>
                            </div>
    </c:otherwise>
</c:choose>
                        </div>
                    </div>
                </div>
            </div>
            </div>
<%@ include file="/WEB-INF/views/fragment/footer2.jsp" %>

    </body>
</html>
