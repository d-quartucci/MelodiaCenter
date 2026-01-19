<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<!DOCTYPE html>
<html lang="it">
<head>
    <meta charset="UTF-8">
    <title>MelodiaCenter – Strumenti musicali online</title>

    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/styles/main.css">
</head>

<body>

    <!-- HEADER -->
    <%@ include file="/fragments/header.jsp" %>

    <main class="container">

        <section class="hero">
            <h1>Benvenuto su MelodiaCenter 🎶</h1>
            <p>
                Il tuo negozio online di strumenti musicali:
                chitarre, bassi, tastiere e accessori.
            </p>

            <a class="btn"
               href="${pageContext.request.contextPath}/CatalogServlet">
                Vai al catalogo
            </a>
        </section>

        <!-- Messaggio per utente loggato -->
        <c:if test="${not empty sessionScope.user}">
            <section class="welcome-user">
                <h2>
                    Bentornato, ${sessionScope.user.username}!
                </h2>
                <p>Scopri le novità o completa il tuo ordine.</p>
            </section>
        </c:if>

        <!-- Sezioni vetrina -->
        <section class="highlights">
            <h2>I più venduti</h2>

            <div class="product-grid">
                <div class="product-card">
                    <h3>Chitarra elettrica</h3>
                    <p>Suono potente, perfetta per il rock.</p>
                </div>

                <div class="product-card">
                    <h3>Basso elettrico</h3>
                    <p>Profondo e versatile.</p>
                </div>

                <div class="product-card">
                    <h3>Tastiera digitale</h3>
                    <p>Ideale per studio e live.</p>
                </div>
            </div>
        </section>

    </main>

    <!-- FOOTER -->
    <%@ include file="/fragments/footer.jsp" %>
</body>
