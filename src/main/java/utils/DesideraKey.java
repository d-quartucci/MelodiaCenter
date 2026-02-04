package utils;

public class DesideraKey implements ComposedKey<Integer, Integer> {

	private Integer idUtente;
	private Integer idProdotto;
	
	public DesideraKey(Integer idUtente, Integer idProdotto) {
		this.idUtente = idUtente;
		this.idProdotto = idProdotto;
	}
	
	public Integer getFirst() {
		return idUtente;
	}

	
	public Integer getSecond() {
		return idProdotto;
	}

}
