<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <%@ include file="/WEB-INF/views/fragment/header2.jsp" %>

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
                    <div class="card-header">비밀번호 변경</div>
                    <div class="card-body">
                        <form action="/mypage/setpass" method="Post">
                            <div class="form-group row">
                                <label for="email_address" class="col-md-4 col-form-label text-md-right">기존 비밀번호</label>
                                <div class="col-md-6">
                                    <input type="text" id="password" class="form-control" name="password" placeholder="비밀번호를 입력하세요." required autofocus>
                                </div>
                            </div>

                            <div class="form-group row">
                                <label for="email_address" class="col-md-4 col-form-label text-md-right">새 비밀번호</label>
                                <div class="col-md-6">
                                    <input type="text" id="newPass" class="form-control" name="newPass" placeholder="비밀번호를 입력하세요." required autofocus>
                                </div>
                            </div>

                            <div class="form-group row">
                                <label for="password" class="col-md-4 col-form-label text-md-right">새 비밀번호 확인</label>
                                <div class="col-md-6">
                                    <input type="text" id="newPassCheck" class="form-control" name="newPassCheck" placeholder="비밀번호를 입력하세요."required>
                                </div>
                            </div>

                            <div class="col-md-6 offset-md-4">
                                <button type="submit" class="btn btn-primary">
                                    수정
                                </button>
                            </div>
                    </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
    </div>

</main>

<%@ include file="/WEB-INF/views/fragment/footer2.jsp" %>


</body>
</html>
