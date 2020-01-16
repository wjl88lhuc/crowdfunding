<%--
  Created by IntelliJ IDEA.
  User: wenjunlong8
  Date: 2020/1/15 0015
  Time: 8:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Title</title>
</head>
<body>
    <c:if test="${!empty list}">
        <c:forEach items="${list}" var="admin">
            ${admin} <br/>
        </c:forEach>
    </c:if>
</body>
</html>
