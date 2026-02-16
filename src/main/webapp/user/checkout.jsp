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
	<h1 id="presentazioneCheckout">Inserisci le informazioni!</h1>
	
	<div id="informazioniUtente">
		<h2>Da spedire a:</h2>
		<fieldset id="informazioniFieldset">
			<p>Nome: ${utente.nome}</p>
			<p>Cognome: ${utente.cognome}</p>
			<p>Numero di telefono: ${utente.telefono}</p>
		</fieldset>
	</div>
	
	<section id="sezioneCheckoutForm">
	<form id="checkoutForm" name="checkoutForm" method="POST" action="${pageContext.request.contextPath}/user/CreateOrderServlet" onsubmit="return validateCheckout()">
		<h2>Inserisci l'indirizzo di consegna:</h2>
		<div id="inserisciIndirizzo">
			<label for="via">Via: </label>
			<input id="via" name="via" type="text" required pattern="^[A-Za-z\s]+$" onchange='validateFormElem(this, document.getElementById("errorVia"), lettersOnlyMessage)'>
			<span id="errorVia"></span><br>
				
			<label for="civico">Civico: </label>
			<input id="civico" name="civico" type="text" required pattern="^[0-9]+([/][A-Za-z])?$" onchange='validateFormElem(this, document.getElementById("errorCivico"), civicoErrorMessage)'>
			<span id="errorCivico"></span><br>
				
			<label for="cap">CAP: </label>
			<input id="cap" name="cap" type="text" required pattern="^[0-9]{5}$" onchange='validateFormElem(this, document.getElementById("errorCAP"), CAPErrorMessage)'>
			<span id="errorCAP"></span><br>
				
			<label for="citta">Città: </label>
			<input id="citta" name="citta" type="text" required pattern="^[A-Za-z\s]+$" onchange='validateFormElem(this, document.getElementById("errorCitta"), lettersOnlyMessage)'>
			<span id="errorCitta"></span><br>
				
			<label for="provincia">Provincia: </label>
			<input id="provincia" name="provincia" type="text" required pattern="^[A-Za-z\s]+$" onchange='validateFormElem(this, document.getElementById("errorProvincia"), lettersOnlyMessage)'>
			<span id="errorProvincia"></span><br>
		</div>
		<h2>Inserisci il metodo di pagamento:</h2>
		<div id="inserisciPagamento">
			<label for="nome">Nome: </label>
			<input id="nomeCheckout" name="nome" type="text" required pattern="^[A-Za-z\s]+$" onchange='validateFormElem(this, document.getElementById("errorNome"), lettersOnlyMessage)'>
			<span id="errorNome"></span><br>
			
			<label for="cognome">Cognome: </label>
			<input id="cognome" name="cognome" type="text" pattern="^[A-Za-z\s]+$" required onchange='validateFormElem(this, document.getElementById("errorCognome"), lettersOnlyMessage)'>
			<span id="errorCognome"></span><br>
			
			<label for="provider">Provider: </label>
			<input id="provider" name="provider" type="text" required onchange='validateFormElem(this, document.getElementById("errorProvider"), )'>
			<span id="errorProvider"></span><br>
			
			<label for="pin">PIN: </label>
			<input id="pin" name="pin" type="text" required pattern="^[0-9]{4}-[0-9]{4}-[0-9]{4}-[0-9]{4}$" onchange='validateFormElem(this, document.getElementById("errorPIN"), PINErrorMessage)'>
			<span id="errorPIN"></span><br>
			
			<label for="cvc">CVC: </label>
			<input id="cvc" name="cvc" type="password" required pattern="^[0-9]{3}$" onchange='validateFormElem(this, document.getElementById("errorCVC"), CVCErrorMessage)'>
			<span id="errorCVC"></span><br>
		</div>
		
		<button id= "bottoneEffettuaAcquisto" type="submit">Effettua acquisto!</button>
	</form>
	</section>
	
	<%@ include file="/fragments/footer.jsp" %>
</body>
</html>