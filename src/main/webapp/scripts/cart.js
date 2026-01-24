function rimuoviUno(idProdotto){
	let quantita = parseInt(document.getElementById(idProdotto + "Quantita").value);
	if(quantita > 1)
		{
			let xhr = new XMLHttpRequest();
			let url = contextPath + "/OperazioneCartServlet?id=" + idProdotto + "&act=dec";
			xhr.onreadystatechange = function(){
				if(xhr.readyState == 4 && xhr.status == 200){
					document.getElementById(idProdotto + "Quantita").value = quantita - 1;
					calcolaPrezzo();
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
		if(xhr.readyState == 4 && xhr.status == 200){
			document.getElementById(idProdotto + "Quantita").value = quantita + 1;
			calcolaPrezzo();
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
	if(xhr.readyState == 4 && xhr.status == 200){
		calcolaPrezzo();
	}
	xhr.open("GET", url, true);
	xhr.send();
}

function rimuoviDalCarrello(idProdotto){
	let xhr = new XMLHttpRequest();
	let url = contextPath + "/OperazioneCartServlet?id=" + idProdotto + "&act=rem";
	xhr.onreadystatechange = function(){
		if(xhr.readyState == 4 && xhr.status == 200){
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
				calcolaPrezzo();
			}
		}
	}
	xhr.open("GET", url, true);
	xhr.send();
}

function calcolaPrezzo(){
	let totale = 0;
	//Sto selezionando tutte le righe della tabellaCarello
	let righe = document.querySelectorAll("#tabellaCarrello tr[id^='tr-']");

	for(let i = 0;  i < righe.length; i++){
		let riga = righe[i];
		
		//Per ogni riga, prendo il valore del prezzo e della quantità
		let prezzo = parseFloat(riga.cells[1].textContent);
		let quantita = parseInt(riga.querySelector("input[type='text']").value);
		
		//Se i valori sono validi, posso aggiungere il prezzo al totale
		if(!isNaN(prezzo) && !isNaN(quantita)){
			totale = totale + (prezzo * quantita);
		}
	}
	//Modifico lo span che contiene il prezzo
	document.getElementById("spanPrezzoTotale").innerHTML = totale.toFixed(2) + "€";
}

window.onload = function() {
    calcolaPrezzo();
};