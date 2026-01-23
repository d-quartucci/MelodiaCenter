package Utils;

public class ComposedKey <K1, K2> {

	private K1 firstKey;
	private K2 secondKey;
	
	public ComposedKey (K1 firstKey, K2 secondKey) {
		this.firstKey = firstKey;
		this.secondKey = secondKey;
	}
	
	public K1 getFirst() {return firstKey;}
	
	public K2 getSecond() {return secondKey;}
	
}
