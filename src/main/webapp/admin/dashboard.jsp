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
             	<li><a href="${pageContext.request.contextPath}/admin/gestioneOrdini.jsp">Ordini</a></li>
             	<li><a href="${pageContext.request.contextPath}/admin/gestioneUtenti.jsp">Utenti</a></li>
        	</ul>
		</nav>
	</aside>
	
	<!-- DashBoard con varie informazioni principali -->
	<main>
		<div id= Ordini>
		
		<p> Ordini di oggi </p>
			<c:forEach var="o" items="${ordiniOggi}">
			
				<p> Ordine ${o.id} </p>
			
			</c:forEach>
		
		</div>
	
		<div id= Utenti>
		
		<p> Utenti iscritti oggi </p>
		
			<c:forEach var="u" items="${utentiOggi}">
			
				<p> Utente ${u.id} </p>
				
			</c:forEach>
		</div>
		
	</main>

    <!-- FOOTER -->
    <%@ include file="/fragments/footer.jsp" %>
    
</body>

</html>