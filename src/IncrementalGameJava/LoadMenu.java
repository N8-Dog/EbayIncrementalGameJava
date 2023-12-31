package IncrementalGameJava;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

public class LoadMenu extends JFrame implements  Serializable {
	imageManager manager;
	private JPanel panel;
	private JButton cancel;
	private MainMenu parent;
	private String[] savelist;
	private ArrayList<loadStateLine> loadStateLineList;
	private String[] ids = {"one",
			"two",
			"three",
			"four"};
	
	public LoadMenu(imageManager manager, MainMenu parent) {
		this.manager = manager;
		this.panel = new JPanel();
		this.parent = parent;
		initLoadMenu();
		

		
		
		setSize(1000,600);
		
		
	}
	
	private String[] listFiles() {
		String[] fileList;
		
		String folderPath = "src\\IncrementalGameJava\\Saves";
		File directory = new File(folderPath);
		File[] filesList = directory.listFiles();
		
		
		fileList = new String[filesList.length];
		int i = 0;
		for (File file : filesList) {
			fileList[i++] = file.toString();
		}
		return fileList;
	}
	
	private void initLoadMenu() {

		savelist = listFiles();

		//saveStateLineList = saveStateLine[4];
		int count = savelist.length;
		
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
		for(int i = 0; i < 4; i++) {
			if(count > 0) {
				System.out.println(savelist[i]);
				panel.add(new loadStateLine(new saveState(savelist[i]), ids[i],i), gc);
			
			//newLine(i);
			gc.gridy++;
			count--;
			}
			else {
				panel.add(new loadStateLine(ids[i]), gc);
				gc.gridy++;
			}
		}
		gc.gridy++;
		cancel = new JButton("Cancel");
		cancel.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				
			}
			
		});
		panel.add(cancel, gc);
		add(panel);
		setVisible(true);
	}
	
	private void clearPanel() {
		panel.removeAll();
		//saveStateLineList.removeAll(saveStateLineList);
		
	}
	
	/*private void newLine(int i) {
		saveStateLine newline = new saveStateLine(new saveState("src\\IncrementalGameJava\\Saves\\" + savelist[i]), ids[i],i);
		panel.add(newline);
		saveStateLineList.add(newline);
	}
	
	private void delLine(int i) {
		saveStateLineList.remove(i);
	}*/
	
	private void performLoad(saveState save) throws IOException {
		
		parent.loadGame(save);
		setVisible(false);
	}
	
	private void performDelete(int id) {
		//JFileChooser fileChooser = new JFileChooser();
		File fileToDelete = new File(savelist[id]);
		fileToDelete.delete();
		this.panel.removeAll();
		initLoadMenu();
		panel.revalidate();
		panel.repaint();
	}
	
	public class loadStateLine extends JPanel{
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		saveState save;
		
		JLabel icon;
		JLabel name;
		JLabel money;
		JLabel inc;
		JLabel storeCount;
		JButton btn;
		JButton del;
		public loadStateLine(saveState p_save, String id, int i) {
			this.save = p_save;
			icon = manager.getLabel(id);
			name = new JLabel("Name : " + p_save.getName());
			money = new JLabel("Money : " + p_save.getMoney());
			inc = new JLabel("Income : " + p_save.getInc());
			storeCount = new JLabel("Store inventory : " + p_save.getOwnStoreCount());
			
			Border innerBorder = BorderFactory.createLineBorder(new Color(9999));
			Border outerBorder = BorderFactory.createEmptyBorder(5,5,5,5);
			setBorder(BorderFactory.createCompoundBorder(outerBorder, innerBorder));
			
			setLayout(new GridBagLayout());
			GridBagConstraints gc = new GridBagConstraints();
			
			gc.gridy = 0;
			gc.gridx = 0;
			gc.gridheight = 2;
			add(icon, gc);
			
			////////1
			gc.gridheight = 1;
			gc.gridx++;
			add(name, gc);
			////////2
			
			gc.gridy++;
			add(money, gc);
			////////3
			
			gc.gridx++;
			add(inc, gc);
			////////4
			
			gc.gridy++;
			add(storeCount, gc);
			
			////////5
			gc.gridx++;
			
				btn = new JButton("Load");
				btn.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						try {
							performLoad(save);
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						System.out.println("tout est bin beau");
						
						//updateSaveMenu();
					}
					
				});
			
			add(btn,gc);

			////////6
			gc.gridx++;
			del = new JButton("Delete");
			del.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					performDelete(i);
					
				}
				
			});
			add(del,gc);

		}
		
		public loadStateLine(String i) {
			icon = manager.getLabel("Empty");
			name = new JLabel("Name : Empty");
			money = new JLabel("Money : Empty");
			inc = new JLabel("Income : Empty");
			storeCount = new JLabel("Store inventory : Empty");
		
			Border innerBorder = BorderFactory.createLineBorder(new Color(9999));
			Border outerBorder = BorderFactory.createEmptyBorder(5,5,5,5);
			setBorder(BorderFactory.createCompoundBorder(outerBorder, innerBorder));
			
			setLayout(new GridBagLayout());
			GridBagConstraints gc = new GridBagConstraints();
			
			gc.gridy = 0;
			gc.gridx = 0;
			gc.gridheight = 2;
			add(icon, gc);
			
			////////1
			gc.gridheight = 1;
			gc.gridx++;
			add(name, gc);
			////////2
			
			gc.gridy++;
			add(money, gc);
			////////3
			
			gc.gridx++;
			add(inc, gc);
			////////4
			
			gc.gridy++;
			add(storeCount, gc);
			

			
		}
	}
	
}












