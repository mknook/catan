package view;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.text.BadLocationException;

public class ChatPanel extends JPanel {
	
	// placeholder
	String playerName = "Speler 1";
	JTextArea textArea = new JTextArea();
	JTextField inputArea = new JTextField();
	int amountOfLines = 0;
	public ChatPanel(){
		// removes the margin on the top
		((FlowLayout)this.getLayout()).setVgap(0);
		
		textArea.setPreferredSize(new Dimension(300, 450));
		this.setMinimumSize(new Dimension(300, 200));
		textArea.setEditable(false);
		inputArea.setPreferredSize(new Dimension(300, 30));
		inputArea.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e){
				textArea.append(playerName + ": " + inputArea.getText()+"\n");
				inputArea.setText("");
				amountOfLines++;
				if(amountOfLines > 28){
					int end;
					try {
						end = textArea.getLineEndOffset(0);
						textArea.replaceRange("", 0, end);
						amountOfLines = 28;
					} catch (BadLocationException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
						
				}
			}
		});
		this.setPreferredSize(new Dimension(300, 400));
		this.add(textArea);
		this.add(inputArea);
	}

}
