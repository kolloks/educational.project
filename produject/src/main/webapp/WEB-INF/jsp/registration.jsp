<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<html>
<head>
    <title>Регистрация</title>
</head>
<body>
    <form action="registration" method="POST">
        <label>
            <input type="text" name="login" value="login"/>
        </label>
        <label>
            <input type="password" name="password" value="password"/>
        </label>
        <label>
            <input type="date" name="age" value="date"/>
        </label>
        <label>
            <input type="email" name="email" value="email"/>
        </label>
        <input type="submit" name="send"/>
    </form>
</body>
</html>
