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
        <title>${infoboardVO.title}</title>
        <style>
            .star-rating {
                display: flex;
                cursor: pointer;
                font-size: 1.3rem;
                color: gray;
                margin-bottom: 1rem;
            }

            .star-rating .bi-star {
                margin: 0 0.1rem;
                transition: color 0.3s;
            }

            .star-rating .bi-star.selected {
                color: gold;
            }

        </style>
        <!-- Favicon-->
        <link rel="icon" type="image/x-icon" href="../assets/favicon1.ico" />
        <!-- Core theme CSS (includes Bootstrap)-->
        <link href="../css/styles1.css" rel="stylesheet" />
        <script>
            $(function (){
                $('#delete').on('click', function(){
                    if (!window.confirm('정말로 삭제하시겠습니까?'))
                        return false;
                });
            })
        
        </script>
    </head>
    <body>
        
            <c:set var = "urlImg" value="../img/info/${infoboardVO.title}.jpg" />
                <!-- Page content-->
                <div class="container mt-5">
                    <div class="row">
                        <div class="col-lg-10 center">
                            <!-- Post content-->
                            <article>
                                <!-- Post header-->
                                <header class="mb-4">

                                <!-- 작성자용 버튼 -->
                                <div style="display: inline-block;">
                                    <div class="mb-3 justify-content-right">
                                    <c:choose>
                                        <c:when test="${not empty admin}">
                                                <a href="../infoboard/?page=${criteria.page}"><button class="btn btn-default" style="text-decoration-line: none;">목록</button></a>
                                                <a href="../infoboard/updateinfo?id=${infoboardVO.id}&page=${criteria.page}"><button class="btn btn-default" style="text-decoration-line: none;">수정</button></a>
                                                <a href="../infoboard/deleteinfo?id=${infoboardVO.id}&page=${criteria.page}"><button class="btn btn-default" style="text-decoration-line: none;" id="delete">삭제</button></a>
                                        </c:when>
                                        <c:otherwise>
                                                <a href="../infoboard/?page=${criteria.page}"><button class="btn btn-default" style="text-decoration-line: none;">목록</button></a>
                                        </c:otherwise>
                                    </c:choose>
                                    </div>
                                </div>
        
                                    <!-- Post title-->
                                    <h1 class="fw-bolder mb-1">${infoboardVO.title}</h1>
                                    <!-- 평점 -->
                                     <div class="mt-1">
                                         <!-- 평점 매기기 -->
                                         <div>
                                             <hr>
                                             <p>총 평점: ${infoboardVO.total_rate} 점</p>
                                             <p>평가자: ${infoboardVO.total} 명</p>
                                             <dr>
                                             <form action="/infoboard/rate" method="post">
                                                 <div class="star-rating">
                                                     <input type="hidden" id="rating" name="rate" value="0">
                                                     <input type="hidden" name="id" value="${infoboardVO.id}">
                                                     <span class="bi bi-star" data-value="1"></span>
                                                     <span class="bi bi-star" data-value="2"></span>
                                                     <span class="bi bi-star" data-value="3"></span>
                                                     <span class="bi bi-star" data-value="4"></span>
                                                     <span class="bi bi-star" data-value="5"></span>
                                                     <button class="btn btn-primary ml-md-3" type="submit">평가</button>
                                                 </div>  
                                             </form>
                                            </div>
                                         <script>
                                            document.addEventListener('DOMContentLoaded', () => {
                                            const stars = document.querySelectorAll('.star-rating .bi-star');
                                            const ratingInput = document.getElementById('rating');

                                            stars.forEach(star => {
                                                star.addEventListener('click', () => {
                                                    const value = star.getAttribute('data-value');
                                                    stars.forEach(s => {
                                                        if (parseInt(s.getAttribute('data-value')) <= parseInt(value)) {
                                                            s.classList.add('selected');
                                                        } else {
                                                            s.classList.remove('selected');
                                                        }
                                                    });
                                                    ratingInput.value = value; 
                                                });
                                            });
                                        });
                                         </script>
                                     </div>
                                    <!-- Post meta content-->
                                    <div class="text-muted fst-italic mb-2">${infoboardVO.regdate}</div>
                                    <!-- Post categories-->
                                    <a class="badge bg-secondary text-decoration-none link-light" href="../infoboard/sort?type=${infoboardVO.type}">${fn:toUpperCase(infoboardVO.type)}</a>
                                </header>
                                <!-- Preview image figure-->
                                <figure class="mb-4"><img class="img-fluid rounded" src="${urlImg}" alt="${infoboardVO.title}" /></figure>
                                <!-- Post content-->
                                <section class="mb-5">
                                    <div class="mb-4"><p>출연: ${infoboardVO.actor}</p></div>
                                    <p class="fs-5 mb-4">${infoboardVO.content}</p>
                                </section>
                            </article>
                            

        <!-- 헤더 마무리 -->
            </div>
        </div>
        <%@ include file="/WEB-INF/views/fragment/footer2.jsp" %>

    </body>
</html>
