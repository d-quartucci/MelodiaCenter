package model;

public class RecensioneUtente {
	private Recensione recensione;
	private Utente utente;
	
	public RecensioneUtente() {
	}
	
	public Recensione getRecensione() {
		return recensione;
	}
	public void setRecensione(Recensione recensione) {
		this.recensione = recensione;
	}
	
	public Utente getUtente() {
		return utente;
	}
	public void setUtente(Utente utente) {
		this.utente = utente;
	}
	
	public String toString() {
		return "RecensioneUtente [recensione = " + recensione + ", utente = " + utente + "]";
	}
}
