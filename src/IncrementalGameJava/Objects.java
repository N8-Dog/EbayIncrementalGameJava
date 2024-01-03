package IncrementalGameJava;

import java.io.Serializable;

public class Objects  implements  Serializable {
	
	enum Rarity{
		normal,
		vintage,
		rare,
		unique,
		legendary
	}
	
	enum Condition{
		brandNew,
		likeNew,
		used,
		normalTear,
		asIs
	}

		int price;
		int inc;
		int id;
		String name;
		String path;
		Rarity rarity;
		Condition condition;
		int value;
		
		public Objects(int price, int inc, String name, String path, int id) {
			this.name = name;
			this.price = price;
			this.inc = inc;
			this.path = path;
			this.id = id;
			this.rarity = setRarity();
			this.condition = setCondition();
			this.value = price;
		}
		public Objects(int price, int inc, String name, String path, int id, Rarity rarity, Condition condition) {
			this.name = name;
			this.price = price;
			this.inc = inc;
			this.path = path;
			this.id = id;
			this.rarity = rarity;
			this.condition = condition;
			this.value = price;
		}
		
		String getName() {
			return name;
		}
		
		String getPath() {
			return path;
		}
		
		int getPrice() {
			return price;
		}
		
		String getLePrice() {
			//return Integer.toString(price);
			String lePrice = Integer.toString(price);
			if(lePrice.length() < 4) return lePrice;
			else {
				String newPrice = "";
				for (int i = 0; i < lePrice.length(); i++) {
					if((lePrice.length() - i) % 3 == 0) {
						newPrice += " ";
					}
					newPrice += lePrice.charAt(i);
				}
				return newPrice;
			}
		}
		
		int getInc() {
			return inc;
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
		
		String getInfo() {
			return "Name : " + name + "\n" + "Path : " + path + "\n" + "Price : " + price + "$\n" + "Increment : " + inc + "$ per s.\n";

		}

		public int getId() {
			return id;
		}

		public void setId(int id) {
			this.id = id;
		}
		
		public void gainValue() {
			if(price/100 < 1) {
				price += 1;
			}
			else price += Math.ceil(price / 100);
		}
		
		public String getStats() {
			String stats;
			stats = "<html>Name: " + getName() + "<br>" + "Price : " + getLePrice() + "<br>" + "Condition : " + getLeCondition() + "<br>" + "Income : " + getLeInc() + "<br>" + "Rarity : " + getLeRarity() + "</html>";
			return stats;
		}

		public Rarity getRarity() {
			return rarity;
		}
		
		public String getLeRarity() {
			return rarity.name();
		}

		public Rarity setRarity() {
			Rarity set = Rarity.normal;
			int newPrice = price;
			int newInc = inc;
			double seed = Math.random();
			
			if(seed > 0.97) {
				set = Rarity.legendary;
				newPrice *= 4;
				newInc *= 4;
			}
			
			else if(seed > 0.91) {
				set = Rarity.unique;
				newPrice *= 2;
				newInc *= 2;
			}
			
			else if(seed > 0.84) {
				set = Rarity.rare;
				newPrice *= 1.5;
				newInc *= 1.5;
			}
			
			else if(seed > 0.74) {
				set = Rarity.vintage;
				newPrice *= 1.25;
				newInc *= 1.25;
			}
			setPrice(newPrice);
			setInc(newInc);
			return set;
		}

		public Condition getCondition() {
			return condition;
		}
		
		public String getLeCondition() {
			return condition.name();
		}

		public Condition setCondition() {
			Condition set = Condition.brandNew;
			int newPrice = price;
			int newInc = inc;
			double seed = Math.random();
			
			if(seed > 0.97) {
				set = Condition.asIs;
				newPrice *= 0.25;
				newInc *= 0.25;
			}
			
			else if(seed > 0.91) {
				set = Condition.normalTear;
				newPrice *= 0.5;
				newInc *= 0.5;
			}
			
			else if(seed > 0.84) {
				set = Condition.used;
				newPrice *= 0.65;
				newInc *= 0.65;
			}
			
			else if(seed > 0.74) {
				set = Condition.likeNew;
				newPrice *= 0.75;
				newInc *= 0.75;
			}
			
			setPrice(newPrice);
			setInc(newInc);
			return set;
		}

		public void setPrice(int price) {
			this.price = price;
		}

		public void setInc(int inc) {
			this.inc = inc;
		}
		public int getValue() {
			return value;
		}
		public void setValue(int value) {
			this.value = value;
		}
		
		
}
