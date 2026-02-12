<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/styles/main.css" type="text/css">
</head>
<body>
	<aside id="MenuLaterale"> 
			<ul class="MenuGestione">
				<li class="selettore"><a class= "active" href="${pageContext.request.contextPath}/admin/Daily">Menù</a></li>
				<li class="selettore"><a href="${pageContext.request.contextPath}/admin/Utenti">Utenti</a></li>
             	<li class="selettore"><a href="${pageContext.request.contextPath}/admin/Ordini">Ordini</a></li>
             	<li class="selettore"><a href="${pageContext.request.contextPath}/admin/Prodotti">Prodotti</a></li>
             	<li class="selettore"><a href="${pageContext.request.contextPath}/admin/Categorie">Categorie</a></li>
             	<li class="selettore"><a href="${pageContext.request.contextPath}/admin/Consulenza">Consulenze</a></li>
        	</ul>
	</aside>
</body>
</html>