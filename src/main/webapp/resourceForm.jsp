<%--
  Created by IntelliJ IDEA.
  User: bogda
  Date: 22.01.2024
  Time: 21:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>ResourceForm</title>
</head>
<body>
<jsp:useBean id ="resource" type="org.example.model.Resource" scope="request"/>
<form method="post" action="resources">
    <input name="id" value="${resource.id}" readonly/>
    <input name="name" type="text" value="${resource.name}"/>
    <input name="price" type="number" value="${resource.price}"/>
    <button type="submit">save</button>
    <button type="button" onclick="window.history.back()">cancel</button>

</form>
</body>
</html>
