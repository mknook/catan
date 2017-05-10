package view;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JRadioButton;

@SuppressWarnings("serial")
public class TradeView extends JFrame {

	private JRadioButton players;
	private JRadioButton bank;
	//de meer havens je bezit de meer havens er op je menu bij komen
	
	private ButtonGroup group;
	
	private Box radio;
	private Box buttons;
	
	private JButton accept;
	private JButton cancel;
	
	JLabel title;
	
	private BankTrade bankTrade;
	private PlayerTrade playerTrade;

	
	public TradeView() {
		
		radio = new Box(BoxLayout.PAGE_AXIS);
		buttons = new Box(BoxLayout.LINE_AXIS);
		
		this.setLayout(new BorderLayout());
		createView();
		this.pack();
		this.setVisible(true);
	}
	private void createView(){
		players = new JRadioButton("Spelers");//als je hier op klikt kies je ervoor om een bod te sturen naar alle spelers
		bank = new JRadioButton("Bank");//als je hier op kilkt kies je ervoor om met de bank te ruilen 4:1 ratio dus
		group = new ButtonGroup();
		title = new JLabel("Met wie wil je ruilen?");
		
		group.add(players);
		group.add(bank);
		
		radio.add(players);
		radio.add(bank);
		
		accept = new JButton("Ok");
		cancel = new JButton("Annuleren");
		
		buttons.add(accept);
		buttons.add(cancel);
		
		accept.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {

				if(players.isSelected() == true){
					//ga naar het volgende trade scherm voor het trade met players
					playerTrade = new PlayerTrade();
					dispose(); 

				}
				else if(bank.isSelected() == true){
					//ga naar het volgende trade scherm voor het trade met de bank
					bankTrade = new BankTrade();
					dispose(); 
				}
				//de meer keuzes de meer else if, maar hoe zorg ik dat die er ook bij komen
			}
		});
		
		cancel.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
			
		});
		
		this.add(title, BorderLayout.PAGE_START);
		this.add(radio, BorderLayout.CENTER);
		this.add(buttons, BorderLayout.PAGE_END);
	}
}
