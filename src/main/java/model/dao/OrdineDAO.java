package model.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.sql.DataSource;

import model.Ordine;

public class OrdineDAO implements GenericDAO <Ordine, Integer>{

	private DataSource ds;
	
	public OrdineDAO (DataSource ds) {
		this.ds = ds;
	}
	
	public synchronized void doSaveOrUpdate(Ordine bean) throws SQLException {
		
		String query1 = "UPDATE ordine SET UtenteID = ?, DataOra = ?, Totale = ?, IndirizzoSpedizione = ? WHERE ID = ?";
		String query2 = "INSERT INTO ordine (UtenteID, DataOra, Totale, IndirizzoSpedizione ) VALUES (?, ?, ?, ?)";
		
		//Update
		if(bean.getId() > 0){
			try(Connection conn = ds.getConnection();
					PreparedStatement ps = conn.prepareStatement(query1);){
				ps.setInt(1, bean.getUtenteId());
				ps.setTimestamp(2, bean.getData());
				ps.setBigDecimal(3, bean.getTotale());
				ps.setString(4, bean.getIndSpedizione());
				ps.setInt(5, bean.getId());
				ps.executeUpdate();
			}
		//Insert con recupero ID, in questo caso necessario per l'eventuale creazione di oggetti RigaOrdine
		}else {
			try(Connection conn = ds.getConnection();
					PreparedStatement ps = conn.prepareStatement(query2, PreparedStatement.RETURN_GENERATED_KEYS);){
				ps.setInt(1, bean.getUtenteId());
				ps.setTimestamp(2, bean.getData());
				ps.setBigDecimal(3, bean.getTotale());
				ps.setString(4, bean.getIndSpedizione());
				ps.executeUpdate();
				
				try(ResultSet rs = ps.getGeneratedKeys()){
					if(rs.next()) {
						int idGenerato = rs.getInt(1);
						bean.setId(idGenerato);
					}
				}
			}
		}
	}

	public synchronized ArrayList<Ordine> doRetrieveAll() throws SQLException {
		
		String query = "SELECT * FROM ordine";
		ArrayList<Ordine> lista = new ArrayList<>();
		
		try(Connection conn = ds.getConnection();
				PreparedStatement ps = conn.prepareStatement(query);){
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				Ordine ordine = mapResultSetToBean(rs);
				lista.add(ordine);
			}
		}
		return lista;
	}

	
	public synchronized Ordine doRetrieveByKey(Integer key) throws SQLException {
		
		String query = "SELECT * FROM ordine WHERE ID = ?";
		
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

	public synchronized ArrayList<Ordine>  doRetrieveByDate(Date data) throws SQLException{
		String querySQL = "SELECT * FROM ordine WHERE DATE (DataOra) = ?";
		ArrayList<Ordine> ordine = new ArrayList<>();
		
		try(Connection conn = ds.getConnection();
				PreparedStatement ps = conn.prepareStatement(querySQL)){
			ps.setDate(1, data);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				ordine.add(mapResultSetToBean(rs));
			}
		}
		return ordine;
	}
	
	public synchronized boolean doDeleteByKey(Integer key) throws SQLException {
		
		String query = "DELETE FROM ordine WHERE ID = ?";
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

	
	public Ordine mapResultSetToBean(ResultSet rs) throws SQLException {
		
		Ordine ordine = new Ordine();
		ordine.setId(rs.getInt("ID"));
		ordine.setUtenteId(rs.getInt("UtenteID"));
		ordine.setData(rs.getTimestamp("DataOra"));
		ordine.setTotale(rs.getBigDecimal("Totale"));
		ordine.setIndSpedizione(rs.getString("IndirizzoSpedizione"));
		return ordine;
		
	}

}
