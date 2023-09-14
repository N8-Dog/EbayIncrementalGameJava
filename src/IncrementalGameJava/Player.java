package IncrementalGameJava;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Player {
	String name;
	int money;
	int inc;
	Objects objectIndex[];
	
	public Player(String name) {
		this.name = name;
		this.money = 100;
		this.inc = 1;
		this.objectIndex = new Objects[36];
		try {
            // Create a FileReader to read the file
            FileReader fileReader = new FileReader("C:\\Users\\Madame_Crawford_V\\eclipse-workspace\\IncrementalGameJava\\src\\IncrementalGameJava\\Assets\\names.objects");
            
            // Wrap the FileReader in a BufferedReader for efficient reading
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            
            String line;
            
            // Read and print each line from the file
            int index = 0;
            int price = 10;
            int inc = 2;
            
            while ((line = bufferedReader.readLine()) != null) {
                System.out.println(line);
                String path = "C:\\Users\\Madame_Crawford_V\\eclipse-workspace\\IncrementalGameJava\\src\\IncrementalGameJava\\Assets\\objects.png";
                objectIndex[index] = new Objects(price, inc, line, path);
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
}
