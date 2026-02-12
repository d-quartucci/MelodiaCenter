<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Ordini effettuati</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/styles/main.css" type="text/css">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
</head>
<body>
	<jsp:include page="/fragments/header.jsp"/>
	<c:if test="${not empty listaOrdini}">
	<h1 class="presentazione">Ecco la lista degli ordini che hai effettuato in passato:</h1>
		<section id="sezioneOrdini" class="contenitore">
			<c:forEach var="order" items="${listaOrdini}">
				<div id="orderDiv-${order.id}" class="ordineDiv">
					<a href="${pageContext.request.contextPath}/user/order?id=${order.id}">Ordine ID: #${order.id}</a>
					<p> Prezzo: ${order.totale}€<p>
				</div>
			</c:forEach>
		</section>
	</c:if>
	<c:if test="${empty listaOrdini}">
		<h1 class="presentazione">Non hai ancora effettuato un ordine!</h1>
		<section id="sezioneOrdini" class="contenitore">
			<h2>Comincia la tua ricerca da <a href="${pageContext.request.contextPath}/catalog">qui</a>!</h3>
		</section>
	</c:if>
	<jsp:include page="/fragments/footer.jsp"/>
</body>
</html>