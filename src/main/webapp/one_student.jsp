<%--
  Created by IntelliJ IDEA.
  User: glebkalinin
  Date: 13.07.2018
  Time: 16:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Edit Student</title>
</head>
<body>
<jsp:include page="styles/style.jsp"/>
<div style="margin-left: 10px">
<h1>Student</h1>

<form method="post">
    <br> First Name <br>
    <input type="text" name="firstName" value="${firstName}" size="20px">
    <br> Last Name <br>
    <input type="text" name="lastName" value="${lastName}" size="20px">
    <br>
    <div style="margin-top: 5px">
        <button type="submit" formaction="one_student?id=${id}&action=editStudent">Save</button>
        <button type="submit" formaction="one_student?id=${id}&action=deleteStudent">Delete</button>
    </div>
</form>
</div>
</body>
</html>
