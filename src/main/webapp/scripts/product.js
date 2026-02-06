function aggiungiAlCarrello(idProdotto){
	let xhr = new XMLHttpRequest();
	let url = contextPath + "/OperazioneCartServlet?q=1&id=" + idProdotto + "&act=add";
	xhr.onreadystatechange = function(){
		if(xhr.readyState == 4 && xhr.status == 200){
			if(xhr.status == 200){
				document.getElementById("pulsanteCarrello").disabled = "true";
				document.getElementById("pulsanteCarrello").innerHTML = "Prodotto già nel carrello";
			}
			else{
				alert(xhr.statusText);
			}
		}
		
	}
	xhr.open("GET", url, true);
	xhr.send();
}

function aggiungiAllaWishlist(idProdotto){
	let xhr = new XMLHttpRequest();
	let url = contextPath + "/user/OperazioneWishlistServlet?id=" + idProdotto + "&act=add";
	xhr.onreadystatechange = function(){
		if(xhr.readyState == 4){
			if(xhr.status == 200){
				document.getElementById("pulsanteWishlist").disabled = "true";
				document.getElementById("pulsanteWishlist").innerHTML = "Già in wishlist";
			}
			else{
				alert(xhr.statusText);
			}	
		}
	}
	xhr.open("GET", url, true);
	xhr.send();
}

//Controlla che le caselle di testo contengano qualcosa per permettere l'invio di testo
function verificaContenuto(input, submit){
	let mess = document.getElementById(input);
	let sendButton = document.getElementById(submit)
	if(mess.value != ""){
		sendButton.disabled = false;
	}
	else{
		sendButton.disabled = true;
	}
}

function aggiornaSpan(){
	let span = document.getElementById("spanVoto");
	span.innerHTML = document.getElementById("voto").value;
}

window.onload()