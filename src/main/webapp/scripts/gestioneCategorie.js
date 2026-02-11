function abilitaModifica(id){
	
	const bMod = document.getElementById("mod_"+id);
	
	if(bMod.innerText == "Modifica" ){
		
		document.getElementById("nome_" + id).disabled = false;
		document.getElementById("descr_" + id).disabled = false;

		bMod.innerText = "Applica";
		
	}else if (bMod.innerText == "Applica" ){
		
		salvaModifica(id);
		document.getElementById("nome_" + id).disabled = true;
		document.getElementById("descr_" + id).disabled = true;

		bMod.innerText = "Modifica";
	}
}

function salvaModifica(id){
	const nome = document.getElementById("nome_" + id).value;
	const descr = document.getElementById("descr_" + id).value;
	const ErrorSpan = document.getElementById("error_" + id);
		
	let xhr = new XMLHttpRequest();
	const url = contextPath + "/admin/AdminUpdateCategorieServlet";
	
	xhr.onreadystatechange = function(){
		if(this.readyState === 4 ){
			if(this.status === 200)
				ErrorSpan.innerHTML = "Salvataggio effettuato!";
		}
	}
	
	xhr.open("POST", url, true);
	xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
	let params = "id="+ id +"&nome=" + encodeURIComponent(nome) + "&descr=" + encodeURIComponent(descr);
	xhr.send(params);
	
}

function eliminaCtg(id){
	 let xhr = new XMLHttpRequest();
	 const url = contextPath + "/admin/AdminDeleteCategoria?id="+ id;
	 const ErrorSpan = document.getElementById("error_" + id);
	 
	 xhr.onreadystatechange = function(){
	 	if(this.readyState === 4 ){
	 		if(this.status === 200)
				ErrorSpan.innerHTML = "Eliminazione avvenuta con successo!";
			} else if (this.status === 403) {
				ErrorSpan.innerHTML = "Errore: La categoria utilizzata!";
			} else if (this.status === 404){
				ErrorSpan.innerHTML = "Errore: Categoria non trovata!";
			}
	 }
	 
	 xhr.open("GET", url, true);
	 xhr.send();
}