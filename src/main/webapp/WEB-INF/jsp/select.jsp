<%--
  Created by IntelliJ IDEA.
  User: TMS
  Date: 22.11.2022
  Time: 21:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<html>
<head>
    <title>Select</title>
</head>
<body>

<c:forEach var="user" items="${names}">
    <p>${user}</p>
</c:forEach>

</body>
</html>
