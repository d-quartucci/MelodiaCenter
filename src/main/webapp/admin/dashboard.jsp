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
	<header class = "header">
    	<%@ include file="/fragments/header.jsp" %>
	</header>
	<section>
			<!-- Menu laterale di gestione -->
		<aside>
			<%@ include file = "/fragments/adminMenu.jsp"%>
		</aside>
		<main class ="pageAdmin">
			<section class= "contenitore" id="Ordini">
				<h1> Ordini di oggi </h1>
				<c:if test = "${not empty ordiniOggi}"> 
					<table class= "tabellaAdmin">
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
			</section>
	
			<section class= "contenitore"  id="Utenti" >
				<h1> Utenti iscritti oggi </h1>
				<c:if test = "${not empty utentiOggi}">
					<table class= "tabellaAdmin">
						<thead>
							<tr>
								<th>Cod.Utente</th>
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
			</section>
		</main>
	</section>
	   <!-- FOOTER -->
    <footer class="footer">
   		 <%@ include file="/fragments/footer.jsp" %>
    </footer>
</body>
</html>