import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Set;

public class FileScraping {

	public FileScraping() {}
	
	void showTopWordsOverTime(int numWords, LyricsMap lyricsMap) {
		try(PrintWriter pw = new PrintWriter(new File("/Users/sunnyfeng/Documents/2019 Spring/HackRU-S19/top" + numWords + "words.csv"))) {
			StringBuilder sb = new StringBuilder();
			sb.append("Year,Word,Frequency\n");
			for (int year = 1964; year < 2016; year++) {
				//System.out.println("--------Year: " + year);
				WordFrequency[] arr = lyricsMap.getTopNumWord(year, numWords);
				if (arr == null) {
					System.out.println("no info for year " + year);
					continue;
				}
				for (WordFrequency wf : arr) {
					if (wf != null) {
						//System.out.println("Word: " + wf.word + " \t\t\tFreq: " + wf.freq);
						sb.append(year);
						sb.append(",");
						sb.append(wf.word);
						sb.append(",");
						sb.append(wf.freq);
						sb.append("\n");
					}
				}
			}
			pw.write(sb.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	void showAllWordsOverTime(LyricsMap lyricsMap) {
		try(PrintWriter pw = new PrintWriter(new File("/Users/sunnyfeng/Documents/2019 Spring/HackRU-S19/topAllwords.csv"))) {
			StringBuilder sb = new StringBuilder();
			sb.append("Year,Word,Frequency\n");
			for (int year = 1964; year < 2016; year++) {
				//System.out.println("--------Year: " + year);
				WordFrequency[] arr = lyricsMap.getTopNumWord(year, Integer.MAX_VALUE);
				if (arr == null) {
					System.out.println("no info for year " + year);
					continue;
				}
				for (WordFrequency wf : arr) {
					if (wf != null) {
						//System.out.println("Word: " + wf.word + " \t\t\tFreq: " + wf.freq);
						sb.append(year);
						sb.append(",");
						sb.append(wf.word);
						sb.append(",");
						sb.append(wf.freq);
						sb.append("\n");
					}
				}
			}
			pw.write(sb.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	void showFrequencyOverTime(LyricsMap lyricsMap, Set<String> set, String setName) {
		try(PrintWriter pw = new PrintWriter(new File("/Users/sunnyfeng/Documents/2019 Spring/HackRU-S19/" + setName + "overtime.csv"))) {
			StringBuilder sb = new StringBuilder();
			sb.append("Year,Word,Frequency\n");
			for (int year = 1964; year < 2016; year++) {
				HashMap<String, Integer> wordMap = lyricsMap.map.get(year);
				if (wordMap == null) {
					System.out.println("no info for year " + year);
					continue;
				}
				
				sb.append(year);
				sb.append(",");
				
				int freq = 0;
				for(String word : set) {
					if (wordMap.get(word) != null) {
						freq += wordMap.get(word);
						sb.append(word);
						sb.append(" ");
					}
				}
				//System.out.println("Word: " + wf.word + " \t\t\tFreq: " + wf.freq);
				sb.append(",");
				sb.append(freq);
				sb.append("\n");
			}
			pw.write(sb.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void readMusicCSV(LyricsMap lyricsMap, String path) {

		//read in CSV and load map
		String line = "";
		try (BufferedReader br = new BufferedReader(new FileReader(path))) {
			//don't look at headings
			line = br.readLine();
			
			//read in csv file
			while ((line = br.readLine()) != null) {
				String[] data = line.split(",");
				if (data.length > 5) {
					String yearString = data[3];
					int year = Integer.parseInt(yearString);
					String lyrics = data[4];
					String[] words = lyrics.split("\\s+");
					for (String word : words) {
						if (isNegligible(word.trim())) {
							continue;
						}
						boolean status = lyricsMap.add(year,  word.trim());
						if (!status) {
							System.out.println("Unable to add because of year: " + year);
						}
					}
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private boolean isNegligible(String word) {
		String[] negligible = {"and", "the", "to", "with", "a", "an", "that", 
								"of", "on", "be", "is", "in", "it", "for", "its", "oh",
								"you", "your", "my", "me", "i", "no", "im", "dont", "all", 
								"but", "do", "like", "so", "up", "we", "us", "this", "yeah", 
								"what", "when", "they", "them", };
		for (String str : negligible) {
			if (word.equals(str)) {
				return true;
			}
		}
		return false;
	}
	
}
