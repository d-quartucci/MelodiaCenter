package model.dao;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.sql.DataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import model.RigaOrdine;

public class RigaOrdineDAO implements GenericDAO<RigaOrdine, Integer> {
	
	private DataSource ds;
	
	public RigaOrdineDAO(DataSource ds) {
		this.ds = ds;
	}
	
	public synchronized void doSaveOrUpdate(RigaOrdine bean) throws SQLException {
		
		String queryUpdate = "UPDATE rigaordine SET OrdineID = ?, ProdottoID = ?, ProdottoNome = ?, Quantita = ?, PrezzoAcquisto = ? WHERE ID = ?";
		String querySave = "INSERT INTO rigaordine (OrdineID, ProdottoID, ProdottoNome, Quantita, PrezzoAcquisto) VALUES (?, ?, ?, ?, ?)";
		
		if(bean.getId()>0) {
			try(Connection con = ds.getConnection(); 
					PreparedStatement ps = con.prepareStatement(queryUpdate);){
				ps.setInt(1,bean.getOrdineId());
				ps.setInt(2,bean.getProdottoId());
				ps.setString(3, bean.getProdottoNome());
				ps.setInt(4,bean.getQuant());
				ps.setBigDecimal(5,bean.getPrezzoAcq());
				ps.setInt(6,bean.getId());
				ps.executeUpdate();
			}
			
		}
		else {
			try (Connection conn = ds.getConnection();
					PreparedStatement ps = conn.prepareStatement(querySave)){
				ps.setInt(1, bean.getOrdineId());
				ps.setInt(2, bean.getProdottoId());
				ps.setString(3, bean.getProdottoNome());
				ps.setInt(4, bean.getQuant());
				ps.setBigDecimal(5, bean.getPrezzoAcq());
				ps.executeUpdate();
			}
		}
	}

	
	public synchronized ArrayList<RigaOrdine> doRetrieveAll() throws SQLException {
		
		String query = "SELECT * FROM rigaordine";
		ArrayList<RigaOrdine> list = new ArrayList<>();
		
		try(Connection conn = ds.getConnection();
				PreparedStatement ps = conn.prepareStatement(query)){
				ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				RigaOrdine ro = mapResultSetToBean(rs);
				list.add(ro);
			}
		}
		
		return list;
	}

	
	public synchronized RigaOrdine doRetrieveByKey(Integer key) throws SQLException {
		
		String query = "SELECT * FROM rigaordine WHERE ID = ?";
		
		try(Connection conn = ds.getConnection();
				PreparedStatement ps = conn.prepareStatement(query);){
				ps.setInt(1,key);
				ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				return mapResultSetToBean(rs);
			}
		}
		
		return null;
	}
	
	public synchronized ArrayList<RigaOrdine> doRetrieveByOrderID(Integer id) throws SQLException {
		String query = "SELECT * FROM rigaordine WHERE OrdineID = ?";
		ArrayList<RigaOrdine> rowList = new ArrayList<RigaOrdine>();
		
		try(Connection conn = ds.getConnection();
				PreparedStatement ps = conn.prepareStatement(query);){
				ps.setInt(1, id);
				ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				RigaOrdine row = new RigaOrdine();
				row = mapResultSetToBean(rs);
				rowList.add(row);
			}
		}
		return rowList;
	}
	
	public synchronized RigaOrdine doRetrieveByProdottoID(Integer id) throws SQLException {
		String query = "SELECT * FROM rigaordine WHERE ProdottoID = ? LIMIT 1";
		
		try(Connection conn = ds.getConnection();
				PreparedStatement ps = conn.prepareStatement(query);){
				ps.setInt(1, id);
				ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				return mapResultSetToBean(rs);
				
			}
		}
		return null;
	}

	
	public synchronized boolean doDeleteByKey(Integer key) throws SQLException {
		
		String query = "DELETE FROM rigaordine WHERE ID = ?";
		int test;
		
		try(Connection conn = ds.getConnection();
				PreparedStatement ps = conn.prepareStatement(query)){
				ps.setInt(1, key);
				test = ps.executeUpdate();
			if(test == 0) {
				return false;
			}
			else return true;
		}
	}

	
	public RigaOrdine mapResultSetToBean(ResultSet rs) throws SQLException {
		
		RigaOrdine ro = new RigaOrdine();
		ro.setId(rs.getInt("ID"));
		ro.setOrdineId(rs.getInt("OrdineID"));
		ro.setProdottoId(rs.getInt("ProdottoID"));
		ro.setProdottoNome(rs.getString("ProdottoNome"));
		ro.setQuant(rs.getInt("Quantita"));
		ro.setPrezzoAcq(rs.getBigDecimal("PrezzoAcquisto"));
		
		return ro;
	}

}
