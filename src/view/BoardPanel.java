package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import model.City;
import model.Port;
import model.Road;
import model.Village;

public class BoardPanel extends JPanel {
	// this panel must NOT resize
	final HashMap<String, BufferedImage> images = getTileImages();

	private Location[][] board = new Location[12][12];

	// this is the drawing method, the information used by this method is stored
	// in the Location class
	// not important :P

	public void paintComponent(Graphics g) {
		final int height = getHeight();
		final int width = 5 * 172;
		// maximum values for the coordinates
		int maxX = 12;
		int maxY = 12;

		// window height and width

		// the coordinates
		int x;
		int y;

		// width and height of the hexagons
		int HEX_WIDTH = 172;
		int HEX_HEIGHT = 200;

		// width and height of the roads
		int ROAD_WIDTH = 10;
		int ROAD_HEIGHT = 100;

		// create a larger easier to read font
		Font fontje = new Font("hoi", this.getFont().getStyle(), 25);
		g.setFont(fontje);
		g.setColor(Color.red);

		// call the super to reset the canvas
		super.paintComponent(g);

		// draw the water background
		g.drawImage(images.get("waterHex"), -300, -300, width * 2, getHeight() * 2, this);

		// draw the tiles
		for (x = 0; x < maxX; x++) {
			for (y = 0; y < maxY; y++) {

				Location currentLocation = board[x][y];
				if (currentLocation.getTile() != null) {
					Tile currentTile = currentLocation.getTile();
					g.drawImage(images.get(currentTile.getType()), Location.getCanvasX(currentTile.getX(), width),
							Location.getCanvasY(currentTile.getX(), currentTile.getY(), height), null);

					// draw the tile numbers
					if (!currentTile.getType().equals("desertHex")) {
						g.setColor(Color.white);

						// draw the circle for the number to be in
						g.fillOval(Location.getCanvasX(currentTile.getX(), width) + (HEX_WIDTH / 4) + 50,
								Location.getCanvasY(currentTile.getX(), currentTile.getY(), height) + (HEX_HEIGHT / 4),
								50, 50);
						g.setColor(Color.black);
						if (currentTile.getNumber() == 8 || currentTile.getNumber() == 6) {
							g.setColor(Color.red);
						}

						// draw the number
						g.drawString(Integer.toString(currentTile.getNumber()),
								Location.getCanvasX(currentTile.getX(), width) + (HEX_WIDTH / 3) + 50,
								Location.getCanvasY(currentTile.getX(), currentTile.getY(), height) + (HEX_HEIGHT / 3)
										+ 15);
					}

					// draw struisvogel
					if (currentTile.hasBandit()) {
						AffineTransform trans = new AffineTransform();
						trans.scale(0.5, 0.5);
						g.drawImage(images.get("bandit"), Location.getCanvasX(currentTile.getX(), width) + 50,
								Location.getCanvasY(currentTile.getX(), currentTile.getY(), height) + 50, null);

					}
				}

			}
		}

		// draw the roads
		// niet jouw business btw
		// verboden te lezen
		// nee doe het niet
		// ik zei nee
		for (x = 0; x < maxX; x++) {
			for (y = 0; y < maxY; y++) {
				// draw a road to the north
				if (board[x][y].getNorth() != null) {
					Road currentRoad = board[x][y].getNorth();
					g.setColor(currentRoad.getColor());
					g.fillRect(Location.getCanvasX(x, width) + HEX_WIDTH / 2 - 4, Location.getCanvasY(x, y, height),
							ROAD_WIDTH, ROAD_HEIGHT);
				}
				if (board[x][y].getSouthWest() != null) {
					Road currentRoad = board[x][y].getSouthWest();
					Rectangle rectangle = new Rectangle();
					// rectangle.setLocation(Location.getX(x,
					// width)+HEX_WIDTH/2-4, Location.getY(x, y, height));
					rectangle.setBounds(Location.getCanvasX(x, width) + HEX_WIDTH / 4 - 4,
							Location.getCanvasY(x, y, height) + HEX_HEIGHT / 4 + 24, ROAD_WIDTH, ROAD_HEIGHT);
					AffineTransform transform = new AffineTransform();
					transform.rotate(Math.toRadians(57), rectangle.getX() + rectangle.width / 2,
							rectangle.getY() + rectangle.height / 2);
					Graphics2D g2d = (Graphics2D) g;
					g2d.setColor(currentRoad.getColor());
					Shape transformed = transform.createTransformedShape(rectangle);
					g2d.fill(transformed);

				}
				if (board[x][y].getSouthEast() != null) {
					Road currentRoad = board[x][y].getSouthEast();
					Rectangle rectangle = new Rectangle();
					// rectangle.setLocation(Location.getX(x,
					// width)+HEX_WIDTH/2-4, Location.getY(x, y, height));
					rectangle.setBounds(Location.getCanvasX(x, width) - HEX_WIDTH / 4 - 4 + HEX_WIDTH,
							Location.getCanvasY(x, y, height) + HEX_HEIGHT / 4 + 25, ROAD_WIDTH, ROAD_HEIGHT);
					AffineTransform transform = new AffineTransform();
					transform.rotate(Math.toRadians(-57), rectangle.getX() + rectangle.width / 2,
							rectangle.getY() + rectangle.height / 2);
					Graphics2D g2d = (Graphics2D) g;
					g2d.setColor(currentRoad.getColor());
					Shape transformed = transform.createTransformedShape(rectangle);
					g2d.fill(transformed);

				}
			}
		}

		// draw the buildings
		for (x = 0; x < maxX; x++) {
			for (y = 0; y < maxY; y++) {
				if (board[x][y].getBuilding() != null) {

					Village currentBuilding = board[x][y].getBuilding();
					if (currentBuilding instanceof City) {
						g.setColor(currentBuilding.getColor());
						g.fillRect(Location.getCanvasX(x, width) + HEX_WIDTH / 2 - 24,
								Location.getCanvasY(x, y, height) + 75, 50, 50);
					} else {
						g.setColor(currentBuilding.getColor());
						g.fillOval(Location.getCanvasX(x, width) + HEX_WIDTH / 2 - 24,
								Location.getCanvasY(x, y, height) + 75, 50, 50);
					}
				}
			}
		}

		// draw ports
		for (x = 0; x < maxX; x++) {
			for (y = 0; y < maxY; y++) {
				if (board[x][y].getPort() != null) {
					Port currentPort = board[x][y].getPort();
					String deal = currentPort.getDeal();
					Graphics2D g2d = (Graphics2D) g;
					AffineTransform orig = g2d.getTransform();
					g2d.setColor(Color.BLACK);
					g2d.setFont(fontje);
					String coordinates = Integer.toString(x) + "," + Integer.toString(y);
					switch (coordinates) {
					case "3,8":
						g2d.rotate(Math.toRadians(-35));
						g2d.drawString(deal, Location.getCanvasX(x, width) + 28,
								Location.getCanvasY(x, y, height) + 180);
						break;
					case "4,1":
						g2d.rotate(Math.toRadians(35));
						g2d.drawString(deal, Location.getCanvasX(x, width) + 440,
								Location.getCanvasY(x, y, height) - 170);
						break;
					case "2,2":
						g2d.drawString(deal, Location.getCanvasX(x, width) + 65,
								Location.getCanvasY(x, y, height) + 60);
						break;
					case "2,5":
						g2d.drawString(deal, Location.getCanvasX(x, width) + 65,
								Location.getCanvasY(x, y, height) + 60);
						break;
					case "6,10":
						g2d.rotate(Math.toRadians(35));
						g2d.drawString(deal, Location.getCanvasX(x, width) + 80,
								Location.getCanvasY(x, y, height) - 170);
						break;
					case "9,10":
						g2d.drawString(deal, Location.getCanvasX(x, width) + 100,
								Location.getCanvasY(x, y, height) + 60);
						break;
					case "11,9":
						g2d.drawString(deal, Location.getCanvasX(x, width) + 40,
								Location.getCanvasY(x, y, height) + 170);
						break;
					case "9,5":
						g2d.rotate(Math.toRadians(-35));
						g2d.drawString(deal, Location.getCanvasX(x, width) - 400,
								Location.getCanvasY(x, y, height) + 400);
						break;
					case "6,2":
						g2d.rotate(Math.toRadians(-35));
						g2d.drawString(deal, Location.getCanvasX(x, width) - 440,
								Location.getCanvasY(x, y, height) + 230);
						break;
					}

					g2d.setTransform(orig);

				}
			}
		}
	}

