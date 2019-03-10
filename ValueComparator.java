import java.util.Comparator;
import java.util.HashMap;

public class ValueComparator implements Comparator<String> {

	    HashMap<String, Integer> base;
	    int year;
	    
	    public ValueComparator(HashMap<String, Integer> map, int year) {
	        this.base = map;
	        this.year = year;
	    }
	  
	    public int compare(String a, String b) {
	        if (base.get(a) >= base.get(b)) {
	            return -1;
	        } else {
	            return 1;
	        }
	    }
	}