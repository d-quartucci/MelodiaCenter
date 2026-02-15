package model.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.sql.DataSource;

import model.Recensione;


public class RecensioneDAO implements GenericDAO <Recensione, Integer>{
	
	private DataSource ds;
	
	public RecensioneDAO (DataSource ds) {
		this.ds = ds;
	}
	
	public synchronized void doSaveOrUpdate(Recensione bean) throws SQLException {
		
		String queryUpdate= "UPDATE recensione SET UtenteID=?, ProdottoID=?, Voto=?, Testo=?, DataInserimento=? WHERE ID=?";
		String querySave= "INSERT INTO recensione (UtenteID, ProdottoID, Voto, Testo, DataInserimento) VALUES (?, ?, ?, ?, ?)";
		
		if(bean.getId() > 0) {
			try(Connection conn = ds.getConnection();
					PreparedStatement ps = conn.prepareStatement(queryUpdate)){
				ps.setInt(1, bean.getUtenteId());
				ps.setInt(2, bean.getProdottoId());
				ps.setInt(3, bean.getVoto());
				ps.setString(4, bean.getTesto());
				ps.setDate(5, bean.getDataIns());
				ps.setInt(6, bean.getId());
				ps.executeUpdate();
			}
		}
		else {
			try(Connection conn = ds.getConnection();
					PreparedStatement ps = conn.prepareStatement(querySave)){
				ps.setInt(1, bean.getUtenteId());
				ps.setInt(2, bean.getProdottoId());
				ps.setInt(3, bean.getVoto());
				ps.setString(4, bean.getTesto());
				ps.setDate(5, bean.getDataIns());
				ps.executeUpdate();
				
			}
		}
		
	}
	
	public synchronized ArrayList<Recensione> doRetrieveAll() throws SQLException {
		
		String query = "SELECT * FROM recensione" ;
		ArrayList<Recensione> lista = new ArrayList<>();
		
		try(Connection conn = ds.getConnection();
				PreparedStatement ps = conn.prepareStatement(query)){
				ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				Recensione rc = mapResultSetToBean(rs);
				lista.add(rc);
			}
		}
		return lista;
	}

	
	public synchronized Recensione doRetrieveByKey(Integer key) throws SQLException {
		
		String query = "SELECT * FROM Recensione WHERE ID = ?";
		
		try(Connection conn = ds.getConnection();
				PreparedStatement ps = conn.prepareStatement(query);){
				ps.setInt(1, key);
				ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				return mapResultSetToBean(rs);
			}	
		}
		return null;
	}

	public synchronized  Recensione doRetrieveByVoto(Integer voto) throws SQLException {
		
		String query = "SELECT * FROM Recensione WHERE Voto = ?";
		
		try(Connection conn = ds.getConnection();
				PreparedStatement ps = conn.prepareStatement(query);){
				ps.setInt(1, voto);
				ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				return mapResultSetToBean(rs);
			}	
		}
		return null;
	}
	
	public synchronized ArrayList<Recensione> doRetrieveByProdottoID(int prodId) throws SQLException {
		String query = "SELECT * FROM Recensione WHERE ProdottoID = ?";
		ArrayList<Recensione> list = null;
		try(Connection conn = ds.getConnection();
				PreparedStatement ps = conn.prepareStatement(query);){
				ps.setInt(1, prodId);
				ResultSet rs = ps.executeQuery();
				list = new ArrayList<>();
			while(rs.next()) {
				list.add(mapResultSetToBean(rs));
			}	
		}
		return list;
	}
	
	public synchronized Recensione doRetrieveByData(Date data) throws SQLException {
		
		String query = "SELECT * FROM Recensione WHERE DataInserimento = ?";
		
		try(Connection conn = ds.getConnection();
				PreparedStatement ps = conn.prepareStatement(query);){
				ps.setDate(1, data);
				ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				return mapResultSetToBean(rs);
			}	
		}
		return null;
		
	}
	
	public synchronized Recensione doRetrieveByUtenteProdotto(int UtenteID, int ProdottoID) throws SQLException{
		String query = "SELECT * FROM Recensione WHERE UtenteID = ? AND ProdottoID = ?";
		
		try(Connection conn = ds.getConnection();
				PreparedStatement ps = conn.prepareStatement(query);){
				ps.setInt(1, UtenteID);
				ps.setInt(2, ProdottoID);
				ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				return mapResultSetToBean(rs);
			}	
		}
		return null;
	}
	
	public synchronized boolean doDeleteByKey(Integer key) throws SQLException {
		
		String query = "DELETE FROM Recensione WHERE ID = ?";
		int test;
		
		try(Connection conn = ds.getConnection();
				PreparedStatement ps = conn.prepareStatement(query);){
				ps.setInt(1, key);
				test = ps.executeUpdate();
			if(test == 0) {
				return false;
			}
			else return true;
		}
		
	}
	
	public Recensione mapResultSetToBean(ResultSet rs) throws SQLException {
	
		Recensione rc = new Recensione();
		
		rc.setId(rs.getInt("ID"));
		rc.setUtenteId(rs.getInt("UtenteID"));
		rc.setProdottoId(rs.getInt("ProdottoID"));
		rc.setVoto(rs.getInt("Voto"));
		rc.setTesto(rs.getString("Testo"));
		rc.setDataIns(rs.getDate("DataInserimento"));
		
		return rc;
	}

}
