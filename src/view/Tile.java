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

	int getX() {
		return x;
	}

	int getY() {
		return y;
	}

	String getType() {
		return type;
	}

	void setX(int x) {
		this.x = x;
	}

	void setY(int y) {
		this.y = y;
	}

	void setBandit(boolean hasBandit) {
		this.hasBandit = hasBandit;
	}

	boolean hasBandit() {
		return hasBandit;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}
}
