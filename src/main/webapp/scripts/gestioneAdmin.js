function abilitaModifica(id){
	
	const emailId= document.getElementById("email_"+id);
	
	emailId.disabled = false;
	
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
				console.log(this.responseText);
				
			}else{
				console.log(this.responseText);
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

function eseguiFiltro(servletName, idCorpoTable){
	
	const inputIn = document.getElementById("dataFrom");
	const inputFin = document.getElementById("dataTo");
	const ord = document.getElementById("ordinaData").value;
		
	let dataIn = inputIn.value;
	let dataFin = inputFin.value;
		
	//se l'admin inserisce prima la data di fine periodo
	//mostriamo un messaggio e resettiamo il valore salvato direttamente sul DOM
	if(dataIn && dataFin && dataIn > dataFin){
		alert("Periodo non valido, impostare una nuova data di fine periodo");
		inputFin.value = "";
		return;
	}
		
	//nel caso in cui inserisce prima la data di inizio periodo
	//impostiamo un limite minimo in modo da non mettere intervalli irregolari
	//cosi facendo impostiamo un valore minimo direttamente sul DOM
	if(dataIn){
		inputFin.min = dataIn;
	}
		
	let url = contextPath + servletName + "?dataIn=" +encodeURIComponent(dataIn) + "&dataFin=" +encodeURIComponent(dataFin) + "&ordinaData=" +encodeURIComponent(ord);
	let xhr = new XMLHttpRequest();
			
	xhr.onreadystatechange = function (){
		if(this.readyState == 4 && this.status == 200){
			//Prendo la risposta dalla servlet e modifico il DOM con la risposta mandata
			document.getElementById(idCorpoTable).innerHTML = this.responseText;
		}
	}
	xhr.open("GET", url, true);
	xhr.send();

}









