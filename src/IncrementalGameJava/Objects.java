package IncrementalGameJava;

public class Objects {

		int price;
		int inc;
		String name;
		String path;
		
		public Objects(int price, int inc, String name, String path) {
			this.name = name;
			this.price = price;
			this.inc = inc;
			this.path = path;
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
}
