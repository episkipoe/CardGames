package com.episkipoe.deck;


/**
 * A single playing card.  Has a {@link Suit} and a {@link Value}
 */
public class Card {
	private final Suit suit;
	private final Value value;

	public Card(Suit suit, Value value) {
		this.suit = suit;
		this.value = value;
	}

	public Suit getSuit() {
		return suit;
	}

	public boolean sameSuit(Card other) {
		return other.getSuit().equals(suit);
	}

	public Value getValue() {
		return value;
	}

	@Override
	public String toString() {
		return value + " of " + suit;
	}
}
