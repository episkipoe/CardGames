package com.episkipoe.deck;

import java.util.*;

/**
 * A collection of {@link Card}s
 */
public class Hand {
	private final List<Card> cards;

	public Hand() {
		this.cards = new ArrayList<>();
	}

	public Hand(List<Card> cards) {
		this.cards = cards;
	}

	/**
	 * @return  A hand containing every card
	 */
	public static Hand getDeck() {
		List<Card> cards = new ArrayList<>();
		for (Suit suit : Suit.values()) {
			for (Value value : Value.values()) {
				cards.add(new Card(suit, value));
			}
		}
		return new Hand(cards);
	}

	/**
	 * Put the cards in a random order
	 */
	public void shuffle() {
		Collections.shuffle(cards);
	}

	public void addCard(Card card) {
		cards.add(card);
	}
	public void addCards(Collection<Card> cardsToAdd) {
		cards.addAll(cardsToAdd);
	}

	/**
	 * @param nHands  generate this many hands
	 * @return a list of n hands
	 */
	public List<Hand> deal(int nHands) {
		List<Hand> hands = new ArrayList<>();
		for (int i = 0 ; i < nHands ; i++) {
			hands.add(new Hand());
		}
		int i=0;
		for (Iterator<Card> iterator = cards.iterator() ; iterator.hasNext();) {
			Card card = iterator.next();
			hands.get(i++ % nHands).addCard(card);
			iterator.remove();
		}
		return hands;
	}

	/**
	 * @return the next card.  it will be removed from the hand
	 */
	public Optional<Card> draw() {
		if(cards.isEmpty()) {
			return Optional.empty();
		}
		return Optional.ofNullable(cards.remove(0));
	}

	public List<Card> getCards() {
		return cards;
	}

	public boolean isEmpty() {
		return cards.isEmpty();
	}
	
}
