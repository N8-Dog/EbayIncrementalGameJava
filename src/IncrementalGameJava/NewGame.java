package IncrementalGameJava;

import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class NewGame extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8224853777338582029L;
	private JLabel title;
	private JLabel subtitle;
	private JLabel nameLabel;
	private JTextField nameField;
	private JButton submitbtn;
	boolean complete;
	
	public NewGame() {
		complete = false;
		title = new JLabel("NEW GAME");
		subtitle = new JLabel("welcome to the incredible world of Ebay Adventures \n please enter your name, adventurer");
		nameLabel = new JLabel("Name : ");
		nameField = new JTextField();
		submitbtn = new JButton("Submit");
		submitbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				complete = true;
			}
		});
		
		setLayout(new GridBagLayout());
		GridBagConstraints gc = new GridBagConstraints();
		
		//////// title
		gc.gridx = 1;
		gc.gridy = 0;
		title.setFont(new Font(Font.SERIF, Font.PLAIN, 20));
		add(title, gc);
		
		//////// subtitle
		gc.gridx = 1;
		gc.gridy = 1;
		add(subtitle, gc);
		
		//////// name field
		gc.gridx = 0;
		gc.gridy = 2;
		add(nameLabel, gc);
		

		gc.gridx = 1;
		gc.gridy = 2;
		add(nameField, gc);
		
		//////// submit btn
		gc.gridx = 3;
		gc.gridy = 3;
		add(submitbtn, gc);
		
		
	}
	
	public boolean isComplete() {
		return complete;
	}
}
