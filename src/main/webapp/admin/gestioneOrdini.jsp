<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Amministrazione Ordini</title>
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
    		<div id=filtriOrdiniAdmin>
    			<form name= formFiltriAdmin action = "${pageContext.request.contextPath}/admin/AdminFilterOrdini"  method="GET" >
    				<label for = "dataFrom">dal: </label>
    				<input type= "date" id= "dataFrom" name = "dataIn" value="${defaultIn}">
    				<label for = "dataTo">al: </label>
    				<input type= "date" id= "dataTo" name = "dataFin" value="${defaultFin}">
    				
    				<label for = "ordinaData">Ordina per data: </label>
    				<select id= "ordinaData" name = "ordinaData">
    					<option value = menoRecenti> meno recenti </option>
    					<option value = piuRecenti selected> più recenti </option>
    				</select><br>
    				<button type="button" onclick= "eseguiFiltro('/AdminFilterUtenti','utente','corpoTableUtenti')">Filtra</button>
    			</form>
    		</div>
    		<div id=listOrdini>
    			<p>Lista Ordini</p>
    			<table>
    				<thead id = "testaTableOrdini">
    					<tr>
    						<th>Cod.Ordine</th>
							<th>Cod.Utente</th>
							<th>Data</th>
							<th>Totale</th>
							<th>IndirizzoSpedizione</th>
    					</tr>
    				</thead>
    				<tbody id = "corpoTableOrdini">
    					<c:if test= "${not empty ordini}">
							<c:forEach var="o" items="${ordini}">
								<tr>
									<td>${o.id}</td>
									<td>${o.utenteId}</td>
									<td>${o.data}</td>
									<td>${o.totale}€</td>
									<td>${o.indSpedizione}</td>
								</tr>
							</c:forEach>
						</c:if>
						<c:if test="${empty ordini}">
       						 <tr>
            					<td colspan="5" style="text-align:center; padding:20px;">
                					Nessun ordine presente
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