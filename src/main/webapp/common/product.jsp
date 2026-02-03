<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
	<button onclick="aggiungiAlCarrello(${prodotto.id})">Aggiungi al carrello!</button>
</div>


<jsp:include page="/fragments/footer.jsp"/>
</body>
</html>