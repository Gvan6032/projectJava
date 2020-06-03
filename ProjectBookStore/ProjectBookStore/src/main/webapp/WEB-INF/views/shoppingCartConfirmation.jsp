<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 23.05.2020
  Time: 22:47
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Shopping Cart Confirmation</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles.css">
</head>
<body>
<jsp:include page="header.jsp" />
<jsp:include page="menu.jsp" />
<fmt:setLocale value="en_US" scope="session"/>

<div class="page-title">Confirmation</div>

<div class="customer-info-container">
    <h3>Customer Information:</h3>
    <ul>
        <li>Name: ${myCart.customerInfo.name}</li>
        <li>Email: ${myCart.customerInfo.email}</li>
        <li>Phone: ${myCart.customerInfo.phone}</li>
        <li>Address: ${myCart.customerInfo.address}</li>
    </ul>
    <h3>Cart Summary:</h3>
    <ul>
        <li>Quantity: ${myCart.quantityTotal}</li>
        <li>Total:
            <span class="total">
            <fmt:formatNumber value="${myCart.amountTotal}" type="currency"/>
          </span></li>
    </ul>
</div>

<form method="POST"
      action="${pageContext.request.contextPath}/shoppingCartConfirmation">
    <a class="navi-item"
       href="${pageContext.request.contextPath}/shoppingCart">Edit Cart</a>
    <a class="navi-item"
       href="${pageContext.request.contextPath}/shoppingCartCustomer">Edit
        Customer Info</a>
    <input type="submit" value="Send" class="button-send-sc" />
</form>

<div class="container">
    <c:forEach items="${myCart.cartLines}" var="cartLineInfo">
        <div class="book-preview-container">
            <ul>
                <li><img class="book-image"
                         src="${pageContext.request.contextPath}/bookImage?code=${cartLineInfo.bookInfo.code}" /></li>
                <li>Code: ${cartLineInfo.bookInfo.code} <input
                        type="hidden" name="code" value="${cartLineInfo.bookInfo.code}" />
                </li>
                <li>Name: ${cartLineInfo.bookInfo.name}</li>
                <li>Price: <span class="price">
                     <fmt:formatNumber value="${cartLineInfo.bookInfo.price}" type="currency"/>
                  </span>
                </li>
                <li>Quantity: ${cartLineInfo.quantity}</li>
                <li>Subtotal:
                    <span class="subtotal">
                       <fmt:formatNumber value="${cartLineInfo.amount}" type="currency"/>
                    </span>
                </li>
            </ul>
        </div>
    </c:forEach>
</div>
<jsp:include page="footer.jsp" />
</body>
</html>
