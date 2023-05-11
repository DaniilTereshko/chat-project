<html xmlns:jsp="http://java.sun.com/JSP/Page"
      xmlns:c="http://java.sun.com/jsp/jstl/core"
      version="2.0"
>
    <jsp:directive.page contentType="text/html" pageEncoding="UTF-8"/>
    <head>
        <meta charset="UTF-8"/>
        <title>Login</title>
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/ui/styles/header.css"/>
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/ui/styles/login.css"/>
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/ui/styles/home.css"/>
    </head>
    <body>
        <jsp:include page="header.jsp"/>
        <form class="login" action="login" method="POST">
            <label for="login">Логин:</label>
            <input type="text" id="login" name="login" required/><br/>
            <label for="password">Пароль:</label>
            <input type="password" id="password" name="password" required/><br/>
            <div class="error-wrapper">
                <c:choose>
                  <c:when test="${not empty requestScope.loginError}">
                    <div class="error">${requestScope.loginError}</div>
                  </c:when>
                  <c:when test="${not empty requestScope.passwordError}">
                    <div class="error">${requestScope.passwordError}</div>
                  </c:when>
                </c:choose>
            </div>
            <input type="submit" value="Войти"/>
        </form>
    </body>
</html>