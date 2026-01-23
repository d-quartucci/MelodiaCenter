<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>${prodotto.nome}</title>
<script>
    const contextPath = "${pageContext.request.contextPath}";
</script>
<script src="${pageContext.request.contextPath}/scripts/product.js"></script>
</head>
<body>
<%@ include file="/fragments/header.jsp" %>
${prodotto.nome}
<button onclick="aggiungiAlCarrello(${prodotto.id})">Aggiungi al carrello!</button>
<%@ include file="/fragments/footer.jsp" %>
</body>
</html>