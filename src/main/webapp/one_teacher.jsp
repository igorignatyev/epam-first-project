<%--
  Created by IntelliJ IDEA.
  User: Игорь
  Date: 13.07.2018
  Time: 15:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Edit Teacher</title>
</head>
<body>
<jsp:include page="styles/style.jsp"/>
<div style="margin-left: 10px">

<form method="post">
    <br> First Name <br>
    <input type="text" name="firstName" value="${firstName}" size="20px">
    <br> Last Name <br>
    <input type="text" name="lastName" value="${lastName}" size="20px">
    <br>
    <div style="margin-top: 5px">
        <button type="submit" style="margin-top:5px" formaction="one_teacher?id=${id}&action=editTeacher">Save</button>
        <button type="submit" style="margin-top:5px" formaction="one_teacher?id=${id}&action=deleteTeacher">Delete</button>
    </div>
</form>
</div>
</body>
</html>

