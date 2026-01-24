package model.dao;

import model.MetodoPagamento;
import utils.MetodoPagamentoKey;

import javax.sql.DataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class MetodoPagamentoDAO implements GenericDAO<MetodoPagamento, MetodoPagamentoKey>{

	private DataSource ds;
	
	public MetodoPagamentoDAO (DataSource ds) {
		this.ds = ds;
	}
	
	public synchronized void doSaveOrUpdate(MetodoPagamento bean) throws SQLException {
		
		String query1 = "UPDATE metodopagamento SET Provider=?, DataScadenza=? WHERE UtenteID=? AND NumeroCarta=?";
		String query2 = "INSERT INTO metodopagamento (UtenteID, NumeroCarta, Provider, DataScadenza) VALUES (?, ?, ?, ?)";
		
		MetodoPagamentoKey key = new MetodoPagamentoKey(bean.getUtenteId(), bean.getNumCarta());
		
		//Non utilizzo if(bean.getId() > 0) o varianti, perchè non garantisce l'esistenza della riga nel DB
		//Questo funziona perchè ID viene generato quando fai INSERT INTO
		if(doRetrieveByKey(key) != null) { // Utilizzo questo metodo per verificare se esiste realmente nel DB
		    try (Connection conn = ds.getConnection();
		    		PreparedStatement ps = conn.prepareStatement(query1)) {   
				ps.setString(1, bean.getProvider());
				ps.setDate(2, bean.getDataScad());
				ps.setInt(3, bean.getUtenteId());
				ps.setString(4, bean.getNumCarta());
				ps.executeUpdate();
		    }
		}
		else {
			try (Connection conn = ds.getConnection();
					PreparedStatement ps = conn.prepareStatement(query2)){
				ps.setInt(1, bean.getUtenteId());
				ps.setString(2, bean.getNumCarta());
				ps.setString(3, bean.getProvider());
				ps.setDate(4, bean.getDataScad());
				ps.executeUpdate();
			}
		}
	}

	
	public synchronized ArrayList<MetodoPagamento> doRetrieveAll() throws SQLException {
		
		String query = "SELECT * FROM metodopagamento";
		ArrayList<MetodoPagamento> lista = new ArrayList<>();
		
		try(Connection conn = ds.getConnection();
				PreparedStatement ps = conn.prepareStatement(query);
				ResultSet rs = ps.executeQuery()){
			while(rs.next()) {
				MetodoPagamento mp = mapResultSetToBean(rs);
				lista.add(mp);
			}
		}
		return lista;
	}

	
	public synchronized MetodoPagamento doRetrieveByKey(MetodoPagamentoKey key) throws SQLException {
		
		String query = "SELECT * FROM metodopagamento WHERE UtenteID = ? AND NumeroCarta = ?";
		
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


	public synchronized boolean doDeleteByKey(MetodoPagamentoKey key) throws SQLException {
		
		String query = "DELETE FROM metodopagamento WHERE UtenteID = ? AND NumeroCarta = ?";
		
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

	
	public MetodoPagamento mapResultSetToBean(ResultSet rs) throws SQLException {
		
		MetodoPagamento mp = new MetodoPagamento();
		
		mp.setUtenteId(rs.getInt("UtenteID"));
		mp.setNumCarta(rs.getString("NumeroCarta"));
		mp.setUtenteId(rs.getInt("Provider"));
		mp.setDataScad(rs.getDate("DataScadenza"));
		
		return mp;
	}

}
