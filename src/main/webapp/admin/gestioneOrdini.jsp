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
	</head>
	<body>
	
	<!-- HEADER -->
    <%@ include file="/fragments/header.jsp" %>
    
    <!-- Menu laterale di gestione -->
	<%@ include file = "/fragments/adminMenu.jsp"%>
    	<main>
    		<div id=filtriOrdiniAdmin>
    			<form name= formFiltriAdmin action ="${pageContext.request.contextPath}/AdminFilterOrdini" method="POST" >
    				<label for = "dataFrom">Dal: </label>
    				<input type= "date" id= "dataFrom" name = "dataIn" >
    				<label for = "dataFrom">Al: </label>
    				<input type= "date" id= "dataTo" name = "dataFin" >
    				
    				<label for = "ordinaData">Ordina per data: </label>
    				<select id= "ordinaData" name = "ordinaData">
    					<option value = menoRecenti> meno recenti </option>
    					<option value = piuRecenti>	piu recenti </option>
    				</select><br>
    				
    			</form>
    		</div>
    		<div id=listOrdini>
    			<p>Lista Ordini</p>
    			<c:if test = "${not empty ordini}">
    				<table>
    					<thead>
    						<tr>
    							<th>Cod.Ordine</th>
								<th>Cod.Utente</th>
								<th>Data</th>
								<th>Totale</th>
								<th>IndirizzoSpedizione</th>
    						</tr>
    					</thead>
    					<tbody>
    						<c:forEach var="o" items="${ordini}">
							<tr>
								<td>${o.id}</td>
								<td>${o.utenteId}</td>
								<td>${o.data}</td>
								<td>${o.totale}</td>
								<td>${o.indSpedizione}</td>
							</tr>
						</c:forEach>
    					</tbody>
    				</table>
    			</c:if>
    			<c:if test = "${empty ordini}">
				<p> Non ci sono ordini! </p>
			</c:if>
    		</div>
		</main>
		 <!-- FOOTER -->
    <%@ include file="/fragments/footer.jsp" %>
    
	</body>
</html>