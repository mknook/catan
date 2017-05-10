package view;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.Timer;

import database.Database;

public class ErrorFrame extends JFrame {
 
 private Database d;

 public ErrorFrame() {
  // TODO Auto-generated constructor stub
  d = new Database();
  create();
  CreateReadOnlyJTextField();
  
  this.setLocationRelativeTo(null);
  this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  this.setResizable(false);
  this.setTitle("Wrong login");
  
  this.pack();
  this.setVisible(true);
 }
 
 private void create(){
  this.getContentPane().setPreferredSize(new Dimension(350, 125));
  
  this.getContentPane().setBackground(Color.WHITE);

  
  this.getContentPane().setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));

 }
 
 public void CreateReadOnlyJTextField() {
  
       this.getContentPane().setLayout(new FlowLayout());
  
       JTextArea field = new JTextArea();
       
       field.setFont(new Font("Serif",Font.PLAIN,20));
       
       field.setText("you've enterd a wrong \n username or password \n please try again.");
  
       field.setEditable(false);
  
       add(field);
       
       Timer timer = new Timer(5000, new ActionListener() {
    
    @Override
    public void actionPerformed(ActionEvent e) {
    	new LoginFrame();
    	dispose();
    }
   });
    timer.setRepeats(false);
    timer.start();
  
      }


}