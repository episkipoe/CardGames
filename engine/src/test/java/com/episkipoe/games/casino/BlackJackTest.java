package com.episkipoe.games.casino;

import com.episkipoe.deck.Card;
import com.episkipoe.deck.Hand;
import com.episkipoe.deck.Suit;
import com.episkipoe.deck.Value;
import org.junit.Test;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNull;

public class BlackJackTest {

	@Test
	public void testBlackJackPlayerOneWins() {
		Hand deck = new Hand();
		//dealt to player one:  21
		deck.addCard(new Card(Suit.HEART, Value.TEN));
		deck.addCard(new Card(Suit.HEART, Value.ACE));
		//dealt to player two:  9, 17
		deck.addCard(new Card(Suit.CLUB, Value.FOUR));
		deck.addCard(new Card(Suit.CLUB, Value.FIVE));
		deck.addCard(new Card(Suit.CLUB, Value.EIGHT));

		BlackJack game = new BlackJack(deck);
		//first round, player two takes a hit
		game.next();
		assertNull(game.getGameStatus());

		//second round, no one does anything
		game.next();

		assertEquals("Player 1", game.getGameStatus());
	}

	@Test
	public void testBlackJackPlayerTwoBusts() {
		Hand deck = new Hand();
		//dealt to player one:  17
		deck.addCard(new Card(Suit.HEART, Value.TEN));
		deck.addCard(new Card(Suit.HEART, Value.SEVEN));
		//dealt to player two:  15, 25
		deck.addCard(new Card(Suit.CLUB, Value.TEN));
		deck.addCard(new Card(Suit.CLUB, Value.FIVE));
		deck.addCard(new Card(Suit.CLUB, Value.KING));

		BlackJack game = new BlackJack(deck);
		//first round, player two takes a hit
		game.next();
		assertNull(game.getGameStatus());

		//second round, no one does anything
		game.next();
		assertEquals("Player 1", game.getGameStatus());
	}

}