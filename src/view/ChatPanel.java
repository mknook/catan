package view;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.Timer;
import javax.swing.text.BadLocationException;

import database.Database;

public class ChatPanel extends JPanel {
	private Database db = new Database();
	// placeholder
	private String playerName = "Speler 1";
	private JTextArea textArea = new JTextArea();
	private JTextField inputArea = new JTextField();
	private String lastSendMessage;
	private boolean canSend = true;
	private boolean updateNow = true;
	private Runnable updateChat = new Runnable() {
		public void run() {
			updateChat();
		}
	};

	public ChatPanel() {

		// removes the margin on the top
		((FlowLayout) this.getLayout()).setVgap(0);

		textArea.setPreferredSize(new Dimension(300, 450));
		this.setMinimumSize(new Dimension(300, 200));
		textArea.setEditable(false);
		inputArea.setPreferredSize(new Dimension(300, 30));

		inputArea.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (canSend) {
					lastSendMessage = inputArea.getText();
					canSend = false;
					updateNow = false;
					Runnable addRow = new Runnable() {
						public void run() {
							db.addChatRow(playerName + ": " + lastSendMessage);
						}
					};

					new Thread(addRow).start();

					textArea.append(playerName + ": " + inputArea.getText() + "\n");
					int end;
					try {
						end = textArea.getLineEndOffset(0);
						textArea.replaceRange("", 0, end);
					} catch (BadLocationException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

					inputArea.setText("");

					
					Timer timer = new Timer(1000, new ActionListener() {
						@Override
						public void actionPerformed(ActionEvent arg0) {
							canSend = true;
							updateNow = true;
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
				if(updateNow){
				updateChat();
				}
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
