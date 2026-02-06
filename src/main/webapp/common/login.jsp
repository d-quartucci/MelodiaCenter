<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Effettua il login</title>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/styles/main.css" type="text/css">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<script>const contextPath = "${pageContext.request.contextPath}";</script>
	<script src="${pageContext.request.contextPath}/scripts/validate.js"></script>
</head>
<body>
<jsp:include page="/fragments/header.jsp"/>

<h1>Accedi!</h1>
<h2 id="errore">${error}</h2>
<div id="formLogin">
	<form id="loginForm" name="loginForm" method="POST" action="${pageContext.request.contextPath}/DoLoginServlet" onsubmit="return validateLogin()">
		<label for="email">Email:</label>
		<input name="email" id="email" onchange="validateFormElem(this, document.getElementById('errorEmail'), emailErrorMessage)" type="text" required pattern="^\S+@\S+\.\S+$" value="${emailRicordata != null ? emailRicordata : ''}"></input>
		<span id="errorEmail"></span><br/>
		
		<label for="password">Password:</label>
		<input name="password" id="password" type="password" onchange="validateFormElem(this, document.getElementById('errorPassword'), passwordErrorMessage)" required minlength="6"></input>
		<span id="errorPassword"></span><br/>
		
		<label for="ricordaCheckbox">Ricorda l'email</label>
		<input name="ricordaCheckbox" id="ricordaCheckbox" type="checkbox" ${wasChecked ? "checked" : ""}>
		
		<button type="submit" id="submit">Accedi</button>
	</form>
</div>
<h4>Non sei ancora registrato? <a href="${pageContext.request.contextPath}/RegisterServlet">Registrati qui!</a> </h4>

<jsp:include page="/fragments/footer.jsp"/>
</body>
</html>