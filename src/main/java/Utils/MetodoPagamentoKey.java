package Utils;

public class MetodoPagamentoKey implements ComposedKey<Integer, String> {
	
	private Integer k1;
	private String k2;
	
	public MetodoPagamentoKey(Integer k1, String k2) {
		this.k1 = k1;
		this.k2 = k2;
	}
	
	public Integer getFirst() {
		
		return k1;
	}

	
	public String getSecond() {
		
		return k2;
	}

}
