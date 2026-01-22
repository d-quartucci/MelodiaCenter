package model;

import java.io.Serializable;
import java.sql.Date;

public class Recensione implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private int id;
	private int utenteId;
	private int prodottoId;
	private int voto;
	private String testo;
	private Date dataIns;
	
	public Recensione() {}
	
	public int getId() {return id;}
	public void setId(int id) { this.id = id;}
	
	public int getUtenteId() {return utenteId;}
	public void setUtenteId(int utenteId) { this.utenteId = utenteId;};
	
	public int getProdottoId() { return prodottoId; }
	public void setProdottoId(int prodottoId) { this.prodottoId = prodottoId; }
	
	public int getVoto() { return voto; }
	public void setVoto(int voto) { this.voto = voto; }
	
	public String getTesto() { return testo; }
	public void setTesto(String testo) { this.testo = testo; }
	
	public Date getDataIns() { return dataIns; }
	public void setDataIns(Date dataIns) { this.dataIns = dataIns; }
	
	public String toString() {
		return "Recensione [ id=" + id + ", utenteId=" + utenteId + ", prodottoId=" + prodottoId + 
				", voto=" + voto + ", testo=" + testo + ", dataIns=" + dataIns + "]";
	}

}
