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
    <title>List of Teachers</title>
</head>
<body>
<jsp:include page="styles/style.jsp"/>
<div style="margin-top: 20px; margin-left: 10px; width: 500px">
    <c:if test="${empty teachers}">
        <p>Teachers list is empty</p>
    </c:if>

    <c:if test="${not empty teachers}">
        <form method="post">
            <table class="table table-condensed" style="width:500px">
                <thead>
                <tr>
                    <th></th>
                    <th>First Name</th>
                    <th>Last Name</th>
                </tr>
                </thead>
                <tbody>
                    <c:forEach items="${teachers}" var="teacher">
                        <tr>
                            <th scope="row"><input type="checkbox" name="option" value="${teacher.id}"></th>
                            <td><a href="one_teacher?id=${teacher.id}">${teacher.firstName}</a></td>
                            <td>${teacher.lastName}</td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
            <button type="submit" formaction="teachers?action=deleteTeacher">
                Delete
            </button>
        </form>
    </c:if>
    <form method="post" action="teachers?action=addTeacher">
        First Name <br>
        <input type="text" name="firstName" size="20px">
        <br> Last Name <br>
        <input type="text" name="lastName" size="20px">
        <br> Login <br>
        <input type="text" name="login"size="20px">
        <br> Password <br>
        <input type="text" name="password"size="20px">
        <br>
        <input type="submit" value="Add" style="margin-top: 5px">
    </form>
</div>
</body>
</html>
