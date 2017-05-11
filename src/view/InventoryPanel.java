package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

import database.Database;

public class InventoryPanel extends JPanel {

	Database d = new Database();

	public InventoryPanel() {
		// de layout is 1 kolomn
		this.setPreferredSize(new Dimension(200, 300));
		this.setLayout(new GridLayout(0, 1));

		// de margin instellen
		// top,left,buttom,rigth
		this.setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 0));

		// alle jlabels voor de resources aanmaken
		// met de database methodes
		// database methodes geven aantal trerug (int)
		JLabel info = new JLabel("Eigen Info");
		JLabel wool = new JLabel("Wol: " + d.getWool());
		JLabel wheat = new JLabel("Graan: " + d.getWheat());
		JLabel ore = new JLabel("Erts: " + d.getOre());
		JLabel stone = new JLabel("Steen: " + d.getStone());
		JLabel wood = new JLabel("Hout: " + d.getWood());

		// de jlabels voor de ontwikkelingskaarten
		JLabel knigth = new JLabel("Ridder: " + d.getKnigth());
		JLabel invention = new JLabel("Uitvindingen: " + d.getInvention());
		JLabel roadConstruction = new JLabel("Stratenbouw: " + d.getRoadConstruction());
		JLabel monopoly = new JLabel("Monopolie: " + d.getMonopoly());
		JLabel cathedral = new JLabel("Kathedraal: " + d.getCathedral());
		JLabel liblary = new JLabel("Bibliotheek: " + d.getLiblary());
		JLabel market = new JLabel("Markt: " + d.getMarkt());
		JLabel university = new JLabel("Universiteit: " + d.getUniversity());
		JLabel congress = new JLabel("parlement: " + d.getCongress());
		


		// Label voor totale overwiningspunten
		JLabel victoryPoints = new JLabel("overwinningspunten: " /* + database.getVictoryPoints() */);

		this.add(info);
		this.add(wool);
		this.add(wheat);
		this.add(ore);
		this.add(stone);
		this.add(wood);

		this.add(knigth);
		this.add(invention);
		this.add(roadConstruction);
		this.add(monopoly);
		this.add(cathedral);
		this.add(liblary);
		this.add(market);
		this.add(university);
		this.add(congress);

		this.add(victoryPoints);
	}
}

