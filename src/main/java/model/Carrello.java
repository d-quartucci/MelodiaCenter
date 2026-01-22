package model;

import java.io.Serializable;
import java.util.ArrayList;

public class Carrello implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private ArrayList<CarrelloItem> listaItem;
	
	public Carrello(){
		listaItem = new ArrayList<>();
	}
	
	public ArrayList<CarrelloItem> getListaItem() {
		return listaItem;
	}
	
	public void addItem(CarrelloItem newItem) {
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
	
	public void removeItem(CarrelloItem item) {
		listaItem.remove(item);
	}
	
	public void modificaQuantita(int idProd, int quantita) {
		for(CarrelloItem c : listaItem){
			if(c.getProdotto().getId() == idProd) {
				c.getQuantita();
				break;
			}
		}
	}
}
