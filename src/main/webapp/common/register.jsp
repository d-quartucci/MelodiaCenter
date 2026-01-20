<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<title>Effettua la registrazione</title>
<script>
    const contextPath = "${pageContext.request.contextPath}";
</script>
<script src="${pageContext.request.contextPath}/scripts/register.js"></script>
</head>

<body>
<%@ include file="/fragments/header.jsp" %>
<h1>Registrati!</h1>

<form name="register" method="POST" action="${pageContext.request.contextPath}/RegisterServlet">
<fieldset>
<legend>Credenziali di accesso</legend>
	Email:<input name="email" id="email" type="text" onblur="verificaEmail()" required/> <span id="errorLine"></span><br/>
	Password:<input name="password" id="password" type="password" required/><br/>
</fieldset>

<fieldset>
<legend>Informazioni</legend>
	Nome:<input name="nome" id="nome" type="text" required/><br/>
	Cognome:<input name="cognome" id="cognome" type="text" required/><br/>
	Numero di telefono:<input name="tel" id="tel" type="tel" required/>
</fieldset>
<button type="submit" id="submit" disabled>Registrati!</button>
</form>

<h4>Sei già registrato? <a href="login.jsp">Accedi qui!</a> </h4>
<%@ include file="/fragments/footer.jsp" %>
</body>
</html>