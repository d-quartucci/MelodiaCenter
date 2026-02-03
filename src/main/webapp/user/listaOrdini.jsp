<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Ordini effettuati</title>
</head>
<body>
	<jsp:include page="/fragments/header.jsp"/>
	<h1>Ecco la lista degli ordini che hai effettuato in passato:</h1>
	<c:forEach var="order" items="${listaOrdini}">
	<div id="orderDiv-${order.id}"></div>
	<h3><a href="${pageContext.request.contextPath}/OrderInfoServlet?id=${order.id}">Ordine ID: ${order.id}</a></h3><br>
	</c:forEach>
	<jsp:include page="/fragments/footer.jsp"/>
</body>
</html>