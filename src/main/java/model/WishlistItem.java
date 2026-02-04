package model;

public class WishlistItem {
	private Desidera desidera;
	private Prodotto prodotto;
	
	public WishlistItem(){
	}
	
	public Desidera getDesidera() {
		return desidera;
	}
	
	public void setDesidera(Desidera desidera) {
		this.desidera = desidera;
	}
	
	public Prodotto getProdotto() {
		return prodotto;
	}
	
	public void setProdotto(Prodotto prodotto) {
		this.prodotto = prodotto;
	}
}
