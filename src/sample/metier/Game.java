package sample.metier;

public class Game {

	private Player p, p2;

	public Game(Player p, Player p2) {
		this.p = p;
		this.p2 = p2;
	}

	public void start() {
		p.throwDice();
		p2.throwDice();
	}

}