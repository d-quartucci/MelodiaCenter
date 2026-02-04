<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>I tuoi desideri!</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/styles/main.css" type="text/css">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<script src="${pageContext.request.contextPath}/scripts/wishlist.js"></script>
<script> var contextPath = "${pageContext.request.contextPath}"</script>
</head>
<body>
	<jsp:include page="/fragments/header.jsp"/>
	<c:if test="${not empty wishlist}">
	<div id="sezioneWishlist">
	<h1>Ecco la lista dei desideri...</h1>
		<c:forEach var="wish" items="${wishlist}">
			<div id="wishDiv-${wish.prodotto.id}">
				<a href="ProductPageServlet?prodottoId=${wish.prodotto.id}">${wish.prodotto.nome}</a> ${wish.prodotto.prezzoAttuale}€
				<button id="wishRemove-${wish.prodotto.id}" onclick="rimuoviDallaWishlist(${wish.prodotto.id})">Rimuovi</button>
				<br>
			</div>
		</c:forEach>
	</div>
	</c:if>
	
	<div id="sezioneVuota" style="<c:if test='${not empty wishlist}'>display:none;</c:if>">
		<h1>Non aggiunto nulla alla tua lista dei desideri!</h1>
		<h3>Comincia a sognare <a href="${pageContext.request.contextPath}/CatalogServlet">qui</a>!</h3>
	</div>
	
	<jsp:include page="/fragments/footer.jsp"/>
</body>
</html>