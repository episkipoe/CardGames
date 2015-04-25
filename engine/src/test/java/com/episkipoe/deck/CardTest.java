package com.episkipoe.deck;

import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class CardTest {

	@Test
	public void testCard() {
		Card firstCard = new Card(Suit.HEART, Value.ACE);
		Card secondCard = new Card(Suit.HEART, Value.KING);
		assertTrue(firstCard.sameSuit(secondCard));
	}
}