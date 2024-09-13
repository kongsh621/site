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
        <!-- 보드용 페이징  -->
<div class="card-footer">
    <div class="row col-12">
        <div class="justify-content-left offset-1">
            <p id=pageInfo><strong>
                <c:if test="${pageMaker.lastPage != 1}">
                ${criteria.page} / ${pageMaker.lastPage}
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
                            <li class="page-item">
                                <a class="page-link" href="../list?page=${pageMaker.criteria.page-1}" aria-label="Previous">
                                    <span aria-hidden="true">Previous</span>
                                </a>
                            </li>
                        </c:otherwise>
                    </c:choose>
        
                    <!-- 페이지 번호, 현재 페이지에는 css를 달리 줌--> 
                    <c:forEach var="i" begin="1" end="${pageMaker.lastPage}">
                        <li class="page-item ${i == pageMaker.criteria.page ? 'active' : ''}">
                            <a class="page-link" href="../list?page=${i}">${i}</a>
                        </li>
                    </c:forEach>
        
                    <!-- Next 버튼 -->
                    <c:choose>
                        <c:when test="${pageMaker.criteria.page == pageMaker.lastPage}">
                            <li class="page-item disabled">
                                <span class="page-link">Next</span>
                            </li>
                        </c:when>
                        <c:otherwise>
                            <li class="page-item">
                                <a class="page-link" href="../list?page=${pageMaker.criteria.page+1}" aria-label="Next">
                                    <span aria-hidden="true">Next</span>
                                </a>
                            </li>
                        </c:otherwise>
                    </c:choose>
                </ul>
            </nav>
        </c:if>
        
       
    <!-- jsp 끝 -->
    </div>
</div>

    </body>
</html>
