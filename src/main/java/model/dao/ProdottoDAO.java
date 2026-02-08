package model.dao;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.sql.DataSource;

import model.Prodotto;

public class ProdottoDAO implements GenericDAO<Prodotto, Integer> {
	
	private DataSource ds;

	public ProdottoDAO(DataSource ds) {
        this.ds = ds;
    }
	
	public Prodotto mapResultSetToBean(ResultSet rs) throws SQLException{
		Prodotto p = new Prodotto();
		p.setId(rs.getInt("ID"));
		p.setCategoriaId(rs.getInt("CategoriaId"));
		p.setNome(rs.getString("Nome"));
		p.setDescrizione(rs.getString("Descrizione"));
		p.setPrezzoAttuale(rs.getBigDecimal("PrezzoAttuale"));
		p.setImgSrc(rs.getString("Immagine"));
		p.setAttivo(rs.getBoolean("IsAttivo"));
		p.setQuantitaVendute(rs.getInt("QuantitaVendute"));
		p.setEvidenza(rs.getBoolean("InEvidenza"));
		return p;
	}
	
	public synchronized ArrayList<Prodotto> doRetrieveByFilters(String r, String ord, String ctg, String max) throws SQLException{
		//L'ORDER BY non può essere parametrica, quindi lo definiamo prima in questo modo (SQL Injection)
		String ordineQuery = "ASC"; //Il default è crescente
		BigDecimal maxPrezzo = new BigDecimal(max);
		if(ord.equals("prezzoDecrescente")) {
			ordineQuery = "DESC";
		}
		
		String queryGenerica = "SELECT * FROM Prodotto WHERE IsAttivo=TRUE AND Nome LIKE ? AND PrezzoAttuale <= ? ORDER BY PrezzoAttuale " + ordineQuery;
		String querySpecifica = "SELECT * FROM Prodotto WHERE IsAttivo=TRUE AND Nome LIKE ? AND CategoriaID = ? AND PrezzoAttuale <= ? ORDER BY PrezzoAttuale " + ordineQuery;
		ArrayList<Prodotto> listaProdotti = new ArrayList<>();
		//Se è stato selezionato "tutte" nella categoria, non avrò filtro sulla categoria
		if(ctg.equals("0")) {
			try(Connection conn = ds.getConnection();
					PreparedStatement ps = conn.prepareStatement(queryGenerica)){
				ps.setString(1, "%" + r + "%");
				ps.setBigDecimal(2, maxPrezzo);
				try(ResultSet rs = ps.executeQuery()){
					while(rs.next()) {
						Prodotto p = mapResultSetToBean(rs);
						listaProdotti.add(p);
					}
				}
			} 
		}
		//Se ho un filtro sulla categoria, utilizzo la query specifica
		else {
			try(Connection conn = ds.getConnection();
					PreparedStatement ps = conn.prepareStatement(querySpecifica)){
				ps.setString(1, "%" + r + "%");
				ps.setInt(2, Integer.parseInt(ctg));
				ps.setBigDecimal(3, maxPrezzo);
				try(ResultSet rs = ps.executeQuery()){
					while(rs.next()) {
						Prodotto p = mapResultSetToBean(rs);
						listaProdotti.add(p);
					}
				}
			} 
		}
		return listaProdotti;
	}
	
	public synchronized void doSaveOrUpdate(Prodotto bean) throws SQLException{
		//Se l'Id non è 0, cioè il valore di default del bean, vuol dire che esso già esiste nel DB. Per questo facciamo UPDATE.
		if(bean.getId() > 0) {
			String querySQL = "UPDATE prodotto SET Nome=?, Descrizione=?, PrezzoAttuale=?, Immagine=?, isAttivo=?, CategoriaId=?, inEvidenza=?, QuantitaVendute = ? WHERE ID=?";
		    try (Connection conn = ds.getConnection();
		    		PreparedStatement ps = conn.prepareStatement(querySQL)) {   
		    	ps.setString(1, bean.getNome());
				ps.setString(2, bean.getDescrizione());
				ps.setBigDecimal(3, bean.getPrezzoAttuale());
				ps.setString(4, bean.getImgSrc());
				ps.setBoolean(5, bean.isAttivo());
				ps.setInt(6, bean.getCategoriaId());
				ps.setBoolean(7, bean.isEvidenza());
				ps.setInt(8, bean.getQuantitaVendute());
				ps.setInt(9, bean.getId());
				ps.executeUpdate();
		    }
		}
		
		//Altrimenti facciamo SAVE
		else {
			String querySQL = "INSERT INTO prodotto (Nome, Descrizione, PrezzoAttuale, Immagine, isAttivo, CategoriaId, inEvidenza, QuantitaVendute) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
			try (Connection conn = ds.getConnection();
					PreparedStatement ps = conn.prepareStatement(querySQL))
			{
				ps.setString(1, bean.getNome());
				ps.setString(2, bean.getDescrizione());
				ps.setBigDecimal(3, bean.getPrezzoAttuale());
				ps.setString(4, bean.getImgSrc());
				ps.setBoolean(5, bean.isAttivo());
				ps.setInt(6, bean.getCategoriaId());
				ps.setBoolean(7, bean.isEvidenza());
				ps.setInt(8, bean.getQuantitaVendute());
				ps.executeUpdate();
			}
		}
	}
	
