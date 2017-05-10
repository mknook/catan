package view;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import database.Database;

public class LoginFrame extends JFrame {

	private JTextField user;
	private JTextField pass;
	private Database d;
	private HomeFrame start;
	public static String username;

	public LoginFrame() {
		d = new Database();
		createInputFields();
		createButtons();
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		this.setTitle("Login");

		this.pack();
		this.setVisible(true);
	}

	private void createInputFields() {
		this.getContentPane().setPreferredSize(new Dimension(250, 125));

		user = new JTextField();
		pass = new JPasswordField();

		user.setPreferredSize(new Dimension(100, 20));
		pass.setPreferredSize(new Dimension(100, 20));

		JLabel labeluser = new JLabel("Username:");
		JLabel labelpass = new JLabel("Password:");

		labeluser.setAlignmentX(CENTER_ALIGNMENT);
		labelpass.setAlignmentX(CENTER_ALIGNMENT);

		this.getContentPane().setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));

		this.add(labeluser);
		this.add(user);
		this.add(labelpass);
		this.add(pass);
	}

	private void createButtons() {
		JPanel buttonPanel = new JPanel();
		JButton login = new JButton("Login");
		JButton create = new JButton("Create Account");
		this.getRootPane().setDefaultButton(login);
		create.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				d.createAccount(user.getText(), pass.getText());
				username = user.getText();
				start = new HomeFrame();
				dispose();

			}
		});

		login.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (d.login(user.getText(), pass.getText())) {

					username = user.getText();
					start = new HomeFrame();
					dispose();
				}
				if (!d.login(user.getText(), pass.getText())){
					new ErrorFrame();
					dispose();
				}
			}
		});

		buttonPanel.add(login);
		buttonPanel.add(create);
		this.getContentPane().add(buttonPanel);
	}

}
