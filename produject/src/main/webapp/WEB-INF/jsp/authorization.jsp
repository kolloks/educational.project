<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<jsp:useBean id="UserRole" scope="request" type="web.product.models.UserRole"/>
<html>
<head>
    <title>Авторизация</title>
</head>
<body>
    <p><h3>Welcome,
        <c:choose>
            <c:when test="${!UserRole.userInRole}">
                Guest!
                <form action="login" method="POST">
                    <label>
                        <input type="text" name="login" value="login"/>
                    </label>
                    <br/>
                    <label>
                        <input type="password" name="password" value="password"/>
                    </label>
                    <br/>
                    <input type="submit" name="login" value="Login"/>
                </form>
                <a href="/registration"><button>Registration</button></a>
            </c:when>
            <c:when test="${UserRole.userInRole}">
                <jsp:useBean id="user" scope="session" type="web.product.models.User"/>
                ${user.login}!
                <br><a href="/logout"><button>Logout</button></a>
            </c:when>
        </c:choose>
    </h3></p>
</body>
</html>
