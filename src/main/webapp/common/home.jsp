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

<section id="benvenuto" class="contenitore">
	<c:if test="${empty sessionScope.utente}">	
		<h1>Benvenuto su MelodiaCenter 🎶</h1>
	</c:if>
	<c:if test="${not empty sessionScope.utente}">	
		<h1>Bentornato, ${utente.nome} su MelodiaCenter 🎶!</h1>
	</c:if>
	<h3>Il tuo negozio online di strumenti musicali!</h3>
</section>

<aside id="prodottiEvidenziati" class="contenitore">
	<h2>I prodotti scelti dal nostro staff!</h2>
	<c:forEach var="p" items="${listaInEvidenza}">
		<div class="contenitoreProdotto">
			<div class="immagineProdotto">
				<img src="${pageContext.request.contextPath}/images/${p.imgSrc}" alt="${p.nome}">
			</div>
			<div class="informazioniProdotto">
				<a href="${pageContext.request.contextPath}/product?prodottoId=${p.id}">${p.nome}</a>
				<p class="prezzo">${p.prezzoAttuale}€</p>
			</div>
		</div>
	</c:forEach>
</aside>

<section id="sezioneCategorie">
	<h2>Seleziona la categoria che fa per te e comincia ad esplorare il nostro catalogo!</h2>
	<ul id="listaCategorie">
	<c:forEach var="c" items="${listaCategorie}">
		<a href="${pageContext.request.contextPath}/catalog?categoria=${c.id}">
			<li id="categoria-${c.id}" class="contenitore">
				<p class="nomeCategoria">${c.nome}</p>
				<p class="descCategoria">${c.descr}</p>
			</li>
		</a>
	</c:forEach>
	</ul>
</section>

<section id="aggiuntiDiRecente">
	<h2>Nuovi arrivi!</h2>
	<c:forEach var="p" items="${listaRecenti}">
		<div class="contenitoreProdotto">
			<div class="immagineProdotto">
				<img src="${pageContext.request.contextPath}/images/${p.imgSrc}" alt="${p.nome}">
			</div>
			<div class="informazioniProdotto contenitore">
				<h3><a href="${pageContext.request.contextPath}/product?prodottoId=${p.id}">${p.nome}</a></h3>
				<p class="prezzo">${p.prezzoAttuale}€</p>
				<p class="descrizioneProdotto">${p.descrizione}</p>
			</div>
		</div>
	</c:forEach>
</section>

<section id="bestSellers">
	<h2>I nostri bestsellers!</h2>
	<c:forEach var="p" items="${listaBestSellers}">
		<div class="contenitoreProdotto">
			<div class="immagineProdotto">
				<img src="${pageContext.request.contextPath}/images/${p.imgSrc}" alt="${p.nome}">
			</div>
			<div class="informazioniProdotto contenitore">
				<h3><a href="${pageContext.request.contextPath}/product?prodottoId=${p.id}">${p.nome}</a></h3>
				<p class="prezzo">${p.prezzoAttuale}€</p>
				<p class="descrizioneProdotto">${p.descrizione}</p>
			</div>
		</div>
	</c:forEach>
</section>

<jsp:include page="/fragments/footer.jsp"/>
</body>
