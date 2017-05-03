package model;

import java.awt.Color;

public class Road {
	// this is a dummy class to be replaced by the real road class
	// the information used to draw is stored here, NOTHING ELSE
	// to keep the board graphics functional use at least these variables and
	// methods:
	private Color color;
	private int[] firstCoordinate = new int[1];
	private int[] secondCoordinate = new int[1];
	
	public Road(char color, int[] firstCoordinate, int[] secondCoordinate) {
		this.firstCoordinate = firstCoordinate;
		this.secondCoordinate = secondCoordinate;
		switch (color) {
		case 'w':
			this.color = Color.white;
			break;
		case 'r':
			this.color = Color.red;
			break;
		case 'o':
			this.color = Color.orange;
			break;
		case 'b':
			this.color = Color.blue;
			break;
		}
	}

	public Color getColor() {
		return color;
	}

	public void setColour(char color) {
		switch (color) {
		case 'w':
			this.color = Color.white;
		case 'r':
			this.color = Color.red;
		case 'o':
			this.color = Color.orange;
		case 'b':
			this.color = Color.blue;
		}
	}

	public int[] getFirstCoordinate() {
		return firstCoordinate;
	}

	public void setFirstCoordinate(int[] firstCoordinate) {
		this.firstCoordinate = firstCoordinate;
	}

	public int[] getSecondCoordinate() {
		return secondCoordinate;
	}

	public void setSecondCoordinate(int[] secondCoordinate) {
		this.secondCoordinate = secondCoordinate;
	}
}
