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
    <p><select name="codeBook">
    <option value="Select">Select a book code</option>
    <s:forEach var="Book" items="${booksAll}">
        <option name ="codeBook">${Book.id}</option>
    </s:forEach>
    </select></p>
<p><select name="quantity" value="0">
    <option>Number</option>
    <option>1</option>
    <option>2</option>
    <option>3</option>
    <option>4</option>
    <option>5</option>
    <option>6</option>
    <option>7</option>
    <option>8</option>
    <option>9</option>
    <option>10</option>
</select>
</p>
<div class="page-title">Enter Customer Information</div>
    <table>
        <tr>
            <td>Name *</td>
            <td><input type="text" path="name" name="name"/></td>
            <td><errors path="name" class="error-message" /></td>
        </tr>
        <tr>
        <td>Email *</td>
        <td><input type="email" path="email" name="email" /></td>
        <td><errors path="email" class="error-message" /></td>
        </tr>
        <tr>
            <td>Phone *</td>
            <td><input type="tel" path="phone" name="phone" /></td>
            <td><errors path="phone" class="error-message" /></td>
        </tr>
        <tr>
            <td>Address *</td>
            <td><input type="text" path="address" name="address" /></td>
            <td><errors path="address" class="error-message" /></td>
        </tr>
    </table>
    <form action="/cartForBuy">
        <input type="submit" value="Add" />
    </form>
<jsp:include page="footer.jsp" />
</body>
</html>

