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
			return Integer.toString(price);
		}
		
		int getInc() {
			return inc;
		}
		
		String getLeInc() {
			return Integer.toString(inc);
		}
		
		String getInfo() {
			return "Name : " + name + "\n" + "Path : " + path + "\n" + "Price : " + price + "$\n" + "Increment : " + inc + "$ per s.\n";

		}
}
