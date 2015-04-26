package com.episkipoe.games.casino;

import com.episkipoe.deck.Card;
import com.episkipoe.deck.Hand;
import com.episkipoe.deck.Value;
import com.episkipoe.games.CardGame;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class BlackJack implements CardGame {
	private Hand deck;
	private List<Player> players = new ArrayList<>();
	private String gameStatus;

	public BlackJack() {
	}

	public BlackJack(Hand deck) {
		this.deck = deck;
		newGame(2);
	}

	@Override
	public void start() {
		deck = Hand.getShuffledDeck();
		newGame(2);
	}

	public static class Player {
		public String name;
		private Hand hand;
		enum Action {DEFAULT, HIT, STAND}
		public Action action;
		public int handValue;
		//TODO:  betting

		private Player(String name, Hand hand) {
			this.name = name;
			this.hand = hand;
			this.action = Action.DEFAULT;
			this.handValue = getHandValue(hand);
		}

		private boolean requestsCard() {
			if (action == Action.DEFAULT) {
				return (handValue > 0 && handValue < 17);
			}
			return (action == Action.HIT);
		}

		private boolean addCard(Hand deck) {
			boolean cardAdded = hand.addCard(deck.draw());
			if(cardAdded) {
				this.handValue = getHandValue(hand);
			}
			return cardAdded;
		}
	}

	private void dealCardsToPlayers() {
		for (Player player : players) {
			player.hand = new Hand();
			boolean firstCardAdded = player.hand.addCard(deck.draw());
			boolean secondCardAdded = player.hand.addCard(deck.draw());
			//if we run out of cards, get a new deck and continue
			if (!firstCardAdded || !secondCardAdded) {
				deck = Hand.getShuffledDeck();
				dealCardsToPlayers();
			}
		}
	}


	/**
	 * Start a new game, with default configuration
	 */
	public void newGame(int nPlayers) {
		players = new ArrayList<>();
		int playerNumber = 1;
		for (Hand hand : deck.deal(nPlayers, 2)) {
			String name = "Player " + playerNumber++;
			players.add(new Player(name, hand));
		}
	}

	@Override
	public void next() {
		boolean anyHits = false;
		//apply player actions
		for (Player player : players) {
			if (player.requestsCard()) {
				if (!player.addCard(deck)) {
					deck = Hand.getShuffledDeck();
					player.addCard(deck);
				}
				anyHits = true;
			}
		}

		//check for the end of the round
		if (!anyHits) {
			determineWinner();
			//start the next round
			dealCardsToPlayers();
		}
	}

	private void determineWinner() {
		if (players.isEmpty()) {
			gameStatus = "No players";
		} else {
			//sort highest score first

			//N.B.  the lambda form is cleaner, but not supported by Jersey:  https://java.net/jira/browse/JERSEY-2429
			//Collections.sort(players, (p1, p2) -> Integer.compare(p2.handValue, p1.handValue));
			Collections.sort(players, new Comparator<Player>() {
				@Override
				public int compare(Player p1, Player p2) {
					return Integer.compare(p2.handValue, p1.handValue);
				}
			});
			int highScore = players.get(0).handValue;
			if (highScore < 0) {
				gameStatus = "All busted";
			} else {
				List<String> winners = new ArrayList<>();
				for (Player player : players) {
					if (player.handValue == highScore) {
						winners.add(player.name);
					} else {
						break;
					}
				}
				gameStatus = StringUtils.join(winners, ", ");
			}
		}
	}

	/**
	 * @param hand the hand to evaluate
	 * @return the value of the hand.
	 * if the card values sum to greater than 21, the hand busts and -1 will be returned
	 */
	private static int getHandValue(Hand hand) {
		int sum = 0;
		int numberOfAces = 0;
		for (Card card : hand.getCards()) {
			if (card.getValue().isFaceCard()) {
				sum += 10;
			} else if (card.getValue() == Value.ACE) {
				numberOfAces++;
				sum += 11;
			} else {
				sum += card.getValue().getValue();
			}
		}
		//if aces would cause us to bust, then treat them as ones
		while (sum > 21 && numberOfAces > 0) {
			sum -= 10;
			numberOfAces--;
		}
		if (sum > 21) {
			return -1;
		}
		return sum;
	}

	public List<Player> getPlayers() {
		return players;
	}

	public String getGameStatus() {
		return gameStatus;
	}
}
