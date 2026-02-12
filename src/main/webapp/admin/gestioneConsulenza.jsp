<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/styles/main.css" type="text/css">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<script>const contextPath = "${pageContext.request.contextPath}";</script>
	<script src="${pageContext.request.contextPath}/scripts/gestioneConsulenze.js"></script>
	<title>Gestione Consulenze</title>
</head>
<body>
			<!-- HEADER -->
<%@ include file="/fragments/header.jsp" %>
    <main class ="pageAdmin">
    	 <aside>
			<%@ include file = "/fragments/adminMenu.jsp"%>
		</aside>
	    <section class= "contenitore" id="filtriConsulenzaAdmin">
			<h1>Filtri Consulenze</h1>
    		<form id= "formFiltriAdmin" method="GET" action = "${pageContext.request.contextPath}/admin/FilterConsulenze">
    			<label for = "dataFrom">DAL: </label>
    			<input type= "date" id= "dataFrom" name = "dataIn" value="${defaultIn}">
    			<br>
    			<label for = "dataTo">AL: </label>
    			<input type= "date" id= "dataTo" name = "dataFin" value="${defaultFin}">
    			<br>	
    			<label for = "ordinaData">ORDINA PER DATA: </label>
    			<select class="menuTendina" id= "ordinaData" name = "ordinaData">
    				<option value = "menoRecenti"> meno recenti </option>
    				<option value = "piuRecenti" ${param.ordinaData == "piuRecenti" ? "selected" : ""}> più recenti </option>
    			</select>
    			<br>
    			<label for = "ordinaStato">ORDINA PER STATO: </label>
    			<select class="menuTendina" id= "ordinaStato" name = "ordinaStato">
    				<option value = "0"> Tutte </option>
    				<option value = "chiuso" ${param.ordinaStato == "chiuso" ? "selected" : ""}>Chiuso </option>
    				<option value = "aperto" ${param.ordinaStato == "aperto" ? "selected" : ""}>Aperto</option>
    			</select>
    			
    			<button type="submit" id="bottoneConsulenza" class= "bottoneFiltro">Filtra</button>
    		</form>
    	</section>
    	<section id="listaConsulenza">
			<c:if test="${not empty consulenze}">
				<h1 id="labelListConsulenze">Le tue richieste di consulenza:</h1>
				<section id="sezioneConsulenze" class="contenitore">
					<c:forEach var="c" items="${consulenze}">
						<div id="consulenzaDiv-${c.id}" class="consulenzaDiv">
							<a href="${pageContext.request.contextPath}/user/consulenza?consId=${c.id}">Richiesta #${c.id}</a>
							<p> Stato: ${c.aperto ? 'Aperto' : 'Chiuso'}<p>
							<p> Data Richiesta: ${c.dataRichiesta}<p>
						</div>
					</c:forEach>
				</section>
			</c:if>
			<c:if test="${empty consulenze}">
				<h2 class="emptyTable">Non ci sono consulenze !</h2>
			</c:if>
		</section>
        		<!-- FOOTER -->
		<%@ include file="/fragments/footer.jsp" %>
	</main>
</body>
</html>