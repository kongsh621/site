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
<body>
    <div id="page-content-wrapper" class="mt-3">
        <div class="container-fluid">
            <div class="col-sm-11 page">
                <div class="row justify-content-center">
                    <c:choose>
                        <c:when test="${not empty total}">
                                    <div class="panel panel-default panel-table col-md-8">
                                      <div class="panel-heading">
                                        <div class="row">
                                          <div class="col col-xs-6" id="title">
                                            <h3 class="panel-title">내 게시글</h3>
                                          </div>
                                        </div>
                                      </div>
                                      <div class="panel-body">
                                        <table class="table table-striped table-bordered table-list">
                                          <thead>
                                            <tr>
                                                <th>ID</th>
                                                <th>제목</th>
                                                <th>작성자</th>
                                                <th>작성일</th>
                                                <th>조회수</th>
                                            </tr>
                                          </thead>
                                          <tbody>
                                              <c:forEach var="board" items="${list}">
                                                          <tr>
                                                              <td>${board.id}</td>
                                                              <td><a href="../read?id=${board.id}">${board.title}</a></td>
                                                              <td>${board.writer}</td>
                                                              <td>${board.regdate}</td>
                                                              <td>${board.hits}</td>
                                                          </tr>
                                              </c:forEach>
                                            </tbody>
                                        </table>
<!-- 페이지 -->
<%@ include file="/WEB-INF/views/fragment/paginationBoard.jsp" %>
<!-- 페이지 -->
             
                    </div>
                  </div>
                </div>
    </c:when>
    <c:otherwise>
        <h1>작성한 게시물이 없습니다.</h1>
    </c:otherwise>
</c:choose>

</div></div></div></div>

<%@ include file="/WEB-INF/views/fragment/footer2.jsp" %>

</body>
</html>
