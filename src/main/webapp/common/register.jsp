<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>
	<meta charset="UTF-8">
	<title>Effettua la registrazione</title>
	<script>const contextPath = "${pageContext.request.contextPath}";</script>
	<script src="${pageContext.request.contextPath}/scripts/validate.js"></script>
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
</head>

<body>
<%@ include file="/fragments/header.jsp" %>

<h1>Registrati!</h1>

<form id="registerForm" name="registerForm" method="POST" action="${pageContext.request.contextPath}/RegisterServlet" onsubmit="return validateRegister()">
<fieldset>
<legend>Credenziali di accesso</legend>
	Email:<input name="email" id="email" type="text" oninput="verificaEmail()" pattern="^\S+@\S+\.\S+$" required/>
	<span id="errorEmail" class="error"></span><br/>
	
	Password:<input name="password" id="password" type="password" onchange="validateFormElem(this, document.getElementById('errorPassword'), passwordErrorMessage)" minlength=6 required/>
	<span id="errorPassword" class="error"></span><br/>
</fieldset>

<fieldset>
<legend>Informazioni</legend>
	Nome:<input name="nome" id="nome" type="text" onchange="validateFormElem(this, document.getElementById('errorNome'), lettersOnlyMessage)" pattern="[A-Za-z]+" required/>
	<span id="errorNome" class="error"></span><br/>
	
	Cognome:<input name="cognome" id="cognome" type="text" onchange="validateFormElem(this, document.getElementById('errorCognome'), lettersOnlyMessage)" pattern="[A-Za-z]+" required/>
	<span id="errorCognome" class="error"></span><br/>
	
	Numero di telefono:<input name="tel" id="tel" type="tel" onchange="validateFormElem(this, document.getElementById('errorTel'), phoneErrorMessage)" pattern="[0-9]{10}" required/>
	<span id="errorTel" class="error"></span>
</fieldset>
<button type="submit" id="submit">Registrati!</button>
</form>

<h4>Sei già registrato? <a href="${pageContext.request.contextPath}/common/login.jsp">Accedi qui!</a> </h4>

<%@ include file="/fragments/footer.jsp" %>
</body>
</html>