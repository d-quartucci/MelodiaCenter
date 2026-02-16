<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/styles/main.css" type="text/css">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<script>const contextPath = "${pageContext.request.contextPath}";</script>
	<script src="${pageContext.request.contextPath}/scripts/gestioneCategorie.js"></script>
	<title>Gestione Categorie</title>
</head>
<body>
			<!-- HEADER -->
<%@ include file="/fragments/header.jsp" %>
    
	 <main class ="pageAdmin">
    	<aside>
			<%@ include file = "/fragments/adminMenu.jsp"%>
		</aside>
		<section id= "creazioneCategoria">
    		<form id = "FormCategoria" name = "FormCategoria" method = "POST" action = "${pageContext.request.contextPath}/admin/nuovaCategoria">
    			<fieldset class= "contenitore" id= "FormCreaCtg">
    				<legend><h2>Crea Categoria</h2></legend>
    				
    				<label for = "nome">Nome</label>
    				<br>
    				<input type = "text" id="nome" name="nome" placeholder = "Nome categoria..." onchange="validateForm(this, document.getElementById('errorNameCtg'))" required>
					<span id= "errorNameCtg" class="error" ></span>
					<br>
    				<label for = "descrizione">Descrizione </label> <span id= "errorDescrCtg" class="error" ></span><br>
    				<textarea class="TextAreaAdmin" id="descr" name="descr" rows = 5  cols = 30 placeholder = "Inserisci categoria..." onchange="validateForm(this, document.getElementById('errorDescrCtg'))" minlength="20" required></textarea>
    				<br>
    				<button class= "bottoneMod" type="submit" id="submitC">Salva</button>
    			</fieldset>
    		</form>
    	</section >
    	 
			<section class= "contenitore" id="listaCtg">
				<h2>Lista Categorie:</h2>
			<c:if test= "${not empty ctgFiltrate}">
				<table class= "tabellaAdmin" id="tabellaCtg">
					<thead id="testaTableCategoria">
						 <tr>
    						<th>Cod.Categoria</th>
							<th>Nome</th>
							<th>Descrizione</th>
    					</tr>
					</thead>
					<tbody id="corpoTableCategoria">
							<c:forEach var="c" items="${ctgFiltrate}">
								 <tr>
    								<td>${c.id}</td>
									<td><input class="inputBox" type="text" id= "nome_${c.id}" value= "${c.nome}" disabled></td>
									<td><input class="inputBox" type="text" id= "descr_${c.id}" value= "${c.descr}" disabled></td>
									<td class= "TastiUtenti" id= "tastoMod">
										<button class= "bottoneMod" type="button" id= "mod_${c.id}" onclick = "abilitaModifica(${c.id})"> Modifica</button>
									</td>
									<td class= "TastiUtenti" id= "tastoDel">
										<button class= "bottoneMod" type="button" id= "del_${c.id}" onclick = "eliminaCtg(${c.id})"> Elimina</button>
									</td>
    							</tr>
    							<h5 id= "ErrorSpan">
									<span id="error_${c.id}" class="error"></span>
								</h5>
							</c:forEach>
						</c:if>
						<c:if test= "${empty ctgFiltrate}">
							<h2 class="emptyTable">Non ci sono categorie!</h2>
						</c:if>
					</tbody>
				</table>
			</section>
		</main>
        <!-- FOOTER -->
<%@ include file="/fragments/footer.jsp" %>
</body>
</html>