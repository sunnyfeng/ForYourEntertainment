import java.util.HashMap;
import java.util.TreeMap;

public class MoviesMap {
	
	//key: year, value: hashmap with key: word and value: frequency
	HashMap<Integer, HashMap<String, Integer>> map;
	//TreeMap<String, Integer> sorted;
	
	public MoviesMap() {
		map = new HashMap<Integer, HashMap<String, Integer>>();
	}
	
	public boolean add(int year, String word) {
		if (year < 1900 || year > 2020) {
			return false;
		}
		
		if (map.get(year) == null) {
			HashMap<String, Integer> wordfreq = new HashMap<>();
			wordfreq.put(word,1);
			map.put(year, wordfreq);
		} else {
			HashMap<String, Integer> wordfreq = map.get(year);
			if (wordfreq.get(word) == null) {
				wordfreq.put(word, 1);
			} else {
				wordfreq.put(word,wordfreq.get(word) + 1);
			}
			map.put(year, wordfreq);
		}
		
		return true;
	}
	
	public TreeMap<String,Integer> sort(HashMap<String, Integer> words, int year) {
		ValueComparator val =  new ValueComparator(words, year);
		TreeMap<String,Integer> sorted = new TreeMap<String,Integer>(val);
	    sorted.putAll(words);
	    return sorted;
	}
	
	public WordFrequency[] getTopNumWord(int year, int num) {
		HashMap<String, Integer> words = map.get(year);
		if (words == null) {
			return null;
		}
		TreeMap<String,Integer> sorted = sort(words, year);
		if (num > sorted.size()) {
			num = sorted.size();
		}
	    WordFrequency[] arr = new WordFrequency[num];
	    int i = 0;
	    for (String word : sorted.keySet()) {
	   // for (String word : words.keySet()) {
	    		if (i == num) {
	    			break;
	    		}
	    		arr[i] = new WordFrequency(word,words.get(word));
	    		//System.out.println("Word: " + word);
	    		i++;
	    }
	    	return arr;
	}

}
