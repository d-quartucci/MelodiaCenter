package model;

import java.io.Serializable;
import java.sql.Date;

import utils.DesideraKey;

public class Desidera implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private DesideraKey key;
	private Date dataAggiunta;
	
	public Desidera() {}
	
	public DesideraKey getKey() {return key;}
	public void setKey(DesideraKey key) { this.key = key;};
	
	public Date getDataAggiunta() {return dataAggiunta;}
	public void setDataAggiunta(Date dataAggiunta) { this.dataAggiunta = dataAggiunta;}
	
	public String toString() {
		return "Desidera [key=" + key + ", dataAggiunta=" + dataAggiunta + "]";
	}
	
}
