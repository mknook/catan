package view;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

@SuppressWarnings("serial")
public class ToolBar extends JPanel {

	private JButton buildCity;
	private JButton buildVillage;
	private JButton buildStreet;
	private JButton buyDevelopmentCard;
	private JButton playDevelopmentCard;
	private JButton trade;
	private JButton endTurn;

	public ToolBar() {
		this.setPreferredSize(new Dimension(400, 200));
		this.setLayout(new GridLayout(4, 2,10,10));
		createButtons();
		this.setBorder(new EmptyBorder(10, 10, 10, 10));
	}

	private void createButtons() {
		buildCity = new JButton("Bouw Stad");
		buildVillage = new JButton("Bouw Dorp");
		buildStreet = new JButton("Bouw Straat");
		buyDevelopmentCard = new JButton("Koop Ontwikkelingskaart");
		playDevelopmentCard = new JButton("Speel Ontwikkelingskaart");
		trade = new JButton("Ruil");
		endTurn = new JButton("Volgende speler");
		this.add(buildCity);
		this.add(buildStreet);
		this.add(buildVillage);
		this.add(buyDevelopmentCard);
		this.add(playDevelopmentCard);
		this.add(trade);
		this.add(endTurn);
		
		trade.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				TradeView tradeView = new TradeView();
			}

		});

	}
}
