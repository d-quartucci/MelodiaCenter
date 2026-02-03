<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<!DOCTYPE html>
<html lang="it">
<head>
	<meta charset="UTF-8">
	<title>Amministrazione MelodiaCenter</title>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/styles/main.css" type="text/css">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
</head>
<body>

	<!-- HEADER -->
    <%@ include file="/fragments/header.jsp" %>
	
	<h1> Iniziamo con la gestione !</h1>
	
	<!-- Menu laterale di gestione -->
	<aside id="Menu"> 
		<nav>
			<ul class="MenuGestione">
             	<li><a href="${pageContext.request.contextPath}/AdminOrdini">Ordini</a></li>
             	<li><a href="${pageContext.request.contextPath}/AdminUtenti">Utenti</a></li>
        	</ul>
		</nav>
	</aside>
	
	<!-- DashBoard con varie informazioni principali -->
	<main>
		<div id= Ordini>
			<p> Ordini di oggi </p>
			<c:if test = "${not empty ordiniOggi}"> 
				<table>
					<thead>
						<tr>
							<th>IdOrdine</th>
							<th>IdUtente</th>
							<th>Data</th>
							<th>Totale</th>
							<th>IndirizzoSpedizione</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="o" items="${ordiniOggi}">
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
			<c:if test = "${empty ordiniOggi}">
				<p> Non ci sono ordini giornalieri! </p>
			</c:if>
		</div>
	
		<div id= Utenti>
			<p> Utenti iscritti oggi </p>
			<c:if test = "${not empty utentiOggi}">
				<table>
					<thead>
						<tr>
							<th>IdUtente</th>
							<th>Nome</th>
							<th>Cognome</th>
							<th>E-mail</th>
							<th>DataIscrizione</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="u" items="${utentiOggi}">
							<tr>
								<td>${u.id}</td>
								<td>${u.nome}</td>
								<td>${u.cognome}</td>
								<td>${u.email}</td>
								<td>${u.dataRegistrazione}</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</c:if>
			<c:if test = "${empty utentiOggi }">
				<p> Non ci sono nuove iscrizioni giornaliere! </p>
			</c:if>
		</div>
	</main>

    <!-- FOOTER -->
    <%@ include file="/fragments/footer.jsp" %>
    
</body>

</html>