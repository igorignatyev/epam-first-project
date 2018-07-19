<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: glebkalinin
  Date: 16.07.2018
  Time: 12:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>${course.name}</title>
</head>
<body>
<jsp:include page="styles/style.jsp"/>
<div style="margin-left: 10px">
<h1>${teacher.firstName} ${teacher.lastName}</h1>

<h2>${course.name} Course</h2>

<c:if test="${empty students}">
    <p>Students have not register for this course yet</p>
</c:if>

<c:if test="${not empty students}">
    <p>Students registered for the course:</p>
    <c:forEach items="${students}" var="student">
        <table class="table table-condensed" style="width:500px">
            <thead>
            <tr>
                <th>First Name</th>
                <th>Last Name</th>
            </tr>
            </thead>
            <tbody>
            <tr>
                <th scope="row"><a href="/student_for_teacher?teacherId=${teacher.id}&studentId=${student.id}&courseId=${course.id}">${student.firstName}</a>
                </th>
                <td>${student.lastName}</td>
            </tr>
            </tbody>
        </table>
    </c:forEach>
</c:if>
<br>
<a href="/teacher?teacherId=${teacher.id}">Back to courses</a>
</div>
</body>
</html>
