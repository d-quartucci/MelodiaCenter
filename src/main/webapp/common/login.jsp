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

<section id="sezioneLogin">
<h1 class="presentazione">Accedi!</h1>
	<section class="contenitore">
	<h2 class="errore">${error}</h2>
		<form id="loginForm" name="loginForm" method="POST" action="${pageContext.request.contextPath}/DoLoginServlet" onsubmit="return validateLogin()">
			
			<div class="riga">
				<label for="email">Email:</label>
				<input name="email" id="email" onchange="validateFormElem(this, document.getElementById('errorEmail'), emailErrorMessage)" type="text" required pattern="^\S+@\S+\.\S+$" value="${emailRicordata != null ? emailRicordata : ''}"></input>
				<span id="errorEmail"></span><br/>
			</div>
			
			<div class="riga">
				<label for="password">Password:</label>
				<input name="password" id="password" type="password" onchange="validateFormElem(this, document.getElementById('errorPassword'), passwordErrorMessage)" required minlength="6"></input>
				<span id="errorPassword"></span><br/>
			</div>
			
			<div class="riga" id="ricordaEmail">
				<label for="ricordaCheckbox">Ricorda l'email?</label>
				<input name="ricordaCheckbox" id="ricordaCheckbox" type="checkbox" ${wasChecked ? "checked" : ""}>
			</div>
			
			<div class="riga">
				<button type="submit" id="submit">Accedi!</button>
			</div>
			
		</form>
		<div class="riga">
			<h4>Non sei ancora registrato? <a href="${pageContext.request.contextPath}/register">Registrati qui!</a> </h4>
		</div>
	</section>
</section>


<jsp:include page="/fragments/footer.jsp"/>
</body>
</html>