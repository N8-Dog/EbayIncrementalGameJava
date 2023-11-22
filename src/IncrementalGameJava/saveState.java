package IncrementalGameJava;

import java.io.Serializable;
import java.util.ArrayList;

public class saveState  implements  Serializable {
	private String name;
	private int money;
	private int inc;
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

		saveString += "$";
		for(String content: getMyStoreArray()){
			//System.out.println(content);
			saveString += content + ",";
		}
		saveString += "$";
		for(String content: getStoreArray()){
			//System.out.println(content);
			saveString += content + ",";
		}
		saveString += "$";
		//System.out.println("-------------------------------------------");
		//System.out.println(saveString);
		return saveString;
	}

}
