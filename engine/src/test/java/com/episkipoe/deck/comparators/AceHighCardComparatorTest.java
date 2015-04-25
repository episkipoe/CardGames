package com.episkipoe.deck.comparators;

import com.episkipoe.deck.Card;
import com.episkipoe.deck.Suit;
import com.episkipoe.deck.Value;
import org.junit.Test;

import static junit.framework.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class AceHighCardComparatorTest {

	@Test
	public void testComparisons() {
		Card aceOfHearts = new Card(Suit.HEART, Value.ACE);
		Card kingOfHearts = new Card(Suit.HEART, Value.KING);
		AceHighCardComparator comparator = new AceHighCardComparator();
		assertTrue(comparator.compare(aceOfHearts, kingOfHearts) > 0);

		Card aceOfDiamonds = new Card(Suit.DIAMOND, Value.ACE);
		assertEquals(0, comparator.compare(aceOfHearts, aceOfDiamonds));
	}

}