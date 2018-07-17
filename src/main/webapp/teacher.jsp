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
    <title>Title</title>
</head>
<body>
<h1>Welcome, ${teacher.firstName} ${teacher.lastName}</h1>


<c:forEach items="${teachersCourses}" var="teacherCourse">
    <table border="1" title="Students">
        <tr>
            <td><a href="/course_for_teacher?teacherId=${teacher.id}&courseId=${teacherCourse.id}">${teacherCourse.id}</a></td>
            <td>${teacherCourse.name}</td>
            <td>${teacherCourse.description}</td>
        </tr>
    </table>
</c:forEach>


</body>
</html>