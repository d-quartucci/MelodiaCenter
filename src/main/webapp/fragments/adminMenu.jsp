<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
    
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
</head>
<body>
	<aside id="Menu"> 
		<nav>
			<ul class="MenuGestione">
				<li><a href="${pageContext.request.contextPath}/admin/AdminUtentiServlet">Utenti</a></li>
             	<li><a href="${pageContext.request.contextPath}/admin/AdminOrdiniServlet">Ordini</a></li>
             	<li><a href="${pageContext.request.contextPath}/admin/AdminProdottiServlet">Prodotti</a></li>
             	<li><a href="${pageContext.request.contextPath}/admin/AdminCategorieServlet">Categorie</a></li>
             	<li><a href="${pageContext.request.contextPath}/admin/AdminConsulenzaServlet">Consulenze</a></li>
        	</ul>
		</nav>
	</aside>
</body>
</html>