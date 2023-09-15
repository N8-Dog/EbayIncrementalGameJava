package IncrementalGameJava;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;

public class GUI implements ActionListener{
	
	int counts = 0;
	JLabel label;
	JFrame frameIntro;
	JFrame mainFrame;
	
	JPanel panel;
	JPanel titlePanel;
	JPanel toolBar;
	
	JButton buttonNewGame, buttonLoadGame, buttonSettings, buttonQuit, run;
	
	Player user;
	
	JLabel objectStore[];
	JLabel mainTitleLogo;
	JLabel username;
	JLabel usercash;
	JLabel userinc;
	
    JMenuBar mb;
    JMenu menu, pause, about;
    JMenuItem newGame, loadGame, settings, quit, pausePlay, info;
    
    int time = 1;
    
    
    
    boolean gameRunning;
	
	public GUI() {
		frameIntro = new JFrame();
		mainFrame = new JFrame();
		panel = new JPanel();
		titlePanel = new JPanel();
		buttonNewGame = new JButton("New Game");
		buttonLoadGame = new JButton("Load Game");
		buttonSettings = new JButton("Settings");
		buttonQuit = new JButton("Quit");
		this.objectStore = new JLabel[36];
		buttonQuit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	frameIntro.dispose();
            }
        });
		
		buttonNewGame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	gameRunning = false;
            	panel.removeAll();
            	frameIntro.setVisible(false);
            	initMenuBar();
            	
            	user = new Player("Jacques Petitgros");
            	panel = new JPanel(new GridLayout(6, 6, 100, 50));
                initImages();
                
            	int i = 0;
            	for (Objects obj : user.objectIndex) {
            		
                    JPanel groupPanel = new JPanel();
                    groupPanel.setLayout(new BorderLayout(20,10));
                    
                    JLabel name = new JLabel(obj.getName());
                    
                    groupPanel.add(name, BorderLayout.NORTH);
                    
                    JLabel price = new JLabel("Price : " + obj.getLePrice() + "$");
                    groupPanel.add(objectStore[i++], BorderLayout.CENTER);//TODO objects don't have the right name
                    groupPanel.add(price, BorderLayout.EAST);
                    JLabel inc = new JLabel("Pay : " + obj.getLeInc() + "$");
                    groupPanel.add(inc, BorderLayout.WEST);
                    JButton buy = new JButton("Buy");//TODO buy button
                    groupPanel.add(buy, BorderLayout.SOUTH);
                    
                    panel.add(groupPanel);
                    //panel.add(Box.createVerticalStrut(1));
                    //TODO fix objects layout
                    
            	}
            	
            	mainFrame.setLayout(new GridLayout(3,1));//TODO add player info and interface
            	titlePanel.add(mainTitleLogo);
            	mainFrame.setLayout(new BorderLayout(20,10));
            	mainFrame.add(titlePanel, BorderLayout.NORTH);
            	mainFrame.add(panel, BorderLayout.CENTER);
            	mainFrame.add(initPlayerUI(), BorderLayout.SOUTH);
            	mainFrame.pack();
            	//titlePanel.setBorder(BorderFactory.createEmptyBorder(0, 50, 0, 50));
            	//panel.setBorder(BorderFactory.createEmptyBorder(0,50,50,50));
            	mainFrame.setLocationRelativeTo(null);
            	mainFrame.setVisible(true);
            	
            	
            }
        });
		
	
		
		
		label = new JLabel("Ebay Adventures");
		label.setHorizontalAlignment(JLabel.CENTER);
		panel.setBorder(BorderFactory.createEmptyBorder(100,100,100,100));
		panel.setLayout(new GridLayout(0,1));
		
		panel.add(buttonNewGame);
		panel.add(buttonLoadGame);
		panel.add(buttonSettings);
		panel.add(buttonQuit);
		
		
		//button.addActionListener(this);
		buttonQuit.addActionListener(this);
		frameIntro.add(label, BorderLayout.NORTH);
		frameIntro.add(panel, BorderLayout.CENTER);
		frameIntro.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frameIntro.setTitle("EbayAdventures");
		
		frameIntro.setIconImage(Toolkit.getDefaultToolkit().getImage("src\\IncrementalGameJavaAssets/Icon.png"));
		
	
		frameIntro.pack();
		frameIntro.setVisible(true);
	}
	public static void main(String[] args) {
		new GUI();
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	public void initImages() {
        try {
            BufferedImage titleImage = ImageIO.read(new File("src\\IncrementalGameJava\\Assets\\mainTitleLogo.png"));
            
            mainTitleLogo = new JLabel(new ImageIcon(titleImage));
            System.out.println("Height : " + titleImage.getHeight());
            System.out.println("Width : " + titleImage.getWidth());
            BufferedImage originalImage = ImageIO.read(new File("src\\IncrementalGameJava\\Assets\\objects.png")); // Replace with your image file path

            int fragmentWidth = originalImage.getWidth() / 6; 
            int fragmentHeight = originalImage.getHeight() / 6; 

            JPanel panel = new JPanel(new GridLayout(6, 6));
            int index = 0;
            for (int i = 0; i < 6; i++) {
                for (int j = 0; j < 6; j++) {
                    int x = i * fragmentWidth;
                    int y = j * fragmentHeight;
                    BufferedImage fragment = originalImage.getSubimage(x, y, fragmentWidth, fragmentHeight);
                    ImageIcon icon = new ImageIcon(fragment);
                    JLabel label = new JLabel(icon);
                    objectStore[index++] = label;
                }
            }

            mainFrame.getContentPane().add(panel);

        } catch (IOException e) {
            e.printStackTrace();
        }
	}
	
	public void initMenuBar() {
	    
		//TODO make events for buttons
		mb = new JMenuBar();
	    menu = new JMenu("Menu");
	    pause = new JMenu("Pause");
	    about = new JMenu("About");
	    
	    newGame = new JMenuItem("New Game");
	    loadGame = new JMenuItem("Load Game");
	    settings = new JMenuItem("Settings");
	    quit = new JMenuItem("Quit");
	    pausePlay = new JMenuItem("Pause");
	    info = new JMenuItem("About");
	    
	    menu.add(newGame);
	    menu.add(loadGame);
	    menu.add(settings);
	    menu.add(quit);
	    pause.add(pausePlay);
	    about.add(info);
	    
	    mb.add(menu);
	    mb.add(pause);
	    mb.add(info);
	    
	    mainFrame.setJMenuBar(mb);
	    
	    
	    
	    
	}
	
	public JPanel initPlayerUI(){
		toolBar = new JPanel(new GridLayout(3, 3));
		username = new JLabel("Username : " + user.name);
		//username.setHorizontalAlignment(JLabel.EAST);
		usercash = new JLabel("Money : " + user.getLeMoney() + "$");
		//usercash.setHorizontalAlignment(JLabel.EAST);
		userinc = new JLabel("Income : " + user.getLeInc() + "$");
		//userinc.setHorizontalAlignment(JLabel.EAST);
		run = new JButton("RUN");
		toolBar.add(username, BorderLayout.NORTH);
		toolBar.add(Box.createVerticalStrut(1));
		toolBar.add(run);
		toolBar.add(usercash, BorderLayout.NORTH);
		toolBar.add(Box.createVerticalStrut(1));
		toolBar.add(Box.createVerticalStrut(1));
		toolBar.add(userinc, BorderLayout.NORTH);
		JProgressBar progressBar = new JProgressBar(0,100);
		toolBar.add(progressBar, BorderLayout.WEST);
		toolBar.add(Box.createVerticalStrut(1));
		
		run.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
		if(gameRunning) gameRunning = false;
		else {
			gameRunning = true;

				user.tick();
				update();
			
		}
            }
            });
		return toolBar;
	}
	
	public void run() {
		while (gameRunning) {
			performDelay();
			user.tick();
			update();
		}
	}
	
	private void performDelay() {
		try { 
			  synchronized (this) { 
			   while (true) {//Or any Loops 
			   //Do Something 
			   this.wait(1000);//Sample obj.wait(1000); 1 second sleep 
			   } 
			  } 
			 } catch (InterruptedException ex) { 
			   mainFrame.dispose();
			 } 
    }
	
	private void update() {
		usercash.setText(user.getLeMoney());
	}

}
