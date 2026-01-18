//Per adesso la funzione controlla solo se l'email è già stata inserita in precedenza, non controlla anche la validità (da fare con espressioni regolari)
function verificaEmail(){
	let email = document.getElementById("email").value;
	let errorLine = document.getElementById("errorLine");
	let submit = document.getElementById("submit");
	
	if(email.length > 0){
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
}