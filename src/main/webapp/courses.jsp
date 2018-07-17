<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--&lt;%&ndash;--%>
<%--Created by IntelliJ IDEA.--%>
<%--User: glebkalinin--%>
<%--Date: 12.07.2018--%>
<%--Time: 15:05--%>
<%--To change this template use File | Settings | File Templates.--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<div>
    <c:if test="${empty courses}">
        <p>Courses list is empty</p>
    </c:if>

    <c:if test="${not empty courses}">
        <form method="post">
            <table border="1">
                <c:forEach items="${courses}" var="course">
                    <tr>
                        <td><input type="checkbox" name="option" value="${course.id}"><a
                                href="/one_course?id=${course.id}">${course.id}</a></td>
                        <td>${course.name}</td>
                        <td>${course.description}</td>
                        <td>${course.teacherId}</td>
                    </tr>
                </c:forEach>
            </table>
            <button type="submit" formaction="courses?action=deleteCourse">Delete</button>
        </form>
    </c:if>

    <form action="courses?action=addCourse" method="post">
        <br> ID <br>
        <input type="text" name="id" size="20px">
        <br> Name <br>
        <input type="text" name="name" size="20px">
        <br> Description <br>
        <input type="text" name="description" size="20px">
        <br>
        <br> Teachers <br>
        <select name="teacherId">
            <c:forEach items="${teachers}" var="teacher">
                <option value="${teacher.id}">
                        ${teacher.firstName} ${teacher.lastName} with Id ${teacher.id}
                </option>
            </c:forEach>
        </select>
        <br>
        <br>
        <input type="submit" value="Add">
        <br>
    </form>

</div>
</body>
</html>
