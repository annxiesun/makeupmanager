
import java.util.Comparator;

//Description: comparator interface to sort by power
public class sortRating implements Comparator <MakeupProduct>{


	public int compare (MakeupProduct one, MakeupProduct two){
		return one.getRating()-two.getRating();
	}
}