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
	<%@ include file="/fragments/header.jsp" %>
	<h1>Sfoglia il nostro catalogo!</h1>
	<div id="risultati">
		<c:if test="${not empty prodotti}">
			<c:forEach var="p" items="${prodotti}">
				<div class="contenitoreProdotto">
					<div class="immagineProdotto">
						<img src="${pageContext.request.contextPath}/images/${p.imgSrc}">
					</div>
					<div class="informazioniProdotto">
						<a href="${pageContext.request.contextPath}/ProductPageServlet?prodottoId=${p.id}">
							${p.nome}
						</a>
						<p class="prezzo">${p.prezzoAttuale}€</p>
					</div>
				</div>
			</c:forEach>
		</c:if>
		
		
		<c:if test="${empty prodotti}">
			<p class="nessunRisultato"> Non è stato trovato alcun risultato! </p>
		</c:if>
	</div>
	<div>
		<form name="formFiltri" action="" method="GET">
			<input type="text" id="barraDiRicercaForm" name="barraDiRicerca" placeholder="Ricerca..." onchange="filtri.js" value="${nomeRicercato}">
			<select id="ordinaPer" name="Ordina per">
				<option value="prezzoCrescente">Prezzo Crescente</option>
				<option value="prezzoDecrescente">Prezzo Decrescente</option>
			</select>
		</form>
	</div>
	<%@ include file="/fragments/footer.jsp" %>
</body>
</html>