<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <%@ include file="/WEB-INF/views/fragment/header2.jsp" %>
        <meta charset="utf-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
        <meta name="description" content="" />
        <meta name="author" content="" />
    </head>
    <body>
            <!-- Content Wrapper -->
    <div id="content-wrapper" class="d-flex flex-column">

        <!-- Main Content -->
        <div id="content">

            <!-- Topbar -->
            <nav class="navbar navbar-expand navbar-light bg-white topbar mb-4 static-top shadow">

                <!-- Sidebar Toggle (Topbar) -->
                <form class="form-inline">
                    <button id="sidebarToggleTop" class="btn btn-link d-md-none rounded-circle mr-3">
                        <i class="fa fa-bars"></i>
                    </button>
                </form>

                <!-- Topbar Search -->
                <form
                    class="d-none d-sm-inline-block form-inline mr-auto ml-md-3 my-2 my-md-0 mw-100 navbar-search"
                    action="search" method="post">
                    <div class="input-group">
                        <input type="text" class="form-control bg-light border-0 small" placeholder="검색어를 입력하세요."
                            aria-label="Search" aria-describedby="basic-addon2" name="search" value="${search}">
                        <div class="input-group-append">
                            <button class="btn btn-primary" type="submit">
                                <i class="fas fa-search fa-sm"></i>
                            </button>
                        </div>
                    </div>
                </form>
            </nav>
            <!-- End of Topbar -->

            <!-- Begin Page Content -->
            <div class="container-fluid">

                <!-- Page Heading -->
                <h1 class="h3 mb-2 text-gray-800"><strong>자유 게시판</strong></h1>
                
                <!-- DataTales Example -->
                <div class="card shadow mb-4">
                    
                    <div class="card-body">
                        <div class="table-responsive">
                            <c:choose>
                                <c:when test="empty emptyResult">
                                    <h1>검색 결과가 존재하지 않습니다.</h1>
                                </c:when>
                                <c:otherwise>
                                    <table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
                                        <thead>
                                            <tr>
                                                <th>id</th>
                                                <th>제목</th>
                                                <th>작성자</th>
                                                <th>작성일</th>
                                                <th>조회수</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                        <c:forEach var="board" items="${list}">
                                            <tr>
                                                <td>
                                                    <c:if test="${not empty admin}">
                                                        <a href="/delete?id=${board.id}">    
                                                            <button class="ml-1" id="adminDelete" onclick="unlinkApp()" data-href="/delete?id=${board.id}"><span>X</span></button>
                                                        </a>    
                                                    </c:if>
                                                    ${board.id} 
                                                </td>
                                                <td><a href="../read?id=${board.id}&page=${pageMaker.criteria.page}">${board.title}</a></td>
                                                <td>${board.writer}</td>
                                                <td>${board.regdate}</td>
                                                <td>${board.hits}</td>
                                            </tr>
                                        </c:forEach>
                                        </tbody>
                                    </table>
                                </c:otherwise>
                            </c:choose>
                        
                            <script>
                                async function unlinkApp() {
                                    event.preventDefault(); // 기본 링크 이동을 막음
                                    console.log('버튼 클릭');
                                    if (window.confirm('정말로 삭제하시겠습니까?')){
                                       // '예'를 클릭한 경우 data-href에 담긴 링크로 이동
                                       const href = event.currentTarget.getAttribute('data-href');
                                       window.location.href = href;          
                                    }                       
                                }
                
                            </script>

                              <!-- 페이징  -->
                    <%@ include file="/WEB-INF/views/fragment/paginationMemberBoard.jsp" %>
                    <!-- 페이징 끝 -->
                        </div>
                        <a href="/write">
                        <button type="button" class="btn btn-sm btn-primary btn-create" name="writePost" id="write">글 작성</button></a>
                    </div>
                </div>
            </div>
        </div>
    </div>
            <!-- /.container-fluid -->

        </div>
        <!-- End of Main Content -->
        
        
    </div>
    <!-- End of Content Wrapper -->
    
</div>
<!-- End of Page Wrapper -->


<!-- Scroll to Top Button-->
<a class="scroll-to-top rounded" href="#page-top">
    <i class="fas fa-angle-up"></i>
</a>

<!-- Logout Modal-->
<div class="modal fade" id="logoutModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"
aria-hidden="true">
<div class="modal-dialog" role="document">
    <div class="modal-content">
        <div class="modal-header">
            <h5 class="modal-title" id="exampleModalLabel">Ready to Leave?</h5>
            <button class="close" type="button" data-dismiss="modal" aria-label="Close">
                <span aria-hidden="true">×</span>
            </button>
        </div>
        <div class="modal-body">Select "Logout" below if you are ready to end your current session.</div>
        <div class="modal-footer">
            <button class="btn btn-secondary" type="button" data-dismiss="modal">Cancel</button>
            <a class="btn btn-primary" href="login.html">Logout</a>
        </div>
    </div>
</div>
</div>



        <!-- 헤더 마무리 -->
    </div>
</div>
<%@ include file="/WEB-INF/views/fragment/footer2.jsp" %>

    </body>
</html>
