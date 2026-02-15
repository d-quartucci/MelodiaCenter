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
	<h1 id="presentazioneWishlistPiena" class="presentazione">Ecco la lista dei desideri...</h1>
	<section id="sezioneWishlist" class="contenitore">
		<c:forEach var="wish" items="${wishlist}">
			<div id="wishDiv-${wish.prodotto.id}" class="contenitoreProdotto">
				<div class="immagineProdotto">
					<img src="${pageContext.request.contextPath}/images/${wish.prodotto.imgSrc}" alt="${wish.prodotto.nome}">
				</div>
				<div class="informazioniProdotto">
					<h2><a href="${pageContext.request.contextPath}/product?prodottoId=${wish.prodotto.id}">${wish.prodotto.nome}</a></h2>
					<p>${wish.prodotto.prezzoAttuale}€</p>
					<button id="wishRemove-${wish.prodotto.id}" onclick="rimuoviDallaWishlist(${wish.prodotto.id})">Rimuovi</button>
				</div>
			</div>
		</c:forEach>
	</section>
	</c:if>
	
	<div id="sezioneVuotaWishlist" style="<c:if test='${not empty wishlist}'>display:none;</c:if>">
		<h1 class="presentazione">Non hai ancora aggiunto nulla alla tua wishlist!</h1>
		<section class="contenitore">
			<h3>Comincia a sognare <a href="${pageContext.request.contextPath}/catalog">qui</a>!</h3>
		</section>
	</div>
	
	<jsp:include page="/fragments/footer.jsp"/>
</body>
</html>