package IncrementalGameJava;

import java.util.EventObject;

public class SellEvent extends EventObject	{
	/**
	 * 
	 */
	private static final long serialVersionUID = -5106591078101748350L;
	private Objects object;
	public SellEvent(Object source) {
		super(source);
		// TODO Auto-generated constructor stub
	}

	public SellEvent(Object source, Objects object) {
		super(source);
		this.object = object;
	}
	
	public Objects getObject() {
		return object;
	}
}
