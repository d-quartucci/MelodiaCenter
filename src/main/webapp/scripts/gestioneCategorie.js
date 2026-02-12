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
	const url = contextPath + "/admin/UpdateCategorie";
	
	xhr.onreadystatechange = function(){
		if(this.readyState === 4 ){
			if(this.status === 200){
				ErrorSpan.innerHTML = "Salvataggio effettuato!";
				setTimeout(function(){
							ErrorSpan.innerHTML = "";
						   }, 10000);
			}	
		}
	}
	
	xhr.open("POST", url, true);
	xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
	let params = "id="+ id +"&nome=" + encodeURIComponent(nome) + "&descr=" + encodeURIComponent(descr);
	xhr.send(params);
	
}

function eliminaCtg(id){
	 let xhr = new XMLHttpRequest();
	 const url = contextPath + "/admin/DeleteCategoria?id="+ id;
	 
	 xhr.onreadystatechange = function(){
	 	if(this.readyState === 4 ){
	 		if(this.status === 200)
				ErrorSpan.innerHTML= "Eliminazione avvenuta con successo!";
				setTimeout(function(){
							ErrorSpan.innerHTML = "";
						   }, 10000);
			} else if (this.status === 403) {
				alert("Errore: La categoria utilizzata!");
				location.reload();
			} else if (this.status === 404){
				alert("Errore: Categoria non trovata!");
				location.reload();
			}
	 }
	 
	 xhr.open("GET", url, true);
	 xhr.send();
}