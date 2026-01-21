<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<header>
    <div class="header-container">

        <!-- Logo -->
        <div class="logo">
            <a href="${pageContext.request.contextPath}/common/index.jsp">
                🎸 MelodiaCenter
            </a>
        </div>

        <!-- Menu principale -->
        <nav>
            <ul class="nav-links">
                <li><a href="${pageContext.request.contextPath}/common/index.jsp">Home</a></li>
                <li><a href="${pageContext.request.contextPath}/CatalogServlet">Catalogo</a></li>
                <li><a href="${pageContext.request.contextPath}/common/cart.jsp">Carrello</a></li>
            </ul>
        </nav>
        
        <div class="ricerca">
        	<form name="formDiRicerca" action="${pageContext.request.contextPath}/SearchServlet" method="GET">
        		<input type="text" id="barraDiRicerca" name="barraDiRicerca" placeholder="Allora, cosa cerchiamo..?">
        		<button type="submit" id="pulsanteRicerca">Vai!</button>
        	</form>
        </div>

        <!-- Area utente -->
        <div class="user-area">

            <!-- Se l'utente non ha fatto il login, mostro le opzioni di login -->
            <c:if test="${empty sessionScope.utente}">
                <a href="${pageContext.request.contextPath}/common/login.jsp">Login</a>
                <a href="${pageContext.request.contextPath}/common/register.jsp">Registrati</a>
            </c:if>

            <!-- Se l'utente ha fatto il login, mostro le opzioni dedicate allo USER -->
            <c:if test="${not empty sessionScope.utente}">
                <span>
                    Ciao, <strong>${sessionScope.utente.nome}</strong>
                </span>

                <a href="${pageContext.request.contextPath}/OrdersServlet">
                    I miei ordini
                </a>

                <a href="${pageContext.request.contextPath}/LogoutServlet">
                    Logout
                </a>
            </c:if>

            <!-- Se l'utente è un admin, mostro le opzioni dedicate all'ADMIN -->
            <c:if test="${sessionScope.utente.ruolo}=='ADMIN'">
                <a href="${pageContext.request.contextPath}/admin/dashboard.jsp">
                    Admin
                </a>
            </c:if>
        </div>
    </div>
    <hr>
</header>