<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: glebkalinin
  Date: 15.07.2018
  Time: 12:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Welcome, ${student.firstName} ${student.lastName}!</title>
</head>
<body>
<jsp:include page="styles/style.jsp"/>
<div style="margin-left: 10px">
<h1>Welcome, ${student.firstName} ${student.lastName}</h1>

Your courses:<br>
<c:if test="${empty registered_courses}">
    You have not register in any courses yet. Please, register now.
</c:if>
<c:if test="${not empty registered_courses}">
    <form method="post">
    <c:forEach items="${registered_courses}" var="registered_course">
            <li><input type="checkbox" name="option" value="${registered_course.id}">${registered_course.name}, ${registered_course.description}</li>
    </c:forEach>
        <button type="submit" formaction="student?studentId=${student.id}&action=deleteParticipation">Unregister</button>
    </form>
</c:if>

<br>Available courses:<br>
<c:if test="${empty courses}">
    No available courses. Please, try again later.
</c:if>

<c:if test="${not empty courses}">
    <table class="table table-condensed" style="width:500px">
        <thead>
        <tr>
            <th>ID</th>
            <th>Course Name</th>
            <th>Course Description</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${courses}" var="course">
            <tr>
                <th scope="row"><a href="/course?studentId=${student.id}&courseId=${course.id}">${course.id}</a></th>
                <td>${course.name}</td>
                <td>${course.description}</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</c:if>

<br>Completed courses:<br>
<c:if test="${empty completed_courses}">
    No completed courses yet.
</c:if>

<c:if test="${not empty completed_courses}">
    <c:forEach items="${completed_courses}" var="completed_course">
        <li><a href="/review?studentId=${student.id}&courseId=${completed_course.id}">${completed_course.name}</a></li>
    </c:forEach>
</c:if>

</div>
</body>
</html>