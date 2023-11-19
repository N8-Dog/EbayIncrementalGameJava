package IncrementalGameJava;

import java.util.EventObject;

public class BuyEvent extends EventObject{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1960904268454684368L;
	private String name;
	private int price;
	private int increment;
	private int id;
	
	public BuyEvent(Object source) {
		super(source);
	}
	
	public BuyEvent(Object source, String name, int increment, int price, int id) {
		super(source);
		
		this.name = name;
		this.price = price;
		this.increment = increment;
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getIncrement() {
		return increment;
	}

	public void setIncrement(int increment) {
		this.increment = increment;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	

}
