<%--
  Created by IntelliJ IDEA.
  User: glebkalinin
  Date: 16.07.2018
  Time: 13:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Student: ${student.firstName} ${student.lastName}</title>
</head>
<body>
<div style="margin-left: 10px">
<jsp:include page="styles/style.jsp"/>
<h1>${teacher.firstName} ${teacher.lastName}</h1>

<h2>Student: ${student.firstName} ${student.lastName}</h2>

<form method="post">
    <p><b>Enter your feedback about this student:</b></p>
    <p><textarea rows="10" cols="45" name="feedback"></textarea></p>
    <p>Mark:</p>
    <select name="mark">
        <option value="5">5</option>
        <option value="4">4</option>
        <option value="3">3</option>
        <option value="2">2</option>
    </select>
    <p><button type="submit" formaction="/teacher/student_for_teacher?teacherId=${teacher.id}&studentId=${studentId}&courseId=${courseId}&action=send">Send</button> </p>
</form>

    <a href="/teacher/course_for_teacher?teacherId=${teacher.id}&courseId=${courseId}">Back to students</a>

    <form action="/logout" method="post">
        <input type="submit" value="Logout" >
    </form>
</div>
</body>
</html>
