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

<section id="schedaProdotto" class="contenitore">

	<div class="immagineProdotto">
		<img src="${pageContext.request.contextPath}/images/${prodotto.imgSrc}" alt="${prodotto.nome}">
	</div>
	
	<div class="informazioniProdotto">
		<h1 id="nomeProdotto">${prodotto.nome}</h1>
		<p id="descProdotto">${prodotto.descrizione}</p>
		<p id="prezzo">${prodotto.prezzoAttuale}€</p>
		
		<div id="sezioneFunzionalità">
			<div id="sezioneAggiungiCarrello">
				<button id="pulsanteAggiungiCarrello" onclick="aggiungiAlCarrello(${prodotto.id})" <c:if test="${inCart}">disabled</c:if>>${inCart ? "Prodotto già nel carrello" : "Aggiungi al carrello!"}</button>
			</div>

			<div id="sezioneAggiungiWishlist">
				<c:if test="${isLogged}">
					<button id="pulsanteAggiungiWishlist" onclick="aggiungiAllaWishlist(${prodotto.id})" <c:if test="${inWishlist}">disabled</c:if>>${inWishlist ? "In wishlist" : "Desidero..."}</button>
				</c:if>
			</div>
		</div>
	</div>
	
		
</section>

<section id="sezioneConsulenza" class="contenitore">
	<c:if test="${isLogged}">
		<form name="richiestaConsulenza" action="${pageContext.request.contextPath}/user/CreateConsulenzaServlet?idProd=${prodotto.id}" method="POST">
			<h3>Vuoi richiedere consulenza su questo prodotto?</h3>
			<textarea id="messaggioConsulenza" name="messaggioConsulenza" placeholder="Scrivi qui le tue domande..." oninput="verificaContenuto('messaggioConsulenza', 'pulsanteConsulenza')"></textarea>
			<button id="pulsanteConsulenza" name="pulsanteConsulenza" type="submit" disabled>Invia!</button>
		</form>
	</c:if>
	
	<section id="sezioneNotLogged">
		<c:if test="${!isLogged}">
			<h4> Per poter tenere traccia dei tuoi prodotti preferiti e fare domande al nostro team, effettua il <a href="${pageContext.request.contextPath}/login">login</a>!</h4>
		</c:if>
	</section>
</section>

<section id="sezioneRecensioni">

	<c:if test="${puoRecensire}">
		<section id="scriviRecensione">
			<form name="inviaRecensione" method="POST" action="${pageContext.request.contextPath}/user/CreateRecensioneServlet?prodottoId=${prodotto.id}">
				<h3>Hai acquistato questo prodotto in precendenza... dicci la tua!</h3>
				<textarea id="recensioneInput" name="recensioneInput" placeholder="Scrivi qui la tua recensione..." oninput="verificaContenuto('recensioneInput', 'recensioneSubmit')"></textarea>
				<input id="voto" name="voto" type="range" min="1" max="5" step="1" value="5" oninput="aggiornaSpan()"> <span id="spanVoto">5</span>
				<button id="recensioneSubmit" name="recensioneSubmit" type="submit" disabled>Invia!</button>
			</form>
		</section>
	</c:if>
	
	<c:if test="${haRecensito}">
	<section id="scriviRecensione">
			<form name="inviaRecensione" method="POST" action="${pageContext.request.contextPath}/user/CreateRecensioneServlet?prodottoId=${prodotto.id}">
				<h3>Hai recensito questo prodotto in precedenza! (${recensionePassata.dataIns})</h3>
				<textarea id="recensioneInput" name="recensioneInput" placeholder="Aggiorna qui la tua recensione..." oninput="verificaContenuto('recensioneInput', 'recensioneSubmit')">${recensionePassata.testo}</textarea>
				<input id="voto" name="voto" type="range" min="1" max="5" step="1" value="${recensionePassata.voto}" oninput="aggiornaSpan()"> <span id="spanVoto">${recensionePassata.voto}</span>
				<button id="recensioneSubmit" name="recensioneSubmit" type="submit" disabled>Invia!</button>
			</form>
		</section>
	</c:if>
	
	<section id="leggiRecensioni">
		<c:if test="${empty listaRecensioni}">
			<h2>Non ci sono ancora recensioni per questo prodotto!</h2>
		</c:if>
		<c:if test="${not empty listaRecensioni}">
			<h2>Le recensioni dei nostri utenti:</h2>
			<c:forEach var="r" items="${listaRecensioni}">
				<div id="recensione-${r.recensione.id}" class="recensione">
					<h3 id="recensione-${r.utente.nome}" class="recensioneNome">Il nostro cliente <i>${r.utente.nome}</i> ha votato ${r.recensione.voto}/5!</h3>
					<p class="bodyRecensione">" ${r.recensione.testo} "</p>
				</div>
			</c:forEach>
		</c:if>
	</section>
</section>
	
<jsp:include page="/fragments/footer.jsp"/>
</body>
</html>