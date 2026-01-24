package utils;

public class DesideraKey implements ComposedKey<Integer, Integer> {

	private Integer k1;
	private Integer k2;
	
	public DesideraKey(Integer k1, Integer k2) {
		this.k1 = k1;
		this.k2 = k2;
	}
	
	public Integer getFirst() {
		
		return k1;
	}

	
	public Integer getSecond() {
		
		return k2;
	}

}
