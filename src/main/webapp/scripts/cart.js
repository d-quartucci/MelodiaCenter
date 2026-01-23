function rimuoviUno(idProdotto){
	let quantita = parseInt(document.getElementById(idProdotto + "Quantita").value);
	if(quantita > 1)
		{
			let xhr = new XMLHttpRequest();
			let url = contextPath + "/OperazioneCartServlet?q=1&id=" + idProdotto + "&act=dec";
			xhr.onreadystatechange = function(){
				if(xhr.readyState == 4 && xhr.status == 200){
					document.getElementById(idProdotto + "Quantita").value = quantita - 1;
				}
			}
			xhr.open("GET", url, true);
			xhr.send();
		}
}

function aggiungiUno(idProdotto){
	let quantita = parseInt(document.getElementById(idProdotto + "Quantita").value);
	let xhr = new XMLHttpRequest();
	let url = contextPath + "/OperazioneCartServlet?q=1&id=" + idProdotto + "&act=add";
	xhr.onreadystatechange = function(){
		if(xhr.readyState == 4 && xhr.status == 200){
			document.getElementById(idProdotto + "Quantita").value = quantita + 1;
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
	xhr.open("GET", url, true);
	xhr.send();
}