/*package IncrementalGameJava;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

import IncrementalGameJava.SaveMenu.saveStateLine;

public class LoadMenu extends JFrame {
	imageManager manager;
	private JPanel panel;
	private JButton cancel;
	private GUI parent;
	private String[] savelist;
	private ArrayList<saveStateLine> saveStateLineList;
	private String[] ids = {"one",
			"two",
			"three",
			"four"};
	
	
	public LoadMenu(imageManager manager) {
		this.manager = manager;
		panel = new JPanel();
		
		String[] saves = listFiles();
		String[] ids = {"one",
				"two",
				"three",
				"four"};
		int count = saves.length;
		
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
		for(int i = 0; i < 4; i++) {
			if(count > 0) {
			panel.add(new loadStateLine(new saveState("src\\IncrementalGameJava\\Saves\\" + saves[i]), ids[i]), gc);
			gc.gridy++;
			count--;
			}
			else {
				panel.add(new loadStateLine(), gc);
				gc.gridy++;
			}
		}
		add(panel);
		setSize(800,600);
		setVisible(true);
		
	}
	
	private String[] listFiles() {
		String[] fileList;
		
		String folderPath = "src\\IncrementalGameJava\\Saves";
		File directory = new File(folderPath);
		File[] filesList = directory.listFiles();
		//System.out.println(filesList);
		fileList = new String[filesList.length];
		int i = 0;
		for (File file : filesList) {
			fileList[i++] = file.toString();
		}
		return fileList;
	}
	
	public void performLoad(String id) {
		
	}
	
	public class loadStateLine extends JPanel{
 
		private static final long serialVersionUID = 1L;

		saveState save;
		
		JLabel icon;
		JLabel name;
		JLabel money;
		JLabel inc;
		JLabel storeCount;
		JButton btn;
		JButton del;
		public loadStateLine(saveState p_save, String id) {
			this.save = p_save;
			icon = manager.getLabel(id);
			name = new JLabel("Name : " + p_save.getName());
			money = new JLabel("Money : " + p_save.getMoney());
			inc = new JLabel("Income : " + p_save.getInc());
			storeCount = new JLabel("Store inventory : " + p_save.getOwnStoreCount());
			
			Border innerBorder = BorderFactory.createLineBorder(new Color(9999));
			Border outerBorder = BorderFactory.createEmptyBorder(5,5,5,5);
			setBorder(BorderFactory.createCompoundBorder(outerBorder, innerBorder));
			
			setLayout(new GridBagLayout());
			GridBagConstraints gc = new GridBagConstraints();
			
			gc.gridy = 0;
			gc.gridx = 0;
			gc.gridheight = 2;
			add(icon, gc);
			
			////////1
			gc.gridheight = 1;
			gc.gridx++;
			add(name, gc);
			////////2
			
			gc.gridy++;
			add(money, gc);
			////////3
			
			gc.gridx++;
			add(inc, gc);
			////////4
			
			gc.gridy++;
			add(storeCount, gc);
			
			////////5
			btn.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					performLoad(id);
					
				}
				
			});
			add(btn,gc);
			gc.gridx++;

			////////6
			gc.gridx++;
			del = new JButton("Delete");
			add(del,gc);

		}
		
		public loadStateLine() {
			icon = manager.getLabel("Empty");
			name = new JLabel("Name : Empty");
			money = new JLabel("Money : Empty");
			inc = new JLabel("Income : Empty");
			storeCount = new JLabel("Store inventory : Empty");
			
			Border innerBorder = BorderFactory.createLineBorder(new Color(9999));
			Border outerBorder = BorderFactory.createEmptyBorder(5,5,5,5);
			setBorder(BorderFactory.createCompoundBorder(outerBorder, innerBorder));
			
			setLayout(new GridBagLayout());
			GridBagConstraints gc = new GridBagConstraints();
			
			gc.gridy = 0;
			gc.gridx = 0;
			gc.gridheight = 2;
			add(icon, gc);
			
			////////1
			gc.gridheight = 1;
			gc.gridx++;
			add(name, gc);
			////////2
			
			gc.gridy++;
			add(money, gc);
			////////3
			
			gc.gridx++;
			add(inc, gc);
			////////4
			
			gc.gridy++;
			add(storeCount, gc);
			
			////////5
			//gc.gridx++;

			
		}
	}
	
}
*/