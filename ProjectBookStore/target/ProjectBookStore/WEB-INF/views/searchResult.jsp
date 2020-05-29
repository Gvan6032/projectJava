<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 29.05.2020
  Time: 17:51
  To change this template use File | Settings | File Templates.
--%>
%@ page language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Search Result</title>
</head>
<body>
<div align="center">
    <h2>Search Result</h2>
    <table border="1" cellpadding="5">
        <tr>
            <th>Code</th>
            <th>Name</th>
            <th>Author</th>
            <th>Price</th>
        </tr>
        <c:forEach items="${result}" var="book">
            <tr>
                <td>${book.nameBook}</td>
                <td>${book.author}</td>
                <td>${book.priceBook}</td>
                <td>${book.nameBook}</td>
            </tr>
        </c:forEach>
    </table>
</div>
</body>
</html>
