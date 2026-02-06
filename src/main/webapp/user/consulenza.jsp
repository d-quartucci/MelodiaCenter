<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Consulenza #${consulenza.id}</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" href="${pageContext.request.contextPath}/styles/main.css" type="text/css">
</head>
<body>
	<jsp:include page="/fragments/header.jsp"/>
	
	<h1>Consulenza #${consulenza.id}</h1>
	Messaggio dell'utente:
	<div id="messaggioUtente">
		<label for="richiestaUtente"></label>
		<textarea name="richiestaUtente" id="richiestaUtente" disabled>${consulenza.messUtente}</textarea>
	</div>
	
	<c:if test="${not empty consulenza.rispAdmin}">
		<div name="messaggioAdmin" id="messaggioAdmin">
			<label for="rispostaAdmin">Risposta dell'admin:</label>
			<textarea name="rispostaAdmin" id="rispostaAdmin" disabled>${consulenza.rispAdmin}</textarea>
		</div>
	</c:if>
	
	<c:if test="${isAdmin}">
		<form name="aggiornaRispostaForm" method="POST" action="${pageContext.request.contextPath}/admin/RispostaConsulenzaServlet?consId=${consulenza.id}">
			<label for="aggiornaRisposta"></label>
			<textarea id="messaggioRisposta" name="messaggioRisposta" placeholder="Inserisci qui la risposta..."></textarea>
			<button id="pulsanteRisposta" name="pulsanteRisposta" type="submit">Invia risposta</button>
		</form>
	</c:if>
	<jsp:include page="/fragments/footer.jsp"/>
</body>
</html>