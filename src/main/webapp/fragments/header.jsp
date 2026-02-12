<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<html>
<head>
	<meta charset="UTF-8">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/styles/main.css" type="text/css">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
</head>
<body>
<header id="headerPrincipale">
        <div class="logo">
            <a href="${pageContext.request.contextPath}/home"><img src="${pageContext.request.contextPath}/images/logo.png"></a>
        </div>

        <nav>
            <ul class="navLinks">
                <li><a href="${pageContext.request.contextPath}/home">Home</a></li>
                <li><a href="${pageContext.request.contextPath}/CatalogServlet">Catalogo</a></li>
                <li><a href="${pageContext.request.contextPath}/CartServlet">Carrello</a></li>
            </ul>
        </nav>
        
        <div class="ricerca">
        	<form name="formDiRicerca" action="${pageContext.request.contextPath}/CatalogServlet" method="POST">
        		<input type="text" id="barraDiRicerca" name="barraDiRicerca" placeholder="Allora, cosa cerchiamo..?">
        		<button type="submit" id="pulsanteRicerca">Vai!</button>
        	</form>
        </div>

        <!-- Area utente -->
        <div class="userArea">

            <!-- Se l'utente non ha fatto il login, mostro le opzioni di login -->
            <c:if test="${empty sessionScope.utente}">
                <a href="${pageContext.request.contextPath}/LoginServlet">Login</a>
                <a href="${pageContext.request.contextPath}/RegisterServlet">Registrati</a>
            </c:if>

            <!-- Se l'utente ha fatto il login, mostro le opzioni dedicate allo USER -->
            <c:if test="${not empty sessionScope.utente}">
                <a href="${pageContext.request.contextPath}/user/UserServlet">Account</a>
                <a href="${pageContext.request.contextPath}/user/WishlistServlet">Wishlist</a>
                <a href="${pageContext.request.contextPath}/user/OrdersListServlet">I miei ordini</a>
                <a href="${pageContext.request.contextPath}/DoLogoutServlet">Logout</a>
            </c:if>

            <!-- Se l'utente è un admin, mostro le opzioni dedicate all'ADMIN -->
            <c:if test="${sessionScope.utente.ruolo=='ADMIN'}">
                <a href="${pageContext.request.contextPath}/admin/AdminDailyServlet">Admin</a>
            </c:if>
        </div>
</header>
</body>
</html>
