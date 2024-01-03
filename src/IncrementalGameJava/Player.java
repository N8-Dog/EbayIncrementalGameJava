package IncrementalGameJava;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Player {
	String name;
	int money;
	int inc;
	Objects objectIndex[];
	Objects myStore[];
	int ownStoreCount;
	private GUI gui;
	
	public Player(String name, GUI gui) {
		this.name = name;
		this.money = 100;
		this.inc = 1;
		this.objectIndex = new Objects[36];
		this.myStore = new Objects[4];
		this.ownStoreCount = 1;
		this.gui = gui;
		try {
            // Create a FileReader to read the file
            FileReader fileReader = new FileReader("src\\IncrementalGameJava\\Assets\\names.objects");
            
            // Wrap the FileReader in a BufferedReader for efficient reading
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            
            String line;
            
            // Read and print each line from the file
            int index = 0;
            int price = 10;
            int inc = 2;
            
            while ((line = bufferedReader.readLine()) != null) {
                //System.out.println(line);
                String path = "C:\\Users\\Madame_Crawford_V\\eclipse-workspace\\IncrementalGameJava\\src\\IncrementalGameJava\\Assets\\objects.png";
                objectIndex[index] = new Objects(price, inc, line, path, index);
                index++;
                price = price + (price / 5) * 3;
                inc = (int) Math.ceil(price/8 - inc);
            }
            
            // Close the BufferedReader and FileReader
            bufferedReader.close();
            fileReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
		/*
		for (int i = 0; i < 36; i++) {
			System.out.println(objectIndex[i].getName());
		}*/
		this.myStore[0] = new Objects(0,1,"Mr PaperClip", "Mr PaperClip",999);
	}
	
	public Player(String name, GUI gui, int money,	int inc, Objects[] objectIndex, Objects[] myStore, 	int ownStoreCount ) {
		this.name = name;
		this.money = money;
		this.inc = inc;
		this.objectIndex = objectIndex;
		this.myStore = myStore;
		this.ownStoreCount = ownStoreCount;
		this.gui = gui;
	}
	
	public Player(saveState load) {
		this.name = load.getName();
		this.money = load.getMoney();
		this.inc = load.getInc();
		this.objectIndex = load.getObjectIndex();
		this.myStore = load.getMyStore();
		this.ownStoreCount = load.getOwnStoreCount();
		this.gui = null;
	}
		
	
	public void tick() {
		money += inc;
	}
	
	String getLeMoney() {
		//return Integer.toString(price);
		String leMoney = Integer.toString(money);
		if(leMoney.length() < 4) return leMoney;
		else {
			String newPrice = "";
			for (int i = 0; i < leMoney.length(); i++) {
				if((leMoney.length() - i) % 3 == 0) {
					newPrice += " ";
				}
				newPrice += leMoney.charAt(i);
			}
			return newPrice;
		}
	}
	
	String getLeInc() {
		String leInc = Integer.toString(inc);
		if(leInc.length() < 4) return leInc;
		else {
			String newInc = "";
			for (int i = 0; i < leInc.length(); i++) {
				if((leInc.length() - i) % 3 == 0) {
					newInc += " ";
				}
				newInc += leInc.charAt(i);
			}
			return newInc;
		}
	}
	
	public void setBuyListener(BuyListener listener) {
		
	}
	
	public String buyObject(int id) {
		if(money < objectIndex[id].getPrice()) return "Error not enough money";
		
		else if(ownStoreCount > 3) 	return "Error not enough room in store";
		
		else {
			money -= objectIndex[id].getPrice();
			inc += objectIndex[id].getInc();
			
			for (int i = 0; i < 4;i++) {
				if(myStore[i] == null) {
					myStore[i] = objectIndex[id];
					i = 4;
				}
			}
			
			ownStoreCount++;
			return "Item Bought !";
		}
	}
	
	public String sellItem(int index) {
		int price = myStore[index].getValue();
		money += price;
		inc -= myStore[index].getInc();
		myStore[index] = null;
		ownStoreCount--;
		return "Item sold for " + price + " $";
	}
	
	public void gainValue() {
		for(Objects object : myStore) {
			if(object != null) object.gainValue();
		}
		gui.addEntry("Congratulation ! Your items gained value !");
	}
}
