package view;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.Timer;
import javax.swing.text.BadLocationException;

import database.Database;

public class ChatPanel extends JPanel {
	private Database db = new Database();
	// placeholder
	String playerName = "Speler 1";
	JTextArea textArea = new JTextArea();
	JTextField inputArea = new JTextField();
	int amountOfLines = 0;
	boolean canSend = true;
	public ChatPanel() {

		// removes the margin on the top
		((FlowLayout) this.getLayout()).setVgap(0);

		textArea.setPreferredSize(new Dimension(300, 450));
		this.setMinimumSize(new Dimension(300, 200));
		textArea.setEditable(false);
		inputArea.setPreferredSize(new Dimension(300, 30));

		inputArea.addActionListener(new ActionListener() {
			// TO-DO: make this method write to database then fetch updated
			// version from database
			@Override
			public void actionPerformed(ActionEvent e) {
				if(canSend){
					canSend = false;
				db.addChatRow(playerName + ": " + inputArea.getText());
				inputArea.setText("");
				amountOfLines++;
				Timer timer = new Timer(1000, new ActionListener() {
					  @Override
					  public void actionPerformed(ActionEvent arg0) {
					    canSend = true;
					  }
					});
					timer.setRepeats(false);
					timer.start(); 
				}
			}

		});
		Timer timer = new Timer(1000, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				updateChat();
			}
		});
		timer.setInitialDelay(0);
		timer.start();
		this.setPreferredSize(new Dimension(300, 400));
		this.add(textArea);
		this.add(inputArea);
	}

	private void updateChat() {
		ResultSet rs = db.getChat();
		textArea.setText("");
		
		try {
			while (rs.next()) {

				textArea.append(rs.getString(1) + "\n");

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
