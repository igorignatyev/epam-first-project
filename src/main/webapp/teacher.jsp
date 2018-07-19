<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: glebkalinin
  Date: 16.07.2018
  Time: 12:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Welcome, ${teacher.firstName} ${teacher.lastName}!</title>
</head>
<body>
<jsp:include page="styles/style.jsp"/>

<div style="margin-left: 10px">
<h1>Welcome, ${teacher.firstName} ${teacher.lastName}</h1>


<c:forEach items="${teachersCourses}" var="teacherCourse">
    <table class="table table-condensed" style="width:500px" title="Students">
        <thead>
        <tr>
            <th>ID</th>
            <th>Course Name</th>
            <th>Course Description</th>
        </tr>
        </thead>
        <tbody>
        <tr>
            <th scope="row"><a href="/course_for_teacher?teacherId=${teacher.id}&courseId=${teacherCourse.id}">${teacherCourse.id}</a></th>
            <td>${teacherCourse.name}</td>
            <td>${teacherCourse.description}</td>
        </tr>
        </tbody>
    </table>
</c:forEach>
</div>

</body>
</html>