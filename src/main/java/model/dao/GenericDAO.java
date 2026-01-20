package model.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

//Questa interfaccia è un'interfaccia generica che contiene i metodi che tutti le classi DAO devono implementare
public interface GenericDAO<E, K> {
	public void doSaveOrUpdate(E bean) throws SQLException; //Salva un oggetto sul DB o lo aggiorna se già esiste
	
	public ArrayList<E> doRetrieveAll() throws SQLException; //Restituisce una lista di oggetti presa dal DB
	
	public E doRetrieveByKey(K key) throws SQLException; //Restituisce l'elemento con chiave "key" dal DB
	
	public boolean doDeleteByKey(K key) throws SQLException; //Elimina un oggetto dal DB data una certa chiave. Restituisce true se è stato eliminato l'elemento, altrimenti false
	
	public E mapResultSetToBean(ResultSet rs) throws SQLException; //Permette di evitare ripetizioni di codice per le operazioni "retrieve"
}
