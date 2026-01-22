package model;

import java.io.Serializable;
import java.sql.Date;

public class MetodoPagamento implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private int utenteId;
	private String numCarta;
	private String provider;
	private Date dataScad;
	
	public MetodoPagamento() {}
	
	public int getUtenteId() {return utenteId;}
	public void setUtenteId(int utenteId) { this.utenteId = utenteId;};
	
	public String getNumCarta() {return numCarta;}
	public void setNumCarta(String numCarta) { this.numCarta = numCarta;}
	
	public String getProvider() {return provider;}
	public void setProvider(String provider) { this.numCarta = provider;}
	
	public String getDataScad() {return provider;}
	public void setDataScad(String provider) { this.numCarta = provider;}
	
	public String toString() {
		return "MetodoPagamento [utenteId=" + utenteId + ", numero carta=" + numCarta +
				"provider=" + provider + "data scadenza=" + dataScad + "]";
	}
	
}
