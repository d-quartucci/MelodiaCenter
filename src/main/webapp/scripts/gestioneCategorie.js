function validateForm(formElem, errorSpan){
	if(formElem.validity.valueMissing){
		errorSpan.innerHTML = "Campo vuoto";
		return false;
	}
	else if(formElem.validity.tooShort){
		errorSpan.innerHTML = "Descrizione troppo corta min20";
		return false
	}
	errorSpan.innerHTML = "";
	return true;
}


function abilitaModifica(id){
	
	const bMod = document.getElementById("mod_"+id);
	
	if(bMod.innerText == "Modifica" ){
		document.getElementById("nome_" + id).disabled = false;
		document.getElementById("descr_" + id).disabled = false;
		bMod.innerText = "Applica";
		
	}else if (bMod.innerText == "Applica" ){
		salvaModifica(id);
	}
}

function salvaModifica(id){
	const bMod = document.getElementById("mod_" + id);
	const nome = document.getElementById("nome_" + id).value;
	const descr = document.getElementById("descr_" + id).value;
	const ErrorSpan = document.getElementById("error_" + id);
		
	let xhr = new XMLHttpRequest();
	const url = contextPath + "/admin/UpdateCategorie";
	
	xhr.onreadystatechange = function(){
		if(this.readyState === 4 ){
			if(this.status === 200){
				ErrorSpan.innerHTML = nome + ": Salvataggio effettuato!";
				document.getElementById("nome_" + id).disabled = true;
				document.getElementById("descr_" + id).disabled = true;
				bMod.innerText = "Modifica";
			}	
		}
		setTimeout(function(){
					ErrorSpan.innerHTML = "";
				   }, 10000);
	}
	
	xhr.open("POST", url, true);
	xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
	let params = "id="+ id +"&nome=" + encodeURIComponent(nome) + "&descr=" + encodeURIComponent(descr);
	xhr.send(params);
	
}

function eliminaCtg(id){
	const nome = document.getElementById("nome_" + id).value;
	
	 let xhr = new XMLHttpRequest();
	 const url = contextPath + "/admin/DeleteCategoria?id="+ id;
	 
	 xhr.onreadystatechange = function(){
	 	if(this.readyState === 4 ){
	 		if(this.status === 200)
				ErrorSpan.innerHTML = nome + ": Eliminazione avvenuta con successo!";
				
			} else if (this.status === 403) {
				ErrorSpan.innerHTML = nome + "- Errore: La categoria utilizzata!";
			} else if (this.status === 404){
				ErrorSpan.innerHTML = nome + "- Errore: Categoria non trovata!";
			}
			setTimeout(function(){
						ErrorSpan.innerHTML = "";
					   }, 10000);
	 }
	 
	 xhr.open("GET", url, true);
	 xhr.send();
}