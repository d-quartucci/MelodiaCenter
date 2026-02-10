function abilitaModifica(id){
	
	const bMod = document.getElementById("mod_"+id);
	
	if(bMod.innerText == "Modifica" ){
		
		document.getElementById("email_"+id).disabled = false;
		
		bMod.innerText = "Applica";
		
	}else if (bMod.innerText == "Applica" ){
		applicaModifica(id);
		document.getElementById("email_"+id).disabled = true;

		bMod.innerText = "Modifica";
	}
	
}

function applicaModifica(id){
	const emailId= document.getElementById("email_"+id);
	const uEmail = emailId.value;
	const errorEmail = document.getElementById("errorEmail_"+id);
	
	let pattern = /^\S+@\S+\.\S+$/g;
	
	if(uEmail.match(pattern)){
		errorEmail.innerHTML = "";
		validateEmail(id);
		salvaModifica(id, "email", uEmail);
		emailId.disabled = true;
	}else{
		if(uEmail.trim() === ""){ //Controllo se il campo è vuoto
			errorEmail.innerHTML = "Campo vuoto";
		} else {
			errorEmail.innerHTML = "Inserire un email valida" ;
		}
	}
		
}

function salvaModifica(id, campo, valore){
	
	let xhr = new XMLHttpRequest();
	const url = contextPath + "/admin/AdminUpdateUtenteServlet";
	
	xhr.onreadystatechange = function(){
		if(this.readyState === 4 ){
			if(this.status === 200){
				alert("Salvataggio avvenuto con successo");
				location.reload();
			}else if(this.status === 400){
				alert("Errore: Campo non valido");
			}else if(this.status === 404){
				alert("Errore: Utente non trovato");
			}
		}
	}
	
	xhr.open("POST", url, true);
	xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
	let params = "id=" + id + "&campo=" + campo + "&valore=" + encodeURIComponent(valore);
	xhr.send(params);
}

function validateEmail(id){
	
	const uEmail= document.getElementById("email_"+id).value;
	const errorEmail = document.getElementById("errorEmail_"+id);
	
	let xhr = new XMLHttpRequest();
	let url = contextPath + "/CheckEmailServlet?email=" + encodeURIComponent(uEmail);
	xhr.open("GET", url, true);
	xhr.onreadystatechange = function(){
			if(this.readyState === 4){
				if(this.status===200){
					let esiste = this.responseText.trim();
						if(esiste === "true"){
							errorEmail.innerHTML = "Email già utilizzata!";
						}
						else{
							errorEmail.innerHTML = "Email valida";
						}
					}
				}
		}
		xhr.send();
}