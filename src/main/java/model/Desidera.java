package model;

import java.io.Serializable;
import java.sql.Date;

public class Desidera implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private int utenteId;
	private int prodottoId;
	private Date dataAgg;
	
	public Desidera() {}
	
	public int getUtenteId() {return utenteId;}
	public void setUtenteId(int utenteId) { this.utenteId = utenteId;};
	
	public int getProdottoId() { return prodottoId; }
	public void setProdottoId(int prodottoId) { this.prodottoId = prodottoId; }
	
	public Date getDataAgg() {return dataAgg;}
	public void setDataAgg(Date dataAgg) { this.dataAgg = dataAgg;}
	
	public String toString() {
		return "Desidera [utenteId=" + utenteId + ", prodottoId=" + prodottoId + ", data aggiunta=" + dataAgg + "]";
	}
	
}
