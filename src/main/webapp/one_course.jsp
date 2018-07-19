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
    <title>Edit Course</title>
</head>
<body>
<jsp:include page="styles/style.jsp"/>
<div style="margin-left: 10px">
<h1>Course</h1>

<form method="post">
    <br> Name <br>
    <input type="text" name="name" value="${name}" size="20px">
    <br> Description <br>
    <input type="text" name="description" value="${description}" size="20px">
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
    <button type="submit" formaction="one_course?id=${id}&action=editCourse">Save</button>
    <button type="submit" formaction="one_course?id=${id}&action=deleteCourse">Delete</button>
</form>
</div>
</body>

</html>

