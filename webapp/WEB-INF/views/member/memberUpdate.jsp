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
<%@ include file="/WEB-INF/views/fragment/header2.jsp" %>

</head>
<body>
    <div class="container">
            <div class="row justify-content-center">
                <div class="col-md-8">
                    <div class="card">
                      <div class="card-header">회원 정보 수정</div>
                        <div class="card-body">
                          <form action="/member/update" method="post">
                            
                            <c:if test="${empty kakaoLogin}">
                                <div class="form-group row">
                                    <label for="email" class="col-md-4 col-form-label text-md-right">아이디:</label>
                                    <div class="col-md-5">
                                        <input type="text" class="form-control" name="email" id="email" value="${member.email}">
                                    </div>
                                </div>
                            </c:if>

                                <div class="form-group row">
                                    <label for="name" class="col-md-4 col-form-label text-md-right">닉네임:</label>
                                    <div class="col-md-5">
                                        <input type="text" class="form-control" name = "nickname" id="nickname" value="${member.nickname}">
                                    </div>
                                </div>

                                <div class="form-group row">
                                    <label for="name" class="col-md-4 col-form-label text-md-right">이름:</label>
                                    <div class="col-md-5">
                                        <input type="text" class="form-control" name = "name" id="name" value="${member.name}">
                                    </div>
                                </div>

                                <div class="form-group row">
                                    <label for="age" class="col-md-4 col-form-label text-md-right">나이:</label>
                                    <div class="col-md-5">
                                        <input type="text" class="form-control" name="age" id="age" value="${member.age}">
                                    </div>
                                </div>

                                <div class="col-md-6 offset-md-4">
                                    <input type="submit" class="btn btn-primary" value="수정">
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
