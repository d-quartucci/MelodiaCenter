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
    	<div>
    		<form action = "/admin/AdminFormServlet" method = "post" enctype = "multipart/form-data">
    			<fieldset>
    				<legend>Inserisci Nuovo Prodotto</legend>
    				<label for = "nome">Nome: </label>
    				<input type = "text" id="nome" name="nome" placeholder = "Nome prodotto" required>
    				<select name="categoria" required>
        				<option value="0">Seleziona Categoria</option>
        				<option value="1">Chitarra</option>
        				<option value="2">Percussioni</option>
        				<option value="3">A fiato</option>
        				<option value="4">Pianoforte</option>
    				</select><br>
    				<label for = "prezzo">Prezzo: </label>
    				<input type = "number" id="prezzo" name="prezzo" step="0.01" min="0" placeholder= "0.00" required><br>
    				<label for = "descrizione">Descrizione: </label><br>
    				<textarea  id="descr" name="descr" rows = 5  cols = 30 placeholder = "Inserisci descrizione prodotto...." required></textarea><br>
    				<label for = "image">Immagine </label><br>
    				<button type= button onclick = "salvaProdotto()">Salva</button>
    			</fieldset>
    		</form>
    	</div>
    	<div id=listProdotti>
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
						<th>Attivo</th>
    				</tr>
    			</thead>
    			<tbody id= "corpoTablePrdotti">
    				<%@ include file = "CorpoTabellaProdotti.jsp"%>
    			</tbody>
    		</table>
    	</div>
    </main>
        <!-- FOOTER -->
<%@ include file="/fragments/footer.jsp" %>
</body>
</html>