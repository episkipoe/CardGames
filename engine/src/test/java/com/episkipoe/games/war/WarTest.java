package com.episkipoe.games.war;

import com.episkipoe.deck.Card;
import com.episkipoe.deck.Hand;
import com.episkipoe.deck.Suit;
import com.episkipoe.deck.Value;
import org.junit.Test;

import static junit.framework.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class WarTest {
	@Test
	public void testWar() {
		Hand playerOne = new Hand();
		playerOne.addCard(new Card(Suit.HEART, Value.TWO));
		playerOne.addCard(new Card(Suit.HEART, Value.FOUR));
		playerOne.addCard(new Card(Suit.HEART, Value.FIVE));

		Hand playerTwo = new Hand();
		playerTwo.addCard(new Card(Suit.CLUB, Value.THREE));
		playerTwo.addCard(new Card(Suit.CLUB, Value.FOUR));
		playerTwo.addCard(new Card(Suit.CLUB, Value.SIX));

		War game = new War(playerOne, playerTwo);
		game.next();
		assertEquals(2, playerOne.getCards().size());
		assertEquals(4, playerTwo.getCards().size());
		game.next();
		assertEquals(0, playerOne.getCards().size());
		assertEquals(6, playerTwo.getCards().size());
		game.next();
		assertTrue(playerOne.getCards().isEmpty());
		assertEquals("Player two wins", game.getGameStatus());
	}

	@Test
	public void testTie() {
		Hand playerOne = new Hand();
		playerOne.addCard(new Card(Suit.HEART, Value.THREE));
		playerOne.addCard(new Card(Suit.HEART, Value.FOUR));

		Hand playerTwo = new Hand();
		playerTwo.addCard(new Card(Suit.CLUB, Value.THREE));
		playerTwo.addCard(new Card(Suit.CLUB, Value.FOUR));

		War game = new War(playerOne, playerTwo);
		game.next();
		assertEquals("Tie", game.getGameStatus());
	}

}