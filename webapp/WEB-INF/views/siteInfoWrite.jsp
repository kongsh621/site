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
    <c:choose>
        <c:when test="${not empty infoboard}">
            <c:set var="action" value="update" />
            <c:set var="title" value="게시글 수정" />
        </c:when>
        <c:otherwise>
            <c:set var="action" value="write" />
            <c:set var="title" value="게시글 작성" />
        </c:otherwise>
    </c:choose> 
    
    <!-- Page content-->
    <div class="container mt-5">
        <div class="row">
            <div class="col-lg-12 center">
                <!-- Basic Card Example -->
                <div class="card shadow mb-4">
                    <div class="card-header py-3">
                        <h6 class="m-0 font-weight-bold text-primary">${title}</h6>
                    </div>
                    <div class="card-body">
                        <form action="${action}info" method="post" enctype="multipart/form-data">
                            <!-- 업데이트일 때 데이터 저장 -->
                            <c:choose>
                                <c:when test="${action == 'update'}">
                                        <input type="hidden" name="id" value="${infoboard.id}">
                                        <input type="hidden" name="page" value="${criteria.page}">
                                        <input type="hidden" name="rowsPerPage" value="${criteria.rowsPerPage}">
                                </c:when>
                            </c:choose>
                            <div class="mb-1 container-fluid">
                                <div>
                                    <label for="title">제목</label>
                                    <input type="text" class="form-control" name="title" placeholder="제목을 입력하세요." value="${infoboard.title}">
                                </div>
                                <div class="form-inline form-group">
                                    <label for="title" class="control-label mt-3" >작성자 </label>
                                    <input type="text" class="form-control-plaintext" name="writer" value="${loginSession.nickname}">
                                </div>
                                <div>
                                    <label for="title">날짜</label>
                                    <input type="text" class="form-control" name="regdate" placeholder="날짜를 입력하세요." value="${infoboard.regdate}">
                                </div>
                                <div>종류
                                    <select class="form-control" id="typeOption" name="type" value="${infoboard.type}">
                                      <option name="drama" value="drama">드라마</option>
                                      <option name="movie" value="movie">영화</option>
                                    </select>
                                </div>
                                <div>
                                    <label>출연:</label>
                                    <textarea class="form-control" rows="1" name="actor" placeholder="내용을 입력하세요.">${infoboard.actor}</textarea>
                                </div>
                                <div>
                                    <label for="title">내용 </label>
                                    <textarea class="form-control" row="5" name="content" placeholder="내용을 입력하세요.">${infoboard.content}</textarea>
                                    <input class="mt-3" type="file" name="file">
                                </div>
                                <button type="submit" class="btn btn-primary mt-4" style="text-decoration-line: none;">${action}</button>
                            </div>
                        </form>
                    </div>
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
