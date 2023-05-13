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
            <thead>
                <tr>
                    <th>ID</th>
                    <th>Login</th>
                    <th>Password</th>
                    <th>FirstName</th>
                    <th>MiddleName</th>
                    <th>LastName</th>
                    <th>Birthday</th>
                    <th>RegistrationDate</th>
                    <th>Role</th>
                    <th>Confirm</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="user" items="${requestScope.users}">
                    <form action="users" method="post">
                        <tr>
                            <td>${user.id}</td>
                            <td>${user.login}</td>
                            <td>${user.password}</td>
                            <td>${user.firstName}</td>
                            <td>${user.middleName}</td>
                            <td>${user.lastName}</td>
                            <td><fmt:formatDate value="${user.birthday}" pattern="yyyy-MM-dd"/></td>
                            <td><fmt:formatDate value="${user.registrationDate}" pattern="yyyy-MM-dd"/></td>
                            <td>
                                <c:forEach var="r" items="${roles}">
                                <label>
                                    ${r.getRoleName()}:
                                    <input type="radio" name="role" value="${r.getRoleName()}" ${user.role.getRoleName() == r.getRoleName() ? 'checked' : ''}>
                                </label>
                                </c:forEach>
                            </td>
                            <input type="hidden" name="id" value="${user.id}"/>
                            <td><input type="submit" class="submit-button"/></td>
                        </tr>
                    </form>
                </c:forEach>
            </tbody>
        </table>
    </body>
</html>