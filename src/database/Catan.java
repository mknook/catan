package database;

import view.GameFrame;
import view.InventoryPanel;

public class Catan {

	public static void main(String[] args) {

		InventoryPanel p = new InventoryPanel();
		//NewGameFrame newGame = new NewGameFrame(true);
		//LoginFrame loginMenu = new LoginFrame();
		GameFrame g = new GameFrame();
		g.add(p);
	}

}
