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
<div id="immagineProdotto">
	<img src="${pageContext.request.contextPath}/images/${prodotto.imgSrc}">
</div>
<div id="informazioniProdotto">
	<p id="nomeProdotto">${prodotto.nome}</p>
	<p id="descProdotto">${prodotto.descrizione}</p>
	<p id="prezzo">${prodotto.prezzoAttuale}€</p>
	<span id="aggiuntoSpan"></span>
	<button id="pulsanteCarrello" onclick="aggiungiAlCarrello(${prodotto.id})" <c:if test="${inCart}">disabled</c:if>>${inCart ? "Prodotto già nel carrello" : "Aggiungi al carrello!"}</button>
	<!-- Se l'utente non è loggato, non verrà visualizzato il pulsante -->
	<c:if test="${isLogged}">
		<button id="pulsanteWishlist" onclick="aggiungiAllaWishlist(${prodotto.id})" <c:if test="${inWishlist}">disabled</c:if>>${inWishlist ? "Già in wishlist" : "Desidero..."}</button>
		
		<form name="richiestaConsulenza" action="${pageContext.request.contextPath}/user/CreateConsulenzaServlet?idProd=${prodotto.id}" method="POST">
			<p>Vuoi richiedere consulenza su questo prodotto?</p>
			<textarea id="messaggioConsulenza" name="messaggioConsulenza" placeholder="Scrivi qui le tue domande..."></textarea>
			<button id="pulsanteConsulenza" name="pulsanteConsulenza" type="submit">Invia!</button>
		</form>
		
	</c:if>
	<c:if test="${!isLogged}">
		<h4> Per poter tenere traccia dei tuoi prodotti preferiti e fare domande al nostro team, effettua il <a href="${pageContext.request.contextPath}/LoginServlet">login</a>!</h4>
	</c:if>
	
</div>


<jsp:include page="/fragments/footer.jsp"/>
</body>
</html>