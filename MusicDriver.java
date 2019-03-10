import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class MusicDriver {
	
	//overall notes:
		// see how the word love gets more or less frequent over time - done
		// drug and alcohol mentions over time - done
		// see how the top 10 songs compare to the rest of them
		// violence mentions (shoot, gun, kill, murder) over time - done
		// explicit words over time - done
	    // sexual words - done
		// gendered words - done
		// you vs I vs we words - done
		// kind vs mean words - done
	
	static String path = "/Users/sunnyfeng/CS112_workspace/GartnerMusic/src/1964-2015.csv";	
	
	public static void main(String[] args) {
		FileScraping scrape = new FileScraping();
		LyricsMap lyricsMap = new LyricsMap();
		scrape.readMusicCSV(lyricsMap, path);
		//scrape.showAllWordsOverTime(lyricsMap);
		
		/*
		scrape.showTopWordsOverTime(10, lyricsMap);
		Set<String> love = new HashSet<>();
		love.add("love");
		scrape.showFrequencyOverTime(lyricsMap,love, "love");
		*/
		
		//scrape.showFrequencyOverTime(lyricsMap, getViolenceSet(), "violence");
		//scrape.showFrequencyOverTime(lyricsMap, getExplicitSet(), "explicit");
		//scrape.showFrequencyOverTime(lyricsMap, getSexualSet(), "sexual");
		//scrape.showFrequencyOverTime(lyricsMap, getDrugsSet(), "drugs");
		
		/*
		String word = "girl";
		Set<String> set = new HashSet<>();
		set.add(word);
		scrape.showFrequencyOverTime(lyricsMap, set, word);
		
		//scrape.showFrequencyOverTime(lyricsMap, getMaleSet(), "male");
		//scrape.showFrequencyOverTime(lyricsMap, getFemaleSet(), "female");
		scrape.showFrequencyOverTime(lyricsMap, getYouSet(), "you");
		scrape.showFrequencyOverTime(lyricsMap, getISet(), "I");
		scrape.showFrequencyOverTime(lyricsMap, getWeSet(), "we");
		scrape.showFrequencyOverTime(lyricsMap, getNiceSet(), "nice");
		scrape.showFrequencyOverTime(lyricsMap, getMeanSet(), "mean");
		*/
		
		//song generation
		HashMap<String, List<String>> grammar = new HashMap<>();
		String[] prep = {"to", "on", "with", "in"};
		grammar.put("prep", Arrays.asList(prep));
		String[] pron = {"I", "you", "she", "he"};
		grammar.put("pron", Arrays.asList(pron));
		String[] end = {"her", "him", "you", "me"};
		grammar.put("end", Arrays.asList(end));
		String[] verb = {"move", "leave", "kill", "shoot", "love", "know", "stop"};
		grammar.put("verb", Arrays.asList(verb));
		String[] before = {"can ", "can't ", "want to ", "won't ", " ", " ", " ", " "};
		grammar.put("before", Arrays.asList(before));
		String[] noun = {"baby", "love", "girl", "time", "crack", "cocaine", "alcohol", "partying"};
		grammar.put("noun", Arrays.asList(noun));
		String[] btwn = {"and", "because", "but", "so"};
		grammar.put("btwn", Arrays.asList(btwn));
		
		for(int i = 0; i < 5; i++) {
			String pronoun = getRandom(grammar.get("pron"));
			String bef = getRandom(grammar.get("before"));
			String s = "s";
			if ((pronoun.equals("I") || pronoun.equals("you")) || !bef.equals(" ")) {
				s = "";
			}
			System.out.println(pronoun + " " + bef  +
					getRandom(grammar.get("verb")) + s + " " + getRandom(grammar.get("end")) + " " + getRandom(grammar.get("btwn")));
			
			pronoun = getRandom(grammar.get("pron"));
			bef = getRandom(grammar.get("before"));
			s = "s";
			if ((pronoun.equals("I") || pronoun.equals("you")) || !bef.equals(" ")) {
				s = "";
			}
			
			System.out.print(pronoun + " " + bef + 
					getRandom(grammar.get("verb")) + s + " " + getRandom(grammar.get("noun")) + "\n");
		}
	}
	
	public static String getRandom(List<String> list) {
		 	Random random = new Random();
		    int listSize = list.size();
		    int randomIndex = random.nextInt(listSize);
		    return list.get(randomIndex);
	}
	
	private static Set<String> getViolenceSet() {
		Set<String> set = new HashSet<>();
		String[] arr = {"kill","murder","shoot","shot","destroy","hurt","punch","stab","gun", "pistol","war",
				"beat", "ruin", "knife", "weapon"};
		set.addAll(Arrays.asList(arr));
		return set;
	}
	
	private static Set<String> getExplicitSet() {
		Set<String> set = new HashSet<>();
		String[] arr = {"fuck","cunt","pussy","vagina","penis","dick","bastard","bitch","shit",
				"nigga", "asshole", "ass", "motherfuckers", "sex", "faggot"};
		set.addAll(Arrays.asList(arr));
		return set;
	}
	
	private static Set<String> getSexualSet() {
		Set<String> set = new HashSet<>();
		String[] arr = {"pussy","vagina","penis","dick","sex","anal","sexy","doggy","cum","hump","ass",
				"butt","sexual","booty","cheeks","boob", "boobs","tits","tit","titties","breasts","nipple","nipples",
				"blowjob","clit","cock", "asshole", "butthole", "suck", "balls"};
		set.addAll(Arrays.asList(arr));
		return set;
	}
	
	private static Set<String> getDrugsSet() {
		Set<String> set = new HashSet<>();
		String[] arr = {"alcohol","beer","drink","wine","vodka","whiskey","drunk","drank","tipsy","high",
				"weed","marijuana","smoke", "joint", "bong", "molly","lsd","xanax","lean", "crack", "cocaine"};
		set.addAll(Arrays.asList(arr));
		return set;
	}
	
	private static Set<String> getMaleSet() {
		Set<String> set = new HashSet<>();
		String[] arr = {"male","man","men","boy","boys","guy","guys","dude","he","his","him","lad","lads",
				"daddy","papi","mister"};
		set.addAll(Arrays.asList(arr));
		return set;
	}
	
	private static Set<String> getFemaleSet() {
		Set<String> set = new HashSet<>();
		String[] arr = {"female","woman","women","girl","girls","gal","gals","shortie","she","her","hers",
				"mami","maam"};
		set.addAll(Arrays.asList(arr));
		return set;
	}
	
	private static Set<String> getYouSet() {
		Set<String> set = new HashSet<>();
		String[] arr = {"you","your","yours","yall"};
		set.addAll(Arrays.asList(arr));
		return set;
	}
	
	private static Set<String> getISet() {
		Set<String> set = new HashSet<>();
		String[] arr = {"i","me","my","mine"};
		set.addAll(Arrays.asList(arr));
		return set;
	}
	
	private static Set<String> getWeSet() {
		Set<String> set = new HashSet<>();
		String[] arr = {"we","our","ours","us"};
		set.addAll(Arrays.asList(arr));
		return set;
	}
	
	private static Set<String> getNiceSet() {
		Set<String> set = new HashSet<>();
		String[] arr = {"nice","love","enjoy","kind", "beautiful", "pretty", "great", "amazing", "lovely",
				"like", "smile", "cute", "cutie"};
		set.addAll(Arrays.asList(arr));
		return set;
	}
	
	private static Set<String> getMeanSet() {
		Set<String> set = new HashSet<>();
		String[] arr = {"hate","anger","angry","hatred", "mad", "horrible", "dislike"};
		set.addAll(Arrays.asList(arr));
		return set;
	}
}
