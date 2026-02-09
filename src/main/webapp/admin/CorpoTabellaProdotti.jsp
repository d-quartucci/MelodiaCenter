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
				<c:forEach var="c" items="${categorie}">
               		 <c:if test="${c.id == p.categoriaId}">
                   	 <td>${c.id}-(${c.nome})</td>
                	</c:if>
				</c:forEach>
				<td><input type = "text" id= "nome_${p.id}" value= "${p.nome}" disabled></td>
				<td><input type = "text" id= "prezzo_${p.id}" value= "${p.prezzoAttuale}" disabled></td>
				<td>${p.imgSrc}</td>
				<td><textarea  id="descr_${p.id}" rows = 5  cols = 30 disabled>${p.descrizione}</textarea></td>
				<td>${p.quantitaVendute}</td>
				<td>
					<select id= "attivo_${p.id}" disabled > 
						<option value = "true" ${p.attivo == "true" ? "selected" : ""} >attivo</option>
						<option value = "false" ${p.attivo == "false" ? "selected" : ""}>non attivo</option>
					</select>
				</td>
				<td>
					<select id= "evidenza_${p.id}" disabled> 
						<option value = "true" ${p.evidenza == "true" ? "selected" : ""} >attivo</option>
						<option value = "false" ${p.evidenza == "false" ? "selected" : ""}>non attivo</option>
					</select>
				</td>
				<td id= "tastoMod">
					<button type = "button" id= "mod_${p.id}" onclick = "abilitaModifica(${p.id})"> Modifica</button>
				</td>
				<td id= "tastoDel">
					<button type = "button" id= "del_${p.id}" onclick = "eliminaProd(${p.id})"> Elimina</button>
				</td>
				<td id= "ErrorSpan">
					<span id="error_${p.id}" class="error"></span>
				</td>
			</tr>	
		</c:forEach>
	</c:if>
</body>
</html>