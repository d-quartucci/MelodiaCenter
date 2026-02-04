function eseguiFiltro(){
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
	
	let url = contextPath + "/AdminFilterOrdini?dataIn=" +dataIn + "&dataFin=" +dataFin + "&ord=" +ord;
	let xhr = new XMLHttpRequest();
		
	xhr.onreadystatechange = function (){
		if(this.readyState == 4 && this.status == 200){
			let risposta = JSON.parse(xhr.responseText);
			
			aggiornaTabella(risposta);
		}
	}
	
	xhr.open("GET", url, true);
	xhr.send();
}


function aggiornaTabella(risposta){
	const corpoTabella = document.getElementById("corpoTable");
	
	corpoTabella.innerHTML = "";
	
	if(!risposta || risposta.length === 0){
		corpoTabella.innerHTML = "<tr><td colspan='5' style='text-align:center; padding: 20px>Nessun ordine trovato</td></tr>";
		return;
	}
	//ciclo sulla risposta e ogni volta prendere un ordine con dati diversi
	risposta.forEach(function (ordine){
		corpoTabella.innerHTML += 
				"<tr>" +
					"<td>"+ ordine.id +"</td>"+
					"<td>"+ ordine.utenteId +"</td>"+
					"<td>"+ ordine.data +"</td>"+
					"<td>"+ ordine.totale +"€</td>"+
					"<td>"+ ordine.indSpedizione +"</td>"
			 +"</tr>";
			
	});
	
}


