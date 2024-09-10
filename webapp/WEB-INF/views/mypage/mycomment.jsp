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
<!-- Favicon-->
<link rel="icon" type="image/x-icon" href="assets/favicon1.ico" />
<!-- 드롭다운 버튼 -->
<link href="../css/dropdown.css" rel="stylesheet" />
<!-- Core theme CSS (includes Bootstrap)-->
<link href="../css/styles1.css" rel="stylesheet" />
<!-- Custom fonts for this template -->
<link href="vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
<link
href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i"
rel="stylesheet">

<!-- Custom styles for this template -->
<link href="css/sb-admin-2.min.css" rel="stylesheet">

<!-- Custom styles for this page -->
<link href="vendor/datatables/dataTables.bootstrap4.min.css" rel="stylesheet">
<!-- 종류 버튼 -->
<!-- <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css"> -->
<link href="../css/button.css" rel="stylesheet">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
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


                                  <!-- <div class="col col-md-10 text-center">
                                           <ul id="pagination">
                                       <c:if test="${pageMaker.startPage != 1}">
                                               <li><a href="list">First</a></li>
                                       </c:if>

                                       <c:if test="${pageMaker.prev}">
                                               <li><a href="list?page=${pageMaker.startPage - 1}">Prev</a></li>
                                       </c:if>
                                       <c:forEach var="page" begin="${pageMaker.startPage}" end="${pageMaker.endPage}">
                                           <c:choose>
                                               <c:when test="${pageMaker.criteria.page != page}">
                                               <li><a href="list?page=${page}">${page}</a></li>
                                               </c:when>
                                               <c:otherwise>
                                               <li><span>${page}</span></li>
                                               </c:otherwise>
                                           </c:choose>
                                       </c:forEach>
                                       <c:if test="${pageMaker.next}">
                                               <li><a href="list?page=${pageMaker.endPage + 1}">Next</a></li>
                                       </c:if>
                                       <c:if test="${pageMaker.lastPage != pageMaker.endPage}">
                                               <li><a href="list?page=${pageMaker.lastPage}">Last</a></li>
                                       </c:if>
                                           </ul>
                              </div> -->
                        </div>
                     </div>



        <hr>
        <hr>

        <%@ include file="/WEB-INF/views/fragment/footer2.jsp" %>

</body>
</html>
