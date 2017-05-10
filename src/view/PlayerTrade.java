package view;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class PlayerTrade extends JFrame {

	// dit zijn de objecten voor het aan geven hoeveel je wilt aanbieden
	private JTextField giveStoneInput;
	private JTextField giveWoodInput;
	private JTextField giveWoolInput;
	private JTextField giveOreInput;
	private JTextField giveCornInput;

	private JLabel giveStone;
	private JLabel giveWood;
	private JLabel giveWool;
	private JLabel giveOre;
	private JLabel giveCorn;

	private JLabel givePlayerTitle;

	private Box giveStoneBox;
	private Box giveWoodBox;
	private Box giveWoolBox;
	private Box giveOreBox;
	private Box giveCornBox;

	private Box give;

	// dit zijn de objecten voor het aangeven hoeveel je terug wilt krijgen voor
	// je bod
	private JTextField getStoneInput;
	private JTextField getWoodInput;
	private JTextField getWoolInput;
	private JTextField getOreInput;
	private JTextField getCornInput;

	private JLabel getStone;
	private JLabel getWood;
	private JLabel getWool;
	private JLabel getOre;
	private JLabel getCorn;

	private JLabel getPlayerTitle;

	private Box getStoneBox;
	private Box getWoodBox;
	private Box getWoolBox;
	private Box getOreBox;
	private Box getCornBox;

	private Box get;

	// de onderstaande buttons
	private JButton accept;
	private JButton cancel;

	private Box buttons;

	public PlayerTrade() {
		this.setLayout(new BorderLayout());
		inatilize();
		createView();
		this.pack();
		this.setVisible(true);
	}

	// alle objecten toevoegen en op het scherm zetten
	private void createView() {

		// voegt de "give" object in box toe om ze in groepjes van 2 naast
		// elkaar te krijgen
		giveStoneBox.add(giveStone);
		giveStoneBox.add(giveStoneInput);

		giveWoodBox.add(giveWood);
		giveWoodBox.add(giveWoodInput);

		giveWoolBox.add(giveWool);
		giveWoolBox.add(giveWoolInput);

		giveOreBox.add(giveOre);
		giveOreBox.add(giveOreInput);

		giveCornBox.add(giveCorn);
		giveCornBox.add(giveCornInput);

		// de paren van 2 worden onder elkaar gezet
		give.add(givePlayerTitle);
		give.add(giveStoneBox);
		give.add(giveWoodBox);
		give.add(giveWoolBox);
		give.add(giveOreBox);
		give.add(giveCornBox);

		// voegt de "get" object in box toe om ze in groepjes van 2 naast elkaar
		// te krijgen
		getStoneBox.add(getStone);
		getStoneBox.add(getStoneInput);

		getWoodBox.add(getWood);
		getWoodBox.add(getWoodInput);

		getWoolBox.add(getWool);
		getWoolBox.add(getWoolInput);

		getOreBox.add(getOre);
		getOreBox.add(getOreInput);

		getCornBox.add(getCorn);
		getCornBox.add(getCornInput);

		// de paren van 2 worden onder elkaar gezet
		get.add(getPlayerTitle);
		get.add(getStoneBox);
		get.add(getWoodBox);
		get.add(getWoolBox);
		get.add(getOreBox);
		get.add(getCornBox);
		
		cancel.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
			
		});

		buttons.add(accept);
		buttons.add(cancel);

		this.add(buttons, BorderLayout.SOUTH);
		this.add(give, BorderLayout.WEST);
		this.add(get, BorderLayout.EAST);

	}

	// alle objecten maken
	private void inatilize() {

		// dit zijn de objecten voor het aan geven hoeveel je wilt aanbieden
		giveStone = new JLabel("Steen: ");
		giveWood = new JLabel("Hout:   ");
		giveWool = new JLabel("Wol:    ");
		giveOre = new JLabel("Erts:    ");
		giveCorn = new JLabel("Graan: ");

		givePlayerTitle = new JLabel("Wat wil je aanbieden?");

		giveStoneInput = new JTextField();
		giveWoodInput = new JTextField();
		giveWoolInput = new JTextField();
		giveOreInput = new JTextField();
		giveCornInput = new JTextField();

		giveStoneBox = new Box(BoxLayout.LINE_AXIS);
		giveWoodBox = new Box(BoxLayout.LINE_AXIS);
		giveWoolBox = new Box(BoxLayout.LINE_AXIS);
		giveOreBox = new Box(BoxLayout.LINE_AXIS);
		giveCornBox = new Box(BoxLayout.LINE_AXIS);

		// dit zijn de objecten voor het aangeven hoeveel je terug wilt krijgen
		// voor je bod
		getStone = new JLabel("Steen:  ");
		getWood = new JLabel("Hout:    ");
		getWool = new JLabel("Wol:     ");
		getOre = new JLabel("Erts:     ");
		getCorn = new JLabel("Graan:   ");

		getPlayerTitle = new JLabel("Wat wil je krijgen?");

		getStoneInput = new JTextField();
		getWoodInput = new JTextField();
		getWoolInput = new JTextField();
		getOreInput = new JTextField();
		getCornInput = new JTextField();

		getStoneBox = new Box(BoxLayout.LINE_AXIS);
		getWoodBox = new Box(BoxLayout.LINE_AXIS);
		getWoolBox = new Box(BoxLayout.LINE_AXIS);
		getOreBox = new Box(BoxLayout.LINE_AXIS);
		getCornBox = new Box(BoxLayout.LINE_AXIS);

		// de 2 boxen waar alle andeer boxen in game om 2 mooie rijen onder
		// elkaar te krijgen.
		give = new Box(BoxLayout.PAGE_AXIS);
		get = new Box(BoxLayout.PAGE_AXIS);

		// de 2 onderstaande buttons
		accept = new JButton("Ok");
		cancel = new JButton("Annuleren");
		buttons = new Box(BoxLayout.LINE_AXIS);
	}
}