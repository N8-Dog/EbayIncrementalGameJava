package IncrementalGameJava;

public class Dog {
	int price;
	int inc;
	String name;
	String breed;
	
	public Dog(int price, int inc, String name, String breed) {
		this.name = name;
		this.breed = breed;
		this.price = price;
		this.inc = inc;
	}
	
	String getName() {
		return name;
	}
	
	String getBreed() {
		return breed;
	}
	
	int getPrice() {
		return price;
	}
	
	int getInc() {
		return inc;
	}
	
	String getInfo() {
		return "Name : " + name + "\n" + "Breed : " + breed + "\n" + "Price : " + price + "$\n" + "Increment : " + inc + "$ per s.\n";
	}
}