	public synchronized ArrayList<Prodotto> doRetrieveAll() throws SQLException{
		String querySQL = "SELECT * FROM prodotto";
		ArrayList<Prodotto> listaProdotti = new ArrayList<>();
		
		try(Connection conn = ds.getConnection();
				PreparedStatement ps = conn.prepareStatement(querySQL);
				ResultSet rs = ps.executeQuery()){
			while(rs.next()) {
				Prodotto u = mapResultSetToBean(rs);
				listaProdotti.add(u);
			}
		}
		return listaProdotti;
	}
	
	public synchronized ArrayList<Prodotto> doRetrieveAllActive() throws SQLException{
		String querySQL = "SELECT * FROM prodotto WHERE IsAttivo=TRUE";
		ArrayList<Prodotto> listaProdotti = new ArrayList<>();
		
		try(Connection conn = ds.getConnection();
				PreparedStatement ps = conn.prepareStatement(querySQL);
				ResultSet rs = ps.executeQuery()){
			while(rs.next()) {
				Prodotto u = mapResultSetToBean(rs);
				listaProdotti.add(u);
			}
		}
		return listaProdotti;
	}
	
	public synchronized ArrayList<Prodotto> doRetrieveInEvidenza() throws SQLException{
		String querySQL = "SELECT * FROM prodotto WHERE IsAttivo=TRUE AND InEvidenza=TRUE";
		ArrayList<Prodotto> listaProdotti = new ArrayList<>();
		
		try(Connection conn = ds.getConnection();
				PreparedStatement ps = conn.prepareStatement(querySQL);
				ResultSet rs = ps.executeQuery()){
			while(rs.next()) {
				Prodotto u = mapResultSetToBean(rs);
				listaProdotti.add(u);
			}
		}
		return listaProdotti;
	}
	
	public synchronized ArrayList<Prodotto> doRetrieveBestSellers() throws SQLException{
		String querySQL = "SELECT * FROM prodotto WHERE IsAttivo=TRUE ORDER BY QuantitaVendute DESC LIMIT 3";
		ArrayList<Prodotto> listaProdotti = new ArrayList<>();
		
		try(Connection conn = ds.getConnection();
				PreparedStatement ps = conn.prepareStatement(querySQL);
				ResultSet rs = ps.executeQuery()){
			while(rs.next()) {
				Prodotto u = mapResultSetToBean(rs);
				listaProdotti.add(u);
			}
		}
		return listaProdotti;
	}
	
	public synchronized Prodotto doRetrieveByKey(Integer key) throws SQLException{
		String querySQL = "SELECT * FROM Prodotto WHERE ID = ? AND IsAttivo=TRUE";
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
		String querySQL = "DELETE FROM Prodotto WHERE ID = ?";
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
	
	//Utilizzo questo metodo per la ricerca tramite barra
	public synchronized ArrayList<Prodotto> doRetrieveByPartialName(String name) throws SQLException{
		String querySQL = "SELECT * FROM Prodotto WHERE Nome LIKE ? AND IsAttivo = TRUE";
		ArrayList<Prodotto> listaProdotti = new ArrayList<>();
		try(Connection conn = ds.getConnection();
				PreparedStatement ps = conn.prepareStatement(querySQL)){
			ps.setString(1, "%" + name + "%");
			try(ResultSet rs = ps.executeQuery()){
				while(rs.next()) {
					Prodotto p = mapResultSetToBean(rs);
					listaProdotti.add(p);
				}
			}
		}
		return listaProdotti;
	}
}
