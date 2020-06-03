<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 26.05.2020
  Time: 17:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1" %>
<%@ taglib prefix="s" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles.css">
</head>
<title>Admin page</title>
</head>
<body><jsp:include page="header.jsp"/>
<jsp:include page="menu.jsp"/>
<table>
    <tr>
        <th>Name</th>
        <th>Author</th>
        <th>Description</th>
        <th>Price</th></h>
        <th>Code</th>
    </tr>
    <s:forEach var="Book" items="${booksAll}">
        <tr>
            <td>${Book.nameBook}</td>
            <td>${Book.author}</td>
            <td>${Book.description}</td>
            <td>${Book.priceBook}</td>
            <td>${Book.id}</td>
            <td>
                <a href="/edit?id=${Book.id}">Edit</a>
                <a href="/delete?id=${Book.id}">Delete</a>
            </td>
        </tr>
    </s:forEach>
</table>
<form action="/new" method="get">
    <input type="submit" value="New book"></input>
</form>
</body>
</html>

