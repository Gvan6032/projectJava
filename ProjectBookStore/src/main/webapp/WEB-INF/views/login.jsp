<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 23.05.2020
  Time: 22:44
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Login</title>
    <link href="../css/styles1.css" rel="stylesheet"  type="text/css">
</head>
<body>

<jsp:include page="header.jsp" />
<jsp:include page="menu.jsp" />

<div class="page-title">Login (For Employee, Manager)</div>
<div class="login-container">
    <h3>Enter username and password</h3>
    <br>
    <c:if test="${param.error == 'true'}">
        <div style="color: red; margin: 10px 0px;">
            Login Failed!!!<br /> Reason :
                ${sessionScope["SPRING_SECURITY_LAST_EXCEPTION"].message}

        </div>
    </c:if>
    <form method="POST"
          action="${pageContext.request.contextPath}/j_spring_security_check">
        <table>
            <tr>
                <td>User Name *</td>
                <td><input name="userName" /></td>
            </tr>
            <tr>
                <td>Password *</td>
                <td><input type="password" name="password" /></td>
            </tr>
            <tr>
                <td>&nbsp;</td>
                <td><input type="submit" value="Login" /> <input type="reset"
                                                                 value="Reset" /></td>
            </tr>
        </table>
    </form>
    <span class="error-message">${error}</span>
    <form action="/editBook" method="get">
        <input type="submit" value="Books"></input>
    </form>
</div>
<jsp:include page="footer.jsp"/>
</body>
</html>
