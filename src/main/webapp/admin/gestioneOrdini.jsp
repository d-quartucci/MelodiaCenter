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
		<script src="${pageContext.request.contextPath}/scripts/gestioneOrdini.js"></script>
	</head>
	<body>
	
	<!-- HEADER -->
    <%@ include file="/fragments/header.jsp" %>
    
    <!-- Menu laterale di gestione -->
	<%@ include file = "/fragments/adminMenu.jsp"%>
    	<main>
    		<div id=filtriOrdiniAdmin>
    			<form name= formFiltriAdmin action = "javascript:void(0)" method="GET" >
    				<label for = "dataFrom">Dal: </label>
    				<input type= "date" id= "dataFrom" name = "dataIn" value="${defaultIn}" onchange = "eseguiFiltro()" >
    				<label for = "dataFrom">Al: </label>
    				<input type= "date" id= "dataTo" name = "dataFin" value="${defaultFin}" onchange = "eseguiFiltro()" >
    				
    				<label for = "ordinaData">Ordina per data: </label>
    				<select id= "ordinaData" name = "ordinaData" onchange = "eseguiFiltro()">
    					<option value = menoRecenti> meno recenti </option>
    					<option value = piuRecenti>	piu recenti </option>
    				</select><br>
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