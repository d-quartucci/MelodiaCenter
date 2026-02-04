<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>I tuoi desideri!</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/styles/main.css" type="text/css">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
</head>
<body>
	<jsp:include page="/fragments/header.jsp"/>
	<c:if test="${not empty listaOrdini}">
	<h1>Ecco la lista dei desideri...</h1>
		<c:forEach var="wish" items="${wishlistitems}">
			<div id="wishDiv-${wishlist.prodotto.id}">
				<p>${wishlist.prodotto.nome}</p>
			</div>
		</c:forEach>
	</c:if>
	<c:if test="${empty listaOrdini}">
		<h1>Non aggiunto nulla alla tua lista dei desideri!</h1>
		<h3>Comincia a sognare <a href="${pageContext.request.contextPath}/CatalogServlet">qui</a>!</h3>
	</c:if>
	<jsp:include page="/fragments/footer.jsp"/>
</body>
</html>