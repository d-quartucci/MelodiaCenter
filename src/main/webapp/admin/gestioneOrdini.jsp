<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<link rel="stylesheet" href="${pageContext.request.contextPath}/styles/main.css" type="text/css">
		<meta name="viewport" content="width=device-width, initial-scale=1.0">
		<script>const contextPath = "${pageContext.request.contextPath}";</script>
		<script src="${pageContext.request.contextPath}/scripts/gestioneAdmin.js"></script>
		<title>Gestione Ordini</title>
	</head>
	<body>
	
	<!-- HEADER -->
    <%@ include file="/fragments/header.jsp" %>
    <main class ="pageAdmin">	    			<!-- Menu laterale di gestione -->
		<aside>
			<%@ include file = "/fragments/adminMenu.jsp"%>
		</aside>
    		<section class= "contenitore" id="filtriOrdiniAdmin">
    			<h1>Filtri Ordini</h1>
    			<form id= "formFiltriAdmin"name= "formFiltriAdmin" method="GET" action = "${pageContext.request.contextPath}/admin/AdminFilterOrdiniServlet">
    				<label for = "dataFrom">DAL: </label>
    				<input type= "date" id= "dataFrom" name = "dataIn" value="${defaultIn}">
    				<br>
    				<label for = "dataTo">AL: </label>
    				<input type= "date" id= "dataTo" name = "dataFin" value="${defaultFin}">
    				<br>
    				<label for = "ordinaData">ORDINA PER DATA: </label>
    				<select class="menuTendina" id= "ordinaData" name = "ordinaData">
    					<option value = menoRecenti> meno recenti </option>
    					<option value = piuRecenti ${param.ordinaData == "piuRecenti" ? "selected" : ""}> più recenti </option>
    				</select>
    				<button type="submit" class= "bottoneFiltro">Filtra</button>
    			</form>
    		</section>
    		<section class= "contenitore" id=listOrdini>
    			<h1>Lista Ordini</h1>
    			<table class= "tabellaAdmin">
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
           						<td>
                					<h1>Nessun ordine presente </h1>
          						 </td>
      						</tr>
   						</c:if>
    				</tbody>
    			</table>
    		</section>
    	</main>
		 <!-- FOOTER -->
    <%@ include file="/fragments/footer.jsp" %>
	</body>
</html>