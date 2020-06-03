<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 23.05.2020
  Time: 22:50
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>User Info</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles.css">
</head>
<body>

<jsp:include page="header.jsp" />
<jsp:include page="menu.jsp" />

<div class="page-title">User Info</div>
<div class="user-container">
    <ul>
        <li>User Name: ${pageContext.request.userPrincipal.name}</li>
        <li>Role:
            <ul>
                <c:forEach items="${userDetails.authorities}" var="auth">
                    <li>${auth.authority }</li>
                </c:forEach>
            </ul>
        </li>
    </ul>
</div>

<jsp:include page="footer.jsp" />

</body>
</html>