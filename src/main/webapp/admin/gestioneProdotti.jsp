<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/styles/main.css" type="text/css">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<script>const contextPath = "${pageContext.request.contextPath}";</script>
	<script src="${pageContext.request.contextPath}/scripts/gestioneProdotti.js"></script>
	<title>Gestione Prodotti</title>
</head>
<body>
		<!-- HEADER -->
<%@ include file="/fragments/header.jsp" %>
    
        <!-- Menu laterale di gestione -->
<%@ include file = "/fragments/adminMenu.jsp"%>
    
    	<section id= "creazioneProdotto">
    		<form id = "FormProdotto" name = "FormProdotto" method = "POST" action = "${pageContext.request.contextPath}/admin/AdminNuovoProdottoServlet" enctype="multipart/form-data">
    			<fieldset>
    				<legend>Inserisci Nuovo Prodotto</legend>
    				<label for = "nome">Nome: </label>
    				
    				<input type = "text" id="nome" name="nome" placeholder = "Nome prodotto" required>
    				<select id = "categoria" name="categoria" required>
        				<option value="">Seleziona Categoria</option>
        				<c:forEach var="c" items="${categorie}">
							<option value="${c.id}">${c.nome}</option>
						</c:forEach>
    				</select><br>
    				
    				<label for = "prezzo">Prezzo: </label>
    				<input type = "number" id="prezzo" name="prezzo" step="0.01" min="0" placeholder= "0.00" required><br>
    				
    				<label for = "descrizione">Descrizione: </label><br>
    				<textarea  id="descr" name="descr" rows = 5  cols = 30 placeholder = "Inserisci descrizione prodotto...." required></textarea><br>
    				
    				<label for = "image">Immagine </label><br>
    				<input type="file" id="image" name="image" accept="image/*" required><br><br>
    				
    				<button type="submit" id="submit">Salva</button>
    			</fieldset>
    		</form>
    	</section>
    	 <section id="sezioneFiltriAdmin">
				<p>Filtro prodotti:</p>
				<form id="formFiltri" name="formFiltri" method="GET" action="${pageContext.request.contextPath}/admin/AdminFilterProdottiServlet" >
				
					<label for="barraDiRicerca">Ricerca:</label>	
					<input type="text" id="barraDiRicerca" name="barraDiRicerca" placeholder="Nome prodotto..." value="${param.barraDiRicerca}"><br>
				
					<label for="filtroCtg">Seleziona categoria:</label>
					<select id= "filtroCtg" name="filtroCtg">
						<option value = "0" ${param.filtroCtg == null || param.filtroCtg == '0' ? 'selected' : ''}>Tutte</option>
						<c:forEach var= "c" items= "${categorie}">
							<option value = "${c.id}" ${param.filtroCtg == c.id.toString() ? 'selected' : ''}>${c.nome}</option>
						</c:forEach>
					</select><br>
				
					<label for="ordinaPrezzo">Ordina: </label>
					<select id="ordina" name="ordina">
						<option value="prezzoCrescente" ${param.ordina == "prezzoCrescente" ? "selected" : ""}>Prezzo Crescente</option>
						<option value="prezzoDecrescente" ${param.ordina == "prezzoDecrescente" ? "selected" : ""}>Prezzo Decrescente</option>
						<option value="vendutiCrescente" ${param.ordina == "VendutiCrescente" ? "selected" : ""}>Quantità Crescente</option>
						<option value="vendutiDecrescente" ${param.ordina == "VendutiDecrescente" ? "selected" : ""}>Quantità Decrescente</option>
					</select><br>
			
					<button type="submit">Applica filtri</button>
				</form>
		</section>
    	<section id=listProdotti>
    			<p>Lista Prodotti</p>
    			<table id ="tabellaPrdotti">
    				<thead id = "testaTableProdotti">
    					<tr>
    						<th>Cod.Prodotto</th>
							<th>Cod.Categoria</th>
							<th>Nome</th>
							<th>Prezzo</th>
							<th>Immagine</th>
							<th>Descrizione</th>
							<th>Quantità Vendute</th>
							<th>Attivo</th>
							<th>Evidenza</th>
    					</tr>
    				</thead>
    				<tbody id= "corpoTableProdotti">
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
									<td id= "image">
										<img id="image_${p.id}" src="${pageContext.request.contextPath}/images/${p.imgSrc}" alt="Immagine ${p.nome}"><br>
       		          				</td>
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
    				</tbody>
    			</table>
    	</section>
        <!-- FOOTER -->
<%@ include file="/fragments/footer.jsp" %>
</body>
</html>