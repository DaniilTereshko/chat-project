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
        <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
        <script src="${pageContext.request.contextPath}/ui/js/users.js"></script>
    </head>
    <body>
        <jsp:include page="../header.jsp"/>
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
                    <th>Actions</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="user" items="${requestScope.users}">
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
                                    <input type="radio" name="role-${user.id}" value="${r.getRoleName()}" ${user.role.getRoleName() == r.getRoleName() ? 'checked' : ''}>
                                </label>
                            </c:forEach>
                        </td>
                        <td>
                            <button class="edit-button" data-user-id="${user.id}">Изменить</button>
                            <button class="delete-button" data-user-id="${user.id}">Удалить</button>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </body>
</html>
