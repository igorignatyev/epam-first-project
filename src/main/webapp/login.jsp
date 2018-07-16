<%--
  Created by IntelliJ IDEA.
  User: Игорь
  Date: 16.07.2018
  Time: 13:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <h1> Login Page </h1>
    <form method="post" action="login?action=login">
        <br> E-mail <br>
        <input type="text" name="email" size="20px">
        <br> Password <br>
        <input type="password" name="password" size="20px">
        <br>
        <input type="submit" value="Log In!">
    </form>
</body>
</html>
