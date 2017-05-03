package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

import database.Database;

public class HomeFrame extends JFrame {

	private Database d;
	private JButton invites;

	public HomeFrame() {
		d = new Database();

		createInputFields();
		createButtons();
		createInviteBox();

		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		this.setTitle("Start menu");

		this.setPreferredSize(new Dimension(300, 160));
		this.pack();
		this.setVisible(true);
	}

	private void createInviteBox() {

		JPanel bottomPanel = new JPanel();
		invites = new JButton();
		ActionListener updateInvites = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				updateInvites();
			}

		};

		Timer timer = new Timer(1000, updateInvites);
		timer.setInitialDelay(0);
		timer.start();

		bottomPanel.add(invites, BorderLayout.AFTER_LAST_LINE);
		this.add(bottomPanel);
	}

	private void updateInvites() {
		if (d.getInvites() != 0) {
			invites.setText("Invites (" + d.getInvites() + ")");
			invites.setEnabled(true);
			if (invites.getActionListeners().length == 0) {
				invites.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						showInvites();
					}
				});
			}
		} else {
			invites.setText("No invites");
			invites.setEnabled(false);
		}
	}

	private void createInputFields() {
		this.getContentPane().setPreferredSize(new Dimension(150, 150));
		this.getContentPane().setLayout(new BorderLayout());

	}

	private void createButtons() {
		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
		buttonPanel.add(Box.createRigidArea(new Dimension(0, 5)));
		if (!d.playerIsInGame()) {
			JButton game = new JButton("New game");
			game.setAlignmentX(CENTER_ALIGNMENT);
			game.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					NewGameFrame g = new NewGameFrame();
				}
			});
			buttonPanel.add(game);
		} else {
			JButton game = new JButton("Continue game");
			game.setAlignmentX(CENTER_ALIGNMENT);
			buttonPanel.add(game);
		}
		JButton rank = new JButton("Ranglijst");
		rank.setAlignmentX(CENTER_ALIGNMENT);
		JButton logout = new JButton("Uitloggen");
		logout.setAlignmentX(CENTER_ALIGNMENT);

		buttonPanel.add(Box.createRigidArea(new Dimension(0, 5)));
		buttonPanel.add(rank);
		buttonPanel.add(Box.createRigidArea(new Dimension(0, 5)));
		buttonPanel.add(logout);
		this.getContentPane().add(buttonPanel, BorderLayout.NORTH);
	}

	private void showInvites() {
		JFrame invitesFrame = new JFrame();
		invitesFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		invitesFrame.getContentPane().setLayout(new BoxLayout(invitesFrame.getContentPane(), BoxLayout.Y_AXIS));
		invitesFrame.setLocationRelativeTo(this);
		ResultSet set = d.getChallengers();
		try {
			while (set.next()) {
				JPanel box = new JPanel();
				JLabel challenger = new JLabel();
				challenger.setText(set.getString("username"));
				JButton accept = new JButton("accepteer");
				accept.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						d.accept(challenger.getText());
						box.setVisible(false);
						invitesFrame.pack();
						updateInvites();
						if (d.getInvites() == 0) {
							invitesFrame.dispose();
						}
					}
				});
				JButton deny = new JButton("weiger");
				deny.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						d.deny(challenger.getText());
						box.setVisible(false);
						invitesFrame.pack();
						updateInvites();
						if (d.getInvites() == 0) {
							invitesFrame.dispose();
						}
					}
				});
				box.add(challenger);
				box.add(accept);
				box.add(deny);
				invitesFrame.add(box);
			}
		} catch (SQLException e) {
			System.out.println("Something went wrong" + e.getMessage());
		}
		invitesFrame.setVisible(true);
		invitesFrame.pack();
	}

}
