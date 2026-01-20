<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script>
    const contextPath = "${pageContext.request.contextPath}";
</script>
<script src="${pageContext.request.contextPath}/scripts/catalog.js"></script>
<title>Catalogo</title>
</head>
<body>
	<h1>Sfoglia il nostro catalogo!</h1>
	<div id="risultati">
		<c:if test="${not empty prodotti}">
			<c:forEach var="p" items="${prodotti}">
				<div class="contenitoreProdotto">
					<div class="immagineProdotto">
						<img src="${pageContext.request.contextPath}/images/${p.imgSrc}">
					</div>
					<div class="informazioniProdotto">
						<h3 id="nomeProdotto">${p.nome}</h3>
						<p class="prezzo">${p.prezzoAttuale}</p>
					</div>
				</div>
			</c:forEach>
		</c:if>
		
		
		<c:if test="${empty prodotti}">
			<p class="nessunRisultato"> Non è stato trovato alcun risultato! </p>
		</c:if>
	</div>
</body>
</html>