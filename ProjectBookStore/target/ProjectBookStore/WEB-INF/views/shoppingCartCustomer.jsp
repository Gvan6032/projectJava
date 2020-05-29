<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 23.05.2020
  Time: 22:48
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Enter Customer Information</title>
    <link href="../css/styles.css" rel="stylesheet"  type="text/css">
</head>
<body>
<jsp:include page="header.jsp" />
<jsp:include page="menu.jsp" />

<div class="page-title">Enter Customer Information</div>
<form method="POST" modelAttribute="customerForm" action="${pageContext.request.contextPath}/shoppingCartCustomer">
    <table>
        <tr>
            <td>Name *</td>
            <td><input type="text" path="name"/></td>
            <td><errors path="name" class="error-message" /></td>
        </tr>
        <tr>
        <td>Email *</td>
        <td><input type="email" path="email" /></td>
        <td><errors path="email" class="error-message" /></td>
        </tr>
        <tr>
            <td>Phone *</td>
            <td><input type="tel" path="phone" /></td>
            <td><errors path="phone" class="error-message" /></td>
        </tr>
        <tr>
            <td>Address *</td>
            <td><input type="text" path="address" /></td>
            <td><errors path="address" class="error-message" /></td>
        </tr>
    </table>
    <form action="/toTheCart" method="get">
        <button type="submit" value="Send to cart">
</form>
<jsp:include page="footer.jsp" />
</body>
</html>

