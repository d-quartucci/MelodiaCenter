<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="jakarta.tags.core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Dettagli dell'ordine</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/styles/main.css" type="text/css">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
</head>
<body>
	<jsp:include page="/fragments/header.jsp"/>
	<h2>Ordine ID: ${idOrdine}</h2>
	<c:forEach var="riga" items="${righeOrdine}">
	<div id="div-${riga.prodottoNome}">Prodotto: ${riga.prodottoNome}<br>Quantità: ${riga.quant}<br>Prezzo: ${riga.prezzoAcq}€</div><br>
	</c:forEach>
	<h3>Prezzo totale: ${totaleOrdine}€</h3>
	<jsp:include page="/fragments/footer.jsp"/>
</body>
</html>