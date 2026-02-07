<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
</head>
<body>
	<c:if test= "${not empty prodotti}">
		<c:forEach var= "p" items="${prodotti}">
			<tr>
				<td>${p.id}</td>
				<td>${p.categoriaId}</td>
				<td><input type = "text" id= "nome_${p.nome}" value= "${p.nome}" disabled></td>
				<td><input type = "text" id= "prezzo_${p.prezzoAttuale}" value= "${p.prezzoAttuale}" disabled></td>
				<td>${p.imgSrc}</td>
				<td><textarea  id="descr_${p.descrizione}" rows = 5  cols = 30 disabled>${p.descrizione}</textarea></td>
				<td>
					<select id= "attivo_${p.id}" > 
						<option value = "true" ${p.attivo == "true" ? "selected" : ""} >attivo</option>
						<option value = "false" ${p.attivo == "false" ? "selected" : ""}>non attivo</option>
					</select>
				</td>
			</tr>	
		</c:forEach>
	</c:if>
</body>
</html>