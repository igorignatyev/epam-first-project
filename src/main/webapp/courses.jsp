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
    <title>List of Courses</title>
</head>
<body>
<jsp:include page="styles/style.jsp"/>

<div style="margin-left: 10px;margin-top: 20px;width: 500px">
    <c:if test="${empty courses}">
        <p>Courses list is empty</p>
    </c:if>

    <c:if test="${not empty courses}">
        <form method="post">
            <table class="table table-condensed" style="width:500px">
                <thead>
                <tr>
                    <th></th>
                    <th>Course Name</th>
                    <th>Course Description</th>
                </tr>
                </thead>
                <c:forEach items="${courses}" var="course">
                    <tr>
                        <th scope="row"><input type="checkbox" name="option" value="${course.id}"></td>
                        <td><a href="/one_course?id=${course.id}">${course.name}</a></td>
                        <td>${course.description}</td>
                    </tr>
                </c:forEach>
            </table>
            <button type="submit" formaction="courses?action=deleteCourse">Delete</button>
        </form>
    </c:if>

    <form action="courses?action=addCourse" method="post">
        Name <br>
        <input type="text" name="name" size="20px">
        <br> Description <br>
        <input type="text" name="description" size="20px">
        <br>
        <br> Teachers <br>
        <select name="teacherId">
            <c:forEach items="${teachers}" var="teacher">
                <option value="${teacher.id}">
                        ${teacher.firstName} ${teacher.lastName}
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
