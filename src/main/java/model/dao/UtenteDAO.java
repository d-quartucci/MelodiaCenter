package model.dao;
import model.Utente;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.sql.DataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UtenteDAO implements GenericDAO<Utente, Integer> {
	
	private DataSource ds;

	public UtenteDAO(DataSource ds) {
        this.ds = ds;
    }
	
	public Utente mapResultSetToBean(ResultSet rs) throws SQLException{
		Utente u = new Utente();
		u.setId(rs.getInt("ID"));
		u.setNome(rs.getString("Nome"));
		u.setCognome(rs.getString("Cognome"));
		u.setEmail(rs.getString("Email"));
		u.setPassword(rs.getString("Password"));
		u.setRuolo(rs.getString("Ruolo"));
		u.setTelefono(rs.getString("Telefono"));
		u.setDataRegistrazione(rs.getDate("DataRegistrazione"));
		return u;
	}
	
	public synchronized Utente doRetrieveByLogin(String email, String password) throws SQLException{
		String querySQL = "SELECT * FROM utente WHERE Email = ? AND Password = ?";
		
		try (Connection conn = ds.getConnection();
			PreparedStatement ps = conn.prepareStatement(querySQL)){
			
			ps.setString(1, email);
			ps.setString(2, password);
			
			try(ResultSet rs = ps.executeQuery()) {
				if(rs.next()) {
					return mapResultSetToBean(rs);
				}
			}
		} 
		return null;
	}
	
	public synchronized Utente doRetrieveByEmail(String email) throws SQLException{
		String querySQL = "SELECT * FROM utente WHERE Email = ?";
		try (Connection conn = ds.getConnection();
				PreparedStatement ps = conn.prepareStatement(querySQL)){
			
			ps.setString(1, email);
			
			try(ResultSet rs = ps.executeQuery()){
				if(rs.next()) {
					return mapResultSetToBean(rs);
				}
			}
		} 
		return null;
	}
	
	
	public synchronized void doSaveOrUpdate(Utente bean) throws SQLException{
		//Se l'Id non è 0, cioè il valore di default del bean, vuol dire che esso già esiste nel DB. Per questo facciamo UPDATE.
		if(bean.getId() > 0) {
			String querySQL = "UPDATE utente SET Nome=?, Cognome=?, Email=?, Password=?, Ruolo=?, Telefono=? WHERE ID=?";
		    try (Connection conn = ds.getConnection();
		    		PreparedStatement ps = conn.prepareStatement(querySQL)) {   
		    	ps.setString(1, bean.getNome());
				ps.setString(2, bean.getCognome());
				ps.setString(3, bean.getEmail());
				ps.setString(4, bean.getPassword());
				ps.setString(5, bean.getRuolo());
				ps.setString(6, bean.getTelefono());
				ps.setInt(7, bean.getId());
				ps.executeUpdate();
		    }
		}
		
		//Altrimenti facciamo SAVE
		else {
			String querySQL = "INSERT INTO utente (Nome, Cognome, Email, Password, Ruolo, Telefono) VALUES (?, ?, ?, ?, ?, ?)";
			try (Connection conn = ds.getConnection();
					PreparedStatement ps = conn.prepareStatement(querySQL))
			{
				ps.setString(1, bean.getNome());
				ps.setString(2, bean.getCognome());
				ps.setString(3, bean.getEmail());
				ps.setString(4, bean.getPassword());
				ps.setString(5, bean.getRuolo());
				ps.setString(6, bean.getTelefono());
				ps.executeUpdate();
			}
		}
	}
	
	public synchronized ArrayList<Utente> doRetrieveAll() throws SQLException{
		String querySQL = "SELECT * FROM utente";
		ArrayList<Utente> listaUtenti = new ArrayList<>();
		
		try(Connection conn = ds.getConnection();
				PreparedStatement ps = conn.prepareStatement(querySQL);
				ResultSet rs = ps.executeQuery()){
			while(rs.next()) {
				Utente u = mapResultSetToBean(rs);
				listaUtenti.add(u);
			}
		}
		return listaUtenti;
	}
	
	public synchronized Utente doRetrieveByKey(Integer key) throws SQLException{
		String querySQL = "SELECT * FROM utente WHERE ID = ?";
		try(Connection conn = ds.getConnection();
				PreparedStatement ps = conn.prepareStatement(querySQL)){
			ps.setInt(1, key);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				return mapResultSetToBean(rs);
			}
		}
		return null;
	}
	
	public synchronized boolean doDeleteByKey(Integer key) throws SQLException{
		String querySQL = "DELETE FROM utente WHERE ID = ?";
		int test;
		try(Connection conn = ds.getConnection();
				PreparedStatement ps = conn.prepareStatement(querySQL)){
			ps.setInt(1, key);
			test = ps.executeUpdate(); //Se l'update ha avuto successo, verrà restituito "1", in quanto è stata modificata un record del DB, altrimenti "0"
		}
		if(test == 0) {
			return false;
		}
		return true;
	}
}
