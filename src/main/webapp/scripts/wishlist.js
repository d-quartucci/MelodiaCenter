function rimuoviDallaWishlist(idProdotto){
	let xhr = new XMLHttpRequest();
	let url = contextPath + "/user/OperazioneWishlistServlet?id=" + idProdotto + "&act=remove";
	xhr.onreadystatechange = function(){
		if(xhr.readyState == 4){
			if(xhr.status == 200){
				let div = document.getElementById("wishDiv-" + idProdotto);
				if(div){
					div.remove();
				}
				//Se non ci sono più elementi nella wishlist, modifichiamo il DOM per mostrare il messaggio appropriato
				let allDivs = document.querySelectorAll("#sezioneWishlist div[id^='wishDiv-']");
				if(allDivs.length === 0){
					let sezioneWishlist = document.getElementById("sezioneWishlist");
					if(sezioneWishlist){
						sezioneWishlist.remove();
					}	
					let sezioneVuota = document.getElementById("sezioneVuota");
					if(sezioneVuota){
						sezioneVuota.style.display = "block";
					}
				}
			}
			else{
				alert(xhr.statusText);
			}	
		}
	}
	xhr.open("GET", url, true);
	xhr.send(null);
}