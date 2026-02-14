<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Richieste di consulenza</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/styles/main.css" type="text/css">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
</head>

<body>
	<jsp:include page="/fragments/header.jsp"/>
	
		<!-- Sezione mostrata se sono state fatte richieste -->
	<c:if test="${not empty consulenzaList}">
		<h1 class="presentazione">Le tue richieste di consulenza:</h1>
		<section id="sezioneConsulenze" class="contenitore">
			<c:forEach var="consulenza" items="${consulenzaList}">
				<div id="consulenzaDiv-${consulenza.id}" class="consulenzaDiv">
					<a href="${pageContext.request.contextPath}/user/consulenza?consId=${consulenza.id}">Richiesta #${consulenza.id}</a>
					<p> Stato: ${consulenza.aperto ? 'Aperto' : 'Chiuso'}<p>
				</div>
			</c:forEach>
		</section>
	</c:if>
	
		<!-- Sezione mostrata se non sono state fatte richieste -->
	<c:if test="${empty consulenzaList}">
		<h1 class="presentazione">Non hai ancora fatto alcuna richiesta al nostro team.</h1>
		<section id="sezioneConsulenze" class="contenitore">
			<h2>Per creare una richiesta, vai alla pagina del prodotto di interesse!</h3>
		</section>
	</c:if>
	
	<jsp:include page="/fragments/footer.jsp"/>
</body>
</html>