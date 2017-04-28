<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<jsp:useBean id="products" class="web.product.DAO.Products"/>
<html>
<body>
    <h2>Продукты</h2>
    <ul>
        <c:forEach var="product" items="${products.selectAll()}">
            <li>
                <a href="/product?id=${product.id}">${product.name}</a>
            </li>
        </c:forEach>
    </ul>
</body>
</html>
