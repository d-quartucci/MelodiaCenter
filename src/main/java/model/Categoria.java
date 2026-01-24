package model;

import java.io.Serializable;

public class Categoria implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private int id;
	private String nome;
	private String descr;

	public Categoria() {}
	
	public int getId() {return id;}
	public void setId(int id) { this.id = id;}
	
	public String getNome() {return nome;}
	public void setNome(String nome) {this.nome = nome;}
	
	public String getDescr() {return descr;}
	public void setDescr(String descr) {this.descr = descr;}
	
	public String toString() {
		return "Categoria [id=" + id + ", nome=" + nome + ", descrizione=" + descr +"]";
	}

}
