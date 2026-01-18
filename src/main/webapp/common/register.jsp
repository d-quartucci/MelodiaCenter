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
<script src="${pageContext.request.contextPath}/script/register.js"></script>
</head>

<body>
<h1>Registrati!</h1>

<form name="register" method="POST" action="${pageContext.request.contextPath}/RegisterServlet">
	Nome:<input name="nome" id="nome" type="text" required/><br/>
	Cognome:<input name="cognome" id="cognome" type="text" required/><br/>
	Email:<input name="email" id="email" type="text" onblur="verificaEmail()" required/> <span id="errorLine"></span><br/>
	Password:<input name="password" id="password" type="password" required/><br/>
	Numero di telefono:<input name="tel" id="tel" type="tel" required/>
	<button type="submit" id="submit" disabled>Registrati!</button>
</form>

<h4>Sei già registrato? <a href="login.jsp">Accedi qui!</a> </h4>

</body>
</html>