function validateForm(formElem, errorSpan){
	
	if(formElem.validity.valueMissing){//Campo vuoto
		errorSpan.innerHTML = "Campo vuoto";
		return false;
	}else if(formElem.validity.tooShort){//Per la descrizione se è troppo corta 
		errorSpan.innerHTML = "Descrizione troppo corta min 50";
		return false;
	}else if(formElem.validity.rangeUnderflow) {//Nel caso si inserisce come valore 0
	    errorSpan.innerHTML = "Il prezzo deve essere maggiore di 0";
		return false;
	}else if (formElem.tagName === "SELECT" && formElem.value === "0") {
	    errorSpan.innerHTML = "Seleziona una categoria valida";
	    return false;
	}
	
	errorSpan.innerHTML = "";
	return true;
}

function validateImg(fileInput, errorSpan){
	if(fileInput.files.length === 0) {
	    errorSpan.innerHTML = "Seleziona un'immagine!";
	    return false;
	}

	const file = fileInput.files[0];
	const maxSize = 5 * 1024 * 1024; // 5MB

	if(file.size > maxSize) {
	    errorSpan.innerHTML = "L'immagine non deve superare i 5MB!";
	    fileInput.value = "";
	    return false;
	}
	return true;
}

function validateProdotto() {
    let valido = true;

    if (!validateForm(document.getElementById("nome"), document.getElementById("errorName"))) {
        valido = false;
    }

    if (!validateForm(document.getElementById("categoria"), document.getElementById("errorCtg"))) {
        valido = false;
    }

    if (!validateForm(document.getElementById("prezzo"), document.getElementById("errorPrezzo"))) {
        valido = false;
    }

    if (!validateForm(document.getElementById("descr"), document.getElementById("errorDescr"))) {
        valido = false;
    }

    if (!validateImg(document.getElementById("image"), document.getElementById("errorImg"))) {
        valido = false;
    }

    return valido; //Se è false, il form non viene inviato
}

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
	}
}

function salvaModifica(id){
	const bMod = document.getElementById("mod_" + id);
	const nome = document.getElementById("nome_" + id).value;
	const prezzo = document.getElementById("prezzo_" + id).value;
	const descr = document.getElementById("descr_" + id).value;
	const attivo = document.getElementById("attivo_" + id).value;
	const evidenza = document.getElementById("evidenza_" + id).value;
	const ErrorSpan = document.getElementById("error_" + id);
		
	let xhr = new XMLHttpRequest();
	const url = contextPath + "/admin/UpdateProdotti";
	
	xhr.onreadystatechange = function(){
		if(this.readyState === 4 ){
			if(this.status === 200){
				ErrorSpan.innerHTML = nome + ": Salvataggio effettuato!";
				document.getElementById("nome_" + id).disabled = true;
				document.getElementById("prezzo_" + id).disabled = true;
				document.getElementById("descr_" + id).disabled = true;
				document.getElementById("attivo_" + id).disabled = true;
				document.getElementById("evidenza_" + id).disabled = true;

				bMod.innerText = "Modifica";
				
			}
		}
		setTimeout(function(){
					ErrorSpan.innerHTML = "";
					}, 10000);
	}
	
	xhr.open("POST", url, true);
	xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
	let params = "id="+ id +"&nome=" + encodeURIComponent(nome) + "&prezzo=" + encodeURIComponent(prezzo)+ "&descr=" + encodeURIComponent(descr) 
				+ "&attivo=" + encodeURIComponent(attivo) + "&evidenza=" + encodeURIComponent(evidenza);
	xhr.send(params);
	
}

function eliminaProd(id){
	
	const ErrorSpan = document.getElementById("error_" + id);
	const riga = document.getElementById("riga_" + id);
	const nome = document.getElementById("nome_" + id).value;
	 let xhr = new XMLHttpRequest();
	 const url = contextPath + "/admin/DeleteProdotto?id="+ id;
	 
	 
	 xhr.onreadystatechange = function(){
	 	if(this.readyState === 4 ){
	 		if(this.status === 200){
				ErrorSpan.innerHTML = nome +  ": Eliminazione effettuata!";
				riga.remove();
			} else if (this.status === 403) {
				ErrorSpan.innerHTML = nome +  ": Errore: Il prodotto è presente in un ordine!";
			} else if (this.status === 404){
				ErrorSpan.innerHTML = nome + ": Errore: Il prodotto non trovato!";
			}
		}
		setTimeout(function(){
					ErrorSpan.innerHTML = "";
					}, 10000); 
	}
	 
	 xhr.open("GET", url, true);
	 xhr.send();
}