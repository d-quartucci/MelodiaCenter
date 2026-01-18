package model.dao;
import model.Utente;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UtenteDAO implements GenericDAO<Utente, Integer> {
	
	private DataSource ds;

	public UtenteDAO(DataSource ds) {
        this.ds = ds;
    }
	
	public synchronized Utente doRetrieveByLogin(String email, String password) throws SQLException{
		Connection conn = null;
		ResultSet rs = null;
		PreparedStatement ps = null;
		Utente u = null;
		String querySQL = "SELECT * FROM utente WHERE Email = ? AND Password = ?";
		try {
			conn = ds.getConnection();
			ps = conn.prepareStatement(querySQL);
			ps.setString(1, email);
			ps.setString(2, password);
			rs = ps.executeQuery();
			if(rs.next()) {
				u = new Utente();
				u.setId(rs.getInt("ID"));
				u.setNome(rs.getString("Nome"));
                u.setCognome(rs.getString("Cognome"));
                u.setEmail(rs.getString("Email"));
                u.setPassword(rs.getString("Password"));
                u.setRuolo(rs.getString("Ruolo"));
                u.setTelefono(rs.getString("Telefono"));
                u.setDataRegistrazione(rs.getTimestamp("DataRegistrazione"));
			}
		} finally {
            try { if (rs != null) rs.close(); } catch (SQLException e) {}
            try { if (ps != null) ps.close(); } catch (SQLException e) {}
            try { if (conn != null) conn.close(); } catch (SQLException e) {}
        }
		return u;
	}
	
	public synchronized Utente doRetrieveByEmail(String email) throws SQLException{
		Connection conn = null;
		ResultSet rs = null;
		PreparedStatement ps = null;
		Utente u = null;
		String querySQL = "SELECT * FROM utente WHERE Email = ?";
		try {
			conn = ds.getConnection();
			ps = conn.prepareStatement(querySQL);
			ps.setString(1, email);
			rs = ps.executeQuery();
			if(rs.next()) {
				u = new Utente();
				u.setId(rs.getInt("ID"));
				u.setNome(rs.getString("Nome"));
                u.setCognome(rs.getString("Cognome"));
                u.setEmail(rs.getString("Email"));
                u.setPassword(rs.getString("Password"));
                u.setRuolo(rs.getString("Ruolo"));
                u.setTelefono(rs.getString("Telefono"));
                u.setDataRegistrazione(rs.getTimestamp("DataRegistrazione"));
			}
		} finally {
            try { if (rs != null) rs.close(); } catch (SQLException e) {}
            try { if (ps != null) ps.close(); } catch (SQLException e) {}
            try { if (conn != null) conn.close(); } catch (SQLException e) {}
        }
		return u;
	}
	
	
	public void doSave(Utente bean) throws SQLException{
		String querySQL = "INSERT INTO Utente (Nome, Cognome, Email, Password, Ruolo, Telefono) VALUES (?, ?, ?, ?, ?, ?)";
		
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
	
	/*public List<Utente> doRetrieveAll() throws SQLException{
		
	}
	public Utente doRetrieveByKey(Integer key) throws SQLException{
		
	}
	public boolean doDelete(Integer key) throws SQLException{
		
	}*/
}
