<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<link rel="stylesheet" href="${pageContext.request.contextPath}/styles/main.css" type="text/css">
		<meta name="viewport" content="width=device-width, initial-scale=1.0">
		<script>const contextPath = "${pageContext.request.contextPath}";</script>
		<script src="${pageContext.request.contextPath}/scripts/gestioneAdmin.js"></script>
		<title>Gestione Utenti</title>
	</head>
	<body>
	
	<!-- HEADER -->
    <%@ include file="/fragments/header.jsp" %>
    
    <!-- Menu laterale di gestione -->
	<%@ include file = "/fragments/adminMenu.jsp"%>
    	<main>
    		<div id="filtriUtentiAdmin" >
    			<form id= "formFiltriAdmin"  name= "formFiltriAdmin" method="GET" action="${pageContext.request.contextPath}/admin/AdminFilterUtentiServlet">
    				<label for = "dataFrom">Iscritto dal: </label>
    				<input type= "date" id= "dataFrom" name = "dataIn" value="${defaultIn}">
    				<label for = "dataTo">al: </label>
    				<input type= "date" id= "dataTo" name = "dataFin" value="${defaultFin}" >
    				
    				<label for = "ordinaData">Ordina per data: </label>
    				<select id= "ordinaData" name = "ordinaData">
    					<option value = menoRecenti> iscritti meno recenti </option>
    					<option value = piuRecenti>	iscritti più recenti </option>
    				</select>
    				<button type="submit" >Filtra </button>
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
    					<c:if test= "${not empty utenti}">
							<c:forEach var="u" items="${utenti}">				
								<tr>
									<td>${u.id}</td>
									<td>${u.nome}</td>
									<td>${u.cognome}</td>
									<td>
										<input type = "text" id= "email_${u.id}" value= "${u.email}" disabled>			
										<button type = "button" id= "mod_${u.id}" onclick = "abilitaModifica(${u.id})"> Modifica</button>
									</td>	
									<td id="TastoMod">
										<select id= "ruolo_${u.id}" > 
											<option value = "USER" ${u.ruolo == "USER" ? "selected" : ""} > USER</option>
											<option value = "ADMIN" ${u.ruolo == "ADMIN" ? "selected" : ""}> ADMIN</option>
										</select>
										<button type = button onclick = "salvaModifica(${u.id}, 'ruolo', document.getElementById('ruolo_${u.id}').value)"> Applica</button>
									</td>
									<td>${u.dataRegistrazione}</td>
									<td id = "ErrorSpan">
										<span id="errorEmail_${u.id }" class="error"></span>
									</td>
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