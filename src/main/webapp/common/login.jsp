<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/styles/main.css" type="text/css">
	<title>Effettua il login</title>
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
</head>
<body>
<%@ include file="/fragments/header.jsp" %>
<h1>Accedi!</h1>
<div id="formLogin">
	<form name="login" method="POST" action="${pageContext.request.contextPath}/LoginServlet">
		Email:<input name="email" id="email" type="text" required></input><br/>
		Password:<input name="password" id="password" type="password" required></input>
		<button type="submit" id="submit">Accedi</button>
	</form>
</div>
<h4>Non sei ancora registrato? <a href="register.jsp">Registrati qui!</a> </h4>
<%@ include file="/fragments/footer.jsp" %>
</body>
</html>