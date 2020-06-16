<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 30.05.2020
  Time: 21:25
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="s"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Shopping Cart</title>
</head>
<body>
<jsp:include page="menu.jsp" />
<fmt:setLocale value="en_US" scope="session"/>
<s:if test="${cartList.size() == 0}">
    <h2>There is no items in Cart</h2>
    <a href="${pageContext.request.contextPath}/allBooks">Show
        Book List</a>
</s:if>
<form method="POST" modelAttribute="cartForm" action="${pageContext.request.contextPath}/buyFromCart">
<div class="page-title">My Cart</div>
<table>
    <tr>
        <th>Book code</th>
        <th>Book title</th>
        <th>Price</th>
        <th>Quantity</th>
        <th>Order number</th>
        <th>Name of  the customer</th>
        <th>Address</th>
        <th>Email</th>
        <th>Phone</th>
        <th>Amount</th>
        <th>Status</th>
    </tr>
    <s:forEach var="cartList" items="${myCart}">
        <tr>
            <td name="code">${cartList.bookCode}</td>
            <td>${cartList.bookName}</td>
            <td>${cartList.bookPrice}</td>
            <td>${cartList.quantity}</td>
            <td>${cartList.orderNum}</td>
            <td>${cartList.name}</td>
            <td>${cartList.address}</td>
            <td>${cartList.email}</td>
            <td>${cartList.phone}</td>
            <td>${cartList.amount}</td>
            <td>${cartList.status}</td>
        </tr>
    </s:forEach>
</table>
    <a class="navi-item"
       href="${pageContext.request.contextPath}/shoppingCartConfirmation">Enter
        Customer Info</a>
    <a class="navi-item"
       href="${pageContext.request.contextPath}/continueBuy">Continue
        Buy</a>
<jsp:include page="footer.jsp" />
</body>
</html>

