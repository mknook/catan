//package view;
//
//import java.awt.Color;
//import java.awt.Dimension;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//import java.util.ArrayList;
//
//import javax.swing.BorderFactory;
//import javax.swing.BoxLayout;
//import javax.swing.JButton;
//import javax.swing.JFrame;
//import javax.swing.JLabel;
//import javax.swing.JPanel;
//import javax.swing.Timer;
//
//import database.Database;
//
//public class LobbyFrame extends JFrame implements ActionListener {
//
//	private Database d;
//	private JPanel contentPane;
//
//	public LobbyFrame() {
//		d = new Database();
//
//		this.setLocationRelativeTo(null);
//		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		this.setResizable(true);
//		this.gameNr = d.getHighestidspel()+1;
//		d.getGame(NewGameFrame.gameNr);
//		this.setTitle("Game:" + gameNr );
//
//		players = new JLabel[3];
//
//		create();
//
//		ActionListener updateInviteStatus = new ActionListener() {
//
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				updateInviteStatus();
//			}
//
//		};
//
//		Timer timer = new Timer(1000, updateInviteStatus);
//		timer.setInitialDelay(0);
//		timer.start();
//
//		this.pack();
//		this.setVisible(true);
//	}
//
//	private void create() {
//		contentPane = new JPanel();
//		lobby = new JPanel();
//		contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.X_AXIS));
//
//		ArrayList<String> playersNotInAGame = d.getPlayersNotInAGame();
//		lobby.setLayout(new BoxLayout(lobby, BoxLayout.Y_AXIS));
//		for (String player : playersNotInAGame) {
//			JButton button = new JButton(player);
//			button.addActionListener(this);
//			lobby.add(button);
//		}
//
//		contentPane.add(lobby);
//
//		player1.setBackground(Color.GREEN);
//		player1.setBorder(BorderFactory.createLineBorder(Color.BLACK));
//		player1.setOpaque(true);
//
//		lobby = new JPanel();
//		lobby.setLayout(new BoxLayout(lobby, BoxLayout.Y_AXIS));
//		lobby.setPreferredSize(new Dimension(200, 200));
//		lobby.add(player1);
//
//		contentPane.add(lobby);
//
//		this.setContentPane(contentPane);
//	}
//
//	private void updateInviteStatus() {
//		
//	}
//
//	@Override
//	public void actionPerformed(ActionEvent e) {
//		for (int i = 0; i < maxPlayers; i++) {
//			if (players[i] == null) {
//				JLabel player = new JLabel(((JButton) e.getSource()).getText());
//				d.invite(gameNr, ((JButton) e.getSource()).getText(), i + 2);
//				player.setBorder(BorderFactory.createLineBorder(Color.BLACK));
//				players[i] = player;
//				lobby.add(player);
//				validate();
//				((JButton) e.getSource()).setVisible(false);
//				return;
//			}
//		}
//	}
//
//}
