package view;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class GameFrame extends JFrame {

	public GameFrame() {
		this.getContentPane().setLayout(new BoxLayout(this.getContentPane(), BoxLayout.X_AXIS));
		JPanel left = new JPanel();
		left.setLayout(new BoxLayout(left, BoxLayout.Y_AXIS));
		left.add(new PlayersInformationPanel());
		left.add(new InventoryPanel());
		JPanel right = new JPanel();
		right.setLayout(new BoxLayout(right, BoxLayout.Y_AXIS));
		right.add(new ChatPanel());
		right.add(new ToolBar());
		this.getContentPane().add(left);
		this.getContentPane().add(new BoardPanel());
		this.getContentPane().add(right);
	
		this.setLocation(0, 0);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(true);
		this.setTitle("Game");

		this.pack();
		this.setVisible(true);
	}
}
