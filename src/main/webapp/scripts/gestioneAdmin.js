function abilitaModifica(id){
	const sMod = document.getElementById("sav_"+id);
	
	const emailId= document.getElementById("email_"+id);
	
	emailId.disabled = false;
	sMod.disabled = false;
	
}

function applicaModifica(id){
	const emailId= document.getElementById("email_"+id);
	const uEmail = emailId.value;
	const sMod = document.getElementById("sav_"+id);
	const errorEmail = document.getElementById("errorEmail_"+id);
	
	let pattern = /^\S+@\S+\.\S+$/g;
	
	if(uEmail.match(pattern)){
		validateEmail(id);
		errorEmail.innerHTML = "";
		emailId.disabled = true;
	}else{
		if(uEmail.trim() === ""){ //Controllo se il campo è vuoto
			errorEmail.innerHTML = "Campo vuoto";
			sMod.disabled = true;
		} else {
			errorEmail.innerHTML = "Inserire un email valida" ;
			sMod.disabled = true;
		}
	}
		
}


function salvaModifica(id, campo, valore){
	
	let xhr = new XMLHttpRequest();
	const url = contextPath + "/admin/AdminUpdateUtente";
	const errorEmail = document.getElementById("errorEmail_"+id);
	
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
	const sMod = document.getElementById("sav_"+id);
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
							sMod.disabled = true;
						}
						else{
							errorEmail.innerHTML = "Email valida";
							sMod.disabled = false;
						}
					}
				}
		}
		xhr.send();
}
