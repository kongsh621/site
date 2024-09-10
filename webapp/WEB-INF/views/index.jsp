<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ko">
<head>
<%@ include file="/WEB-INF/views/fragment/header.jsp" %>
    <meta charset="UTF-8">
    <title>Index</title>

</head>
<body>
    <h2>Index Page</h2>
    <a href="/list">게시판</a>
    <a href="/member/save">회원가입</a>
<%@ include file="/WEB-INF/views/fragment/footer.jsp" %>
</body>
</html>