	// initialize all the available images to be used
	HashMap<String, BufferedImage> getTileImages() {
		String[] tiles = { "clayHex", "oreHex", "wheatHex", "sheepHex", "woodHex", "desertHex", "waterHex", "bandit" };
		HashMap<String, BufferedImage> tilesMap = new HashMap<String, BufferedImage>();
		for (int i = 0; i < tiles.length; i++) {
			BufferedImage image = getImage(tiles[i]);
			tilesMap.put(tiles[i], image);
		}
		return tilesMap;
	}

	// initialize the panel
	public BoardPanel() {
		int HEX_WIDTH = 172;
		int HEX_HEIGHT = 200;
		this.setPreferredSize(new Dimension((int) (HEX_WIDTH * 5), (int) (HEX_HEIGHT * 4.5)));
		this.setMinimumSize(new Dimension((int) (HEX_WIDTH * 5), (int) (HEX_HEIGHT * 4.5)));
		this.setMaximumSize(new Dimension((int) (HEX_WIDTH * 5), (int) (HEX_HEIGHT * 4.5)));
		generateBoard();

	}

	// static method to be used for getting images from the Tiles folder
	// to-do: rename to images and keep all image resources in one folder?
	// or make an option to change the folder through method parameters?
	static BufferedImage getImage(String name) {
		BufferedImage image = null;
		String path = "src/Tiles/" + name + ".png";
		try {
			image = ImageIO.read(new File(path));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return image;
	}

	// randomize the tiles their location, DONT CHANGE ANYTHING, changing a
	// variable will change the board
	// WIP method
	void generateBoard() {
		initLocations();
		ArrayList<Tile> availableTiles = generateTiles();
		int x = 4;
		int y = 8;
		int availableTilesAmount = availableTiles.size() + 1;
		for (int i = 0; i < availableTilesAmount; i++) {

			int index = (int) (Math.random() * availableTiles.size());
			Tile currentTile = availableTiles.get(index);

			currentTile.setX(x);
			currentTile.setY(y);

			// only add tiles if they are NOT in the middle, this spot is
			// reserved for the desert tile
			if (!(x == 6 && y == 6)) {
				currentTile.setNumber(getTileNumber(x, y));
				board[x][y].setTile(currentTile);

				availableTiles.remove(index);
			}

			x = x + 2;
			y++;

			if (x == 10 && y == 11) {
				x = 3;
				y = 6;
			} else if (x == 11 && y == 10) {
				x = 2;
				y = 4;
			} else if (x == 12 && y == 9) {
				x = 3;
				y = 3;
			} else if (x == 11 && y == 7) {
				x = 4;
				y = 2;
			}
		}

		// add the desert tile to the middle
		Tile desertTile = new Tile(6, 6, "desertHex");
		desertTile.setBandit(true);
		board[x][y].setTile(desertTile);
	}

	// intialize all the available tiles
	ArrayList<Tile> generateTiles() {
		ArrayList<Tile> tiles = new ArrayList<Tile>();
		for (int wheatCounter = 0; wheatCounter < 4; wheatCounter++) {
			tiles.add(new Tile(0, 0, "wheatHex"));
		}
		for (int sheepCounter = 0; sheepCounter < 4; sheepCounter++) {
			tiles.add(new Tile(0, 0, "sheepHex"));
		}
		for (int oreCounter = 0; oreCounter < 3; oreCounter++) {
			tiles.add(new Tile(0, 0, "oreHex"));
		}
		for (int clayCounter = 0; clayCounter < 3; clayCounter++) {
			tiles.add(new Tile(0, 0, "clayHex"));
		}
		for (int woodCounter = 0; woodCounter < 4; woodCounter++) {
			tiles.add(new Tile(0, 0, "woodHex"));
		}

		return tiles;
	}

	// intialize the locations to start adding objects to the board
	private void initLocations() {
		for (int x = 0; x < board.length; x++) {

			for (int y = 0; y < board.length; y++) {
				board[x][y] = new Location(x, y);
				if (x == 4 && y == 6) {

					Location newLocation = new Location(x, y);
					newLocation.setSouthWest(new Road('r'));
					newLocation.setNorth(new Road('b'));
					newLocation.setSouthEast(new Road('w'));
					newLocation.setBuilding(new Village('r'));
					board[x][y] = newLocation;
				} else if (x == 6 && y == 7) {
					Location newLocation = new Location(x, y);
					newLocation.setSouthWest(new Road('r'));
					newLocation.setNorth(new Road('b'));
					newLocation.setSouthEast(new Road('w'));
					newLocation.setBuilding(new City('r'));

					board[x][y] = newLocation;
				}
				String coordinates = Integer.toString(x) + "," + Integer.toString(y);
				Location newLocation = new Location(x, y);
				switch (coordinates) {
				case "3,8":
					newLocation.setPort(new Port("3-1"));
					break;
				case "4,1":
					newLocation.setPort(new Port("3-1"));
					break;
				case "2,2":
					newLocation.setPort(new Port("B"));
					break;
				case "2,5":
					newLocation.setPort(new Port("H"));
					break;
				case "6,10":
					newLocation.setPort(new Port("G"));
					break;
				case "9,10":
					newLocation.setPort(new Port("E"));
					break;
				case "11,9":
					newLocation.setPort(new Port("3-1"));
					break;
				case "9,5":
					newLocation.setPort(new Port("W"));
					break;
				case "6,2":
					newLocation.setPort(new Port("3-1"));
					break;
				}
				board[x][y] = newLocation;
			}
		}
	}

	// get the number to roll per coordinate
	int getTileNumber(int x, int y) {
		String coordinates = Integer.toString(x) + "," + Integer.toString(y);

		// make a string of both coordinates and set the respective numbers to
		// roll per location
		switch (coordinates) {
		case "4,8":
			return 10;
		case "6,9":
			return 2;
		case "8,10":
			return 9;
		case "3,6":
			return 12;
		case "5,7":
			return 6;
		case "7,8":
			return 4;
		case "9,9":
			return 10;
		case "2,4":
			return 9;
		case "4,5":
			return 11;
		case "8,7":
			return 3;
		case "10,8":
			return 8;
		case "3,3":
			return 8;
		case "5,4":
			return 3;
		case "7,5":
			return 4;
		case "9,6":
			return 5;
		case "4,2":
			return 5;
		case "6,3":
			return 6;
		case "8,4":
			return 11;
		default:
			return 1;
		}
	}
}
