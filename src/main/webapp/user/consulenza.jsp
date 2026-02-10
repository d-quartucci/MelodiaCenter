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
	
	<h1 id="presentazioneConsulenza" class="presentazione">Consulenza #${consulenza.id}</h1>
	
	<section id="sezioneMessaggiConsulenza" class="contenitore">
		<section id="sezioneMessaggiUtente">
			<div id="messaggioUtente">
				<h2>Messaggio dell'utente:</h2>
				<textarea name="richiestaUtente" id="richiestaUtente" disabled>${consulenza.messUtente}</textarea>
			</div>
		</section>
		
	<c:if test="${not empty consulenza.rispAdmin}">
		<section id="sezioneRispostaAdmin">
			<div id="messaggioAdmin">
				<h2>Risposta dell'admin:</h2>
				<textarea name="rispostaAdmin" id="rispostaAdmin" disabled>${consulenza.rispAdmin}</textarea>
			</div>
		</section>
	</c:if>
	
	</section>
	
	<c:if test="${isAdmin}">
		<section id="rispondiConsulenza" class="contenitore">
		<h2>Rispondi all'utente, o aggiorna la risposta:</h2>
			<form name="aggiornaRispostaForm" method="POST" action="${pageContext.request.contextPath}/admin/RispostaConsulenzaServlet?consId=${consulenza.id}">
				<label for="aggiornaRisposta"></label>
				<textarea id="messaggioRisposta" name="messaggioRisposta" placeholder="Inserisci qui la risposta..."></textarea>
				<button id="pulsanteRisposta" name="pulsanteRisposta" type="submit">Invia risposta</button>
			</form>
		</section>
	</c:if>
	<jsp:include page="/fragments/footer.jsp"/>
</body>
</html>