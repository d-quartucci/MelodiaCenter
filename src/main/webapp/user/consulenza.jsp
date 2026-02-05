<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Consulenza #${consulenza.id}</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" href="${pageContext.request.contextPath}/styles/main.css" type="text/css">
</head>
<body>
	<jsp:include page="/fragments/header.jsp"/>
	<h1>Consulenza #${consulenza.id}</h1>
	Il tuo messaggio:
	<div id="messaggioUtente">
		<textarea disabled>${consulenza.messUtente}</textarea>
	</div>
	<jsp:include page="/fragments/footer.jsp"/>
</body>
</html>