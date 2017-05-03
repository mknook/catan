package view;

import model.Port;
import model.Road;
import model.Village;

public class Location {
	// this is a WIP class of how information of the board will be stored.
	// the only information stored here is the informatio required to draw

	private Tile tile;
	private Village building;
	private Road north;
	private Road southWest;
	private Road southEast;
	private Port port;

	private int x;
	private int y;

	public Location(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public Tile getTile() {
		return tile;
	}

	public void setTile(Tile tile) {
		this.tile = tile;
	}

	public Village getBuilding() {
		return building;
	}

	public void setBuilding(Village building) {
		this.building = building;
	}

	public Road getNorth() {
		return north;
	}

	public void setNorth(Road north) {
		this.north = north;
	}

	public Road getSouthWest() {
		return southWest;
	}

	public void setSouthWest(Road southWest) {
		this.southWest = southWest;
	}

	public Road getSouthEast() {
		return southEast;
	}

	public Port getPort() {
		return port;
	}

	public void setPort(Port port) {
		this.port = port;
	}

	public void setSouthEast(Road southEast) {
		this.southEast = southEast;
	}

	static int getCanvasX(int locX, int width) {
		int HEX_WIDTH = 172;
		int HEX_OFFSET = 3;
		int x = (width / 2) - ((HEX_WIDTH / 2) * 7 - HEX_OFFSET * 5) + ((HEX_WIDTH / 2) * locX - (HEX_OFFSET * locX))
				+ ((locX + HEX_OFFSET) - (HEX_OFFSET * 2)) + (int) (+locX * 0.5 - 3);

		return x;
	}

	static int getCanvasY(int locX, int locY, int height) {

		int HEX_HEIGHT = 200;
		int HEX_OFFSET = 3;
		int y = ((height / 2) - (locY * (HEX_HEIGHT / 2 - HEX_OFFSET)) + (locX * HEX_HEIGHT / 4) + (180));
		return y;
	}

}
