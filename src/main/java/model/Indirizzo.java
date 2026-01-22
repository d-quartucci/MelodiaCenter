package model;

import java.io.Serializable;

public class Indirizzo implements Serializable{
	
	private static final long serialVersionUID = 1L;

	private int utenteId;
	private String alias;
	private String via;
	private String civico;
	private String cap;
	private String citta;
	private String provincia;
	
	public Indirizzo () {}
	
	public int getUtenteId() {return utenteId;}
	public void setUtenteId(int utenteId) { this.utenteId = utenteId;};
	
	public String getAlias () {return alias;}
	public void setAlias (String alias) {this.alias = alias;}
	
	public String getVia () {return via;}
	public void setVia (String via) {this.via = via;}
	
	public String getCivico () {return civico;}
	public void setCivico (String civico) {this.civico = civico;}
	
	public String getCap () {return cap;}
	public void setCap (String cap) {this.cap = cap;}
	
	public String getCitta () {return citta;}
	public void setCitta (String citta) {this.citta = citta;}
	
	public String getProvincia () {return provincia;}
	public void setProvincia (String provincia) {this.provincia = provincia;}
	
	
	public String toString() {
		return "Indirizzo [utenteid=" + utenteId + ", alias=" + alias + ", via=" + via 
				+ ", civico=" + civico + "CAP=" + cap + "citta=" + citta + "provincia=" + provincia + "]";
	}
	
	
}
