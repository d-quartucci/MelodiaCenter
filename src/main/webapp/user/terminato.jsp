<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Acquisto concluso!</title>
</head>
<body>
<jsp:include page="/fragments/header.jsp"/>
<h1>Grazie per l'acquisto!</h1>
<p>Arriverà entro la data:</p>
<p>Torna alla <a href="${pageContext.request.contextPath}/common/index.jsp">home</a>!</p>
<jsp:include page="/fragments/footer.jsp"/>
</body>
</html>