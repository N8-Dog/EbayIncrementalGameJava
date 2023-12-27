package IncrementalGameJava;

import java.awt.BorderLayout;
import java.awt.Color;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import javax.swing.border.Border;


public class GUI implements ActionListener{
	
	imageManager manager;
	
	int counts = 0;
	
	JFrame mainFrame;
	JPanel mainPanel;
	JPanel panel;
	JPanel titlePanel;
	JPanel toolBar;
	
	yourStorePanel yourStore;
	playerToolBar playerTB;
	JButton run;
	
	Player user;
	JLabel objectStore[];
	JLabel label;
	JLabel mainTitleLogo;
	JLabel username;
	JLabel usercash;
	JLabel userinc;
	JDialog welcome;
	MainMenu parent;
	
	boolean incOn;
	boolean gameRunning;
	
	private JTextArea textArea;
	
	   JMenuBar mb;
	    JMenu menu, pause, about;
	    JMenuItem newGame, settings, quit, pausePlay, info, saveGame;

	JButton buttonNewGame,  buttonSettings, buttonQuit;

	BuyListener buyListener;   
    int time = 1;
    
    public GUI(String userName, MainMenu parent) {
    	user = new Player(userName, this);
    	this.parent = parent;
    	initGUI(user, true);
    }
    
    public GUI(saveState save, MainMenu parent) {
    	user = new Player(save);
    	this.parent = parent;
    	initGUI(user, false);

    }
    
