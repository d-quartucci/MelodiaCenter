const lettersOnlyMessage = "Questo campo deve contenere solo lettere";
const emailErrorMessage = "Inserire un email valida (es. nome@dom.it)";
const phoneErrorMessage = "Il numero deve contenere 10 cifre";
const emptyFieldErrorMessage = "Campo obbligatorio";
const passwordErrorMessage = "La password deve contenere almeno 6 caratteri";

const lettersOnlyRegex = /^[A-Za-zÀ-ÿ ]+$/;
const emailRegex = /^\S+@\S+\.\S+$/;
const phoneRegex = /^[0-9]{10}$/;

function validateFormElem(formElem, errorSpan, errorMessage){
	if(formElem.checkValidity()) { //Se il campo è valido, lo span che contiene l'errore deve essere vuoto.
		errorSpan.innerHTML = "";
		return true; //Non vi sono errori
	}
	//Se il campo non è valido, lo span deve contenere l'errore corretto
	if(formElem.validity.valueMissing){ //Controllo se il campo è vuoto
		errorSpan.innerHTML = emptyFieldErrorMessage;
	} else {
		errorSpan.innerHTML = errorMessage; //Se il campo non è vuoto, mostro l'altro errore
	}
	return false; //Vi sono errori
}

function validate(){
	let valid = true;
	let form = document.getElementById("registerForm");
	
	//Controllo se gli elementi del form sono validi. Nel caso in cui non lo siano, allora verrà mostrato nello span rispettivo il messaggio di errore
	if(!validateFormElem(form.nome, document.getElementById("errorNome"), lettersOnlyMessage)) valid = false; //Controllo sul cognome
	if(!validateFormElem(form.cognome, document.getElementById("errorCognome"), lettersOnlyMessage)) valid = false; //Controllo sul nome
	if (!validateFormElem(form.tel, document.getElementById("errorTel"), phoneErrorMessage)) valid = false; //Controllo sul numero di telefono
	if (!validateFormElem(form.email, document.getElementById("errorEmail"), emailErrorMessage)) valid = false; //Controllo sull'email
	if (!validateFormElem(form.password, document.getElementById("errorPassword"), passwordErrorMessage)) valid = false; //Controllo sulla password
	
	//Se uno dei controlli sugli elementi del form non è andato a buon fine, valid = false, e il submit non avviene
	return valid;
}

//Ho creato una funzione a parte per verificare che l'email non sia già registrata
function verificaEmail(){
	let submit = document.getElementById("submit");
	let email = document.getElementById("email").value; //Salvo il valore da mandare alla servlet (AJAX)
	let errorEmail = document.getElementById("errorEmail");
	
	//Se il campo non è vuoto, effettuo i controlli
	if(!validateFormElem(document.getElementById("email"), document.getElementById("errorEmail"), emailErrorMessage)){
		return; //Se il formato non è corretto, non viene fatta la chiamata AJAX
	}
		
	//Effettuo la chiamata ad AJAX per verificare che l'email sia valida
	let xhr = new XMLHttpRequest();
	let url = contextPath + "/CheckEmailServlet?email=" + encodeURIComponent(email);
	xhr.open("GET", url, true);
		
	xhr.onreadystatechange = function(){
		if(xhr.readyState === 4 && xhr.status===200){
			let esiste = xhr.responseText.trim();
			if(esiste === "true"){
				errorEmail.innerHTML = "Questa email è già registrata!"
				submit.disabled = true;
			}
			else{
				errorEmail.innerHTML = "Email valida"
				submit.disabled = false;
			}
		}
	}
	xhr.send();
}