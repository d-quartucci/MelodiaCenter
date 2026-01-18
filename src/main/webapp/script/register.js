function verificaEmail(){
	let email = document.getElementById("email").value;
	let errorLine = document.getElementById("errorLine");
	let submit = document.getElementById("submit");
	
	const regexEmail = /^\S+@\S+\.\S+$/;
	
	//Se il campo email è vuoto, disabilito il tasto per effettuare la registrazione
	if(email.length === 0){
		errorLine.innerHTML ="";
		submit.disabled = true;
		return;
	}
	
	//Se il campo non è vuoto, effettuo i controlli
	if(!regexEmail.test(email)){
			errorLine.innerHTML="Formato email non valido!"
			submit.disabled = true;
			return; //Per evitare di effettuare la chiamata AJAX
		}
		
	//Effettuo la chiamata ad AJAX per verificare che l'email sia valida
	let xhr = new XMLHttpRequest();
	let url = contextPath + "/CheckEmailServlet?email=" + encodeURIComponent(email);
	xhr.open("GET", url, true);
		
	xhr.onreadystatechange = function(){
		if(xhr.readyState === 4 && xhr.status===200){
			let esiste = xhr.responseText.trim();
			if(esiste === "true"){
				errorLine.innerHTML = "Questa email è già registrata!"
				submit.disabled = true;
			}
			else{
				errorLine.innerHTML = "Email valida."
				submit.disabled = false;
			}
		}
	}
	xhr.send();
}