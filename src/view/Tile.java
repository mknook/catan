package view;

public class Tile {
	// this is a dummy class to be replaced by the real tile class
	// the information used to draw is stored here, NOTHING ELSE
	private int x;
	private int y;
	private String type;
	private boolean hasBandit;
	private int number;

	public Tile(int x, int y, String type) {
		this.x = x;
		this.y = y;
		this.type = type;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public String getType() {
		return type;
	}

	public void setX(int x) {
		this.x = x;
	}

	public void setY(int y) {
		this.y = y;
	}

	public void setBandit(boolean hasBandit) {
		this.hasBandit = hasBandit;
	}

	public boolean hasBandit() {
		return hasBandit;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}
	
	public char getTypeChar(){
		switch(type){
		case "oreHex": return 'E';
		case "wheatHex": return 'G';
		case "clayHex": return 'B';
		case "desertHex": return 'X';
		case "woodHex": return 'H';
		case "sheepHex": return 'W';
		default: return 'A';
		}
	}
}
