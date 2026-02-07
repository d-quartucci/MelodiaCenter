<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Amministrazione Utenti</title>
		<link rel="stylesheet" href="${pageContext.request.contextPath}/styles/main.css" type="text/css">
		<meta name="viewport" content="width=device-width, initial-scale=1.0">
		<script>const contextPath = "${pageContext.request.contextPath}";</script>
		<script src="${pageContext.request.contextPath}/scripts/gestioneAdmin.js"></script>
	</head>
	<body>
	
	<!-- HEADER -->
    <%@ include file="/fragments/header.jsp" %>
    
    <!-- Menu laterale di gestione -->
	<%@ include file = "/fragments/adminMenu.jsp"%>
    	<main>
    		<div id=filtriUtentiAdmin>
    			<form name= formFiltriAdmin action = "${pageContext.request.contextPath}/admin/AdminFilterUtenti" method="GET" >
    				<label for = "dataFrom">Iscritto dal: </label>
    				<input type= "date" id= "dataFrom" name = "dataIn" value="${defaultIn}">
    				<label for = "dataTo">al: </label>
    				<input type= "date" id= "dataTo" name = "dataFin" value="${defaultFin}" >
    				
    				<label for = "ordinaData">Ordina per data: </label>
    				<select id= "ordinaData" name = "ordinaData">
    					<option value = menoRecenti> iscritti meno recenti </option>
    					<option value = piuRecenti>	iscritti più recenti </option>
    				</select>
    				<button type="button" onclick = "eseguiFiltro('/admin/AdminFilterUtentiServlet','corpoTableUtenti')">Filtra </button>
    			</form>
    		</div>
    		<div id=listUtenti>
    			<p>Lista Utenti</p>
    			<table>
    				<thead id = "testaTableUtenti">
    					<tr>
    						<th>Cod.Utente</th>
							<th>Nome</th>
							<th>Cognome</th>
							<th>Email</th>
							<th>Ruolo</th>
							<th>Data Registrazione</th>
    					</tr>
    				</thead>
    				<tbody id= "corpoTableUtenti">
    					<%@ include file = "CorpoTabellaUtenti.jsp"%>
    				</tbody>
			
    			</table>
    		</div>
		</main>
		 <!-- FOOTER -->
    <%@ include file="/fragments/footer.jsp" %>
    
	</body>
</html>