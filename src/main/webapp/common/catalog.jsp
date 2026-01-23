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
<script src="${pageContext.request.contextPath}/scripts/slider.js"></script>
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
		<form name="formFiltri" action="${pageContext.request.contextPath}/FilterServlet" method="GET">
			<input type="text" id="barraDiRicerca" name="barraDiRicerca" placeholder="Ricerca..." value="${param.barraDiRicerca}"><br>
			
			<label for="ordinaPrezzo">Ordina per prezzo:</label>
			<select id="ordinaPrezzo" name="ordinaPrezzo">
				<option value="prezzoDecrescente">Prezzo Decrescente</option>
				<option value="prezzoCrescente" ${param.ordinaPrezzo == "prezzoCrescente" ? "selected" : ""}>Prezzo Crescente</option>
			</select><br>
			
			<label for="categoria">Seleziona la categoria:</label>
			<select id="categoria" name="categoria">
				<option value="tutte">Tutte</option>
				<option value="1" ${param.categoria == "1" ? "selected" : ""}>Chitarra</option> <!-- Questi valori corrispondono all'ID su Database per la corrispondente categoria -->
				<option value="2" ${param.categoria == "2" ? "selected" : ""}>Pianoforte</option> <!-- Il motivo della scelta è legato alla natura dell'ID autoincrementante -->
				<option value="3" ${param.categoria == "3" ? "selected" : ""}>Percussione</option>
				<option value="4" ${param.categoria == "4" ? "selected" : ""}>A fiato</option>
			</select><br>
			
			<label for="prezzoMax">Prezzo massimo:</label>
			<input id="prezzoMax" name="prezzoMax" type="range" min="0" max="10000" step="250" value=${param.prezzoMax == null ? "5000" : param.prezzoMax} oninput="aggiornaSpan()"> 
			<span id="prezzoMaxVisual">${param.prezzoMax == null ? "5000" : param.prezzoMax}€</span><br>
			
			<button type="submit">Applica filtri</button>
		</form>
	</div>
	<%@ include file="/fragments/footer.jsp" %>
</body>
</html>