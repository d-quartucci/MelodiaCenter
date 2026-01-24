package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.sql.DataSource;

import model.Categoria;

public class CategoriaDAO implements GenericDAO <Categoria, Integer> {

	private DataSource ds;

	public CategoriaDAO(DataSource ds) {
        this.ds = ds;
    }
	
	public synchronized void doSaveOrUpdate(Categoria bean) throws SQLException {
		
		String query1 = "UPDATE categoria SET Nome = ?, Descrizione = ? WHERE ID = ?";
		String query2 = "INSERT INTO categoria (Nome, Descrizione) VALUES (?, ?)";
		
		if(bean.getId() > 0){
			try(Connection conn = ds.getConnection();
					PreparedStatement ps = conn.prepareStatement(query1);){
				ps.setString(1, bean.getNome());
				ps.setString(2, bean.getDescr());
				ps.setInt(3, bean.getId());
				ps.executeUpdate();
			}
		
		}else {
			try(Connection conn = ds.getConnection();
					PreparedStatement ps = conn.prepareStatement(query2);){
				ps.setString(1, bean.getNome());
				ps.setString(2, bean.getDescr());
				ps.executeUpdate();
			}
		}
	}

	
	public synchronized ArrayList<Categoria> doRetrieveAll() throws SQLException {
		
		String query = "SELECT * FROM categoria";
		ArrayList<Categoria> lista = new ArrayList<>();
		
		try(Connection conn = ds.getConnection();
				PreparedStatement ps = conn.prepareStatement(query);){
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				Categoria ct = mapResultSetToBean(rs);
				lista.add(ct);
			}
		}
		return lista;
	}

	
	public synchronized Categoria doRetrieveByKey(Integer key) throws SQLException {
		
	String query = "SELECT * FROM categoria WHERE ID = ?";
		
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

	public synchronized boolean doDeleteByKey(Integer key) throws SQLException {
		
		String query = "DELETE FROM categoria WHERE ID = ?";
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

	
	public Categoria mapResultSetToBean(ResultSet rs) throws SQLException {
	Categoria ct = new Categoria();
		
		ct.setId(rs.getInt("ID"));
		ct.setNome(rs.getString("Nome"));
		ct.setDescr(rs.getString("Descrizione"));
		
		return ct;
		
	}
}
		
	
	
