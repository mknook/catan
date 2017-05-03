package view;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

@SuppressWarnings("serial")
public class PlayersInformationPanel extends JPanel {

	JTable table;

	JLabel longestRoute;
	JLabel biggestArmy;
	JLabel gameOverview;

	public PlayersInformationPanel() {
		this.setPreferredSize(new Dimension(300, 400));
		this.setLayout(new BorderLayout());
		createView();
	}

	private void createView() {

		gameOverview = new JLabel("Spelers Overzicht");
		longestRoute = new JLabel(
				"Langste Straat:"/*
									 * hier komt de naam van de speler met de
									 * langste straat
									 */);
		biggestArmy = new JLabel(
				"Grootste Riddermacht:"/*
										 * hier komt de naam van de speler met
										 * de grootste riddermacht
										 */);
		Box box = new Box(BoxLayout.PAGE_AXIS);

		JScrollPane scrollPane;

		String[] players = { "", "Speler1", "Speler2", "Speler3", "Speler4" };
		String[][] databaseInfo = { { "Ontwikkelingskaarten:", "", "", "", "" },
				{ "gespeelde ridderkaarten:", "", "", "", "" }, { "Grondstofkaarten:", "", "", "", "" },
				{ "OverwinningsPunten:", "", "", "", "" }, };
		// de connectie met de database moeten nog komen en die worden hier dus
		// steeds neergezet op de lege plekken

		table = new JTable(databaseInfo, players);
		table.setEnabled(false);
		table.getTableHeader().setReorderingAllowed(false);
		table.getTableHeader().setResizingAllowed(false);
		scrollPane = new JScrollPane(table);

		box.add(longestRoute);
		box.add(biggestArmy);

		this.add(gameOverview, BorderLayout.PAGE_START);
		this.add(scrollPane);
		this.add(box, BorderLayout.PAGE_END);

	}
}
