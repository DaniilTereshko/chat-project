<header>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <nav>
        <ul>
            <li><a href="/chat-project-1.0.0/ui/">Home</a></li>
            <li><a href="/chat-project-1.0.0/ui/user/message">Messages</a></li>
            <li><a href="/chat-project-1.0.0/ui/user/chats">Chats</a></li>
            <c:if test="${sessionScope.user != null and sessionScope.user.role.getRoleName() == 'ADMIN'}">
                    <li><a href="/chat-project-1.0.0/ui/admin/users">Users</a></li>
                    <li><a href="/chat-project-1.0.0/ui/admin/statistics">Statistics</a></li>
            </c:if>
            <c:choose>
                <c:when test="${sessionScope.user == null || empty sessionScope.user}">
                    <li style="float: right;"><a href="/chat-project-1.0.0/ui/signIn">Login</a></li>
                </c:when>
                <c:otherwise>
                    <li style="float: right;"><a href="/chat-project-1.0.0/api/logout">Log out</a></li>
                </c:otherwise>
            </c:choose>
            <c:choose>
                <c:when test="${sessionScope.user != null and not empty sessionScope.user}">
                  <li style="float: right;"><span>Login: ${sessionScope.user.login} Role: ${sessionScope.user.role.getRoleName()}</span></li>
                </c:when>
                <c:otherwise>
                <li style="float: right;"><a href="/chat-project-1.0.0/ui/signUp">Registration</a></li>
                  <li style="float: right;"><span>Login: guest Role: unknown</span></li>
                </c:otherwise>
            </c:choose>
        </ul>
    </nav>
</header>
