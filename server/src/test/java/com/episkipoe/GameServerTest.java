package com.episkipoe;

import com.episkipoe.deck.Card;
import com.episkipoe.deck.Hand;
import com.episkipoe.deck.Suit;
import com.episkipoe.deck.Value;
import com.episkipoe.games.war.War;
import com.google.gson.Gson;
import org.junit.Test;

import static junit.framework.Assert.assertEquals;

public class GameServerTest {
	@Test
	public void testWar() throws ClassNotFoundException {
		Hand playerOne = new Hand();
		playerOne.addCard(new Card(Suit.HEART, Value.TWO));
		playerOne.addCard(new Card(Suit.HEART, Value.FOUR));
		playerOne.addCard(new Card(Suit.HEART, Value.FIVE));

		Hand playerTwo = new Hand();
		playerTwo.addCard(new Card(Suit.CLUB, Value.THREE));
		playerTwo.addCard(new Card(Suit.CLUB, Value.FOUR));
		playerTwo.addCard(new Card(Suit.CLUB, Value.SIX));

		War game = new War(playerOne, playerTwo);

		GameServer server = new GameServer();
		String result = server.play(War.class.getName(), new Gson().toJson(game));

		War secondRound = new Gson().fromJson(result, War.class);

		result = server.play(War.class.getName(), new Gson().toJson(secondRound));

		War conclusion = new Gson().fromJson(result, War.class);
		assertEquals("Player two wins", conclusion.getGameStatus());
	}

}