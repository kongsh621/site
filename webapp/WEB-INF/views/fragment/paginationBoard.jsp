<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
        <meta name="description" content="" />
        <meta name="author" content="" />


    </head>
    <body>
        <!-- 인포보드 페이징  -->

<div class="card-footer">
    <div class="row col-12">
        <div class="justify-content-left offset-1">
            <p id=pageInfo><strong>
                <c:if test="${pageMaker.lastPage != 1}">
                    ${pageMaker.criteria.page} / ${pageMaker.lastPage}
                </c:if>
            </strong></p>
        </div>
        <!-- jsp -->
        <c:if test="${pageMaker != null}">
            <nav aria-label="Page navigation">
                <ul class="pagination justify-content-center">
                    <!-- Previous 버튼 -->
                    <c:choose>
                        <c:when test="${pageMaker.criteria.page == 1}">
                            <li class="page-item disabled">
                                <span class="page-link">Previous</span>
                            </li>
                        </c:when>
                        <c:otherwise>
                            <c:choose>
                                <c:when test="empty button">
                                    <li class="page-item">
                                        <a class="page-link" href="?page=${pageMaker.criteria.page - 1}" aria-label="Previous">
                                            <span aria-hidden="true">Previous</span>
                                        </a>
                                    </li>
                                </c:when>
                                <c:otherwise> <!--정렬 버튼 눌렀을 때-->
                                    <li class="page-item"></li>
                                        <a class="page-link" href="?type=${button}&page=${pageMaker.criteria.page - 1}" aria-label="Previous">
                                            <span aria-hidden="true">Previous</span>
                                        </a>
                                    </li>
                                </c:otherwise>
                            </c:choose>
                        </c:otherwise>
                    </c:choose>
        
                    <!-- 페이지 번호 -->
                    <c:forEach var="i" begin="1" end="${pageMaker.lastPage}">
                        <c:choose>
                            <c:when test="empty button">
                                <li class="page-item ${i == pageMaker.criteria.page ? 'active' : ''}">
                                    <a class="page-link" href="?page=${i}">${i}</a>
                                </li>
                            </c:when>
                            <c:otherwise> <!--정렬 버튼 눌렀을 때-->
                                <li class="page-item ${i == pageMaker.criteria.page ? 'active' : ''}">
                                    <a class="page-link" href="?type=${button}&page=${i}">${i}</a>
                                </li>
                            </c:otherwise>
                        </c:choose>
                    </c:forEach>
        
                    <!-- Next 버튼 -->
                    <c:choose>
                        <c:when test="${pageMaker.criteria.page == pageMaker.lastPage}">
                            <li class="page-item disabled">
                                <span class="page-link">Next</span>
                            </li>
                        </c:when>
                        <c:otherwise>
                            <c:choose>
                                <c:when test="empty button">
                                    <li class="page-item">
                                        <a class="page-link" href="?page=${pageMaker.criteria.page + 1}" aria-label="Next">
                                            <span aria-hidden="true">Next</span>
                                        </a>
                                    </li>
                                </c:when>
                                <c:otherwise> <!--정렬 버튼 눌렀을 때-->
                                    <li class="page-item">
                                        <a class="page-link" href="?type=${button}&page=${pageMaker.criteria.page + 1}" aria-label="Next">
                                            <span aria-hidden="true">Next</span>
                                        </a>
                                    </li>
                                </c:otherwise>
                            </c:choose>
                        </c:otherwise>
                    </c:choose>
                </ul>
            </nav>
        </c:if>
        
       
    <!-- jsp 끝 -->
    </div>
</div>
    <!-- 페이징 끝 -->


    </body>
</html>
