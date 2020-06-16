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
<%@ taglib prefix="s" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>
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
<h2>To purchase a book fill in the fields information</h2>
<form method="POST" modelAttribute="customerForm" action="${pageContext.request.contextPath}/cartForBuy">
    <div class="page-title">Edit Customer Information</div>
    <table>
    <s:forEach var="cart" items="${myCart}">
        <tr>
        <td>Code: </td>
        <td><input type="text" path="code" name="code" value="${cart.bookCode}"/></td>
        <td><errors path="name" class="error-message" /></td>
        </tr>
        <tr>
        <td>Quantity: </td>
        <td><input type="text" path="quantity" name="quantity" value="${cart.quantity}"/></td>
        <td><errors path="name" class="error-message" /></td>
        </tr>
        <tr>
            <td>Name: </td>
            <td><input type="text" path="name" name="name" value="${cart.bookName}"/></td>
            <td><errors path="name" class="error-message" /></td>
        </tr>
        <tr>
            <td>Email:</td>
            <td><input type="email" path="email" name="email" value="${cart.email}" /></td>
            <td><errors path="email" class="error-message" /></td>
        </tr>
        <tr>
            <td>Phone: </td>
            <td><input type="tel" path="phone" name="phone" value="${cart.phone}"/></td>
            <td><errors path="phone" class="error-message" /></td>
        </tr>
        <tr>
            <td>Address: </td>
            <td><input type="text" path="address" name="address" value="${cart.address}"/></td>
            <td><errors path="address" class="error-message" /></td>
        </tr>
    </s:forEach>
    </table>
    <form action="/cartForBuy">
        <input type="submit" value="Add" />
    </form>
    <jsp:include page="footer.jsp" />
</body>
</html>

