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
	<h1 class="presentazione">Ordine ID: #${idOrdine}</h1>
	<section id="listaRigheOrdine" class="contenitore">
		<c:forEach var="riga" items="${righeOrdine}">
			<div class="rigaOrdine" id="div-${riga.prodottoNome}">
				<h3>Prodotto: <a href="${pageContext.request.contextPath}/ProductPageServlet?prodottoId=${riga.prodottoId}">${riga.prodottoNome}</a></h3>
				<p>Quantità: ${riga.quant}</p>
				<p>Prezzo: ${riga.prezzoAcq}€</p>
			</div>
		</c:forEach>
		<h2>Prezzo totale: ${totaleOrdine}€</h2>
	</section>
	<jsp:include page="/fragments/footer.jsp"/>
	
</body>
</html>