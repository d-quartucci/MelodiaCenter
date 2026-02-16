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
    <main class ="pageAdmin">
    			<!-- Menu laterale di gestione -->
		<aside>
			<%@ include file = "/fragments/adminMenu.jsp"%>
		</aside>
    		<section class= "contenitore" id="filtriUtentiAdmin" >
    			<h1>Filtri Utenti</h1>
    			<form id= "formFiltriAdmin"  name= "formFiltriAdmin" method="GET" action="${pageContext.request.contextPath}/admin/FilterUtenti">
    				<label for = "dataFrom"> DAL: </label>
    				<input type= "date" id= "dataFrom" name = "dataIn" value="${defaultIn}">
    				<br>
    				<label for = "dataTo">AL: </label>
    				<input type= "date" id= "dataTo" name = "dataFin" value="${defaultFin}" >
    				<br>
    				<label for = "ordinaData">ORDINA PER DATA: </label>
    				<select class="menuTendina" id= "ordinaData" name = "ordinaData">
    					<option value = "menoRecenti"> meno recenti </option>
    					<option value = "piuRecenti" ${param.ordinaData == "piuRecenti" ? "selected" : ""}>più recenti </option>
    				</select>
    			
    				<button type="submit" class="bottoneFiltro">Filtra </button>
    			</form>
    		</section>
    		<section class= "contenitore" id=listUtenti>
    			<h1>Lista Utenti</h1>
    			<c:if test= "${not empty utenti}">
    				<table class= "tabellaAdmin">
    					<thead>
    						<tr>
    							<th>Cod.Utente</th>
								<th>Nome</th>
								<th>Cognome</th>
								<th>Email</th>
								<th>Ruolo</th>
								<th>Data Registrazione</th>
    						</tr>
    					</thead>
    					<tbody>
							<c:forEach var="u" items="${utenti}">				
								<tr>
									<td>${u.id}</td>
									<td id= "nome_${u.id}">${u.nome}</td>
									<td id= "cognome_${u.id}">${u.cognome}</td>
									<td>
										<input type= "text" id= "email_${u.id}" value= "${u.email}" disabled>
										<br>
											
										<button type= "button" class= "bottoneMod" id= "mod_${u.id}" onclick= "abilitaModifica(${u.id})"> Modifica</button>
									</td>	
									<td id="TastoMod">
										<select class="menuTendina" id= "ruolo_${u.id}"> 
											<option value = "USER" ${u.ruolo == "USER" ? "selected" : ""} > USER</option>
											<option value = "ADMIN" ${u.ruolo == "ADMIN" ? "selected" : ""}> ADMIN</option>
										</select>
										<button type = button class= "bottoneMod" onclick = "salvaModifica(${u.id}, 'ruolo', document.getElementById('ruolo_${u.id}').value)"> Applica</button>
									</td>
									<td>${u.dataRegistrazione}</td>
								</tr>
								<h5 id= "ErrorSpan">
										<span id="error_${u.id}" class="error"></span>
								</h5>
							</c:forEach>
						<c:if test="${empty utenti}">
                			<h2 class="emptyTable">Nessun utente presente!</h2>
    					</c:if>
    					</tbody>
    				</table>
    			</c:if>
    		</section>
   	 </main>
		 <!-- FOOTER -->
    <%@ include file="/fragments/footer.jsp" %>
    
	</body>
</html>