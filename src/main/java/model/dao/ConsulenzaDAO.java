package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

import javax.sql.DataSource;

import model.Consulenza;

public class ConsulenzaDAO implements GenericDAO<Consulenza, Integer>{

	private DataSource ds;

	public ConsulenzaDAO(DataSource ds) {
        this.ds = ds;
    }
	
	public synchronized void doSaveOrUpdate(Consulenza bean) throws SQLException {
		
		String queryUpdate = "UPDATE consulenza SET UtenteID = ?, MessaggioUtente = ?, RispostaAdmin = ?, DataRichiesta = ?, isAperto = ? WHERE ID = ?";
		String querySave = "INSERT INTO consulenza (UtenteID, MessaggioUtente, RispostaAdmin, DataRichiesta, isAperto) VALUES (?, ?, ?, ?, ?)";
		
		if(bean.getId() > 0){
			try(Connection conn = ds.getConnection();
					PreparedStatement ps = conn.prepareStatement(queryUpdate);){
				ps.setInt(1, bean.getUtenteId());
				ps.setString(2, bean.getMessUtente());
				ps.setString(3, bean.getRispAdmin());
				ps.setDate(4, bean.getDataRichiesta());
				ps.setBoolean(5, bean.isAperto());
				ps.setInt(6, bean.getId());
				ps.executeUpdate();
			}
		
		}else {
			try(Connection conn = ds.getConnection();
					PreparedStatement ps = conn.prepareStatement(querySave);){
				ps.setInt(1, bean.getUtenteId());
				ps.setString(2, bean.getMessUtente());
				ps.setString(3, bean.getRispAdmin());
				ps.setDate(4, bean.getDataRichiesta());
				ps.setBoolean(5, bean.isAperto());
				ps.executeUpdate();
			}
		}
	}

	public synchronized ArrayList<Consulenza> doRetrieveAll() throws SQLException {
		
		String query = "SELECT * FROM consulenza";
		ArrayList<Consulenza> lista = new ArrayList<>();
		
		try(Connection conn = ds.getConnection();
				PreparedStatement ps = conn.prepareStatement(query);){
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				Consulenza cs = mapResultSetToBean(rs);
				lista.add(cs);
			}
		}
		return lista;
	}

	public synchronized Consulenza doRetrieveByKey(Integer key) throws SQLException {
		
	String query = "SELECT * FROM consulenza WHERE ID = ?";
		
		try(Connection conn = ds.getConnection();
				PreparedStatement ps = conn.prepareStatement(query)){
			ps.setInt(1, key);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				return mapResultSetToBean(rs);
			}
		}
		return null;
		
	}
	
	public synchronized ArrayList<Consulenza> doRetrieveByUserID(int userID) throws SQLException {
		ArrayList<Consulenza> list = new ArrayList<>();
		String query = "SELECT * FROM consulenza WHERE UtenteID = ?";
		
		try(Connection conn = ds.getConnection();
				PreparedStatement ps = conn.prepareStatement(query)){
			ps.setInt(1, userID);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				 list.add(mapResultSetToBean(rs));
			}
		}
		return list;
	}

	public synchronized ArrayList<Consulenza> doRetrieveByFilter(Timestamp dataIn, Timestamp dataFin, String stato, String ord) throws SQLException{
		
		String ordineQuery = "DESC"; //Il default è decrescente
		
		if(ord.equals("menoRecenti")) {
			ordineQuery = "ASC";
		}
		String querySQL1 = "SELECT * FROM consulenza WHERE DataRichiesta >= ? AND DataRichiesta < ? AND isAperto = ? ORDER BY DataRichiesta " + ordineQuery;
		String querySQL2 = "SELECT * FROM consulenza WHERE DataRichiesta >= ? AND DataRichiesta < ? ORDER BY DataRichiesta " + ordineQuery;
		ArrayList<Consulenza> consulenze = new ArrayList<>();
		if(stato.equals("0")) {
		
			try(Connection conn = ds.getConnection();
				PreparedStatement ps = conn.prepareStatement(querySQL2)){
				ps.setTimestamp(1, dataIn);
				ps.setTimestamp(2, dataFin);
				ResultSet rs = ps.executeQuery();
				while(rs.next()) {
					consulenze.add(mapResultSetToBean(rs));
				}
			}
		}
		else {
			try(Connection conn = ds.getConnection();
					PreparedStatement ps = conn.prepareStatement(querySQL1)){
				ps.setTimestamp(1, dataIn);
				ps.setTimestamp(2, dataFin);
				int valoreStato = stato.equals("aperto") ? 1 : 0;
				ps.setInt(3, valoreStato);
				ResultSet rs = ps.executeQuery();
				while(rs.next()) {
					consulenze.add(mapResultSetToBean(rs));
				}
			}
		}
		return consulenze;
	}
	
	public synchronized boolean doDeleteByKey(Integer key) throws SQLException {
		
		String query = "DELETE FROM consulenza WHERE ID = ?";
		int test;
		
		try(Connection conn = ds.getConnection();
				PreparedStatement ps = conn.prepareStatement(query)){
			ps.setInt(1, key);
			test = ps.executeUpdate();
		}
		if(test == 0) {
			return false;
		}
		return true;
	}
	
	public Consulenza mapResultSetToBean(ResultSet rs) throws SQLException {
		
		Consulenza cs = new Consulenza();
		
		cs.setId(rs.getInt("ID"));
		cs.setUtenteId(rs.getInt("UtenteID"));
		cs.setMessUtente(rs.getString("MessaggioUtente"));
		cs.setRispAdmin(rs.getString("RispostaAdmin"));
		cs.setDataRichiesta(rs.getDate("DataRichiesta"));
		cs.setIsAperto(rs.getBoolean("isAperto"));
		
		return cs;
	}

}
