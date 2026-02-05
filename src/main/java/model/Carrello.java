package model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;

public class Carrello implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private ArrayList<CarrelloItem> listaItem;
	
	public Carrello(){
		listaItem = new ArrayList<>();
	}
	
	public synchronized ArrayList<CarrelloItem> getListaItem() {
		return listaItem;
	}
	
	public synchronized void addItem(CarrelloItem newItem) {
		//Se l'oggetto non è valido, non lo aggiungo al carrello
		if(newItem == null || newItem.getQuantita() <= 0) {
			return;
		}
		//Controllo se l'oggetto da aggiungere già esiste nel carrello, in tal caso ne aumento semplicemente la quantità
		for(CarrelloItem item : listaItem) {
			if(item.getProdotto().getId() == newItem.getProdotto().getId()) {
				item.setQuantita(item.getQuantita() + newItem.getQuantita());
				return;
			}
		}
		//Altrimenti aggiungo l'item al carrello senza ulteriori problemi
		listaItem.add(newItem);
		return;
	}
	
	public synchronized void removeItem(CarrelloItem item) {
		listaItem.remove(item);
	}
	
	public synchronized void modificaQuantita(int idProd, int quantita) {
		for(CarrelloItem c : listaItem){
			if(c.getProdotto().getId() == idProd) {
				c.setQuantita(quantita);
				break;
			}
		}
	}
	
	public synchronized void decrementaQuantita(int idProd) {
		for(CarrelloItem c : listaItem){
			if(c.getProdotto().getId() == idProd && c.getQuantita() > 1) {
				c.setQuantita(c.getQuantita() - 1);
				break;
			}
		}
	}
	
	public synchronized BigDecimal getPrezzoTotale() {
		BigDecimal totale = BigDecimal.ZERO;
		for(CarrelloItem item : listaItem) {
			BigDecimal prezzo = item.getProdotto().getPrezzoAttuale();
			BigDecimal quantita = BigDecimal.valueOf(item.getQuantita());
			totale = totale.add(prezzo.multiply(quantita));
		}
		return totale;
	}
}
