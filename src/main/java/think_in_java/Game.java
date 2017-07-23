package think_in_java;

public interface Game {
	boolean move();
}

interface GameFactory{
	Game getGame();
}

class ImplGame1 implements Game{
	public boolean move() {
		return false;
	}
}

class ImplGame2 implements Game{
	public boolean move() {
		return false;
	}
}


class ImplGameFactory1 implements GameFactory{
	public Game getGame() {
		return new ImplGame1();
	}
}

class ImplGameFactory2 implements GameFactory{
	public Game getGame() {
		return new ImplGame2();
	}
}

class Games{
	
	public static void playGame(GameFactory gf){
		Game g = gf.getGame();
		g.move();
	}
	public static void main(String[] args) {
		playGame(new ImplGameFactory1());
		playGame(new ImplGameFactory2());
		
	}
	
}


