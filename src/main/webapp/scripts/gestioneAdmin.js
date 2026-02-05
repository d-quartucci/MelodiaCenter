function eseguiFiltro(servletName, tipo, idCorpoTabella){
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
	
	let url = contextPath + servletName + "?dataIn=" +dataIn + "&dataFin=" +dataFin + "&ord=" +ord;
	let xhr = new XMLHttpRequest();
		
	xhr.onreadystatechange = function (){
		if(this.readyState == 4 && this.status == 200){
			let risposta = JSON.parse(this.responseText);
			
			aggiornaTabella(risposta, tipo, idCorpoTabella);
		}
	}
	
	xhr.open("GET", url, true);
	xhr.send();
}


function aggiornaTabella(risposta, tipo, idCorpoTabella){
	const corpoTabella = document.getElementById(idCorpoTabella);
	
	corpoTabella.innerHTML = "";
	
	//se la risposta è falsa e c'è stato qualche errore
	//oppure c'è la risposta ed è un array ma è vuoto
	if(!risposta || risposta.length === 0){
		corpoTabella.innerHTML = "<tr><td colspan='5' style='text-align:center'>'Nessun dato trovato</td></tr>";
		return;
	}
	
	if(tipo === "ordine"){
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
	if(tipo === "utente"){
		risposta.forEach(function (utente){
			
			//Serve per far si che l'admin corrente non possa modificare i suoi dati
			if(utente.id === adminCorrente){
				corpoTabella.innerHTML += 
						"<tr>" +
							"<td>" + "(TU) "+ utente.id +"</td>"+
							"<td>" + utente.nome +"</td>"+
							"<td>" + utente.cognome +"</td>"+
							"<td>" + utente.email +"</td>"+
							"<td>" + utente.ruolo +"</td>" +
							"<td>" + utente.dataRegistrazione +"</td>"+
						"</tr>";			
			}
			else{
			corpoTabella.innerHTML += 
					"<tr>" +
						"<td>"+ utente.id +"</td>"+
						"<td>"+ utente.nome +"</td>"+
						"<td>"+ utente.cognome +"</td>"+
						//Input email disabilitato ma che può essere modificato solo da due pulsati che abilitano e applicano la modifica
						//La modifica verà salvata sul DataBase
						"<td><input type= 'email' id= 'email_"+ utente.id +"' value='"+ utente.email +"'oninput= 'verificaEmail()' pattern= '^\S+@\S+\.\S+$' disabled>"+
							"<button type=button id='mod_"+utente.id +"' onclick = \"abilitaModifica("+ utente.id +")\"> Modifica </button>"+
							"<button type=button id='sav_"+utente.id +"' onclick = \"applicaModifica("+ utente.id +")\" disabled> Applica </button>"+
						"</td>"+
						//Menu che di default ha selezionato i ruoli stabiliti per ogni utente
						//L'admin può cambiare i ruoli di ogni singolo utente e salvarlo su DataBase
						"<td><select onchange= \"salvaModifica(" + utente.id + ", 'ruolo', this.value)\">"+
								"<option value= 'USER' "+ (utente.ruolo === 'USER' ? 'selected' : '' )+"> USER </option>"+
								"<option value= 'ADMIN' "+ (utente.ruolo === 'ADMIN' ? 'selected' : '' )+"> ADMIN </option>"+ 
							"</select></td>"+
						"<td>"+ utente.dataRegistrazione +"</td>"
			 	+"</tr>";
			}
		});
	}
}
  

function abilitaModifica(id){
	const input = document.getElementById("email_"+id);
	const bMod = document.getElementById("mod_"+id);
	const bSav = document.getElementById("sav_"+id);
	
	//Abilito la modifica input email
	input.disabled = false;
	input.required = true;
	
	//Disabilito il tasto di Modifica e attivo quello di Applica
	bMod.disable = true;
	bSav.disable = false;
	
}

function applicaModifica(id){
	const input = document.getElementById("email_"+id);
	const bMod = document.getElementById("mod_"+id);
	const bSav = document.getElementById("sav_"+id);
	
	salvaModifica(id, "email", input.value)
	
	//Disabilito la modifica input email
	input.disabled = true;
	input.required = false;
	
	//Attivo il tasto di Modifica e disabilito quello di Applica
	bMod.disable = false;
	bSav.disable = true;
}

function salvaModifica(id, campo, valore){
	
	const url = contextPath + "\AdminUptadeUtente";
	let xhr = new XMLHttpRequest();
	
	xhr.onreadystatechange = function (){
			if(this.readyState == 4 && this.status == 200){
				alert("Salvataggio riuscito: "+ responseText);
			}
			else{
				eseguiFiltro("/AdminFilterUtenti", "utente", "corpoTableUtenti");
				alert("Salvataggio non riuscito: "+ responseText);
			}
		}
		
	xhr.open("POST", url, true);
	xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
	let params = "id=" + id + "&campo=" + campo + "&valore=" + encodeURIComponent(valore);
	xhr.send(params);
}





