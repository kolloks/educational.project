<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@page isELIgnored="false" %>
<jsp:useBean id="product" scope="request" type="web.product.models.Product"/>
<html>
<head>
    <title>MyServletProduct</title>
</head>
<body>
    <h1>Продукт JSP</h1>
    <br>Дата: <%= new java.util.Date()%>
    <p>Продукт: ${product.name}</p>
    <p><a href="./add?id=${product.id}"><button>Добавить в корзину</button></a></p>
    <p><a href="./index.jsp">К другим продуктам</a></p>
    <h3>Корзина</h3>
    <ul>
        <c:forEach var="productInBasket" items="${productsInBasket}">
            <li>
                <a href="/product?id=${productInBasket.key.id}">
                ${productInBasket.key.name}</a> : ${productInBasket.value} шт.
            </li>
        </c:forEach>
    </ul>
    <c:if test="${productsInBasket!=null}">
        <p><a href="./buy"><button>Купить</button></a></p>
    </c:if>
</body>
</html>
