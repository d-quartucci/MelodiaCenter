package model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Date;

public class Ordine implements Serializable{
	
	private static final long serialVersionUID = 1L;

	private int id;
	private int utenteId;
	private Date data;
	private BigDecimal totale;
	private Indirizzo indSpedizione;

	public Ordine() {}
	
	public int getId() {return id;}
	public void setId(int id) { this.id = id;}
	
	public int getUtenteId() {return utenteId;}
	public void setUtenteId(int utenteId) { this.utenteId = utenteId;};
	
	public Date getData() {return data;}
	public void setData(Date data) { this.data = data;}
	
	public BigDecimal getTotale() {return totale;}
	public void setTotale(BigDecimal totale) { this.totale = totale;}
	
	public Indirizzo getIndSpedizione() {return indSpedizione;}
	public void setIndSpedizione(Indirizzo indSpedizione ) {this.indSpedizione = indSpedizione;}
	
	
	public String toString() {
		return "Ordine [id=" + id + ", utenteId=" + utenteId + ", data=" + data + 
				", totale=" + totale + "indirizzo di spedizione=" + indSpedizione + "]";
	}

}
