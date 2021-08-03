package CafePos;

public class Stock {
	private int wondu,whitegrapes,strawberry,milk,lemon,cocoa,vanila,sparkling;
	
	public Stock() {
		
	}
	
	public void setWondu(int wondu) {
		this.wondu=wondu;
	}
	
	public int getWondu() {
		return wondu;
	}
	
	public void setWhitegrapes(int whitegrapes) {
		this.whitegrapes=whitegrapes;
	}
	
	public int getWhitegrapes() {
		return whitegrapes;
	}
	
	public void setStrawberry(int strawberry) {
		this.strawberry=strawberry;
	}
	
	public int getStrawberry() {
		return strawberry;
	}
	
	public void setMilk(int milk) {
		this.milk=milk;
	}
	
	public int getMilk() {
		return milk;
	}
	
	public void setLemon(int lemon) {
		this.lemon=lemon;
	}
	
	public int getLemon() {
		return lemon;
	}
	
	public void setCocoa(int cocoa) {
		this.cocoa=cocoa;
	}
	
	public int getCocoa() {
		return cocoa;
	}
	
	public void setVanila(int vanila) {
		this.vanila=vanila;
	}
	
	public int getVanila() {
		return vanila;
	}
	
	public void setSparkling(int sparkling) {
		this.sparkling=sparkling;
	}
	
	public int getSparkling() {
		return sparkling;
	}

	@Override
	public String toString() {
		return "Stock [wondu=" + wondu + ", whitegrapes=" + whitegrapes + ", strawberry=" + strawberry + ", milk="
				+ milk + ", lemon=" + lemon + ", cocoa=" + cocoa + ", vanila=" + vanila + ", sparkling=" + sparkling
				+ "]";
	}
	 	
}
