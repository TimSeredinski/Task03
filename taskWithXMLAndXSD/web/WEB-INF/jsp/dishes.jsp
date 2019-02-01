<%--
  Created by IntelliJ IDEA.
  User: Tim
  Date: 27.01.2019
  Time: 16:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.local}"/>
<fmt:setBundle basename="pagecontent" var="loc"/>
<html>
<body>
<table border="1" rules="all" cellpadding="10">
    <thead>
    <tr>
        <th><fmt:message key="thead.name" bundle="${loc}"/></th>
        <th><fmt:message key="thead.image" bundle="${loc}"/></th>
        <th><fmt:message key="thead.description" bundle="${loc}"/></th>
        <th><fmt:message key="thead.portion" bundle="${loc}"/></th>
        <th><fmt:message key="thead.price" bundle="${loc}"/></th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${myMenu}" var="dish">
    <jsp:useBean id="dish" class="by.epam.task03.entity.Dish" type="java.lang.Object" scope="request"/>
    <tr>
        <td><c:out value="${dish.name}"/></td>
        <td><c:out value="${dish.img}"/></td>
        <td><c:out value="${dish.description}"/> <c:out value="${dish.additionDesc}"/></td>
        <td><c:out value="${dish.portion}"/> <c:out value="${dish.additionPortion}"/></td>
        <td><c:out value="${dish.price}"/></td>
    </tr>
    </tbody>
    </c:forEach>
</table>
<fmt:message key="button.name.ru" bundle="${loc}" var="ru_button"/>
<fmt:message key="button.name.en" bundle="${loc}" var="en_button"/>
<fmt:message key="button.name.home" bundle="${loc}" var="home"/>
<form action="${sessionScope.req}" method="post">
    <input type="hidden" name="local" value="ru_RU"/>
    <input type="submit" value="${ru_button}">
</form>
<form action="${sessionScope.req}" method="post">
    <input type="hidden" name="local" value="en_US"/>
    <input type="submit" value="${en_button}">
</form>
<form action="index.jsp" method="post">
    <input type="submit" value="${home}">
</form>
</body>
</html>
