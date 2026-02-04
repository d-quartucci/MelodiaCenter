function aggiungiAlCarrello(idProdotto){
	let xhr = new XMLHttpRequest();
	let url = contextPath + "/OperazioneCartServlet?q=1&id=" + idProdotto + "&act=add";
	xhr.onreadystatechange = function(){
		if(xhr.readyState == 4){
			if(xhr.status == 200){
				document.getElementById("aggiuntoSpan").textContent = "Aggiunto al carrello!";
			}
		}
	}
	xhr.open("GET", url, true);
	xhr.send();
}