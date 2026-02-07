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
<jsp:include page="/fragments/header.jsp"/>

<div id="benvenuto">
	<c:if test="${empty sessionScope.utente}">	
		<h1>Benvenuto su MelodiaCenter 🎶</h1>
	</c:if>
	<c:if test="${not empty sessionScope.utente}">	
		<h1>Bentornato, ${utente.nome} su MelodiaCenter 🎶!</h1>
	</c:if>
	<p>Il tuo negozio online di strumenti musicali: chitarre, bassi, tastiere e accessori.</p>
	<a href="${pageContext.request.contextPath}/CatalogServlet">Vai al catalogo</a>
</div>

<div id="prodottiEvidenziati">
	<h2>I prodotti scelti dal nostro staff!</h2>
	<c:forEach var="p" items="${listaInEvidenza}">
		<div class="contenitoreProdotto">
			<div class="immagineProdotto">
				<img src="${pageContext.request.contextPath}/images/${p.imgSrc}">
			</div>
			<div class="informazioniProdotto">
				<a href="${pageContext.request.contextPath}/ProductPageServlet?prodottoId=${p.id}">${p.nome}</a>
				<p class="prezzo">${p.prezzoAttuale}€</p>
			</div>
		</div>
	</c:forEach>
</div>

<div id="bestSellers">
	<h2>La nostra top 3 dei prodotti più venduti!</h2>
	<c:forEach var="p" items="${listaBestSellers}">
		<div class="contenitoreProdotto">
			<div class="immagineProdotto">
				<img src="${pageContext.request.contextPath}/images/${p.imgSrc}">
			</div>
			<div class="informazioniProdotto">
				<a href="${pageContext.request.contextPath}/ProductPageServlet?prodottoId=${p.id}">${p.nome}</a>
				<p class="prezzo">${p.prezzoAttuale}€</p>
			</div>
		</div>
	</c:forEach>
</div>

<div id="sezioneCategorie">
	<h2>Seleziona la categoria che fa per te!</h2>
	<c:forEach var="c" items="${listaCategorie}">
		<div id="categoria-${c.id}">
			<a href="${pageContext.request.contextPath}/CatalogServlet?categoria=${c.id}">${c.nome}</a>
		</div>
	</c:forEach>
</div>

<jsp:include page="/fragments/footer.jsp"/>
</body>
