import java.util.Comparator;

public class sortPriceWeight implements Comparator <MakeupProduct> {

	public int compare (MakeupProduct one, MakeupProduct two){
		double num1 = one.getPrice()/one.getWeight();
		double num2 = two.getPrice()/two.getWeight();
		
		return (int)(num1-num2*100);
	}

}
