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
public class BankTrade extends JFrame {

	private JLabel giveBank;
	private JLabel getBank;

	private JRadioButton giveStone;
	private JRadioButton giveWood;
	private JRadioButton giveWool;
	private JRadioButton giveOre;
	private JRadioButton giveCorn;

	private JRadioButton getStone;
	private JRadioButton getWood;
	private JRadioButton getWool;
	private JRadioButton getOre;
	private JRadioButton getCorn;

	private ButtonGroup resourcesToGive;
	private ButtonGroup resourcesToGet;

	private Box giveResources;
	private Box getResources;
	private Box buttons;
	
	private JButton accept;
	private JButton cancel;
	
	
	public BankTrade() {
		this.setLayout(new BorderLayout());
		createView();
		this.pack();
		this.setVisible(true);

	}

	private void createView() {
		//het eerste stuk waar je aangeeft wat je wilt inleveren bij de bank
		giveBank = new JLabel("Welke grondstof wil je aan de bank geven?   ");

		giveStone = new JRadioButton("Steen");
		giveWood = new JRadioButton("Hout");
		giveWool = new JRadioButton("Wol");
		giveOre = new JRadioButton("Erts");
		giveCorn = new JRadioButton("Graan");

		resourcesToGive = new ButtonGroup();

		giveResources = new Box(BoxLayout.PAGE_AXIS);

		resourcesToGive.add(giveStone);
		resourcesToGive.add(giveWood);
		resourcesToGive.add(giveWool);
		resourcesToGive.add(giveOre);
		resourcesToGive.add(giveCorn);

		giveResources.add(giveBank);
		giveResources.add(giveStone);
		giveResources.add(giveWood);
		giveResources.add(giveWool);
		giveResources.add(giveOre);
		giveResources.add(giveCorn);

		//het tweede stuk waar je aangeeft wat je wilt krijgen van de bank
		getBank = new JLabel("     Welke grondstof wil je van de bank hebben?");

		getStone = new JRadioButton("Steen");
		getWood = new JRadioButton("Hout");
		getWool = new JRadioButton("Wol");
		getOre = new JRadioButton("Erts");
		getCorn = new JRadioButton("Graan");

		resourcesToGet = new ButtonGroup();

		getResources = new Box(BoxLayout.PAGE_AXIS);

		resourcesToGet.add(getStone);
		resourcesToGet.add(getWood);
		resourcesToGet.add(getWool);
		resourcesToGet.add(getOre);
		resourcesToGet.add(getCorn);

		getResources.add(getBank);
		getResources.add(getStone);
		getResources.add(getWood);
		getResources.add(getWool);
		getResources.add(getOre);
		getResources.add(getCorn);
		
		//Dit zijn de buttons onder aan het scherm
		buttons = new Box(BoxLayout.LINE_AXIS);
		
		accept = new JButton("Ok");
		cancel = new JButton("Annuleren");
		
		cancel.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
			
		});
		
		buttons.add(accept);
		buttons.add(cancel);
		
		//alles word toegevoegd aan het pannel
		this.add(giveResources, BorderLayout.WEST);
		this.add(getResources, BorderLayout.EAST);
		this.add(buttons, BorderLayout.SOUTH);
	}
}
