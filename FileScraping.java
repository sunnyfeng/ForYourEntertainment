import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Set;

public class FileScraping {

	public FileScraping() {}
	
	void showAllWordsOverTime(MoviesMap lyricsMap) {
		try(PrintWriter pw = new PrintWriter(new File("/Users/sunnyfeng/Documents/2019 Spring/HackRU-S19/allMovieGenres.csv"))) {
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
	
	
	void showFrequencyOverTime(MoviesMap lyricsMap, Set<String> set, String setName) {
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

	public void readMovieCSV(MoviesMap map, String path) {

		//read in CSV and load map
		String line = "";
		try (BufferedReader br = new BufferedReader(new FileReader(path))) {
			//don't look at headings
			line = br.readLine();
			
			//read in csv file
			while ((line = br.readLine()) != null) {
				String[] data = line.split(",");
				if (data.length > 2) {
					String titleAndYear = data[1];
					System.out.println(titleAndYear);
					String[] title = titleAndYear.trim().split("[\\(||\\)]");
					if (title.length > 1) {
						int year = Integer.parseInt(title[title.length-1]);
						String genresString = data[2];
						String[] genres = genresString.split("\\|");
						for (String word : genres) {
							boolean status = map.add(year,  word.trim());
							if (!status) {
								System.out.println("Unable to add because of year: " + year);
							}
						}
					}
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
