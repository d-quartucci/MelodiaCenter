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
		<script> const adminCorrente = "${sessionScope.utente.id}";</script>
	</head>
	<body>
	
	<!-- HEADER -->
    <%@ include file="/fragments/header.jsp" %>
    
    <!-- Menu laterale di gestione -->
	<%@ include file = "/fragments/adminMenu.jsp"%>
    	<main>
    		<div id=filtriUtentiAdmin>
    			<form name= formFiltriAdmin action = "javascript:void(0)" method="GET" >
    				<label for = "dataFrom">Iscritto dal: </label>
    				<input type= "date" id= "dataFrom" name = "dataIn" value="${defaultIn}" onchange = "eseguiFiltro('/AdminFilterUtenti','utente','corpoTableUtenti')" >
    				<label for = "dataTo">al: </label>
    				<input type= "date" id= "dataTo" name = "dataFin" value="${defaultFin}" onchange = "eseguiFiltro('/AdminFilterUtenti','utente','corpoTableUtenti')" >
    				
    				<label for = "ordinaData">Ordina per data: </label>
    				<select id= "ordinaData" name = "ordinaData" onchange = "eseguiFiltro('/AdminFilterUtenti','utente','corpoTableUtenti')">
    					<option value = menoRecenti> iscritti meno recenti </option>
    					<option value = piuRecenti>	iscritti più recenti </option>
    				</select><br>
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
    				<tbody id = "corpoTableUtenti">
    					<c:if test= "${not empty utenti}">
							<c:forEach var="u" items="${utenti}">
								<tr>
									<td>${u.id}</td>
									<td>${u.nome}</td>
									<td>${u.cognome}</td>
									<td>${u.email}</td>
									<td>${u.ruolo}</td>
									<td>${u.dataRegistrazione}</td>
								</tr>
							</c:forEach>
						</c:if>
						<c:if test="${empty utenti}">
       						 <tr>
            					<td colspan="5" style="text-align:center; padding:20px;">
                					Nessun utente iscritto
            					</td>
      					  	</tr>
    					</c:if>
    				</tbody>
    			</table>
    		</div>
		</main>
		 <!-- FOOTER -->
    <%@ include file="/fragments/footer.jsp" %>
    
	</body>
</html>