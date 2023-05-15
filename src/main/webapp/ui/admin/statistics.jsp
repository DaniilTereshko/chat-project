<html>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
    <jsp:directive.page contentType="text/html" pageEncoding="UTF-8"/>
    <head>
        <title>Home</title>
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/ui/styles/header.css"/>
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/ui/styles/home.css"/>
        <style>
            .submit-button {
            display: block;
            width: 100%;
            margin-bottom: 20px;
            }
        </style>
    </head>
    <body>
        <jsp:include page="../header.jsp"/>
        <div class="error-wrapper">
            <c:choose>
              <c:when test="${not empty requestScope.ok}">
                <div class="alert-success">${requestScope.ok}</div>
              </c:when>
              <c:when test="${not empty requestScope.error}">
                <div class="alert-danger">${requestScope.error}</div>
              </c:when>
            </c:choose>
        </div>
        <table>
    <tr>
       <th>Количество активных пользователей</th>
        <th>${countActiveUsers}</th>
    </tr>
    <tr>
       <th>Количество пользователей</th>
       <th>${countUsers}</th>
    </tr>
      <tr>
       <th>Количество сообщений</th>
       <th>${countMessages}</th>
      </tr>
      </table>
    </body>
</html>