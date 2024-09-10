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
                    action="search">
                    <div class="input-group">
                        <input type="text" class="form-control bg-light border-0 small" placeholder="검색어를 입력하세요."
                            aria-label="Search" aria-describedby="basic-addon2" name="search" value="${search}">
                        <div class="input-group-append">
                            <button class="btn btn-primary" type="button">
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
                <h1 class="h3 mb-2 text-gray-800"><strong>관심 목록</strong></h1>
                
                <!-- DataTales Example -->
                <div class="card shadow mb-4">

                    <section class="py-4">
                        <div class="container px-4">
                            <div class="row row-cols-1 row-cols-sm-2 row-cols-md-3 row-cols-lg-4 g-4" id="board-container">
                                <c:forEach var="board" items="${list}">
                                    <div class="col">
                                        <div class="card h-100">
                                            <!-- Product image -->
                                            <img class="card-img-top" src="<c:url value='../img/info/${board.title}.jpg'/>" alt="${board.title}" />
                                            <!-- Product details -->
                                            <div class="card-body text-center">
                                                <!-- Product name -->
                                                <h5 class="fw-bolder">${board.title}</h5>
                                                <!-- Product reviews -->
                                               
                                                <!-- 방영연도 추가하기 -->
                                                <p class="text-muted">${board.regdate}</p>
                                            </div>
                                            <!-- Product actions -->
                                            <div class="card-footer p-4 pt-0 border-top-0 bg-transparent text-center">
                                                <a class="btn btn-outline-dark" href="../infoboard/readinfo?id=${board.id}&page=${pageMaker.criteria.page}">더보기</a>
                                                <!-- Check if loginSession exists -->



                                                <c:if test="${not empty loginSession}">

                                                            <c:set var="isFavorite" value="false" />

                                                            <!-- Iterate over favorites to check if the current board is in the favorites list -->
                                                            <c:forEach var="list_jjim" items="${favorites}">
                                                                <c:if test="${list_jjim.infoboard_id == board.id && list_jjim.member_id == loginSession.id}">
                                                                    <!-- Update the flag based on is_like value -->
                                                                    <c:set var="isFavorite" value="${list_jjim.is_like}" />
                                                                </c:if>
                                                            </c:forEach>

                                                                    <a href="/infoboard/like?member_id=${loginSession.id}&infoboard_id=${board.id}&is_like=N" class="btn mt-1">♥</a>
                    

                                                    </c:if>
                                            </div>
                                        </div>
                                    </div>
                                </c:forEach>
                            </div>
                        </div>
                    </section>
                    
<!-- 페이징  -->
<%@ include file="/WEB-INF/views/fragment/paginationComment.jsp" %>
<!-- 페이징 끝 -->


                    
            </div>
            <!-- /.container-fluid -->

        </div>
        <!-- End of Main Content -->

    </div>
    <!-- End of Content Wrapper -->
                
</div>
<!-- End of Page Wrapper -->

<!-- Footer -->
<footer class="sticky-footer bg-white">
    <div class="container my-auto">
        <div class="copyright text-center my-auto">
            <span>공지윤</span>
        </div>
    </div>
</footer>
<!-- End of Footer -->

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
<!-- 헤더 마무리 -->

<script>
    // 토글 버튼
    window.addEventListener('DOMContentLoaded', event => {

// Toggle the side navigation
const sidebarToggle = document.body.querySelector('#sidebarToggle');
if (sidebarToggle) {
    // Uncomment Below to persist sidebar toggle between refreshes
    // if (localStorage.getItem('sb|sidebar-toggle') === 'true') {
    //     document.body.classList.toggle('sb-sidenav-toggled');
    // }
    sidebarToggle.addEventListener('click', event => {
        event.preventDefault();
        document.body.classList.toggle('sb-sidenav-toggled');
        localStorage.setItem('sb|sidebar-toggle', document.body.classList.contains('sb-sidenav-toggled'));
    });
}

});
// 오른쪽 상단 드롭다운
document.getElementById('navbarDropdown').addEventListener('click', function() {
    const content = document.getElementById('nav-content');
    content.classList.toggle('dropdown-item'); // 'show' 클래스를 토글
});
    </script>
</script>


<%@ include file="/WEB-INF/views/fragment/footer2.jsp" %>

   
    </body>
</html>
