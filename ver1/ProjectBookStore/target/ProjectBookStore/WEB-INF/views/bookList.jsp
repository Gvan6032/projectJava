<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 23.05.2020
  Time: 22:42
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Book List</title>
    <link href="../css/styles.css" rel="stylesheet"  type="text/css">
</head>
<body>
<jsp:include page="header.jsp" />
<jsp:include page="menu.jsp" />
<fmt:setLocale value="en_US" scope="session"/>
<div class="page-title">Book List</div>

<c:forEach items="${paginationBooks.list}" var="bookInfo">
    <div class="book-preview-container">
        <ul>
            <li>Code: ${bookInfo.code}</li>
            <li>Name: ${bookInfo.name}</li>
            <li>Price: <fmt:formatNumber value="${bookInfo.price}" type="currency"/></li>
            <li><a
                    href="${pageContext.request.contextPath}/buyBook?code=${bookInfo.code}">
                Buy Now</a></li>
            <security:authorize  access="hasRole('ADMIN')">
                <li><a style="color:red;"
                       href="${pageContext.request.contextPath}/book?code=${bookInfo.code}">
                    Edit Book</a></li>
            </security:authorize>
        </ul>
    </div>
</c:forEach>
<br/>
<c:if test="${paginationBooks.totalPages > 1}">
    <div class="page-navigator">
        <c:forEach items="${paginationBooks.navigationPages}" var = "page">
            <c:if test="${page != -1 }">
                <a href="bookList?page=${page}" class="nav-item">${page}</a>
            </c:if>
            <c:if test="${page == -1 }">
                <span class="nav-item"> ... </span>
            </c:if>
        </c:forEach>
    </div>
</c:if>
<jsp:include page="footer.jsp"/>
</body>
</html>
