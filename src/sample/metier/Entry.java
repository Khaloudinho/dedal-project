package sample.metier;

public class Entry {

	private String name;
	private int points;

	/**
	 * 
	 * @param name
	 * @param points
	 */
	public Entry(String name, int points) {
		this.name = name;
		this.points = points;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPoints() {
		return points;
	}

	public void setPoints(int points) {
		this.points = points;
	}
}