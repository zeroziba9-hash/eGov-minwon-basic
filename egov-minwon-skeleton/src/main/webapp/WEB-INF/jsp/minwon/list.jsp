<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8"/>
    <title>민원 목록</title>
</head>
<body>
<h2>민원 목록</h2>
<form method="post" action="${pageContext.request.contextPath}/logout.do" style="margin-bottom:10px;">
    <button type="submit">로그아웃</button>
</form>

<c:if test="${not empty message}">
    <p style="color: green;">${message}</p>
</c:if>

<h3>민원 등록</h3>
<form method="post" action="${pageContext.request.contextPath}/minwon/insert.do">
    <div>
        <label>제목: <input type="text" name="title" required/></label>
    </div>
    <div>
        <label>내용:</label><br/>
        <textarea name="content" rows="4" cols="60"></textarea>
    </div>
    <button type="submit">등록</button>
</form>

<hr/>

<table border="1" cellpadding="6" cellspacing="0">
    <thead>
    <tr>
        <th>ID</th>
        <th>제목</th>
        <th>내용</th>
        <th>상태</th>
        <th>등록일</th>
        <th>상태변경</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="item" items="${minwonList}">
        <tr>
            <td>${item.id}</td>
            <td>${item.title}</td>
            <td>${item.content}</td>
            <td>${item.status}</td>
            <td>${item.createdAt}</td>
            <td>
                <form method="post" action="${pageContext.request.contextPath}/minwon/status.do">
                    <input type="hidden" name="id" value="${item.id}"/>
                    <select name="status">
                        <option value="접수" ${item.status == '접수' ? 'selected' : ''}>접수</option>
                        <option value="처리중" ${item.status == '처리중' ? 'selected' : ''}>처리중</option>
                        <option value="완료" ${item.status == '완료' ? 'selected' : ''}>완료</option>
                    </select>
                    <button type="submit">변경</button>
                </form>
            </td>
        </tr>
    </c:forEach>
    <c:if test="${empty minwonList}">
        <tr>
            <td colspan="6">등록된 민원이 없습니다.</td>
        </tr>
    </c:if>
    </tbody>
</table>
</body>
</html>
