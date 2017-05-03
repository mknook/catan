package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class InventoryPanel extends JPanel {

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
		JLabel wool = new JLabel("Wol: " /* + Database.getWool() */);
		JLabel wheat = new JLabel("Graan: " /* + database.getWheat() */);
		JLabel ore = new JLabel("Erts: " /* + database.getOre() */);
		JLabel stone = new JLabel("Stone: " /* + database.getStone() */);
		JLabel wood = new JLabel("Wood: " /* + database.getWood() */);

		// de jlabels voor de ontwikkelingskaarten
		JLabel knigth = new JLabel("Ridder: " /* + database.getKnigth() */);
		JLabel invention = new JLabel(
				"Uitvindingen: " /* + database.getInvention() */);
		JLabel roadConstruction = new JLabel(
				"Stratenbouw: " /* + database.getRoadConstruction() */);
		JLabel monopoly = new JLabel(
				"Monopolie: " /* + database.getMonopoly() */);
		JLabel cathedral = new JLabel(
				"Kathedraal: " /* + database.getCathedral() */);
		JLabel liblary = new JLabel(
				"Bibliotheek: " /* + database.getLiblary() */);

		// Label voor totale overwiningspunten
		JLabel victoryPoints = new JLabel(
				"overwinningspunten: " /* + database.getVictoryPoints() */);

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

		this.add(victoryPoints);

	}
}
