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


    <script>
             $(document).ready(function() {
                       $('#idCheck').click(function() {
                              const email = $('#email').val();

                              if (!email) {
                                  alert("이메일을 입력해 주세요.");
                                  return;
                              }
                              $.ajax({
                                  type: "GET",
                                  url: "/member/idcheck",
                                  data: {
                                      email: email
                                  },
                                  success: function(response) {
                                      // AJAX 요청의 성공 시, HTML을 파싱하여 메시지를 추출
                                      var tempDiv = $('<div>').html(response);
                                      var message = tempDiv.find('p').text();
                                      alert(message);
                                  },
                                    error: function(xhr, status, error) {
                                      alert("값을 가져오지 못했습니다. 상태: " + status + " 오류: " + error);
                                  }
                               });
                           });
                       });
   </script>
</head>
<body>
    <div class="container">
        <div class="row justify-content-center">
            <div class="col-md-8">
                <div class="card">
                  <div class="card-header">회원가입</div>
                    <div class="card-body">
                        <form action="/member/save" method="Post">
                            <div class="form-group row">
                                <label for="email" class="col-md-4 col-form-label text-md-right">이메일</label>
                                    <div class="col-md-5">
                                        <input type="text" id="email" class="form-control" name="email" placeholder="example@naver.com" required autofocus>

                                    <span class="input-group-btn">
                                        <button class="btn btn-primary" type="button" id="idCheck" name="idCheck">아이디 중복 검사</button>
                                    </span>
                                </div>
                            </div>

                            <div class="form-group row">
                                <label for="password" class="col-md-4 col-form-label text-md-right">비밀번호</label>
                                <div class="col-md-5">
                                    <input type="password" id="password" class="form-control" name="password" placeholder="비밀번호"required>
                                </div>
                            </div>

                            <div class="form-group row">
                                <label for="passwordCheck" class="col-md-4 col-form-label text-md-right">비밀번호 확인</label>
                                <div class="col-md-5">
                                    <input type="password" id="passwordCheck" class="form-control" name="passwordCheck" placeholder="비밀번호 확인"required>
                                </div>
                            </div>

                            <div class="form-group row">
                                <label for="name" class="col-md-4 col-form-label text-md-right">이름</label>
                                <div class="col-md-5">
                                    <input type="text" id="name" class="form-control" name="name" placeholder="이름"required>
                                </div>
                            </div>
                            <div class="form-group row">
                                <label for="nickname" class="col-md-4 col-form-label text-md-right">닉네임</label>
                                <div class="col-md-5">
                                    <input type="text" id="nickname" class="form-control" name="nickname" placeholder="닉네임"required>
                                </div>
                            </div>

                            <div class="form-group row">
                                <label for="age" class="col-md-4 col-form-label text-md-right">연령</label>
                                <div class="col-md-5">
                                    <input type="text" id="age" class="form-control" name="age" placeholder="연령"required>
                                </div>
                            </div>

                           <!-- <div class="form-group row">
                                <div class="col-md-6 offset-md-3">
                                    <div class="radio_wrap"">
                                        <label>성별</label>
                                            <label for="gender1" class="col-md-1">남</label>
                                            <input type="radio" name="gender" value="m">
                                            <label for="gender2">여</label>
                                            <input type="radio" name="gender" value="f">
                                    </div>
                                </div>
                            </div> -->

                            <div class="col-md-6 offset-md-4">
                                <button type="submit" class="btn btn-primary">
                                    회원가입
                                </button>
                            </div>
                        </form>
                    </div>
                    </div>
                </div>
            </div>
        </div>
    </div>


    <%@ include file="/WEB-INF/views/fragment/footer2.jsp" %>

</body>
</html>
