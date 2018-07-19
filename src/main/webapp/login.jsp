<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="i18n.login" var="login"/>
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
    <title><fmt:message bundle="${login}" key="login.title"/></title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/flag-icon-css/3.1.0/css/flag-icon.min.css"/>
</head>
<body>
<h1><fmt:message bundle="${login}" key="login.title"/></h1>

<c:set var="currentUrl" value="${requestScope['javax.servlet.forward.request_uri']}?${pageContext.request.queryString}"/>
<c:url var="localUrl" value="/locale"/>
<form action="${localUrl}" method="post">
    <input type="hidden" name="redirect_to" value="${currentUrl}">
    <input type="hidden" name="locale" value="en">
    <button type="submit" class="flag-icon flag-icon-us" title="${enTitle}"></button>

</form>
<form action="${localUrl}" method="post">
    <input type="hidden" name="redirect_to" value="${currentUrl}">
    <input type="hidden" name="locale" value="ru">
    <button type="submit" class="flag-icon flag-icon-ru" title="${ruTitle}"></button>
</form>

<form method="post" action="login?action=login">
    <br> <fmt:message bundle="${login}" key="login.email"/> <br>
    <input type="text" name="email" size="20px">
    <br> <fmt:message bundle="${login}" key="login.password"/> <br>
    <input type="password" name="password" size="20px">
    <br>
    <select name="locale">
        <c:forEach items="${locales}" var="locale">
            <option value="${locale}">
                    ${locale}
            </option>
        </c:forEach>
    </select>
    <input type="submit" value="Log In!">
</form>

<a href="/registration">Sign up</a>
</body>
</html>