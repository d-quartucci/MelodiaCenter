
function abilitaModifica(id){
	
	    alert("cliccato " + id);
	
	
	document.getElementById("nome_" + id).disabled = false;
	document.getElementById("prezzo_" + id).disabled = false;
	document.getElementById("descr_" + id).disabled = false;
	document.getElementById("attivo_" + id).disabled = false;
	document.getElementById("evidenza_" + id).disabled = false;
	
}
