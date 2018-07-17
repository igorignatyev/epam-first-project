<%--
  Created by IntelliJ IDEA.
  User: glebkalinin
  Date: 15.07.2018
  Time: 13:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form method="post">
    <h1>${course.name}</h1>
    <h2>${course.description}</h2>
    <h3>Teacher: ${teacher.firstName} ${teacher.lastName}</h3>
    <button type="submit" formaction="course?studentId=${student.id}&courseId=${course.id}&action=register">Register</button>
</form>
</body>
</html>
