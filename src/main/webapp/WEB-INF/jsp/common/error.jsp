<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8"/>
    <title>오류</title>
</head>
<body>
<h2>처리 중 오류가 발생했습니다.</h2>
<p>${errorMessage}</p>
<a href="${pageContext.request.contextPath}/minwon/list.do">목록으로</a>
</body>
</html>
