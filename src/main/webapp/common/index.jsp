<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<!DOCTYPE html>
<html lang="it">
<head>
    <meta charset="UTF-8">
    <title>MelodiaCenter – Strumenti musicali online</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/styles/main.css" type="text/css">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
</head>

<body>

    <!-- HEADER -->
    <%@ include file="/fragments/header.jsp" %>

    <main class="container">
        <section class="benvenuto">
        	<c:if test="${empty sessionScope.utente}">	
				<h1>Benvenuto su MelodiaCenter 🎶</h1>
			</c:if>
			<c:if test="${not empty sessionScope.utente}">	
				<h1>Bentornato, ${sessionScope.utente.nome} su MelodiaCenter 🎶!</h1>
			</c:if>
				<p>Il tuo negozio online di strumenti musicali: chitarre, bassi, tastiere e accessori.</p>
				<a href="${pageContext.request.contextPath}/CatalogServlet">Vai al catalogo</a>
		</section>

        <!-- Sezioni vetrina -->
        <section class="highlights">
            <h2>I più venduti</h2>

            <div class="productGrid">
                <div class="productCard">
                    <h3>Chitarra elettrica</h3>
                    <p>Suono potente, perfetta per il rock.</p>
                </div>

                <div class="productCard">
                    <h3>Basso elettrico</h3>
                    <p>Profondo e versatile.</p>
                </div>

                <div class="productCard">
                    <h3>Tastiera digitale</h3>
                    <p>Ideale per studio e live.</p>
                </div>
            </div>
        </section>

    </main>

    <!-- FOOTER -->
    <%@ include file="/fragments/footer.jsp" %>
</body>
