<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 02.06.2020
  Time: 22:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Edit book</title>
</head>
<body>
<div align="center">
    <h2>Edit book</h2>
    <form action="save" method="post" modelAttribute="book">
        <table border="0" cellpadding="5">
            <tr>
                <td>ID: </td>
                <td>${book.id}
                    <form:hidden path="id"/>
                </td>
            <tr>
                <td>Code: </td>
                <td><input path="id" /></td>
            </tr>
            <tr>
                <td>Date: </td>
                <td><input path="createDate" /></td>
            </tr>
            <tr>
                <td>Name: </td>
                <td><input path="nameBook" /></td>
            </tr>
            <tr>
                <td>Author: </td>
                <td><input path="author" /></td>
            </tr>
            <tr>
                <td>Description: </td>
                <td><input path="description" /></td>
            </tr>
            <tr>
                <td>Price: </td>
                <td><input path="priceBook" /></td>
            </tr>
            <tr>
                <td colspan="2"><input type="submit" value="Save"></td>
            </tr>
        </table>
    </form>
</div>
</body>
</html>
