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

public class SaveMenu extends JFrame implements  Serializable {
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
	
	public SaveMenu(imageManager manager, GUI parent) {
		this.manager = manager;
		this.panel = new JPanel();
		this.parent = parent;
		initSaveMenu();
		

		
		
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
	
	private void initSaveMenu() {
		
		
		
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
				panel.add(new saveStateLine(new saveState(savelist[i]), ids[i],i), gc);
			
			//newLine(i);
			gc.gridy++;
			count--;
			}
			else {
				panel.add(new saveStateLine(ids[i]), gc);
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
	
	private void performSave(String id) throws IOException {
		String saveFile;
		saveFile = id + ".save";
		saveState state = new saveState(parent.user);
		
		/*FileOutputStream fos = new FileOutputStream(saveFile);
		ObjectOutputStream oos = new ObjectOutputStream(fos);
		
		//System.out.println(state.getSaveString());
		oos.writeObject(state);
		oos.close();*/
		
		try {
            // Specify the file path
            String filePath = "src\\IncrementalGameJava\\Saves\\" + saveFile;

            // Create a BufferedWriter to write to the file
            BufferedWriter writer = new BufferedWriter(new FileWriter(filePath));

            // Write the data to the file
            writer.write(state.getSaveString());

            // Close the writer
            writer.close();

            System.out.println("Data has been written to the file successfully.");
            
        } catch (IOException e) {
            System.out.println("An error occurred: " + e.getMessage());
            e.printStackTrace();
        }
		
		this.panel.removeAll();
		initSaveMenu();
		panel.revalidate();
		panel.repaint();
		parent.addEntry("Game Saved");
	}
	
	private void performDelete(int id) {
		//JFileChooser fileChooser = new JFileChooser();
		File fileToDelete = new File(savelist[id]);
		fileToDelete.delete();
		this.panel.removeAll();
		initSaveMenu();
		panel.revalidate();
		panel.repaint();
	}
	
	public class saveStateLine extends JPanel{
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
		public saveStateLine(saveState p_save, String id, int i) {
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
		
		public saveStateLine(String i) {
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
			gc.gridx++;
			
				btn = new JButton("Save");
				btn.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						try {
							performSave(i);
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						System.out.println("tout est bin beau");
						
						//updateSaveMenu();
					}
					
				});
			
			add(btn,gc);
			
		}
	}
	
}
