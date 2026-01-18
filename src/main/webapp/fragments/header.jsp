<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<header>
    <div class="header-container">

        <!-- Logo -->
        <div class="logo">
            <a href="${pageContext.request.contextPath}/index.jsp">
                🎸 MelodiaCenter
            </a>
        </div>

        <!-- Menu principale -->
        <nav>
            <ul class="nav-links">
                <li><a href="${pageContext.request.contextPath}/index.jsp">Home</a></li>
                <li><a href="${pageContext.request.contextPath}/CatalogServlet">Catalogo</a></li>
                <li><a href="${pageContext.request.contextPath}/common/cart.jsp">Carrello</a></li>
            </ul>
        </nav>

        <!-- Area utente -->
        <div class="user-area">

            <!-- Utente NON loggato -->
            <c:if test="${empty sessionScope.user}">
                <a href="${pageContext.request.contextPath}/common/login.jsp">Login</a>
                <a href="${pageContext.request.contextPath}/common/register.jsp">Registrati</a>
            </c:if>

            <!-- Utente loggato -->
            <c:if test="${not empty sessionScope.user}">
                <span>
                    Ciao, <strong>${sessionScope.user.username}</strong>
                </span>

                <a href="${pageContext.request.contextPath}/OrdersServlet">
                    I miei ordini
                </a>

                <a href="${pageContext.request.contextPath}/LogoutServlet">
                    Logout
                </a>
            </c:if>

            <!-- Admin -->
            <c:if test="${sessionScope.user.admin}">
                <a href="${pageContext.request.contextPath}/admin/dashboard.jsp">
                    Admin
                </a>
            </c:if>

        </div>
    </div>
</header>