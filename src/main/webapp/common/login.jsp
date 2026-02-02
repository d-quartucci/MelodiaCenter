<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/styles/main.css" type="text/css">
	<title>Effettua il login</title>
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<script>const contextPath = "${pageContext.request.contextPath}";</script>
	<script src="${pageContext.request.contextPath}/scripts/validate.js"></script>
</head>
<body>
<%@ include file="/fragments/header.jsp" %>

<h1>Accedi!</h1>
<h2 id="errore">${error}</h2>
<div id="formLogin">
	<form id="loginForm" name="loginForm" method="POST" action="${pageContext.request.contextPath}/LoginServlet" onsubmit="return validateLogin()">
		Email:<input name="email" id="email" onchange="validateFormElem(this, document.getElementById('errorEmail'), emailErrorMessage)" type="text" required pattern="^\S+@\S+\.\S+$"></input>
		<span id="errorEmail"></span><br/>
		Password:<input name="password" id="password" type="password" onchange="validateFormElem(this, document.getElementById('errorPassword'), passwordErrorMessage)" required minlength="6"></input>
		<span id="errorPassword"></span><br/>
		<button type="submit" id="submit">Accedi</button>
	</form>
</div>
<h4>Non sei ancora registrato? <a href="${pageContext.request.contextPath}/common/register.jsp">Registrati qui!</a> </h4>

<%@ include file="/fragments/footer.jsp" %>
</body>
</html>