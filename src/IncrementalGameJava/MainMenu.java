 package IncrementalGameJava;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class MainMenu implements ActionListener{
	JButton buttonNewGame, buttonLoadGame, buttonSettings, buttonQuit, run;
	JFrame frameIntro;	
	JPanel panel;
	JLabel mainTitleLogo;
	JLabel label;
	public GUI laGame;
	public NewGame newgame;
	imageManager manager;
	
	public MainMenu() {
		manager = new imageManager();
		label = new JLabel("Ebay Adventures");
		label.setFont(new Font(Font.SANS_SERIF, Font.BOLD,30));
		frameIntro = new JFrame();
		frameIntro.setMinimumSize(new Dimension(500,500));
		panel = new JPanel();
		
		buttonNewGame = new JButton("New Game");
		buttonNewGame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	String text = null;
            	text = JOptionPane.showInputDialog(frameIntro, "Enter your Username", "Enter Username", JOptionPane.OK_OPTION|JOptionPane.PLAIN_MESSAGE);
            	
            	if(text != null) {
            		frameIntro.setVisible(false);
            	}
            	if(text.isEmpty()) {
            		text = "DoucheBag";
            	}
            	
            	newGame(text);
            	
            }
        });
		buttonLoadGame = new JButton("Load Game");
		buttonLoadGame.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				openLoadMenu();
				
			}
			
		});
		buttonSettings = new JButton("Settings");
		buttonQuit = new JButton("Quit");
		
		buttonQuit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	frameIntro.dispose();
            }
        });
		label = new JLabel();
		
		panel.setLayout(new GridBagLayout());
		GridBagConstraints gc = new GridBagConstraints();
		
		gc.gridy = 0;
		gc.gridx = 0;
		BufferedImage originalImage = null;
		try {
			originalImage = ImageIO.read(new File("src\\IncrementalGameJava\\Assets\\IntroLogo.png"));
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		ImageIcon icon = new ImageIcon(originalImage);
		panel.add(new JLabel(icon));
		
		gc.gridy++;
		panel.add(buttonNewGame,gc);
		
		gc.gridy++;
		panel.add(buttonLoadGame,gc);
		
		gc.gridy++;
		panel.add(buttonSettings,gc);
		
		gc.gridy++;
		panel.add(buttonQuit,gc);
		
		buttonQuit.addActionListener(this);
		frameIntro.add(label, BorderLayout.NORTH);
		frameIntro.add(panel, BorderLayout.CENTER);
		frameIntro.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frameIntro.setTitle("EbayAdventures");
		
		frameIntro.setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\Madame_Crawford_V\\eclipse-workspace\\IncrementalGameJava\\src\\IncrementalGameJavaAssets/Icon.png"));
		
		frameIntro.pack();
		frameIntro.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	public static void main(String[] args) {
	new MainMenu();
}
	public void newGame(String text) {
		laGame = new GUI(text, this);
		//frameIntro.setVisible(false);
	}
	
	public void loadGame(saveState save) {
		laGame = new GUI(save, this);
		//frameIntro.setVisible(false);
	}
	
	private void openLoadMenu() {
		new LoadMenu(manager, this);
		}
}
