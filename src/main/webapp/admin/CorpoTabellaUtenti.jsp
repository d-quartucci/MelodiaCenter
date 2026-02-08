<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
</head>
<body>
	<c:if test= "${not empty utenti}">
		<c:forEach var="u" items="${utenti}">				
				<tr>
					<td>${u.id}</td>
					<td>${u.nome}</td>
					<td>${u.cognome}</td>
					<td>
						<input type = "text" id= "email_${u.id}" value= "${u.email}" disabled>			
						<button type = "button" id= "mod_${u.id}" onclick = "abilitaModifica(${u.id})"> Modifica</button>
					</td>	
					<td id="TastoMod">
						<select id= "ruolo_${u.id}" > 
							<option value = "USER" ${u.ruolo == "USER" ? "selected" : ""} > USER</option>
							<option value = "ADMIN" ${u.ruolo == "ADMIN" ? "selected" : ""}> ADMIN</option>
						</select>
						<button type = button onclick = "salvaModifica(${u.id}, 'ruolo', document.getElementById('ruolo_${u.id}').value)"> Applica</button>
					</td>
					<td>
						${u.dataRegistrazione} 
					</td>
					<td id = "ErrorSpan">
						<span id="errorEmail_${u.id }" class="error"></span>
					</td>
				</tr>	
		</c:forEach>
	</c:if>
	<c:if test="${empty utenti}">
       	 <tr>
            <td colspan="5" style="text-align:center; padding:20px;">
                	Nessun utente iscritto
            </td>
      	</tr>
    </c:if>
</body>
</html>