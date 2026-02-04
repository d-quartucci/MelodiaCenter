package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.sql.DataSource;

import model.Desidera;
import utils.DesideraKey;

public class DesideraDAO implements GenericDAO<Desidera, DesideraKey>{

	private DataSource ds;
	
	public DesideraDAO(DataSource ds) {
		this.ds = ds;
	}
	
	public synchronized void doSaveOrUpdate(Desidera bean) throws SQLException {
		
		String query1 = "UPDATE desidera SET DataAggiunta=? WHERE UtenteID=? AND ProdottoID=?";
		String query2 = "INSERT INTO desidera (UtenteID, ProdottoID, DataAggiunta) VALUES (?, ?, ?)";
		
		if(doRetrieveByKey(bean.getKey()) != null) {
		    try (Connection conn = ds.getConnection();
		    		PreparedStatement ps = conn.prepareStatement(query1)) {   
				ps.setDate(1, bean.getDataAggiunta());
				ps.setInt(2, bean.getKey().getFirst());
				ps.setInt(3, bean.getKey().getSecond());
				ps.executeUpdate();
		    }
		}
		else {
			try (Connection conn = ds.getConnection();
					PreparedStatement ps = conn.prepareStatement(query2)){
				ps.setInt(1, bean.getKey().getFirst());
				ps.setInt(2, bean.getKey().getSecond());
				ps.setDate(3, bean.getDataAggiunta());
				ps.executeUpdate();
			}
		}
		
	}

	
	public synchronized ArrayList<Desidera> doRetrieveAll() throws SQLException {
		String query = "SELECT * FROM desidera";
		ArrayList<Desidera> lista = new ArrayList<>();
		
		try(Connection conn = ds.getConnection();
				PreparedStatement ps = conn.prepareStatement(query);
				ResultSet rs = ps.executeQuery()){
			while(rs.next()) {
				Desidera d = mapResultSetToBean(rs);
				lista.add(d);
			}
		}
		return lista;
	}

	
	public synchronized Desidera doRetrieveByKey(DesideraKey key) throws SQLException {
		String query = "SELECT * FROM desidera WHERE UtenteID = ? AND ProdottoID= ?";
		
		try(Connection conn = ds.getConnection();
				PreparedStatement ps = conn.prepareStatement(query)){
			ps.setInt(1, key.getFirst());
			ps.setInt(2, key.getSecond());
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				return mapResultSetToBean(rs);
			}
		}
		return null;
	}
	
	public synchronized ArrayList<Desidera> doRetrieveByUserID(int id) throws SQLException {
		String query = "SELECT * FROM desidera WHERE UtenteID = ?";
		ArrayList<Desidera> list = new ArrayList<>();
		try(Connection conn = ds.getConnection();
				PreparedStatement ps = conn.prepareStatement(query);){
				ps.setInt(1, id);
				ResultSet rs = ps.executeQuery();
				while(rs.next()) {
					Desidera item = new Desidera();
					item = mapResultSetToBean(rs);
					list.add(item);
				}
			}
		return list;
	}

	
	public synchronized boolean doDeleteByKey(DesideraKey key) throws SQLException {
		String query = "DELETE FROM desidera WHERE UtenteID = ? AND ProdottoID = ?";
		
		int test;
		try(Connection conn = ds.getConnection();
				PreparedStatement ps = conn.prepareStatement(query)){
			ps.setInt(1, key.getFirst());
			ps.setInt(2, key.getSecond());
			test = ps.executeUpdate();
		}
		if(test == 0) {
			return false;
		}
		return true;	
	}

	
	public Desidera mapResultSetToBean(ResultSet rs) throws SQLException {
		Desidera d = new Desidera();
		DesideraKey dKey = new DesideraKey(rs.getInt("UtenteID"), rs.getInt("ProdottoID"));
		
		d.setKey(dKey);
		d.setDataAggiunta(rs.getDate("DataAggiunta"));
		
		return d;
	}

}
