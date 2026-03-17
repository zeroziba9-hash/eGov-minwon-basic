<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8"/>
    <title>로그인</title>
</head>
<body>
<h2>민원 시스템 로그인</h2>
<c:if test="${not empty message}">
    <p style="color:red;">${message}</p>
</c:if>
<form method="post" action="${pageContext.request.contextPath}/login.do">
    <div><label>ID: <input type="text" name="username" required/></label></div>
    <div><label>PW: <input type="password" name="password" required/></label></div>
    <button type="submit">로그인</button>
</form>
<p>테스트 계정: admin / 1234</p>
</body>
</html>
