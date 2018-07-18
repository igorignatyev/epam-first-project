
<%--
  Created by IntelliJ IDEA.
  User: glebkalinin
  Date: 11.07.2018
  Time: 15:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" session="true" language="java" %>
<html>
<head>
    <title>Welcome!</title>
</head>
<body>
<jsp:include page="styles/style.jsp"/>

<div style="margin-left: 10px">
    <h1>Welcome: ${name}</h1>
    <ul>
        <li><a href="/teachers">teachers</a></li>
        <li><a href="/courses">courses</a></li>
        <li><a href="/students">students</a></li>
    </ul>
</div>


</body>
</html>
