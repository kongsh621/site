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
      <div id="page-content-wrapper">
         <div class="col-md-10">
                <div class="row justify-content-center">
                    <div class="col-md-11 page">
                        <!-- 댓글 리스트 -->
                         <div class="card">
                               <div class="card-header col-md-12">내가 쓴 댓글</div>
                                  <div style="margin: 10px 20px;"><p><span>내 댓글(${pageMaker.total})</span></p></div>
                                    <div class="card-body">
                               <div class="card-group">
                                <c:forEach var="comment" items="${list}">
                                          <div class="col-md-12">
                                          <h6 class="card-heading">${comment.writer} <small><i>${comment.regdate}에 작성된 댓글입니다.</i></small></h6>
                                          <a href="../read?id=${comment.postid}"><h3><small>${comment.content}</small></h3></a>
                                            <a href="../deleteComment?id=${comment.id}&postid=${comment.postid}">
                                            <button id = "deleteCom" class="btn btn-secondary ">삭제</button></a><hr>
                                          </div>
                                </c:forEach>
                                     </div>
                                </div>
                                </div>
                                   </div>
                                         </div>
                                      </div>
                                <!--- 댓글 페이지 --->
                                <%@ include file="/WEB-INF/views/fragment/paginationBoard.jsp" %>
                        </div>
                     </div>
        <%@ include file="/WEB-INF/views/fragment/footer2.jsp" %>

</body>
</html>
