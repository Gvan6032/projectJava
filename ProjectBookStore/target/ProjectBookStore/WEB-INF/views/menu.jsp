<%--
  Created by IntelliJ IDEA.
   User: user
  Date: 15.05.2020
  Time: 21:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %>
<div class="menu-container">
    <a href="${pageContext.request.contextPath}/">Home</a>
    |
    <a href="${pageContext.request.contextPath}/bookList">
        Book List
    </a>
    |
    <a href="${pageContext.request.contextPath}/shoppingCart">
        My Cart
    </a>
    |
    <security:authorize  access="hasAnyRole('ROLE_MANAGER','ROLE_EMPLOYEE')">
        <a href="${pageContext.request.contextPath}/orderList">
            Order List
        </a>
        |
    </security:authorize>

    <security:authorize  access="hasRole('ROLE_MANAGER')">
        <a href="${pageContext.request.contextPath}/book">
            Create Book
        </a>
        |
    </security:authorize>
</div>