
function abilitaModifica(id){
	const bMod = document.getElementById("mod_"+id);
	const sMod = document.getElementById("sav_"+id);
	const emailId= document.getElementById("email_"+id);
	
	emailId.readOnly = false;
	
}

function applicaModifica(id){
	const emailId= document.getElementById("email_"+id);
	
	    if(!emailId.checkValidity()){
	        alert("Attenzione: il formato email non è valido!");
	        return; 
	    }
		
		emailId.readOnly = true;
	
	salvaModifica(id, "email",  emailId.value);
}


function salvaModifica(id, campo, valore){
	
	let xhr = new XMLHttpRequest();
	const url = contextPath + "/admin/AdminUpdateUtente";
	
	xhr.onreadystatechange = function(){
		if(this.readyState === 4 ){
			console.log("Stato risposta: " + this.status);
			if(this.status === 200){
				alert("Salvataggio completato con successo!");
				
			}else{
				alert("Errore durante il salvataggio: " + this.responseText);
			}
		}
		
	}
	
	xhr.open("POST", url, true);
	xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
	let params = "id=" + id + "&campo=" + campo + "&valore=" + encodeURIComponent(valore);
	xhr.send(params);
}

