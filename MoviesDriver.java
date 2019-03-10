
public class MoviesDriver {
	static String movies = "/Users/sunnyfeng/CS112_workspace/GartnerMovies/src/movies.csv";	
	
	public static void main(String[] args) {
		FileScraping scrape = new FileScraping();
		MoviesMap map = new MoviesMap();
		scrape.readMovieCSV(map, movies);
		scrape.showAllWordsOverTime(map);
	}
	
	
}
