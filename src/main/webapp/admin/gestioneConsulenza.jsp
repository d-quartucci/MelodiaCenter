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
    
        <!-- Menu laterale di gestione -->
<%@ include file = "/fragments/adminMenu.jsp"%>
	    <div id="filtriConsulenzaAdmin">
    		<form id= "formFiltriAdmin"name= "formFiltriAdmin" method="GET" action = "${pageContext.request.contextPath}/admin/AdminFilterConsulenzeServlet">
    			<label for = "dataFrom">dal: </label>
    			<input type= "date" id= "dataFrom" name = "dataIn" value="${defaultIn}">
    			<label for = "dataTo">al: </label>
    			<input type= "date" id= "dataTo" name = "dataFin" value="${defaultFin}">
    				
    			<label for = "ordinaData">Ordina per data: </label>
    			<select id= "ordinaData" name = "ordinaData">
    				<option value = "menoRecenti"> meno recenti </option>
    				<option value = "piuRecenti" ${param.ordinaData == "piuRecenti" ? "selected" : ""}> più recenti </option>
    			</select>
    			<label for = "ordinaStato">Ordina per stato: </label>
    			<select id= "ordinaStato" name = "ordinaStato">
    				<option value = "0"> Tutte </option>
    				<option value = "chiuso" ${param.ordinaStato == "chiuso" ? "selected" : ""}>Chiuso </option>
    				<option value = "aperto" ${param.ordinaStato == "aperto" ? "selected" : ""}>Aperto</option>
    			</select>
    			
    			<button type="submit">Filtra</button>
    		</form>
    	</div>
    	<div id="listaConsulenza">
			<c:if test="${not empty consulenze}">
				<h1 class="presentazione">Le tue richieste di consulenza:</h1>
				<section id="sezioneConsulenze" class="contenitore">
					<c:forEach var="c" items="${consulenze}">
						<div id="consulenzaDiv-${c.id}" class="consulenzaDiv">
							<a href="${pageContext.request.contextPath}/user/ConsulenzaPageServlet?consId=${c.id}">Richiesta #${c.id}</a>
							<p> Stato: ${c.aperto ? 'Aperto' : 'Chiuso'}<p>
							<p> Data Richiesta: ${c.dataRichiesta}<p>
						</div>
					</c:forEach>
				</section>
			</c:if>
			<c:if test="${empty consulenze}">
				Non ci sono consulenze
			</c:if>
		</div>
        <!-- FOOTER -->
<%@ include file="/fragments/footer.jsp" %>

</body>
</html>