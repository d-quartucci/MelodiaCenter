function abilitaModifica(id){
	
	const bMod = document.getElementById("mod_"+id);
	const emailInput= document.getElementById("email_"+id);
	
	if(bMod.innerText == "Modifica" ){
		emailInput.disabled = false;
		bMod.innerText = "Applica";
		
	}else if (bMod.innerText == "Applica" ){
		applicaModifica(id);
	}
	
}

function applicaModifica(id){
	const emailId= document.getElementById("email_"+id);
	const uEmail = emailId.value;
	const errorEmail = document.getElementById("error_"+id);
	const nome = document.getElementById("nome_"+id).innerText;
	const cognome = document.getElementById("cognome_"+id).innerText;
	
	let pattern = /^\S+@\S+\.\S+$/g;
	
	if(uEmail.match(pattern)){
		errorEmail.innerHTML = "";
		validateEmail(id);
	}else{
		if(uEmail.trim() === ""){ //Controllo se il campo è vuoto
			errorEmail.innerHTML =nome + " " + cognome + "- Errore: Campo vuoto!";
		} else {
			errorEmail.innerHTML =nome + " " + cognome + "- Errore: Email non valida!";

		}
		setTimeout(function(){
				errorEmail.innerHTML = "";
				}, 10000);
	}

}

function salvaModifica(id, campo, valore){
	
	const errorEmail = document.getElementById("error_"+id);
	const nome = document.getElementById("nome_"+id).innerText;
	const cognome = document.getElementById("cognome_"+id).innerText;
	
	let xhr = new XMLHttpRequest();
	const url = contextPath + "/admin/UpdateUtente";
	
	xhr.onreadystatechange = function(){
		if(this.readyState === 4 ){
			if(this.status === 200){
				errorEmail.innerHTML =nome + " " + cognome + ": Salvataggio effettuato!";
				emailInput.disabled = true;
				bMod.innerText = "Modifica";
			}else if(this.status === 400){
				errorEmail.innerHTML =nome + " " + cognome + "- Errore: Campo non valido!";
			}else if(this.status === 404){
			errorEmail.innerHTML =nome + " " + cognome +"- Errore: Utente non trovato!";	
			}
		}
		setTimeout(function(){
				errorEmail.innerHTML = "";
				}, 10000);
	}
	
	xhr.open("POST", url, true);
	xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
	let params = "id=" + id + "&campo=" + campo + "&valore=" + encodeURIComponent(valore);
	xhr.send(params);
}

function validateEmail(id){
	
	const uEmail= document.getElementById("email_"+id).value;
	const errorEmail = document.getElementById("error_"+id);
	const nome = document.getElementById("nome_"+id).innerText;
	const cognome = document.getElementById("cognome_"+id).innerText;
	
	let xhr = new XMLHttpRequest();
	let url = contextPath + "/CheckEmailServlet?email=" + encodeURIComponent(uEmail);
	xhr.open("GET", url, true);
	xhr.onreadystatechange = function(){
			if(this.readyState === 4){
				if(this.status===200){
					let esiste = this.responseText.trim();
						if(esiste === "true"){
							errorEmail.innerHTML =nome + " " + cognome +"- Errore: Email già usata!";
						}
						else{//Se email è valida e non è già usata allora la salvo
							salvaModifica(id, "email", uEmail);
						}
				}
			}
			setTimeout(function(){
					errorEmail.innerHTML = "";
					}, 10000);
	}
	xhr.send();
}