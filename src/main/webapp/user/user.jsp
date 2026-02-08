<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="${pageContext.request.contextPath}/styles/main.css" type="text/css">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Pagina dell'utente</title>
</head>
<body> 
	<jsp:include page="/fragments/header.jsp"/>
	<h1 id="presentazioneUtente">Le tue informazioni:</h1>
	<section id="sezioneUtente">
		<div id="informazioniUtente">
			<p>Nome: ${utente.nome}</p>
			<p>Cognome: ${utente.cognome}</p>
			<p>Numero di telefono: ${utente.telefono}</p>
			<p>Email: ${utente.email}</p>
		</div>
	</section>
	<div id="consulenzeLink">
		<h2><a href="${pageContext.request.contextPath}/user/ConsulenzaListServlet">Premi qui per visualizzare le tue richieste di consulenza al nostro staff!</a></h2>
	</div>
	<jsp:include page="/fragments/footer.jsp"/>
</body>
</html>