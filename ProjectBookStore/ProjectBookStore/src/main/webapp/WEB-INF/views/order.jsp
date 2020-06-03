<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 23.05.2020
  Time: 22:44
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Bookshop online</title>
</head>
<body>
<jsp:include page="header.jsp" />
<jsp:include page="menu.jsp" />
<fmt:setLocale value="en_US" scope="session"/>
<div class="page-title">Order Info</div>
<div class="customer-info-container">
    <h3>Customer Information:</h3>
    <ul>
        <li>Name: ${orderInfo.customerName}</li>
        <li>Email: ${orderInfo.customerEmail}</li>
        <li>Phone: ${orderInfo.customerPhone}</li>
        <li>Address: ${orderInfo.customerAddress}</li>
    </ul>
    <h3>Order Summary:</h3>
    <ul>
        <li>Total:
            <span class="total">
           <fmt:formatNumber value="${orderInfo.amount}" type="currency"/>
           </span></li>
    </ul>
</div>
<br/>
<table border="1" style="width:100%">
    <tr>
        <th>Book Code</th>
        <th>Book Name</th>
        <th>Quantity</th>
        <th>Price</th>
        <th>Amount</th>
    </tr>
    <c:forEach items="${orderInfo.details}" var="orderDetailInfo">
        <tr>
            <td>${orderDetailInfo.bookCode}</td>
            <td>${orderDetailInfo.bookName}</td>
            <td>${orderDetailInfo.quanity}</td>
            <td>
                <fmt:formatNumber value="${orderDetailInfo.price}" type="currency"/>
            </td>
            <td>
                <fmt:formatNumber value="${orderDetailInfo.amount}" type="currency"/>
            </td>
        </tr>
    </c:forEach>
</table>
<c:if test="${pagination.totalPages > 1}">
    <div class="page-navigator">
        <c:forEach items="${pagination.navigationPages}" var = "page">
            <c:if test="${page != -1 }">
                <a href="orderList?page=${page}" class="nav-item">${page}</a>
            </c:if>
            <c:if test="${page == -1 }">
                <span class="nav-item"> ... </span>
            </c:if>
        </c:forEach>

    </div>
</c:if>
<jsp:include page="footer.jsp" />
</body>
</html>
