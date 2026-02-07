<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">	
	<script>
 	   const contextPath = "${pageContext.request.contextPath}";
	</script>
	<script src="${pageContext.request.contextPath}/scripts/catalog.js"></script>	
	<link rel="stylesheet" href="${pageContext.request.contextPath}/styles/main.css" type="text/css">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>Catalogo</title>
</head>
<body>
	<jsp:include page="/fragments/header.jsp"/>
	<h1>Sfoglia il nostro catalogo!</h1>
	<div id="risultati">
		<c:if test="${not empty listaProdotti}">
			<c:forEach var="p" items="${listaProdotti}">
				<div class="contenitoreProdotto">
					<div class="immagineProdotto">
						<img src="${pageContext.request.contextPath}/images/${p.imgSrc}">
					</div>
					<div class="informazioniProdotto">
						<a href="${pageContext.request.contextPath}/ProductPageServlet?prodottoId=${p.id}">${p.nome}</a>
						<p class="prezzo">${p.prezzoAttuale}€</p>
					</div>
				</div>
			</c:forEach>
		</c:if>
		<c:if test="${empty listaProdotti}">
			<p class="nessunRisultato"> Non è stato trovato alcun risultato! </p>
		</c:if>
	</div>
	<div id="formFiltriContainer">
		<form name="formFiltri" action="${pageContext.request.contextPath}/CatalogServlet" method="POST">
			<input type="text" id="barraDiRicerca" name="barraDiRicerca" placeholder="Ricerca..." value="${param.barraDiRicerca}"><br>
			
			<label for="ordinaPrezzo">Ordina per prezzo:</label>
			<select id="ordinaPrezzo" name="ordinaPrezzo">
				<option value="prezzoDecrescente">Prezzo Decrescente</option>
				<option value="prezzoCrescente" ${param.ordinaPrezzo eq "prezzoCrescente" ? "selected" : ""}>Prezzo Crescente</option>
			</select><br>
			
			<label for="categoria">Seleziona la categoria:</label>
			<select id="categoria" name="categoria">
				<!-- "Tutte" e la categoria di default -->
				<option value="0" ${param.categoria == null || param.categoria == '0' ? 'selected' : ''}>Tutte</option>
				<!-- Le altre categorie -->
				<c:forEach var="c" items="${listaCategorie}">
				<!--  Controllo che il parametro già inserito in passato non sia nullo e che esso corrisponda all'id della categoria rappresentata dall'option -->
					<option value="${c.id}" ${param.categoria == c.id.toString() ? 'selected' : ''}>${c.nome}</option>
				</c:forEach>
			</select><br>
			
			<label for="prezzoFiltro">Prezzo massimo:</label>
			<input id="prezzoFiltro" name="prezzoFiltro" type="range" min="0" max="${prezzoMax}" step="500" 
				value="${param.prezzoFiltro == null ? prezzoMax : param.prezzoFiltro}" oninput="aggiornaSpan()"> 
			<span id="prezzoFiltroVisual">${param.prezzoFiltro == null ? prezzoMax : param.prezzoFiltro}€</span><br>
			
			<button type="submit">Applica filtri</button>
		</form>
	</div>
	<jsp:include page="/fragments/footer.jsp"/>
</body>
</html>