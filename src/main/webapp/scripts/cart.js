let error = document.getElementById("error");
function rimuoviUno(idProdotto){
	let quantita = parseInt(document.getElementById(idProdotto + "Quantita").value);
	if(quantita > 1)
		{
			let xhr = new XMLHttpRequest();
			let url = contextPath + "/OperazioneCartServlet?id=" + idProdotto + "&act=dec";
			xhr.onreadystatechange = function(){
				if(xhr.readyState == 4){
					if(xhr.status == 200){
						document.getElementById(idProdotto + "Quantita").value = quantita - 1;
						aggiornaPrezzo(xhr.responseText);
					} else {
						error.innerHTML= "Errore: riprova più tardi!";
					}
				}
			}
			xhr.open("GET", url, true);
			xhr.send();
		}
}

function aggiungiUno(idProdotto){
	let quantita = parseInt(document.getElementById(idProdotto + "Quantita").value);
	let xhr = new XMLHttpRequest();
	let url = contextPath + "/OperazioneCartServlet?id=" + idProdotto + "&act=add";
	xhr.onreadystatechange = function(){
		if(xhr.readyState == 4){
			if(xhr.status == 200){
				document.getElementById(idProdotto + "Quantita").value = quantita + 1;
				aggiornaPrezzo(xhr.responseText);
			} else {
				error.innerHTML= "Errore: riprova più tardi!";
			}
		}
	}
	xhr.open("GET", url, true);
	xhr.send();
}

function cambiaQuantita(idProdotto){
	let quantita = parseInt(document.getElementById(idProdotto + "Quantita").value);
	if(quantita <= 0 || isNaN(quantita)){
		quantita = 1;
		document.getElementById(idProdotto + "Quantita").value = 1;
	}
	let xhr = new XMLHttpRequest();
	let url = contextPath + "/OperazioneCartServlet?q=" + quantita + "&id=" + idProdotto + "&act=mod";
	xhr.onreadystatechange = function(){
		if(xhr.readyState == 4 ){
			if(xhr.status == 200){
				aggiornaPrezzo(xhr.responseText);
			} else {
				error.innerHTML= "Errore: riprova più tardi!";
			}
		}
	}
	xhr.open("GET", url, true);
	xhr.send();
}

function rimuoviDalCarrello(idProdotto){
	let xhr = new XMLHttpRequest();
	let url = contextPath + "/OperazioneCartServlet?id=" + idProdotto + "&act=rem";
	xhr.onreadystatechange = function(){
		if(xhr.readyState == 4){
			if(xhr.status == 200){
				let riga = document.getElementById("tr-" + idProdotto); //Prendo la riga da rimuovere dalla tabella
				riga.remove();
							
				//Controlliamo se vi sono ancora righe nella tabella o se ho svuotato l'intero carrello
				//Nel caso in cui sia vuoto, cancelliamo la tabella e mostro il messaggio del carrello vuoto
				let righe = document.querySelectorAll("#tabellaCarrello tr[id^='tr-']");
				if(righe.length === 0){
					let sezione = document.getElementById("sezioneCarrello");
					
					if(sezione){
						sezione.remove();
					}
								
					let messaggioVuoto = document.getElementById("carrelloVuoto");
					if(messaggioVuoto){
						messaggioVuoto.style.display = "block";
					}
					
				} else{ //Se il carrello non è vuoto, devo ricalcolare il prezzo mostrato
					aggiornaPrezzo(xhr.responseText);
				}
			} else {
				error.innerHTML= "Errore: riprova più tardi!";
			}
		}
	}
	xhr.open("GET", url, true);
	xhr.send();
}

function aggiornaPrezzo(responseText){
	let risposta = JSON.parse(responseText);
	document.getElementById("spanPrezzoTotale").innerHTML = parseFloat(risposta.totale).toFixed(2) + "€";
}

function svuotaCarrello(){
	let xhr = new XMLHttpRequest();
	let url = contextPath + "/EmptyCartServlet";
	xhr.onreadystatechange = function(){
			if(xhr.readyState == 4){
				if(xhr.status == 200){
					let div = document.getElementById("sezioneCarrello"); //Rimuovo tutta la sezione del carrello
					if(div){
						div.remove();
					}
					
					let messaggioVuoto = document.getElementById("carrelloVuoto");
					if(messaggioVuoto){
						messaggioVuoto.style.display = "block";
					}	
				} else {
					error.innerHTML= "Errore: riprova più tardi!";
				}
			}
		}
	xhr.open("GET", url, true);
	xhr.send();
}