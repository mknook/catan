package view;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.Timer;

import database.Database;

public class NewGameFrame extends JFrame implements ActionListener {

	private Database d;
	public static int gameNr;
	private JScrollPane invitables;
	private JPanel invites;

	public NewGameFrame(boolean random) {
		d = new Database();

		create();

		ActionListener updateInviteStatus = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				updateInviteStatus();
			}

		};

		Timer timer = new Timer(1000, updateInviteStatus);
		timer.setInitialDelay(0);
		timer.start();

		this.pack();
		this.setVisible(true);
	}

	private void create() {
		ArrayList<String> playersNotInAGame = d.getPlayersNotInAGame();
		final JPanel textArea = new JPanel();
        textArea.setLayout(new BoxLayout(textArea, BoxLayout.Y_AXIS));
        for(String player: playersNotInAGame){
        	textArea.add(new JLabel(player));
        }
        JScrollPane invitables = new JScrollPane(textArea,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        
        this.add(invitables);
        this.setPreferredSize(new Dimension(250,125));
        this.setVisible(true);
        this.setLocationRelativeTo(null);
	}

	private void updateInviteStatus() {
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
	}

}
