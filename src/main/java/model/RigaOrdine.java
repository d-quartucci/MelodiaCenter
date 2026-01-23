package model;

import java.io.Serializable;
import java.math.BigDecimal;


public class RigaOrdine implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private int id;
	private int ordineId;
	private int prodottoId;
	private int quant;
	private BigDecimal prezzoAcq;
	
	public RigaOrdine () {}
	
	public int getId() { return id; }
	public void setId(int id) { this.id = id; }
	
	public int getOrdineId() { return ordineId; }
	public void setOrdineId(int ordineId) { this.ordineId = ordineId; }
	
	public int getProdottoId() { return prodottoId; }
	public void setProdottoId(int prodottoId) { this.prodottoId = prodottoId; }
	
	public int getQuant() { return quant; }
	public void setQuant(int quant) { this.quant = quant; }
	
	public BigDecimal getPrezzoAcq() { return prezzoAcq; }
	public void setPrezzoAcq(BigDecimal prezzoAcq) { this.prezzoAcq = prezzoAcq; }
	
	public String toString() {
		return "RigaOrdine [ ordineId=" + ordineId + ", prodottoId=" + prodottoId 
				+ ", quantita=" + quant + ", prezzo acquisto=" + prezzoAcq + "]";
	}
}
