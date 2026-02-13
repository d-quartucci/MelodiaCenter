package model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;

public class Prodotto implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private int id;
	private int categoriaId;
	private String nome;
	private String descrizione;
	private BigDecimal prezzoAttuale;
	private String imgSrc; //URL dell'immagine
	private boolean attivo;
	private boolean evidenza;
	private int quantitaVendute;
	private Timestamp dataAggiunta;
	
	public Prodotto() { };
	
	public int getId() { return id; }
	public void setId(int id) { this.id = id; }
	
	public int getCategoriaId() { return categoriaId; }
	public void setCategoriaId(int categoriaId) { this.categoriaId = categoriaId; }

	public String getNome() { return nome; }
	public void setNome(String nome) { this.nome = nome; }

	public String getDescrizione() { return descrizione; }
	public void setDescrizione(String descrizione) { this.descrizione = descrizione; }
	
	public BigDecimal getPrezzoAttuale() { return prezzoAttuale; }
	public void setPrezzoAttuale(BigDecimal prezzoAttuale) { this.prezzoAttuale = prezzoAttuale; }
	
	public String getImgSrc() { return imgSrc; }
	public void setImgSrc(String imgSrc) { this.imgSrc = imgSrc; }

	public boolean isAttivo() { return attivo; }
	public void setAttivo(boolean attivo) { this.attivo = attivo; }
	
	public boolean isEvidenza() { return evidenza; }
	public void setEvidenza(boolean evidenza) { this.evidenza = evidenza; } 
	
	public int getQuantitaVendute() { return quantitaVendute; }
	public void setQuantitaVendute(int quantitaVendute) { this.quantitaVendute = quantitaVendute; }
	
	public Timestamp getDataAggiunta() { return dataAggiunta; }
	public void setDataAggiunta(Timestamp dataAggiunta) { this.dataAggiunta = dataAggiunta; }
	
	public String toString() {
		return "Prodotto [id=" + id + ", categoriaId=" + categoriaId + ", nome=" + nome + ", descrizione=" + descrizione + ", prezzoAttuale=" + prezzoAttuale + 
				", imgSrc=" + imgSrc + ", attivo=" + attivo + ", evidenza = " + evidenza + ", quantitaVendute = " + quantitaVendute + ", dataAggiunta=" + dataAggiunta + "]";
	}

}
