
import java.util.Comparator;

public class sortRecent implements Comparator <MakeupProduct> {

	public int compare (MakeupProduct one, MakeupProduct two){
		return one.getID()-two.getID();
	}

}
