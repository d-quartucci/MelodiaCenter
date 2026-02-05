<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Richieste di consulenza</title>
</head>
<body>
	<jsp:include page="/fragments/header.jsp"/>
	<!-- Sezione mostrata se sono state fatte richieste -->
	<c:if test="${not empty consulenzaList}">
	<div id="sezioneConsulenze">
	<h1>Le tue richieste di consulenza:</h1>
		<c:forEach var="consulenza" items="${consulenzaList}">
			<div id="consulenzaDiv-${consulenza.id}">
				<a href="${pageContext.request.contextPath}/user/ConsulenzaPageServlet?consId=${consulenza.id}">Richiesta #${consulenza.id}</a>
				<p> Stato: ${consulenza.aperto ? 'Aperto' : 'Chiuso'}<p>
				<br>
			</div>
		</c:forEach>
	</div>
	</c:if>
	
	<!-- Sezione mostrata se non sono state fatte richieste -->
	<c:if test="${empty consulenzaList}">
	<div id="sezioneVuota">
		<h1>Non hai fatto alcuna richiesta.</h1>
		<h3>Per creare una richiesta, vai alla pagina del prodotto di interesse!</h3>
	</div>
	</c:if>
	
	<jsp:include page="/fragments/footer.jsp"/>
</body>
</html>