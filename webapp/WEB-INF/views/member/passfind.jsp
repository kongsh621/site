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
                            <form action="/member/passfind" method="Post">
                                <div class="form-group row">
                                    <label for="email_address" class="col-md-3 col-form-label text-md-right">이메일</label>
                                    <div class="col-md-6">
                                        <input type="text" id="email" name="email" class="form-control" placeholder="example@naver.com" required autofocus>
                                    </div>
                                    <div class="col-md-2">
                                        <span class="input-group-btn">
                                            <button class="btn btn-primary" type="button" id="checkEmail">인증번호</button>
                                        </span>
                                    </div>
                                </div>

                                <div class="form-group row">
                                    <label for="password" class="col-md-3 col-form-label text-md-right">인증번호</label>
                                    <div class="col-md-6">
                                        <input type="text" id="code" class="form-control" name="inputCode" placeholder="인증번호를 입력하세요."required>
                                    </div>
                                </div>

                                <div class="col-md-6 offset-md-3">
                                    <button type="submit" class="btn btn-primary">
                                        찾기
                                    </button>
                                </div>
                            </div>
                        </form>
                    </div>

                </div>
            </div>
        </div>
        </div>
<%@ include file="/WEB-INF/views/fragment/footer2.jsp" %>
<script>
    $(document).ready(function() {
       $('#checkEmail').click(function() {
           $.ajax({
               type: "POST",
               url: "/api/mail/confirm/json",
               dataType: "text",
               contentType: "application/x-www-form-urlencoded", // 요청 형식에 맞추어 contentType 설정
               data: {
                   email: $('#email').val() 
               },
               success: function(data) {
                   alert("해당 이메일로 인증번호 발송이 완료되었습니다. \n확인부탁드립니다.");
                   console.log("data : " + data);
               },
               error: function(xhr, status, error) {
                   alert("값을 가져오지 못했습니다. 상태: " + status + " 오류: " + error);
               }
           });
       });
    });
  </script>
    </body>
</html>
