<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Ordini effettuati</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/styles/main.css" type="text/css">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
</head>
<body>
	<jsp:include page="/fragments/header.jsp"/>
	<c:if test="${not empty listaOrdini}">
	<h1>Ecco la lista degli ordini che hai effettuato in passato:</h1>
		<c:forEach var="order" items="${listaOrdini}">
			<div id="orderDiv-${order.id}"></div>
			<h3><a href="${pageContext.request.contextPath}/user/OrderInfoServlet?id=${order.id}">Ordine ID: ${order.id}</a></h3><br>
		</c:forEach>
	</c:if>
	<c:if test="${empty listaOrdini}">
		<h1>Non hai ancora effettuato un ordine!</h1>
		<h3>Comincia la tua ricerca da <a href="${pageContext.request.contextPath}/CatalogServlet">qui</a>!</h3>
	</c:if>
	<jsp:include page="/fragments/footer.jsp"/>
</body>
</html>