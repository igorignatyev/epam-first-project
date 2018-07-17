<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Игорь
  Date: 13.07.2018
  Time: 14:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Teachers</title>
</head>
<body>
    <div>
        <table border="1">
            <tr>
                <td>ID</td>
                <td>First Name</td>
                <td>Last Name</td>
            </tr>
            <c:forEach items="${teachers}" var="teacher">
                <tr>
                    <td><a href="teacher?id=${teacher.id}">${teacher.id}</a></td>
                    <td>${teacher.firstName}</td>
                    <td>${teacher.lastName}</td>
                </tr>
            </c:forEach>
        </table>
        <form method="post" action="teachers?action=addTeacher">
            <br> ID <br>
            <input type="text" name="id" size="20px">
            <br> First Name <br>
            <input type="text" name="firstName" size="20px">
            <br> Last Name <br>
            <input type="text" name="lastName" size="20px">
            <br>
            <input type="submit" value="Add">
        </form>
    </div>
</body>
</html>
