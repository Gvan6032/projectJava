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
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles.css">
</head>
<title>Choose a books to buy</title>
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
            <td><jsp:param name="code" value="${Book.id}"></jsp:param></td>
            <td><form action="/shoppingCartCustomer" method="get">
                <button type="submit" value="Make an order"></button>
            </form> </td>
        </tr>
    </s:forEach>
</table>
<jsp:include page="footer.jsp" />
</body>
</html>
