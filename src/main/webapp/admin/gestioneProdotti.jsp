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
    
    <main>
    	<div id= "creazioneProdotto">
    		<form id = "FormProdotto" name = "FormProdotto" method = "POST" action = "${pageContext.request.contextPath}/admin/AdminProdottiServlet" enctype="multipart/form-data">
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
    	</div>
    	<div id=listProdotti>
    	<section id="sezioneFiltriAdmin">
			<h2>Filtro prodotti:</h2>
			<form id="formFiltri" name="formFiltri" method="GET" action="${pageContext.request.contextPath}/admin/AdminFilterProdottiServlet" >
				
				<label for="barraDiRicerca">Ricerca:</label>	
				<input type="text" id="barraDiRicerca" name="barraDiRicerca" placeholder="Nome prodotto..." value="${param.barraDiRicerca}"><br>
				
				<label for="filtroCtg">Seleziona categoria:</label>
				<select id= "filtroCtg" name="filtroCtg">
					<option value = "0" ${param.filtroCtg == null || param.Ctg == '0' ? 'selected' : ''}>Tutte</option>
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
    		<p>Lista Prodotti</p>
    		<table>
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
    				<%@ include file = "CorpoTabellaProdotti.jsp"%>
    			</tbody>
    		</table>
    	</div>
    </main>
        <!-- FOOTER -->
<%@ include file="/fragments/footer.jsp" %>
</body>
</html>