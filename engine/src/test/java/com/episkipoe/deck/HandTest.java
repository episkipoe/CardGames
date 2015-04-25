package com.episkipoe.deck;

import org.junit.Test;

import java.util.List;
import java.util.Optional;

import static junit.framework.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class HandTest {
	@Test
	public void testDeck() {
		Hand deck = Hand.getDeck();
		assertEquals(52, deck.getCards().size());

		Optional<Card> c = deck.draw();
		assertTrue(c.isPresent());
		c = deck.draw();

		assertEquals(50, deck.getCards().size());

		List<Hand> hands = deck.deal(2);
		assertEquals(2, hands.size());
		assertEquals(25, hands.get(0).getCards().size());
	}

}