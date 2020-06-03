<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 31.05.2020
  Time: 11:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Adding a book to the cart</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles.css">
</head>
<body>
<jsp:include page="header.jsp" />
<jsp:include page="menu.jsp" />
<div class="page-title">Order added to the cart!</div>
<h3 style="color:red;">You can view the shopping cart</h3>
<form action="/buyFromCart">
    <input type="submit" value="Add" />
</form>
<jsp:include page="footer.jsp" />
</body>
</html>
