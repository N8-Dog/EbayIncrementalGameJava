package IncrementalGameJava;

import java.util.EventListener;

public interface SellListener extends EventListener{
	public void sellEventOccured(SellEvent e);
}
