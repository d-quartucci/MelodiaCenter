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
    
	 <main class ="pageAdmin">
    	<aside>
			<%@ include file = "/fragments/adminMenu.jsp"%>
		</aside>
    	<section id= "SezioneCreaProdotto">
    		<form id = "FormProdotto" name = "FormProdotto" method = "POST" action = "${pageContext.request.contextPath}/admin/NuovoProdotto" onsubmit= "return validateProdotto()" enctype="multipart/form-data">
    			<fieldset id= "FormCreazione" class= "contenitore">
    				<legend ><h2>Crea Prodotto</h2></legend>
    				<label for="nome">Nome </label>
    				<br>
    				<input type="text" id="nome" name="nome" placeholder= "Nome prodotto..." onchange="validateForm(this, document.getElementById('errorName'))" required>
    				<span id= "errorName" class="error" ></span>
    				<br>
    				<label for="categoria">Categoria</label>
    				<br>
    				<select class="menuTendina" id = "categoria" name="categoria" onchange="validateForm(this, document.getElementById('errorCtg'))" required>
        				<option value="0">Seleziona Categoria</option>
        				<c:forEach var="c" items="${categorie}">
							<option value="${c.id}">${c.nome}</option>
						</c:forEach>
    				</select>
    				<span id= "errorCtg" class="error" ></span>
    				<br>
    				
    				<label for = "prezzo">Prezzo </label>
    				<br>
    				<input type = "number" id="prezzo" name="prezzo" step="0.01" min="1" placeholder= "0.00" pattern="^([0-9]){1,}$" onchange="validateForm(this, document.getElementById('errorPrezzo'))" required>
    				<span id= "errorPrezzo" class="error" ></span>
    				<br>
    				<label id="labelDescr" for = "descrizione">Descrizione</label>   <span id= "errorDescr" class="error" ></span>
    				<br>
    				<textarea  class="TextAreaAdmin" id="descr" name="descr" rows = 5  cols = 30 placeholder = "Inserisci descrizione prodotto...." onchange="validateForm(this, document.getElementById('errorDescr'))" minlength="50" required ></textarea>
    				<br>

    				<label for = "image">Inserisci un immagine: </label>
    				<br>
    				<input type="file" id="image" name="image" accept="image/*" onchange="validateImg(this, document.getElementById('errorImg'))" required><br><br>
    				<span id= "errorImg" class="error" ></span>
    				
    				<button type="submit" id="submitP" class="bottoneFiltro">Salva</button>
    			</fieldset>
    		</form>
    	</section>
    	 <section class= "contenitore" id="filtriProdottiAdmin">
				<h2>Filtri Prodotti:</h2>
				<form id="formFiltri" name="formFiltri" method="POST" action="${pageContext.request.contextPath}/admin/FilterProdotti" >
				<span class="sezioneFiltro">
					<label for="barraDiRicerca">RICERCA:</label>	
					<input type="text" id="barraDiRicerca" name="barraDiRicerca" placeholder="Nome prodotto..." value="${param.barraDiRicerca}">
				</span>
				<span class="sezioneFiltro">
					<label id= "labelCtg" for="filtroCtg">CATEGORIA:</label>
					<select class="menuTendina" id= "filtroCtg" name="filtroCtg">
						<option value = "0" ${param.filtroCtg == null || param.filtroCtg == '0' ? 'selected' : ''}>Tutte</option>
						<c:forEach var= "c" items= "${categorie}">
							<option value = "${c.id}" ${param.filtroCtg == c.id.toString() ? 'selected' : ''}>${c.nome}</option>
						</c:forEach>
					</select>
				</span>
				<span class="sezioneFiltro">
					<label id= "labelOrd" for="filtroOrdina">ORDINA:</label>
					<select class="menuTendina" id="filtroOrdina" name="filtroOrdina">
						<option value="prezzoCrescente" ${param.filtroOrdina == "prezzoCrescente" ? "selected" : ""}>Prezzo Crescente</option>
						<option value="prezzoDecrescente" ${param.filtroOrdina == "prezzoDecrescente" ? "selected" : ""}>Prezzo Decrescente</option>
						<option value="vendutiCrescente" ${param.filtroOrdina == "vendutiCrescente" ? "selected" : ""}>Quantità Crescente</option>
						<option value="vendutiDecrescente" ${param.filtroOrdina == "vendutiDecrescente" ? "selected" : ""}>Quantità Decrescente</option>
					</select>
				</span>
				
					<button class="bottoneFiltro" id="FiltroProd" type="submit">Filtra</button>
				</form>
		</section>
    	<section class= "contenitore"  id= "listProdotti">
    			<h2>Lista Prodotti:</h2>
    		<c:if test= "${not empty prodotti}">
    			<table class= "tabellaAdmin" id ="tabellaProdotti">
    				<thead id = "testaTableProdotti">
    					<tr>
    						<th>Cod.Prod</th>
							<th>Categoria</th>
							<th>Img</th>
							<th>Nome</th>
							<th>Prezzo</th>
							<th>Descrizione</th>
							<th>Q.Vendute</th>
							<th>Attivo</th>
							<th>Evidenza</th>
    					</tr>
    			</thead>
    				<tbody id= "corpoTableProdotti">
							<c:forEach var= "p" items="${prodotti}">
								<tr id= "riga_${p.id}">
									<td><a href="${pageContext.request.contextPath}/product?prodottoId=${p.id}">#${p.id}</a></td>
									<c:forEach var="c" items="${categorie}">
	               						 <c:if test="${c.id == p.categoriaId}">
	                   	 					<td>ID:${c.id}<br>(${c.nome})</td>
                						</c:if>
									</c:forEach>
									<td id= "tdImage">
										<img class="ImageAdmin" id="image_${p.id}" src="${pageContext.request.contextPath}/images/${p.imgSrc}" alt="Immagine ${p.nome}"><br>
       		          				</td>
									<td><textarea class="TextAreaAdmin"  id= "nome_${p.id}" disabled>${p.nome}</textarea >
									<td><input type="text" id= "prezzo_${p.id}" value= "${p.prezzoAttuale}" disabled></input>

									<td><textarea  class="TextAreaAdmin" id="descr_${p.id}" disabled>${p.descrizione}</textarea></td>
									<td>${p.quantitaVendute}</td>
									<td >
										<select class="menuTendina" class="MenuTabella" id= "attivo_${p.id}" disabled > 
											<option value = "true" ${p.attivo == "true" ? "selected" : ""} >ATTIVO</option>
											<option value = "false" ${p.attivo == "false" ? "selected" : ""}>NO ATTIVO</option>
										</select>
									</td>
									<td >
										<select class="menuTendina" class="MenuTabella" id="evidenza_${p.id}" disabled> 
											<option value = "true" ${p.evidenza == "true" ? "selected" : ""} >EVID.</option>
											<option value = "false" ${p.evidenza == "false" ? "selected" : ""}>NO EVID.</option>
										</select>
									</td>

									<td class= "TastiUtenti" id= "tastoMod">
										<button type = "button" class= "bottoneMod" id= "mod_${p.id}" onclick = "abilitaModifica(${p.id})"> Modifica</button>

									<td class= "TastiUtenti" id= "tastoDel">
										<button type = "button" class= "bottoneMod" id= "del_${p.id}" onclick = "eliminaProd(${p.id})"> Elimina</button>
									</td>
								</tr>
								<h5 id= "ErrorSpan">
										<span id="error_${p.id}" class="error"></span>
									</h5>
							</c:forEach>
    				</tbody>
    			</table>
    		</c:if>
    		<c:if test="${empty prodotti}">
                <h2 class="emptyTable">Nessun prodotto presente!</h2> 
   			</c:if>
    	</section>
    </main>
        <!-- FOOTER -->
<%@ include file="/fragments/footer.jsp" %>
</body>
</html>