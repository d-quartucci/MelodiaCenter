package model;
import java.sql.Timestamp;

public class Utente {
	private int id;
	private String nome;
	private String cognome;
	private String email;
	private String password;
	private String ruolo;
	private String telefono;
	private Timestamp dataRegistrazione;

	public Utente() { }

	public int getId() { return id; }
	public void setId(int id) { this.id = id; }

	public String getNome() { return nome; }
	public void setNome(String nome) { this.nome = nome; }

	public String getCognome() { return cognome; }
	public void setCognome(String cognome) { this.cognome = cognome; }
	
	public String getEmail() { return email; }
	public void setEmail(String email) { this.email = email; }

	public String getPassword() { return password; }
	public void setPassword(String password) { this.password = password; }

	public String getRuolo() { return ruolo; }
	public void setRuolo(String ruolo) { this.ruolo = ruolo; }

	public String getTelefono() { return telefono; }
	public void setTelefono(String telefono) { this.telefono = telefono; }

	public Timestamp getDataRegistrazione() { return dataRegistrazione; }
	public void setDataRegistrazione(Timestamp dataRegistrazione) { this.dataRegistrazione = dataRegistrazione; }
	    
	public String toString() {
		return "Utente [id=" + id + ", nome=" + nome + ", cognome=" + cognome + ", email=" + email + "password" + password + "ruolo" + ruolo + "telefono" + telefono + "dataRegistrazione" + dataRegistrazione + "]";
	}
}
