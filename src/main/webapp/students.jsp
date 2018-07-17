<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>--%>
<%--
  Created by IntelliJ IDEA.
  User: glebkalinin
  Date: 12.07.2018
  Time: 15:05
  To change this template use File | Settings | File Templates.
--%>
<%--<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<div>
    <c:if test="${empty students}">
        <p>Students list is empty</p>
    </c:if>

    <c:if test="${not empty students}">
        <form method="post">
            <table border="1">
                <c:forEach items="${students}" var="student">
                    <tr>
                        <td><input type="checkbox" name="option" value="${student.id}"><a
                                href="/one_student?id=${student.id}">${student.id}</a></td>
                        <td>${student.firstName}</td>
                        <td>${student.lastName}</td>
                    </tr>
                </c:forEach>
            </table>
            <button type="submit" formaction="students?action=deleteStudent">Delete</button>
        </form>
    </c:if>

    <form action="students?action=addStudent" method="post">
        First Name <br>
        <input type="text" name="firstName" size="20px">
        <br> Last Name <br>
        <input type="text" name="lastName" size="20px">
        <br>
        <input type="submit" value="Add">
    </form>
</div>
</body>
</html>

