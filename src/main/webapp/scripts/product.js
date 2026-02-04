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
	let url = contextPath + "/OperazioneWishlistServlet?id=" + idProdotto + "&act=add";
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

window.onload()