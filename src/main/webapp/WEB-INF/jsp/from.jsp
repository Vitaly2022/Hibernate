<%--
  Created by IntelliJ IDEA.
  User: TMS
  Date: 01.12.2022
  Time: 20:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<html>
<head>
    <title>From</title>
</head>
<body>

<c:forEach var="user" items="${group}">
    <p>${user}</p>
</c:forEach>

</body>
</html>
