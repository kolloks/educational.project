<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>

<html>
<head>
    <title>Главная</title>
</head>
<body>
    <h3><%@include file="WEB-INF/jsp/authorization.jsp"%></h3>

    <%@include file="WEB-INF/jsp/getAllProducts.jsp"%>
</body>
</html>
