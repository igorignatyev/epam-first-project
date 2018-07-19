<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>&ndash;%&gt;--%>
<%--&lt;%&ndash;--%>
<%--Created by IntelliJ IDEA.--%>
<%--User: glebkalinin--%>
<%--Date: 12.07.2018--%>
<%--Time: 15:05--%>
<%--To change this template use File | Settings | File Templates.--%>
<%--&ndash;%&gt;--%>
<%--&lt;%&ndash;<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>&ndash;%&gt;--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<div>

    <form action="registration?action=register" method="post">
        <br> First Name <br>
        <input type="text" name="firstName"size="20px">
        <br> Last Name <br>
        <input type="text" name="lastName"size="20px">
        <br> Login <br>
        <input type="text" name="login"size="20px">
        <br> Password <br>
        <input type="password" name="password"size="20px">
        <br>
        <br> Status <br>
        <select name="status">
            <c:forEach items="${statuses}" var="status">
                <option value="${status}">
                        ${status}
                </option>
            </c:forEach>
        </select>
        <br>
        <br>
        <input type="submit" value="Register">
        <br>
    </form>

</div>
</body>
</html>
