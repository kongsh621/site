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

   <style>
  /* 사이드바 래퍼 스타일 */

  #page-wrapper {
    padding-left: 250px;
  }

  #sidebar-wrapper {
    position: absolute;
    width: 250px;
    height: 100%;
    margin-left: -250px;
    overflow-x: hidden;
    overflow-y: auto;
  }

  #page-content-wrapper {
    width: 100%;
    padding: 20px;
     border-left: 1px solid #aaa;
  }
  /* 사이드바 스타일 */

  .sidebar-nav {
    width: 250px;
    margin: 0;
    padding: 0;
    list-style: none;
  }

  .sidebar-nav li {
    text-indent: 1.5em;
    line-height: 2.8em;
  }

  .sidebar-nav li a {
    display: block;
    text-decoration: none;
    color: black;
    padding: 0.4em 1em;
  }

  .sidebar-nav li a:hover {
    background: #ccc;
  }

    </style>
</head>
<body>
 
    <div id="page-content-wrapper">
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
                      <!-- <div class="col col-xs-8 text-center">
                               <ul id="pagination">
                           <c:if test="${pageMaker.startPage != 1}">
                                   <li><a href="/">First</a></li>
                           </c:if>

                           <c:if test="${pageMaker.prev}">
                                   <li><a href="?page=${pageMaker.startPage - 1}">Prev</a></li>
                           </c:if>
                           <c:forEach var="page" begin="${pageMaker.startPage}" end="${pageMaker.endPage}">
                               <c:choose>
                                   <c:when test="${pageMaker.criteria.page != page}">
                                   <li><a href="?page=${page}">${page}</a></li>
                                   </c:when>
                                   <c:otherwise>
                                   <li><span>${page}</span></li>
                                   </c:otherwise>
                               </c:choose>
                           </c:forEach>
                           <c:if test="${pageMaker.next}">
                                   <li><a href="?page=${pageMaker.endPage + 1}">Next</a></li>
                           </c:if>
                           <c:if test="${pageMaker.lastPage != pageMaker.endPage}">
                                   <li><a href="?page=${pageMaker.lastPage}">Last</a></li>
                           </c:if>
                               </ul>
                      </div> -->
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
