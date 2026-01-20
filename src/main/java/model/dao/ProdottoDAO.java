package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.sql.DataSource;

import model.Prodotto;

public class ProdottoDAO {
	
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
		p.setPrezzoAttuale(rs.getFloat("PrezzoAttuale"));
		p.setImgSrc(rs.getString("Immagine"));
		p.setAttivo(rs.getBoolean("IsAttivo"));
		return p;
	}
	
	
	public void doSaveOrUpdate(Prodotto bean) throws SQLException{
		//Se l'Id non è 0, cioè il valore di default del bean, vuol dire che esso già esiste nel DB. Per questo facciamo UPDATE.
		if(bean.getId() > 0) {
			String querySQL = "UPDATE utente SET Nome=?, Descrizione=?, PrezzoAttuale=?, Immagine=?, isAttivo=?, CategoriaId=? WHERE ID=?";
		    try (Connection conn = ds.getConnection();
		    		PreparedStatement ps = conn.prepareStatement(querySQL)) {   
		    	ps.setString(1, bean.getNome());
				ps.setString(2, bean.getDescrizione());
				ps.setFloat(3, bean.getPrezzoAttuale());
				ps.setString(4, bean.getImgSrc());
				ps.setBoolean(5, bean.isAttivo());
				ps.setInt(6, bean.getCategoriaId());
				ps.setInt(7, bean.getId());
				ps.executeUpdate();
		    }
		}
		
		//Altrimenti facciamo SAVE
		else {
			String querySQL = "INSERT INTO utente (Nome, Descrizione, PrezzoAttuale, Immagine, isAttivo, CategoriaId) VALUES (?, ?, ?, ?, ?, ?)";
			try (Connection conn = ds.getConnection();
					PreparedStatement ps = conn.prepareStatement(querySQL))
			{
				ps.setString(1, bean.getNome());
				ps.setString(2, bean.getDescrizione());
				ps.setFloat(3, bean.getPrezzoAttuale());
				ps.setString(4, bean.getImgSrc());
				ps.setBoolean(5, bean.isAttivo());
				ps.setInt(6, bean.getCategoriaId());
				ps.executeUpdate();
			}
		}
	}
	
	public ArrayList<Prodotto> doRetrieveAll() throws SQLException{
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
	
	public Prodotto doRetrieveByKey(Integer key) throws SQLException{
		String querySQL = "SELECT * FROM Prodotto WHERE ID = ?";
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
	
	public boolean doDeleteByKey(Integer key) throws SQLException{
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
	public ArrayList<Prodotto> doRetrieveByPartialName(String name) throws SQLException{
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
