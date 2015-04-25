package com.episkipoe.deck.comparators;

import com.episkipoe.deck.Card;

import java.util.Comparator;

public class AceHighCardComparator implements Comparator<Card> {
	@Override
	public int compare(Card o1, Card o2) {
		return o1.getValue().compareTo(o2.getValue());
	}
}
