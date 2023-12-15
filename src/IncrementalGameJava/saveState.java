package IncrementalGameJava;

import java.awt.Color;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

import IncrementalGameJava.Objects.Condition;
import IncrementalGameJava.Objects.Rarity;

public class saveState  implements  Serializable {
	private String name;
	private int inc;
	private int money;
	private Objects objectIndex[];
	private Objects myStore[];
	private int ownStoreCount;
	
	public saveState(String name, GUI gui, int money,	int inc, Objects[] objectIndex, Objects[] myStore, 	int ownStoreCount ) {
		this.name = name;
		this.money = money;
		this.inc = inc;
		this.objectIndex = objectIndex;
		this.myStore = myStore;
		this.ownStoreCount = ownStoreCount;
	}
	
	public saveState(Player player) {
		this.name = player.name;
		this.money = player.money;
		this.inc = player.inc;
		this.objectIndex = player.objectIndex;
		this.myStore = player.myStore;
		this.ownStoreCount = player.ownStoreCount;
	}
	
	public saveState(String path) {
		////init
		 String line = loadSaveString(path);
		 String[] splitLine = line.split("\\$");
		 this.myStore = new Objects[4];
		 this.objectIndex = new Objects[36];
		 
		 ////player info
		 String[] playerInfo = splitLine[0].split(",");
		 this.name = playerInfo[0];
		 this.money = Integer.parseInt(playerInfo[1]);
		 this.inc = Integer.parseInt(playerInfo[2]);
		 this.ownStoreCount = Integer.parseInt(playerInfo[3]);
		 
		 ////myStore
		 
		 String[] loadMyStore = splitLine[1].split("&");
		 for(int i = 0; i < 4; i++) {
			 String[] loadMyStoreObject = loadMyStore[i].split(",");
			if(loadMyStoreObject[1].equals("empty")) myStore[i] = null;
			else {
				int id = Integer.parseInt(loadMyStoreObject[1]);
				int price = Integer.parseInt(loadMyStoreObject[2]);
				int inc = Integer.parseInt(loadMyStoreObject[3]);
				String name = loadMyStoreObject[4];
				Condition cond = stringToCondition(loadMyStoreObject[5]);
				Rarity rare = stringToRarity(loadMyStoreObject[6]);		
				 this.myStore[i] = new Objects(price, inc, name, path, id, rare, cond);
			}
		 }
		 
		 
		 ////Store
		 Objects[] store = new Objects[36];
		 String[] loadStore = splitLine[2].split("&");
		 for(int i = 0; i < loadStore.length-1; i++) {
			 String[] loadStoreObject = loadStore[i].split(",");
			 int id = Integer.parseInt(loadStoreObject[1]);
				int price = Integer.parseInt(loadStoreObject[2]);
				int inc = Integer.parseInt(loadStoreObject[3]);
				String name = loadStoreObject[4];
				Condition cond = stringToCondition(loadStoreObject[5]);
				Rarity rare = stringToRarity(loadStoreObject[6]);		
				this.objectIndex[i] = new Objects(price, inc, name, path, id, rare, cond);
		 }
	}


	public String getName() {
		return name;
	}

	public int getMoney() {
		return money;
	}

	public int getInc() {
		return inc;
	}

	public Objects[] getObjectIndex() {
		return objectIndex;
	}

	public Objects[] getMyStore() {
		return myStore;
	}

	public int getOwnStoreCount() {
		return ownStoreCount;
	}
	
	private ArrayList<String> getArray() {
		ArrayList<String> saveArray = new ArrayList<String>();
		saveArray.add(name);
		saveArray.add(Integer.toString(money));
		saveArray.add(Integer.toString(inc));
		saveArray.add(Integer.toString(ownStoreCount));
		return saveArray;
	}
	
	private ArrayList<String> getStoreArray() {
		return getObjectArray(objectIndex);
	}
	
	private ArrayList<String> getMyStoreArray() {
		return getObjectArray(myStore);
	}
	
	//Id, price, inc, name, path
	private ArrayList<String> getObjectArray(Objects[] objectArray) {
		ArrayList<String> objArray = new ArrayList<String>();
		for(Objects obj : objectArray) {
			if(obj == null) {
				for (int i = 0; i < 5; i++) {
					objArray.add("empty");
				}
				objArray.add("&");
				//return objArray;
			}
			else {
			objArray.add(Integer.toString(obj.getId()));
			objArray.add(Integer.toString(obj.getPrice()));
			objArray.add(Integer.toString(obj.getInc()));
			objArray.add(obj.getName());
			//objArray.add(obj.getPath());
			objArray.add(obj.getLeCondition());
			objArray.add(obj.getLeRarity());
			objArray.add("&");
			}
		}
		return objArray;
	}
	
	
	
	public String getSaveString() {
		String saveString = "";
		for(String content: getArray()) {
			//System.out.println(content);
			saveString += content + ",";
		}

		saveString += "$,";
		for(String content: getMyStoreArray()){
			//System.out.println(content);
			saveString += content + ",";
		}
		saveString += "$,";
		for(String content: getStoreArray()){
			//System.out.println(content);
			saveString += content + ",";
		}
		saveString += "$,";
		//System.out.println("-------------------------------------------");
		//System.out.println(saveString);
		return saveString;
	}
	
	public String loadSaveString(String path) {
		String line = null;
		try {
            line = new String(Files.readAllBytes(Paths.get(path)));
            System.out.println("File content:\n" + line);
        } catch (IOException e) {
            System.err.println("Error reading the file: " + e.getMessage());
        }

		return line;
	}
	
	public Condition stringToCondition(String string) {
		Condition cond = null;
  		switch(string) {
		case "likeNew":
			cond = Condition.likeNew;
			break;
		case "used":
			cond = Condition.used;
			break;
		case "asIs":
			cond = Condition.asIs;
			break;
		case "normalTear":
			cond = Condition.normalTear;
			break;
		case("brandNew"):
			cond = Condition.brandNew;
			break;
		default:
			cond = null;
			break;
		}
  		
  		return cond;
	}
	
	public Rarity stringToRarity(String string) {
		Rarity rare = null;
  		switch(string) {
		case "normal":
			rare = Rarity.normal;
			break;
		case "vintage":
			rare = Rarity.vintage;
			break;
		case "rare":
			rare = Rarity.rare;
			break;
		case "unique":
			rare = Rarity.unique;
			break;
		case("legendary"):
			rare = Rarity.legendary;
		default:
			rare = null;
			break;
		}
  		
  		return rare;
	}

}
