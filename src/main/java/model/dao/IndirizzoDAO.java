package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.sql.DataSource;

import model.Indirizzo;
import utils.IndirizzoKey;

public class IndirizzoDAO implements GenericDAO<Indirizzo, IndirizzoKey> {

	private DataSource ds;
	
	public IndirizzoDAO(DataSource ds) {
		this.ds = ds;
	}
	
	public synchronized void doSaveOrUpdate(Indirizzo bean) throws SQLException {
		String query1 = "UPDATE indirizzo SET Via=?, Civico=?, CAP=?, Citta=?, Provincia=? WHERE UtenteID=? AND Alias=?";
		String query2 = "INSERT INTO indirizzo (UtenteID, Alias, Via, Civico, CAP, Citta, Provincia) VALUES (?, ?, ?, ?, ?, ?, ?)";
		
		IndirizzoKey key = new IndirizzoKey(bean.getUtenteId(), bean.getAlias());
		
		if(doRetrieveByKey(key) != null) {
		    try (Connection conn = ds.getConnection();
		    		PreparedStatement ps = conn.prepareStatement(query1)) {   
				ps.setString(1, bean.getVia());
				ps.setString(2, bean.getCivico());
				ps.setString(3, bean.getCap());
				ps.setString(4, bean.getCitta());
				ps.setString(5, bean.getProvincia());
				ps.executeUpdate();
		    }
		}
		else {
			try (Connection conn = ds.getConnection();
					PreparedStatement ps = conn.prepareStatement(query2)){
				ps.setInt(1, bean.getUtenteId());
				ps.setString(2, bean.getAlias());
				ps.setString(3, bean.getVia());
				ps.setString(4, bean.getCivico());
				ps.setString(3, bean.getCap());
				ps.setString(4, bean.getCitta());
				ps.setString(5, bean.getProvincia());
				ps.executeUpdate();
			}
		}
	}

	
	public synchronized ArrayList<Indirizzo> doRetrieveAll() throws SQLException {
		String query = "SELECT * FROM indirizzo";
		ArrayList<Indirizzo> lista = new ArrayList<>();
		
		try(Connection conn = ds.getConnection();
				PreparedStatement ps = conn.prepareStatement(query);
				ResultSet rs = ps.executeQuery()){
			while(rs.next()) {
				Indirizzo ind = mapResultSetToBean(rs);
				lista.add(ind);
			}
		}
		return lista;
	}

	
	public synchronized Indirizzo doRetrieveByKey(IndirizzoKey key) throws SQLException {
		String query = "SELECT * FROM indirizzo WHERE UtenteID = ? AND Alias = ?";
		
		try(Connection conn = ds.getConnection();
				PreparedStatement ps = conn.prepareStatement(query)){
			ps.setInt(1, key.getFirst());
			ps.setString(2, key.getSecond());
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				return mapResultSetToBean(rs);
			}
		}
		return null;
	}

	
	public synchronized boolean doDeleteByKey(IndirizzoKey key) throws SQLException {
		
		String query = "DELETE FROM indirizzo WHERE UtenteID = ? AND Alias = ?";
		int test;
		
		try(Connection conn = ds.getConnection();
				PreparedStatement ps = conn.prepareStatement(query)){
			ps.setInt(1, key.getFirst());
			ps.setString(2, key.getSecond());
			test = ps.executeUpdate();
		}
		if(test == 0) {
			return false;
		}
		return true;
	}

	
	public Indirizzo mapResultSetToBean(ResultSet rs) throws SQLException {
		
		Indirizzo ind = new Indirizzo();
		
		ind.setUtenteId(rs.getInt("UtenteId"));
		ind.setAlias(rs.getString("Alias"));
		ind.setVia(rs.getString("Via"));
		ind.setCivico(rs.getString("Civico"));
		ind.setCap(rs.getString("CAP"));
		ind.setProvincia(rs.getString("Provincia"));
		
		return ind;
	}

}
