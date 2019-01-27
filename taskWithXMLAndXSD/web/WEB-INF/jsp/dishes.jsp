<%--
  Created by IntelliJ IDEA.
  User: Tim
  Date: 27.01.2019
  Time: 16:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<body>
<c:forEach items="${myMenu}" var="dish">
    <jsp:useBean id="dish" class="by.epam.task03.entity.Dish" type="java.lang.Object" scope="request"/>
        <jsp:getProperty name="dish" property="id"/>
        <jsp:getProperty name="dish" property="name"/>
        <jsp:getProperty name="dish" property="img"/>
        <jsp:getProperty name="dish" property="description"/>
        <jsp:getProperty name="dish" property="portion"/>
        <jsp:getProperty name="dish" property="price"/>
</c:forEach>
</body>
</html>
