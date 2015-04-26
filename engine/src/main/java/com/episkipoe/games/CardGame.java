package com.episkipoe.games;

public interface CardGame {
	/**
	 * initialize the game state with reasonable defaults
	 */
	void start();

	/**
	 * Advance the game by one turn
	 */
	void next();
}
