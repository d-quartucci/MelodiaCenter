package model.dao;

import java.sql.SQLException;
import java.util.List;

//Questa interfaccia è un'interfaccia generica che contiene i metodi che tutti le classi DAO devono implementare
public interface GenericDAO<E, K> {
	public void doSave(E bean) throws SQLException; //Serve a salvare un oggetto sul DB, o aggiornarlo se già esiste
	//public List<E> doRetrieveAll() throws SQLException; //Serve a prendere tutti gli elementi di un certo tipo dal DB
	//public E doRetrieveByKey(K key) throws SQLException; //Serve a prendere l'elemento con chiave "key" dal DB
	//public boolean doDelete(K key) throws SQLException; //Serve ad eliminare un oggetto dal DB data una certa chiave
}
