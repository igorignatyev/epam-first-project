<%--
  Created by IntelliJ IDEA.
  User: glebkalinin
  Date: 18.07.2018
  Time: 11:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<jsp:include page="styles/style.jsp"/>
<h2>This is your review for course: ${course.name}</h2>

Feedback:<br>
${review.feedback}
<br><br>Mark:<br>
${review.mark}


<form action="/logout" method="post">
    <input type="submit" value="Logout" >
</form>

</body>
</html>
