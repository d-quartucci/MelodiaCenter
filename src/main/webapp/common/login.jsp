<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Effettua il login</title>
</head>
<body>
<h1>Accedi!</h1>
<form name="login" method="POST" action="${pageContext.request.contextPath}/LoginServlet">
	Email:<input name="email" id="email" type="text" required></input><br/>
	Password:<input name="password" id="password" type="password" required></input>
	<button type="submit" id="submit">Accedi</button>
</form>
<h4>Non sei ancora registrato? <a href="register.jsp">Registrati qui!</a> </h4>
</body>
</html>