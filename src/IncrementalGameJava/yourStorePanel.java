package IncrementalGameJava;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;

public class yourStorePanel extends JPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private yourStoreItem[] content;
	private imageManager manager;
	private Player player;
	private GUI gui;
	
	public yourStorePanel(Player player, imageManager manager, GUI gui, boolean newGame) {
		this.manager = manager;
		this.player = player;
		if(newGame)	{
			this.content = new yourStoreItem[4];
			addItem(player.myStore[0]);
		}
		else {
			this.content = new yourStoreItem[4];
			for(Objects obj : player.myStore) {
				updateContent();
			}
		}
		
		this.gui = gui;
		Dimension dim = this.getPreferredSize();
		dim.height = 500;
		dim.width = 500;
		setPreferredSize(dim);
		Border innerBorder = BorderFactory.createLineBorder(new Color(9999));
		Border outerBorder = BorderFactory.createEmptyBorder(5,5,5,5);
		setBorder(BorderFactory.createCompoundBorder(outerBorder, innerBorder));
		updateStorePanel();
		
		
	}
	
	private void addItem(Objects object) {
		GridBagConstraints gc = new GridBagConstraints();
		gc.gridx = 0;
		gc.gridy = 1;
		int index = 0;
		if(content[index] != null) {
			while(!content[index].isEmpty) {
				index++;
				gc.gridy++;
			}
		}
		
		if(index < 4) {
			content[index] = new yourStoreItem(object,index,this);
			//this.remove(index);
			add(content[index], gc);
		}
		
		else System.out.println("Check tes shit !");
	}
	

	
	public void updateContent() {
		for(int i = 0; i < 4; i++) {
			if(player.myStore[i] != null) {
				content[i] = new yourStoreItem(player.myStore[i], i, this);
			}
			else {
				content[i] = new yourStoreItem();
			}
		}
	}
	
	
	
	public void updateStorePanel() {
		updateContent();
		this.removeAll();
		setLayout(new GridBagLayout());
		GridBagConstraints gc = new GridBagConstraints();
		gc.gridx = 0;
		gc.gridy = 0;
		JLabel yourStoreTitle = manager.getLabel("yourStore");
		gc.gridy++;
		add(yourStoreTitle, gc);
		
		for(int i = 0; i < 4; i++) {
			gc.gridy++;
		
			add(content[i], gc);
	
		}
	}
	
	public void buyItem(Objects object) {
		addItem(object);
		updateStorePanel();
	}
	
	public class yourStoreItem extends JPanel{
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		private Objects object;
		public GridBagConstraints gc;
		public boolean isEmpty;

		
		public yourStoreItem(Objects object, int index, yourStorePanel storePanel) {
			this.object = object;
			this.isEmpty = false;
			setLayout(new GridBagLayout());
			this.gc = new GridBagConstraints();
    		Border innerBorder = BorderFactory.createTitledBorder(object.getName());
    		Border outerBorder = BorderFactory.createEmptyBorder(5,5,5,5);
    		setBorder(BorderFactory.createCompoundBorder(outerBorder, innerBorder));
    		
			////////1
    		gc.gridx = 0;
    		gc.gridy = 1;
    		add(manager.getLabel(object.name), gc);
    		
    		////////2
    		gc.gridx = 1;
    		gc.gridy = 1;
    		JLabel price = new JLabel("Value : " + object.getLePrice() + " $");
    		add(price, gc);
    		
    		////////3
    		gc.gridx = 1;
    		gc.gridy = 2;
    		JLabel inc = new JLabel("Pay : " + object.getLeInc() + " $");
    		add(inc, gc);
    		
    		////////4
    		gc.gridx = 1;
    		gc.gridy = 3;
    		
    		JButton sellBtn;
    		sellBtn = new JButton("Sell");
    		sellBtn.addActionListener(new ActionListener() {
    			public void actionPerformed(ActionEvent e) {
    				@SuppressWarnings("unused")
					SellEvent sellEv = new SellEvent(this, object);
    				gui.addEntry(player.sellItem(index));
    				storePanel.updateStorePanel();
    			}
    		});
			add(sellBtn, gc);
		}
		
		public yourStoreItem() {
	
			this.isEmpty = true;
			setLayout(new GridBagLayout());
			this.gc = new GridBagConstraints();
    		Border innerBorder = BorderFactory.createTitledBorder("Empty");
    		Border outerBorder = BorderFactory.createEmptyBorder(5,5,5,5);
    		setBorder(BorderFactory.createCompoundBorder(outerBorder, innerBorder));
    		
			////////1
    		gc.gridx = 0;
    		gc.gridy = 1;
    		add(new JLabel(manager.getIcon("Empty")), gc);
    		
    		////////2
    		gc.gridx = 1;
    		gc.gridy = 1;
    		JLabel price = new JLabel("Value : NONE");
    		add(price, gc);
    		
    		////////3
    		gc.gridx = 1;
    		gc.gridy = 2;
    		JLabel inc = new JLabel("Pay : NONE");
    		add(inc, gc);
		}
		
		public String getName() {
			
			if(object == null) return "empty";
			return object.getName();
		}
		
		public int getPrice() {
			return object.getPrice();
		}
		
		String getPath() {
			return object.getPath();
		}
		
		String getLePrice() {
			return object.getLePrice();
		}
		
		int getInc() {
			return object.getInc();
		}
		
		String getLeInc() {
			return object.getLeInc();
		}
	}
}
