
public class TTTRecord {
	private String configuration;
	private int theScore, theLevel;
	public TTTRecord(String config, int score, int level) {
		configuration = config;
		theScore = score;
		theLevel = level;
	}
	public String getConfiguration() {
		return configuration;
	}
	public int getScore() {
		return theScore;
	}
	public int getLevel() {
		return theLevel;
	}
	
}
