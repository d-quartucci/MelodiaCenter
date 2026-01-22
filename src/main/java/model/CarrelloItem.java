package model;

import java.io.Serializable;

public class CarrelloItem implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Prodotto prod = null;
	private int quantita;
	
	public CarrelloItem(Prodotto prod, int quantita) {
		this.prod = prod;
		this.quantita = quantita;
	}
	
	public Prodotto getProdotto() {return prod;}
	public void setProdotto(Prodotto prod) {this.prod = prod;}
	
	public int getQuantita() {return quantita;}
	public void setQuantita(int quantita) {this.quantita = quantita;}
	
	public String toString() {
		return "CarrelloItem [prod=" + prod + ", quantita=" + quantita + "]";
	}
	
	public boolean equals(Object object) {
		if(this == object)
			return true;
		if(object == null || getClass() != object.getClass())
			return false;
		
		CarrelloItem other = (CarrelloItem) object;
		return this.getProdotto().getId() == other.getProdotto().getId(); //Non ho bisogno di controllare tutto, se gli ID sono uguali, anche i prodotti lo saranno
	}
}
