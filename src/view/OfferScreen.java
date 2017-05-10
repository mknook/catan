package view;
import java.awt.BorderLayout;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class OfferScreen extends JPanel {

	private JButton accept;
	private JButton cancel;
	private JButton counterOffer;

	private Box buttons;

	private Box give;
	private Box get;

	private JLabel youGive;
	private JLabel youGet;

	public OfferScreen() {
		this.setLayout(new BorderLayout());
		initialize();
		createView();
		this.setVisible(true);
	}

	private void createView() {
		give.add(youGive);
		get.add(youGet);

		buttons.add(accept);
		buttons.add(cancel);
		buttons.add(counterOffer);

		this.add(buttons, BorderLayout.SOUTH);
		this.add(give, BorderLayout.WEST);
		this.add(get, BorderLayout.EAST);
	}

	private void initialize() {
		youGive = new JLabel("Je Geeft: ");
		youGet = new JLabel("Je Krijgt: ");

		give = new Box(BoxLayout.PAGE_AXIS);
		get = new Box(BoxLayout.PAGE_AXIS);

		// de 2 onderstaande buttons
		accept = new JButton("Accepteren");
		cancel = new JButton("Weigeren");
		counterOffer = new JButton("TegenBod");

		buttons = new Box(BoxLayout.LINE_AXIS);
	}
}
