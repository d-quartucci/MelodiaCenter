<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>Il tuo carrello</title>
	<script>const contextPath = "${pageContext.request.contextPath}";</script>
	<script src="${pageContext.request.contextPath}/scripts/cart.js"></script>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/styles/main.css" type="text/css">
</head>
<body>
<jsp:include page="/fragments/header.jsp"/>
<h1 id="introduzioneCarrello">Gestisci il tuo carrello!</h1>

<!-- Sezione mostrata quando il carrello è vuoto -->
<section class="contenitore" id="carrelloVuoto" style="<c:if test='${not empty carrello.listaItem}'>display:none;</c:if>">
    <h3>Il carrello è vuoto! Comincia la tua ricerca da <a href="${pageContext.request.contextPath}/catalog">qui</a>!</h3>
</section>

<!-- Sezione mostrata quando il carrello non è vuoto -->
<c:if test="${not empty carrello.listaItem}">
<section id="sezioneCarrello" class="contenitore">
	<h2 id="presentazioneCarrello">Ecco i tuoi prodotti:</h2>

	<!-- Tabella con tutti i prodotti inseriti nel carrello -->
	<table id="tabellaCarrello" class="contenitore">
	
		<tr>
			<th>Nome</th>
			<th>Prezzo</th>
			<th class="sezioneQuantità">Quantità</th>
			<th></th>
		</tr>
	
		<c:forEach var="item" items="${carrello.listaItem}">
			<tr id="tr-${item.prodotto.id}">
				<td>${item.prodotto.nome}</td>
				<td>${item.prodotto.prezzoAttuale}€</td>
				<td class="sezioneQuantità">
					<button class="pulsanteOpCarrello" id="${item.prodotto.id}RimuoviUno" onclick="rimuoviUno(${item.prodotto.id})">-</button>
					<input id="${item.prodotto.id}Quantita" type="text" size="3" value="${item.quantita}" oninput="cambiaQuantita(${item.prodotto.id})">
					<button class="pulsanteOpCarrello" id="${item.prodotto.id}AggiungiUno" onclick="aggiungiUno(${item.prodotto.id})">+</button>
				</td>
				<td><button id="bottoneRimuoviProdCart" name="rimuoviDalCarrello" onclick="rimuoviDalCarrello(${item.prodotto.id})">Rimuovi!</button></td>
			</tr>
		</c:forEach>
	
	</table>

	<button id="svuotaCarrello" name="svuotaCarrello" onclick="svuotaCarrello()">Svuota il carrello!</button>

	<section id="sezioneDestraCarrello">
		<h2>Prezzo totale:<span id="spanPrezzoTotale">${carrello.prezzoTotale}€</span></h2>
	
		<form id="checkoutForm" method="POST" action="${pageContext.request.contextPath}/user/checkout">
			<button type="submit" id="checkout">Vai al checkout!</button>
		</form>
	</section>

</section>
</c:if>
<div id="error"></div>
<jsp:include page="/fragments/footer.jsp"/>
</body>

</html>