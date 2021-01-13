import java.util.Comparator;

//Description: comparator interface to sort by power
public class sortPrice implements Comparator <MakeupProduct>{


	public int compare (MakeupProduct one, MakeupProduct two){
		return ((int)((one.getPrice()-two.getPrice())*100));
	}
}