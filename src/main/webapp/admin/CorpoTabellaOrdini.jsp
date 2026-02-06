<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
</head>
<body>
	<c:if test= "${not empty ordini}">
		<c:forEach var="o" items="${ordini}">
			<tr>
				<td>${o.id}</td>
				<td>${o.utenteId}</td>
				<td>${o.data}</td>
				<td>${o.totale}€</td>
				<td>${o.indSpedizione}</td>
			</tr>
		</c:forEach>
	</c:if>
	<c:if test="${empty ordini}">
       	<tr>
           <td colspan="5" style="text-align:center; padding:20px;">
                	Nessun ordine presente
           </td>
      	</tr>
   </c:if>
</body>
</html>