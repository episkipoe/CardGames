package com.episkipoe.games.war;

import com.episkipoe.deck.Card;
import com.episkipoe.deck.Hand;
import com.episkipoe.deck.comparators.AceHighCardComparator;
import com.episkipoe.games.CardGame;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class War implements CardGame {
	private Hand playerOne;
	private Hand playerTwo;
	private String gameStatus;
	private final List<Card> cardsInBattle = new ArrayList<>();

	public War() {
	}

	public War(Hand playerOne, Hand playerTwo) {
		this.playerOne = playerOne;
		this.playerTwo = playerTwo;
	}

	@Override
	public void start() {
		List<Hand> hands = Hand.getShuffledDeck().deal(2);
		this.playerOne = hands.get(0);
		this.playerTwo = hands.get(1);
	}

	@Override
	public void next() {
		Optional<Card> one = playerOne.draw();
		Optional<Card> two = playerTwo.draw();

		//play the round
		if (one.isPresent() && two.isPresent()) {
			battleCards(one.get(), two.get());
		}

		//check the result
		if (playerOne.isEmpty() && playerTwo.isEmpty()) {
			gameStatus = "Tie";
		} else if(playerOne.isEmpty()) {
			gameStatus = "Player two wins";
		} else if (playerTwo.isEmpty()) {
			gameStatus = "Player one wins";
		}
	}

	private void battleCards(Card one, Card two) {
		cardsInBattle.add(one);
		cardsInBattle.add(two);
		Comparator<Card> comparator = new AceHighCardComparator();
		int comparation = comparator.compare(one, two);
		if(comparation > 0) {
			playerOne.addCards(cardsInBattle);
			cardsInBattle.clear();
		} else if(comparation < 0)	 {
			playerTwo.addCards(cardsInBattle);
			cardsInBattle.clear();
		} else {
			next();
		}
	}

	public String getGameStatus() {
		return gameStatus;
	}
}
