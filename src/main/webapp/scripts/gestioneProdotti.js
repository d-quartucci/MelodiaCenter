function abilitaModifica(id){
	
	const bMod = document.getElementById("mod_"+id);
	
	if(bMod.innerText == "Modifica" ){
		document.getElementById("nome_" + id).disabled = false;
		document.getElementById("prezzo_" + id).disabled = false;
		document.getElementById("descr_" + id).disabled = false;
		document.getElementById("attivo_" + id).disabled = false;
		document.getElementById("evidenza_" + id).disabled = false;
		
		bMod.innerText = "Applica";
		
	}else if (bMod.innerText == "Applica" ){
		
		salvaModifica(id);
		document.getElementById("nome_" + id).disabled = true;
		document.getElementById("prezzo_" + id).disabled = true;
		document.getElementById("descr_" + id).disabled = true;
		document.getElementById("attivo_" + id).disabled = true;
		document.getElementById("evidenza_" + id).disabled = true;

		bMod.innerText = "Modifica";
	}
}

function salvaModifica(id){
	const nome = document.getElementById("nome_" + id).value;
	const prezzo = document.getElementById("prezzo_" + id).value;
	const descr = document.getElementById("descr_" + id).value;
	const attivo = document.getElementById("attivo_" + id).value;
	const evidenza = document.getElementById("evidenza_" + id).value;
	const ErrorSpan = document.getElementById("error_" + id);
	
	let xhr = new XMLHttpRequest();
	const url = contextPath + "/admin/AdminUpdateProdottiServlet";
	
	xhr.onreadystatechange = function(){
		if(this.readyState === 4 ){
			if(this.status === 200)
				ErrorSpan.innerHTML = "Salvataggio Completato";
		}
	}
	
	xhr.open("POST", url, true);
	xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
	let params = "id="+ id +"&nome=" + encodeURIComponent(nome) + "&prezzo=" + encodeURIComponent(prezzo)+ "&descr=" + encodeURIComponent(descr) 
				+ "&attivo=" + encodeURIComponent(attivo) + "&evidenza=" + encodeURIComponent(evidenza);
	xhr.send(params);
	
}


function eliminaProd(id){
	 let xhr = new XMLHttpRequest();
	 const url = contextPath + "/admin/AdminDeleteProdotto?id="+ id;
	 
	 xhr.onreadystatechange = function(){
	 	if(this.readyState === 4 ){
	 		if(this.status === 200)
				alert("Eliminazione avvenuta con successo");
				location.reload();        // Ricarica la pagina per aggiornare la lista prodotti
			} else if (this.status === 403) {
				alert("Errore: Il prodotto è presente in un ordine e non può essere rimosso.");
			} else if (this.status === 404){
				alert("Errore: Il prodotto non trovato");
			}
	 }
	 
	 xhr.open("GET", url, true);
	 xhr.send();
	
}