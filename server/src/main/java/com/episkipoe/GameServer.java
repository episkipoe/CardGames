package com.episkipoe;

import com.episkipoe.games.CardGame;
import com.google.gson.Gson;

import javax.enterprise.context.RequestScoped;
import javax.ws.rs.*;
import javax.ws.rs.core.MultivaluedMap;

@RequestScoped
@Path("/games/{game}")
public class GameServer {

	/**
	 * Advance the game by one round and return the new game state
	 *
	 * @param gameClass the class of the game
	 * @param data      the game state in json format.  if this equals "new" then a new game will be generated and returned
	 * @return
	 */
	@GET
	@Produces("text/json")
	public String get(@PathParam("game") String gameClass, @QueryParam("data") String data) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
		return play(gameClass, data);
	}

	@POST
	@Produces("text/json")
	public String post(@PathParam("game") String gameClass, MultivaluedMap<String, String> formParams) throws IllegalAccessException, InstantiationException, ClassNotFoundException {
		return play(gameClass, formParams.getFirst("data"));
	}

	private String play(String gameClass, String data) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
		Class clazz = Class.forName(gameClass);
		CardGame game;
		if (data.equals("new")) {
			game = (CardGame) clazz.newInstance();
			game.start();
		} else {
			game = (CardGame) new Gson().fromJson(data, clazz);
			game.next();
		}
		return new Gson().toJson(game);
	}

}
