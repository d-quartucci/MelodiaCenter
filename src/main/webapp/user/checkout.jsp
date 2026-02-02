<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<!DOCTYPE html>
<html>
<head>
<script>
 	   const contextPath = "${pageContext.request.contextPath}";
</script>
<script src="${pageContext.request.contextPath}/scripts/validate.js"></script>
<meta charset="UTF-8">
<link rel="stylesheet" href="${pageContext.request.contextPath}/styles/main.css" type="text/css">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Concludi il tuo ordine!</title>
</head>
<body>
	<%@ include file="/fragments/header.jsp" %>
	<h1>Inserisci le informazioni!</h1>
	
	<div id="informazioniUtente">
		<h2>Da spedire a:</h2>
		<fieldset>
			<p>Nome: ${utente.nome}</p>
			<p>Cognome: ${utente.cognome}</p>
			<p>Numero di telefono: ${utente.telefono}</p>
		</fieldset>
	</div>
	
	<form id="checkoutForm" name="checkoutForm" method="POST" action="${pageContext.request.contextPath}/CreateOrderServlet" onsubmit="return validateCheckout()">
		<div id="inserisciIndirizzo">
			<h2>Inserisci l'indirizzo di consegna:</h2>
				Via: <input id="via" name="via" type="text" required pattern="^[A-Za-z\s]+$" onchange='validateFormElem(this, document.getElementById("errorVia"), lettersOnlyMessage)'>
				<span id="errorVia"></span><br>
				Civico: <input id="civico" name="civico" type="text" pattern="^[0-9]+(-[A-Za-z])?$" onchange='validateFormElem(this, document.getElementById("errorCivico"), civicoErrorMessage)'>
				<span id="errorCivico"></span><br>
				CAP: <input id="cap" name="cap" type="text" required pattern="^[0-9]{5}$" onchange='validateFormElem(this, document.getElementById("errorCAP"), CAPErrorMessage)'>
				<span id="errorCAP"></span><br>
				Città: <input id="citta" name="citta" type="text" required pattern="^[A-Za-z\s]+$" onchange='validateFormElem(this, document.getElementById("errorCitta"), lettersOnlyMessage)'>
				<span id="errorCitta"></span><br>
				Provincia: <input id="provincia" name="provincia" type="text" required pattern="^[A-Za-z\s]+$" onchange='validateFormElem(this, document.getElementById("errorProvincia"), lettersOnlyMessage)'>
				<span id="errorProvincia"></span><br>
		</div>
		
		<div id="inserisciPagamento">
			<h2>Inserisci il metodo di pagamento:</h2>
				Nome: <input id="nome" name="nome" type="text" required pattern="^[A-Za-z\s]+$" onchange='validateFormElem(this, document.getElementById("errorNome"), lettersOnlyMessage)'>
				<span id="errorNome"></span><br>
				Cognome: <input id="cognome" name="cognome" type="text" pattern="^[A-Za-z\s]+$" required onchange='validateFormElem(this, document.getElementById("errorCognome"), lettersOnlyMessage)'>
				<span id="errorCognome"></span><br>
				Provider: <input id="provider" name="provider" type="text" required onchange='validateFormElem(this, document.getElementById("errorProvider"), )'>
				<span id="errorProvider"></span><br>
				PIN: <input id="pin" name="pin" type="text" required pattern="^[0-9]{4}-[0-9]{4}-[0-9]{4}-[0-9]{4}$" onchange='validateFormElem(this, document.getElementById("errorPIN"), PINErrorMessage)'>
				<span id="errorPIN"></span><br>
				CVC: <input id="cvc" name="cvc" type="password" required pattern="^[0-9]{3}$" onchange='validateFormElem(this, document.getElementById("errorCVC"), CVCErrorMessage)'>
				<span id="errorCVC"></span><br>
		</div>
		
		<button type="submit">Effettua acquisto!</button>
	</form>
	
	<%@ include file="/fragments/footer.jsp" %>
</body>
</html>