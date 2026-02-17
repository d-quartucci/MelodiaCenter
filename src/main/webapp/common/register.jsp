<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>
	<meta charset="UTF-8">
	<title>Effettua la registrazione</title>
	<script>const contextPath = "${pageContext.request.contextPath}";</script>
	<script src="${pageContext.request.contextPath}/scripts/validate.js"></script>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/styles/main.css" type="text/css">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
</head>

<body>
<jsp:include page="/fragments/header.jsp"/>
<section id="sezioneRegister">
	<h1 class="presentazione">Registrati!</h1>
	<section class="contenitore" id="sezioneRegister">

		<form id="registerForm" name="registerForm" method="POST" action="${pageContext.request.contextPath}/DoRegisterServlet" onsubmit="return validateRegister()">
			<fieldset>
				<legend>Credenziali di accesso</legend>
				<div class="riga">
					<label for="email">Email:</label>
					<input name="email" id="email" type="text" oninput="verificaEmail()" pattern="^\S+@\S+\.\S+$" required/>
					<span id="errorEmail" class="error"></span><br/>
				</div>	
				
				<div class="riga">
					<label for="password">Password:</label>
					<input name="password" id="password" type="password" onchange="validateFormElem(this, document.getElementById('errorPassword'), passwordErrorMessage)" minlength=6 required/>
					<span id="errorPassword" class="error"></span><br/>
				</div>	
			</fieldset>

			<fieldset>
				<legend>Informazioni</legend>
				<div class="riga">
					<label for="nome">Nome:</label>
					<input name="nome" id="nomeUtente" type="text" onchange="validateFormElem(this, document.getElementById('errorNome'), lettersOnlyMessage)" pattern="^[A-Za-zÀÈÉÌÒÙàèéìòù]+(['\s][A-Za-zÀÈÉÌÒÙàèéìòù]+)*$" required/>
					<span id="errorNome" class="error"></span><br/>
				</div>
				
	   			<div class="riga">
					<label for="cognome">Cognome:</label>
					<input name="cognome" id="cognome" type="text" onchange="validateFormElem(this, document.getElementById('errorCognome'), lettersOnlyMessage)" pattern="^[A-Za-zÀÈÉÌÒÙàèéìòù]+(['\s][A-Za-zÀÈÉÌÒÙàèéìòù]+)*$" required/>
					<span id="errorCognome" class="error"></span><br/>
				</div>
			
				<div class="riga">
					<label for="tel">Numero di telefono:</label>
					<input name="tel" id="tel" type="tel" onchange="validateFormElem(this, document.getElementById('errorTel'), phoneErrorMessage)" pattern="[0-9]{10}" required/>
					<span id="errorTel" class="error"></span>
				</div>
		
			</fieldset>
			<div class="rigaExtra">
				<button type="submit" id="submit">Registrati!</button>
			</div>
		</form>
		<div class="rigaExtra">
			<h4>Sei già registrato? <a href="${pageContext.request.contextPath}/login">Accedi qui!</a> </h4>
		</div>
	</section>
</section>

<jsp:include page="/fragments/footer.jsp"/>
</body>
</html>