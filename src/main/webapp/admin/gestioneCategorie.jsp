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
    
        <!-- Menu laterale di gestione -->
<%@ include file = "/fragments/adminMenu.jsp"%>

	<main>
		<div id= "creazioneCategoria">
    		<form id = "FormCategoria" name = "FormCategoria" method = "POST" action = "${pageContext.request.contextPath}/admin/AdminNuovaCategoriaServlet" enctype="multipart/form-data">
    			<fieldset>
    				<legend>Inserisci Nuova Categoria</legend>
    				
    				<label for = "nome">Nome: </label>
    				<input type = "text" id="nome" name="nome" placeholder = "Nome prodotto" required><br>

    				<label for = "descrizione">Descrizione: </label><br>
    				<textarea  id="descr" name="descr" rows = 5  cols = 30 placeholder = "Inserisci descrizione prodotto...." required></textarea><br>
    				
    				<button type="submit" id="submit">Salva</button>
    			</fieldset>
    		</form>
    	</div>
		<div id = "ListaCategorie">
		    <section id="sezioneFiltri">
				<p>Filtro Categorie:</p>
				<form id="formFiltri" name="formFiltri" method="GET" action="${pageContext.request.contextPath}/admin/AdminFilterCategoriaServlet" >
				
					<label for="filtroCtg">Seleziona categoria:</label>
					<select id= "filtroCtg" name="filtroCtg">
						<option value = "0" ${param.filtroCtg == null || param.filtroCtg == '0' ? 'selected' : ''}>Tutte</option>
						<c:forEach var= "c" items= "${categorie}">
							<option value = "${c.id}" ${param.filtroCtg == c.id.toString() ? 'selected' : ''}>${c.nome}</option>
						</c:forEach>
					</select><br>
			
					<button type="submit">Applica filtri</button>
				</form>
			</section>
			<section id="sezioneListaCategorie">
				<table id="tabellaCategoria">
					<thead id="testaTableCategoria">
						 <tr>
    						<th>Cod.Categoria</th>
							<th>Nome</th>
							<th>Descrizione</th>
    					</tr>
					</thead>
					<tbody id="corpoTableCategoria">
						<c:if test= "${not empty ctgFiltrate}">
							<c:forEach var="c" items="${ctgFiltrate}">
								 <tr>
    								<td>${c.id}</td>
									<td><input type = "text" id= "nome_${c.id}" value= "${c.nome}" disabled></td>
									<td><input type = "text" id= "descr_${c.id}" value= "${c.descr}" disabled></td>
									<td id= "tastoMod">
										<button type = "button" id= "mod_${c.id}" onclick = "abilitaModifica(${c.id})"> Modifica</button>
									</td>
									<td id= "tastoDel">
										<button type = "button" id= "del_${c.id}" onclick = "eliminaCtg(${c.id})"> Elimina</button>
									</td>
									<td id= "ErrorSpan">
										<span id="error_${c.id}" class="error"></span>
									</td>
    							</tr>
							</c:forEach>
						</c:if>
						<c:if test= "${empty ctgFiltrate}">
							Non ci sono categorie
						</c:if>
					</tbody>
				</table>
			</section>
		</div>
	</main>
        <!-- FOOTER -->
<%@ include file="/fragments/footer.jsp" %>
</body>
</html>