<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 29.05.2020
  Time: 17:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles.css">
    <title>Search Result</title>
</head>
<body>
<jsp:include page="header.jsp" />
<jsp:include page="menu.jsp" />
<fmt:setLocale value="en_US" scope="session"/>
<div align="center">
    <h2>Search Result</h2>
    <table border="1" cellpadding="5">
        <tr>
            <th>Code</th>
            <th>Name</th>
            <th>Author</th>
            <th>Price</th>
        </tr>
    <c:forEach var="Book" items="${result}">
    <tr>
    <td>${Book.id}</td>
    <td>${Book.nameBook}</td>
    <td>${Book.author}</td>
    <td>${Book.priceBook}</td>
    </tr>
    </c:forEach>
    </table>
</div>
</body>
</html>
