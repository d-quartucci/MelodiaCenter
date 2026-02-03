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
	<h1>Le tue informazioni:</h1>
	<div id="informazioniUtente">
		<fieldset>
			<p>Nome: ${utente.nome}</p>
			<p>Cognome: ${utente.cognome}</p>
			<p>Numero di telefono: ${utente.telefono}</p>
		</fieldset>
	</div>
	<jsp:include page="/fragments/footer.jsp"/>
</body>
</html>