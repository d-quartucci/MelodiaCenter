package model;

import java.io.Serializable;
import java.sql.Date;

public class Consulenza implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private int id;
	private int utenteId;
	private String MessUtente;
	private String RispAdmin;
	private Date dataRichiesta;
	private boolean aperto;
	
	public Consulenza () {}
	
	public int getId() {return id;}
	public void setId(int id) { this.id = id;}
	
	public int getUtenteId() {return utenteId;}
	public void setUtenteId(int utenteId) { this.utenteId = utenteId;};
	
	public String getMessUtente() {return MessUtente;}
	public void setMessUtente(String MessUtente) { this.MessUtente = MessUtente;}
	
	public String getRispAdmin() {return RispAdmin;}
	public void setRispAdmin(String RispAdmin) { this.RispAdmin = RispAdmin;}
	
	public Date getDataRichiesta() {return dataRichiesta;}
	public void setDataRichiesta(Date dataRichiesta) { this.dataRichiesta = dataRichiesta;}
	
	public boolean isAperto() { return aperto; }
	public void setIsAperto(boolean aperto) { this.aperto = aperto; }
	
	public String toString() {
		return "Consulenza [id=" + id + ", utenteId=" + utenteId + ", RispAdmin=" + RispAdmin+ 
				", MessUtente=" + MessUtente + ", dataRichiesta=" + dataRichiesta + "]";
	}
	
}
