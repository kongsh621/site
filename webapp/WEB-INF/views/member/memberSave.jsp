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
<style>
    .error-message {
        display: none;
    }
 
    /* 또는 span 태그 자체를 none으로 설정해둬도 된다 */
</style>
    <script>
            
   </script>
</head>
<body>
    <div class="container">
        <div class="row justify-content-center">
            <div class="col-md-8">
                <div class="card">
                  <div class="card-header">회원가입</div>
                    <div class="card-body">
                        <form action="/member/save" method="Post" id="sign-up">
                            <div class="form-group row">
                                <label for="email" class="col-md-4 col-form-label text-md-right">*이메일</label>
                                    <div class="col-md-5">
                                        <input type="text" id="email" class="form-control" name="email" placeholder="example@naver.com" required autofocus>

                                    <span class="input-group-btn">
                                        <button class="btn btn-primary" type="button" id="idCheck" name="idCheck">아이디 중복 검사</button>
                                    </span>
                                    <span id="emailEmptyError" class="error-message">이메일을 입력해 주세요.</span>
                                </div>
                            </div>

                            <div class="form-group row">
                                <label for="password" class="col-md-4 col-form-label text-md-right">*비밀번호</label>
                                <div class="col-md-5">
                                    <input type="password" id="password" class="form-control" name="password" placeholder="비밀번호"required>
                                    <span id="passwordEmptyError" class="error-message">비밀번호를 입력해 주세요.</span>
                                    <span id="passwordLengthError" class="error-message">비밀번호를 8글자 이상 입력해 주세요.</span>
                                </div>
                            </div>

                            <div class="form-group row">
                                <label for="passwordCheck" class="col-md-4 col-form-label text-md-right">*비밀번호 확인</label>
                                <div class="col-md-5">
                                    <input type="password" id="passwordCheck" class="form-control" name="passwordCheck" placeholder="비밀번호 확인"required>
                                    <span id="passwordCheckEmptyError" class="error-message">비밀번호 확인을 입력해 주세요.</span>
                                    <span id="passwordCheckIncorrectError" class="error-message">비밀번호 확인이 일치하지 않습니다.</span>
                                </div>
                            </div>

                            <div class="form-group row">
                                <label for="name" class="col-md-4 col-form-label text-md-right">*이름</label>
                                <div class="col-md-5">
                                    <input type="text" id="name" class="form-control" name="name" placeholder="이름"required>
                                    <span id="nameEmptyError" class="error-message">이름을 입력해 주세요.</span>
                                </div>
                            </div>
                            <div class="form-group row">
                                <label for="nickname" class="col-md-4 col-form-label text-md-right">*닉네임</label>
                                <div class="col-md-5">
                                    <input type="text" id="nickname" class="form-control" name="nickname" placeholder="닉네임"required>
                                    <span id="nicknameEmptyError" class="error-message">닉네임을 입력해 주세요.</span>
                                </div>
                            </div>

                            <div class="form-group row">
                                <label for="age" class="col-md-4 col-form-label text-md-right">*나이</label>
                                <div class="col-md-5">
                                    <input type="text" id="age" class="form-control" name="age" placeholder="나이" required>
                                    <span id="ageEmptyError" class="error-message">나이를 입력해 주세요.</span>
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
                                <button  disabled="disabled" id="submitButton" type="submit" class="btn btn-primary">
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

    <script src="${pageContext.request.contextPath}/js/register.js"></script>

    <%@ include file="/WEB-INF/views/fragment/footer2.jsp" %>

</body>
</html>
