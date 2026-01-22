<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Il tuo carrello</title>
</head>
<body>
<h1>Gestisci il tuo carrello!</h1>
<c:if test="${empty sessionScope.carrello}">
Il carrello è vuoto! Comincia la tua ricerca da <a href="catalog.jsp">qui</a>!
</c:if>

<c:if test="${not empty sessionScope.carrello}">
<h2>Ecco i tuoi prodotti:</h2>
</c:if>
</body>
</html>