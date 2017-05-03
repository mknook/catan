package view;

import javax.swing.JFrame;
import javax.swing.JScrollBar;
import javax.swing.JTextField;

import database.Database;

public class NewGameFrame extends JFrame {

	private Database d;

	public NewGameFrame() {
		d = new Database();

		createPlayerList();

		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(true);
		this.setTitle("New game");

		this.pack();
		this.setVisible(true);
	}

	private void createPlayerList() {
		JTextField list = new JTextField();
		list.setText("hoi");
		JScrollBar thingy = new JScrollBar();
		list.add(thingy);
		this.getContentPane().add(list);
	}

}
