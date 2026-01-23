<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Il tuo carrello</title>
<script>
   const contextPath = "${pageContext.request.contextPath}";
</script>
<script src="${pageContext.request.contextPath}/scripts/cart.js"></script>
</head>
<body>
<%@ include file="/fragments/header.jsp" %>
<h1>Gestisci il tuo carrello!</h1>
<c:if test="${empty sessionScope.carrello.listaItem}">
Il carrello è vuoto! Comincia la tua ricerca da <a href="${pageContext.request.contextPath}/CatalogServlet">qui</a>!
</c:if>

<c:if test="${not empty sessionScope.carrello.listaItem}">
<h2>Ecco i tuoi prodotti:</h2>
<table>
	<tr>
		<th>Nome</th>
		<th>Prezzo</th>
		<th>Quantità</th>
		<th></th>
	</tr>
	<c:forEach var="item" items="${sessionScope.carrello.listaItem}">
		<tr>
			<td>${item.prodotto.nome}</td>
			<td>${item.prodotto.prezzoAttuale}</td>
			<td>
				<button id="${item.prodotto.id}RimuoviUno" onclick="rimuoviUno(${item.prodotto.id})">-</button>
				<input id="${item.prodotto.id}Quantita" type="text" size="3" value="${item.quantita}" oninput="cambiaQuantita(${item.prodotto.id})">
				<button id="${item.prodotto.id}AggiungiUno" onclick="aggiungiUno(${item.prodotto.id})">+</button>
			</td>
			<td><button name="rimuoviDalCarrello">Rimuovi!</button></td>
		</tr>
	</c:forEach>
</table>
</c:if>

<%@ include file="/fragments/footer.jsp" %>
</body>
</html>