    private void initGUI(Player user, boolean newGame) {
    	manager = parent.manager;
    	
    	gameRunning = true;
    	mainFrame = new JFrame();
    	//mainFrame.setSize(1000,1000);
    	
    	panel = new JPanel();
    	titlePanel = new JPanel();
    	label = new JLabel();
    	incOn = false;
    	this.objectStore = new JLabel[36];
    	
    	panel.removeAll();
    	
    	initMenuBar();
    	
    	
    	panel = new JPanel(new GridLayout(6,7,10,10));
    	
    	for (Objects obj : user.objectIndex) {
    		
    		JPanel groupPanel = new JPanel();
    		Dimension dim = groupPanel.getPreferredSize();
    		dim.height = 150;
    		dim.width = 250;
    		groupPanel.setPreferredSize(dim);
    		
    		Color color;

    		switch(obj.getRarity()) {
    		case vintage:
    			color = new Color(255,200,51);
    			break;
    		case rare:
    			color = new Color(51,129,255);
    			break;
    		case unique:
    			color = new Color(51,255,75);
    			break;
    		case legendary:
    			color = new Color(51, 220,255);
    			break;
    		default:
    			color = new Color(0,0,0);
    			break;
    		}
    		
    		
    		Border coloredBorder = BorderFactory.createLineBorder(color,3);
    		
    		Border innerBorder = BorderFactory.createTitledBorder(coloredBorder, obj.getName());
    		
    		Border outerBorder = BorderFactory.createEmptyBorder(5,5,5,5);
    		groupPanel.setBorder(BorderFactory.createCompoundBorder(outerBorder, innerBorder));
    		groupPanel.setLayout(new GridBagLayout());
    		GridBagConstraints gc = new GridBagConstraints();
    		
    		//////// 1
    		gc.gridx = 0;
    		gc.gridy = 0;
    		gc.gridheight = 4;
    		JLabel image = manager.getLabel(obj.name);
    		image.setToolTipText(obj.getStats());
    		groupPanel.add(image, gc);
    		
    		//////// 2
    		gc.gridx = 1;
    		gc.gridy = 1;
    		gc.gridheight = 1;
    		JLabel price = new JLabel("Price : " + obj.getLePrice() + "$");
    		groupPanel.add(price,gc);
    		
    		//////// 3
    		gc.gridx = 1;
    		gc.gridy++;
    		JLabel condTag = new JLabel("Condition : ");
    		groupPanel.add(condTag, gc);
    		
    		//////// 4
    		gc.gridy++;
    		JLabel cond = new JLabel(obj.getLeCondition());
    		groupPanel.add(cond,gc);
    		
    		//////// 5 
    		gc.gridy++;
    		JLabel inc = new JLabel("Pay : " + obj.getLeInc() + "$");
    		groupPanel.add(inc,gc);
    		
    		//////// 6 
    		gc.gridy++;
    		JButton buy = new JButton("Buy");
    		buy.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					String name = obj.getName();
					int price = obj.getPrice();
					int increment = obj.getInc();
					int index = obj.getId();
					
					@SuppressWarnings("unused")
					BuyEvent ev = new BuyEvent(this, name, increment, price, index);
					
					addEntry(user.buyObject(index));
					yourStore.updateStorePanel();
					
					
				}
    			
    		});
    		
    		groupPanel.add(buy, gc);
    		panel.add(groupPanel);
    	}
    	
    	user.setBuyListener(buyListener);
    	mainPanel = new JPanel();
    	mainPanel.setLayout(new GridBagLayout());
    	GridBagConstraints gc = new GridBagConstraints();
    	
    	Toolkit toolkit = Toolkit.getDefaultToolkit();

        // Get the screen size as a java.awt.Dimension
        Dimension screenSize = toolkit.getScreenSize();

        int screenWidth = screenSize.width;
        int screenHeight = screenSize.height;
        
    	//////// top logo
    	gc.fill = GridBagConstraints.HORIZONTAL;
    	gc.gridy = 0;
    	gc.gridx = 0;
    	if(screenWidth <= 1600 && screenHeight <=1000) {
    		// Load an image
            BufferedImage originalImage = null;
            try {
                originalImage = ImageIO.read(new File("src\\IncrementalGameJava\\Assets\\mainTitleLogo.png")); // Replace with your image path
            } catch (IOException e) {
                e.printStackTrace();
            }

            // Scale the image
            int scaledWidth = screenWidth - 200; // Desired width
            int scaledHeight = 97; // Desired height
            Image scaledImage = originalImage.getScaledInstance(scaledWidth, scaledHeight, Image.SCALE_SMOOTH);

            // Create a JLabel and set the scaled image as its icon
            ImageIcon icon = new ImageIcon(scaledImage);
            JLabel imageLabel = new JLabel(icon);
            titlePanel.add(imageLabel);
    	}
    	
    	else {
        	titlePanel.add(manager.getLabel("mainTitleLogo"));
    	}

    	mainPanel.add(titlePanel, gc);
    	
    	//////// welcome dialog	
    	
    	JFrame dialogFrame = new JFrame();

    	
    	dialogFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	dialogFrame.setSize(400, 300);
    	
    	JOptionPane.showMessageDialog(dialogFrame, "Welcome to Ebay Adventures, press RUN to begin earning", "Welcome", JOptionPane.INFORMATION_MESSAGE);
    	dialogFrame.setLocationRelativeTo(mainFrame);
    	
    	//////// store section
    	gc.gridy++;
    	gc.gridheight = 10;
    	JScrollPane scrollPane = new JScrollPane(panel);
    	scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
    	scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
    	 scrollPane.setMinimumSize(new Dimension(600, 400)); 
    	gc.gridy++;
    	gc.gridheight = 1;
    	mainPanel.add(scrollPane,gc);
    	
    	//////// player ui
    	gc.gridy++;
    	playerTB = new playerToolBar();
    	toolBar = playerTB.getPanel();
    	mainPanel.add(toolBar,gc);
    	
    	//////// console section
    	gc.gridy++;
    	textArea = new JTextArea();
    	textArea.setEnabled(false);
    	textArea.setText("Welcome to the incredible world of Ebay Adventures ! \n");
    	JScrollPane textPane = new JScrollPane(textArea);
    	textPane.setMinimumSize(new Dimension(400,100));
    	mainPanel.add(textPane, gc);
    	
    	//////// your store
    	
    	this.yourStore = new yourStorePanel(user, manager, this, newGame);
    	gc.gridy=2;
    	gc.gridx=2;
    	mainPanel.add(yourStore,gc);
    	
    	mainFrame.add(mainPanel);
    	mainFrame.pack();
    	System.out.println(yourStore.getWidth());
        if(screenWidth > 1600 && screenHeight >1000) {
        	mainFrame.setSize(1600,1000);

        }
        else {
        	mainFrame.setSize(screenWidth,screenHeight);
        }
    	mainFrame.setVisible(true);
    	label.setHorizontalAlignment(JLabel.CENTER);
    	panel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
    	
    	    }
    
    public void actionPerformed(ActionEvent e) {
    	
    }
	
	public void openSaveMenu() {
		SaveMenu popup = new SaveMenu(manager, this);
	}
	
	public void initMenuBar() {
	    
		//TODO make events for buttons
		mb = new JMenuBar();
	    menu = new JMenu("Menu");
	    pause = new JMenu("Pause");
	    about = new JMenu("About");
	    
	    newGame = new JMenuItem("New Game");
	    saveGame = new JMenuItem("Save Game");
	    saveGame.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if(incOn)run();
				openSaveMenu();
				
			}
	    	
	    });
	    settings = new JMenuItem("Settings");
	    quit = new JMenuItem("Quit");
	    quit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				int confirm = JOptionPane.showConfirmDialog(null, "All unsaved progress will be lost, do you wish to abandon this beautiful realm of unseen magic ?", "Quit", JOptionPane.YES_NO_OPTION);
				if(confirm == JOptionPane.YES_OPTION) {
					mainFrame.setVisible(false);
					parent.frameIntro.setVisible(true);
				}

			}
	    	
	    });
	    pausePlay = new JMenuItem("Pause");
	    info = new JMenuItem("About");
	    
	    menu.add(newGame);
	   menu.add(saveGame);
	    menu.add(settings);
	    menu.add(quit);
	    pause.add(pausePlay);
	    about.add(info);
	    
	    mb.add(menu);
	    mb.add(pause);
	    mb.add(info);
	    
	    mainFrame.setJMenuBar(mb);
	    
	}
	
	private void run() {
		if(incOn) {
			incOn = false;
			run.setText("RUN");
		}
		else {
			incOn = true;
			
			run.setText("STOP");
			startLoop();
		}
	}
	
	private void update() {
		usercash.setText("Money : " + user.getLeMoney() + "$");
		userinc.setText("Income : " + user.getLeInc() + "$/sec.");
	}
	
	private void incrementer() {
		user.tick();
		update();
	}
	
	public void startLoop() {
		new Thread(() ->{
			int clock = 0;
			int i = 0;
			while (incOn) {
				if(i == 9) {
					incrementer();
					i = 0;
					if(clock > 99) {
						clock = 0;
						user.gainValue();
						yourStore.updateStorePanel();
					}
					clock++;
				}
				playerTB.tick(i);
				i = i +1;
				
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		}).start();
	}
	
	public void addEntry(String line) {
		String oldLine = textArea.getText();
		textArea.setText(oldLine + line + "\n");
	}
	
	private class playerToolBar{


		private JLabel username;
		private JProgressBar progressBar;

		public playerToolBar(){
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
			progressBar = new JProgressBar(0,9);
			progressBar.setValue(0);			
			toolBar.add(progressBar, BorderLayout.WEST);
			toolBar.add(Box.createVerticalStrut(1));

			run.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					run();
				}
			});

		}
		
		public JPanel getPanel() {
		return toolBar;
	}
	


	public void tick(int i) {
		progressBar.setValue(i);
	}
}}
