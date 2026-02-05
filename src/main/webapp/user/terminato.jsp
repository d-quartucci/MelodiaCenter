<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Acquisto concluso!</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/styles/main.css" type="text/css">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
</head>
<body>
<jsp:include page="/fragments/header.jsp"/>
<h1>Grazie per l'acquisto!</h1>
<p>Arriverà entro la data:</p>
<p>Torna alla <a href="${pageContext.request.contextPath}/home">home</a>!</p>
<jsp:include page="/fragments/footer.jsp"/>
</body>
</html>