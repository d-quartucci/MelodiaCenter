<%@ page language="java" contentType="text/html; charset=UTF-8" isErrorPage="true"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<jsp:include page="/fragments/header.jsp"/>
	<h1>${errorMessage}</h1>
	<jsp:include page="/fragments/footer.jsp"/>
</body>
</html>