<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>${prodotto.nome}</title>
	<script>const contextPath = "${pageContext.request.contextPath}";</script>
	<script src="${pageContext.request.contextPath}/scripts/product.js"></script>
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/styles/main.css" type="text/css">
</head>
<body>
<jsp:include page="/fragments/header.jsp"/>
<div id="schedaProdotto">
<div class="immagineProdotto">
	<img src="${pageContext.request.contextPath}/images/${prodotto.imgSrc}">
</div>
<div class="informazioniProdotto">
	<h1 id="nomeProdotto">${prodotto.nome}</h1>
	<p id="descProdotto">${prodotto.descrizione}</p>
	<p id="prezzo">${prodotto.prezzoAttuale}€</p>
</div>
</div>

<div id="sezioneFunzionalità" name="sezioneFunzionalità">

	<div id="sezioneCarrello" name="sezioneCarrello">
		<span id="aggiuntoSpan"></span>
		<button id="pulsanteCarrello" onclick="aggiungiAlCarrello(${prodotto.id})" <c:if test="${inCart}">disabled</c:if>>${inCart ? "Prodotto già nel carrello" : "Aggiungi al carrello!"}</button>
	</div>
	
	<div id="sezioneWishlist" name="sezioneWishlist">
		<c:if test="${isLogged}">
			<button id="pulsanteWishlist" onclick="aggiungiAllaWishlist(${prodotto.id})" <c:if test="${inWishlist}">disabled</c:if>>${inWishlist ? "Già in wishlist" : "Desidero..."}</button>
		</c:if>
	</div>
	
	<div id="sezioneConsulenza" name="sezioneConsulenza">
		<c:if test="${isLogged}">
			<form name="richiestaConsulenza" action="${pageContext.request.contextPath}/user/CreateConsulenzaServlet?idProd=${prodotto.id}" method="POST">
				<h3>Vuoi richiedere consulenza su questo prodotto?</h3>
				<textarea id="messaggioConsulenza" name="messaggioConsulenza" placeholder="Scrivi qui le tue domande..." oninput="verificaContenuto('messaggioConsulenza', 'pulsanteConsulenza')"></textarea>
				<button id="pulsanteConsulenza" name="pulsanteConsulenza" type="submit" disabled>Invia!</button>
			</form>
		</c:if>
	</div>
	
	<div id="sezioneNotLogged" name="sezioneNotLogged">
		<c:if test="${!isLogged}">
			<h4> Per poter tenere traccia dei tuoi prodotti preferiti e fare domande al nostro team, effettua il <a href="${pageContext.request.contextPath}/LoginServlet">login</a>!</h4>
		</c:if>
	</div>
</div>

<div id="sezioneRecensioni" name="sezioneRecensioni">
	<div id="leggiRecensioni" name="leggiRecensioni">
		<c:if test="${empty listaRecensioni}">
			<h3>Non ci sono ancora recensioni per questo prodotto!</h3>
		</c:if>
		<c:if test="${not empty listaRecensioni}">
			<h3>Le recenzioni dei nostri utenti:</h3>
			<c:forEach var="r" items="${listaRecensioni}">
				<div id="recensione-${r.recensione.id}" class="recensione">
					<h3 id="recensione-${r.utente.nome}">${r.utente.nome} - ${r.recensione.voto}/5</h3>
					<p>${r.recensione.testo}</p>
				</div>
			</c:forEach>
		</c:if>
	</div>
	<c:if test="${puoRecensire}">
		<div id="scriviRecensione" name="scriviRecensione">
			<form name="inviaRecensione" method="POST" action="${pageContext.request.contextPath}/user/CreateRecensioneServlet?prodottoId=${prodotto.id}">
				<p>Hai acquistato questo prodotto in precendenza... dicci la tua!</p>
				<textarea id="recensioneInput" name="recensioneInput" placeholder="Scrivi qui la tua recensione..." oninput="verificaContenuto('recensioneInput', 'recensioneSubmit')"></textarea>
				<input id="voto" name="voto" type="range" min="1" max="5" step="1" value="5" oninput="aggiornaSpan()"> <span id="spanVoto" name="spanVoto">5</span>
				<button id="recensioneSubmit" name="recensioneSubmit" type="submit" disabled>Invia!</button>
			</form>
		</div>
	</c:if>
</div>
	
<jsp:include page="/fragments/footer.jsp"/>
</body>
</html>