<%--
  Created by IntelliJ IDEA.
  User: bogda
  Date: 19.01.2024
  Time: 14:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>dalboeb.ua</title>
</head>
<body>
<table>
    <tr>
    <th>id</th>
        <th>name</th>
        <th>price</th>
        <th><th>
        <th><th>
    </tr>
    <c:forEach items="${requestScope.resources}" var="res">
        <jsp:useBean id="res" type="org.example.model.Resource"/>
            <tr>
            <td>${res.id}</td>
            <td>${res.name}</td>
            <td>${res.price}</td>
            <td><a href="resources?action=update&id=${res.id}">update</a></td>
                <td><a href="resources?action=delete&id=${res.id}">delete</a></td>
            </tr>


    </c:forEach>

</table>
<a href="resources?action=create">Add Resource</a>
</body>
</html>
