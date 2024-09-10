<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ko">
<head>
<%@ include file="/WEB-INF/views/fragment/header.jsp" %>
    <meta charset="UTF-8">
    <title>Member List</title>
</head>
<link rel="stylesheet" href="<c:url value='/css/member/memberList.css'/>">
</head>
<body>
    <h1>Member List</h1>
    <div id="container">

        <table id="table", style="text-align: center; border: 1px solid #dddddd">
            <thead>
                <tr>
                    <th style="background-color: #eeeeee; text-align: center;">ID</th>
                    <th style="background-color: #eeeeee; text-align: center;">Email</th>
                    <th style="background-color: #eeeeee; text-align: center; display: none">Password</th>
                    <th style="background-color: #eeeeee; text-align: center;">Name</th>
                    <th style="background-color: #eeeeee; text-align: center;">Age</th>
                </tr>
            </thead>

            <tbody>
    <c:forEach var="member" items="${memberList}">
                <tr>
                    <td>${member.id}</td>
                    <td><a href="/member/load?id=${member.id}">${member.email}</a></td>
                    <td style="display: none;">${member.password}</td>
                    <td>${member.name}</td>
                    <td>${member.age}</td>
                </tr>
    </c:forEach>
            </tbody>
        </table>
    </div>
    [<a href="/member/save">회원가입</a>]
    [<a href="/">메인</a>]
<%@ include file="/WEB-INF/views/fragment/footer.jsp" %>

</body>
</html>
