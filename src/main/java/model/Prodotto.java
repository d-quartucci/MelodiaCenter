package model;

import java.io.Serializable;

public class Prodotto implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private int id;
	private int categoriaId;
	private String nome;
	private String descrizione;
	private float prezzoAttuale;
	private String imgSrc; //URL dell'immagine
	private boolean attivo;
	
	public Prodotto() { };
	
	public int getId() { return id; }
	public void setId(int id) { this.id = id; }
	
	public int getCategoriaId() { return categoriaId; }
	public void setCategoriaId(int categoriaId) { this.categoriaId = categoriaId; }

	public String getNome() { return nome; }
	public void setNome(String nome) { this.nome = nome; }

	public String getDescrizione() { return descrizione; }
	public void setDescrizione(String descrizione) { this.descrizione = descrizione; }
	
	public float getPrezzoAttuale() { return prezzoAttuale; }
	public void setPrezzoAttuale(float prezzoAttuale) { this.prezzoAttuale = prezzoAttuale; }
	
	public String getImgSrc() { return imgSrc; }
	public void setImgSrc(String imgSrc) { this.imgSrc = imgSrc; }

	public boolean isAttivo() { return attivo; }
	public void setAttivo(boolean attivo) { this.attivo = attivo; }
	
	public String toString() {
		return "Utente [id=" + id + ", categoriaId=" + categoriaId + ", nome=" + nome + ", descrizione=" + descrizione + ", prezzoAttuale=" + prezzoAttuale + ", imgSrc=" + imgSrc + ", attivo=" + attivo + "]";
	}

}
