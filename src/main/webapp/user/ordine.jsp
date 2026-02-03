<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="jakarta.tags.core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Dettagli dell'ordine</title>
</head>
<body>
	<jsp:include page="/fragments/header.jsp"/>
	<c:forEach var="riga" items="${righeOrdine}">
	<p>Quantità: ${riga.quant}<br>${riga.prezzoAcq}€</p>
	</c:forEach>
	<jsp:include page="/fragments/footer.jsp"/>
</body>
</html>