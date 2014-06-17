package game;

/**
 * Game type enumerator for the game. This is used to determine what type of
 * game is being played.
 * 
 * @author Matt
 */
public enum GameType {
	AItype1vs1, AItype2vs2, AItypeFreeForAll, type1vs1, type2vs2, typeFreeForAll;
	static int difficulty = 1;

	/**
	 * Sets the difficulty level of the game.
	 * 
	 * @param difficultyParam
	 *            : int - 1 being easy, 2 being medium, 3 being hard
	 */
	public static void setDifficulty(int difficultyParam) {
		difficulty = difficultyParam;
	}

	/**
	 * Gets the difficulty level of the game.
	 * 
	 * @return difficulty : Int - the game difficulty.
	 */
	public static int getDifficulty() {
		return difficulty;
	}
}