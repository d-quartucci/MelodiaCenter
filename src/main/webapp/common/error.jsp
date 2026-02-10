<%@ page language="java" contentType="text/html; charset=UTF-8" isErrorPage="true"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Errore!</title>
</head>
<body>
	<jsp:include page="/fragments/header.jsp"/>
	<section class="contenitore" id="sezioneErrore">
		<h1>${errorMessage}</h1>
		<h3>Torna alla <a href="${pageContext.request.contextPath}/home">home</a>!</h3>
	</section>
	<jsp:include page="/fragments/footer.jsp"/>
</body>
</html>