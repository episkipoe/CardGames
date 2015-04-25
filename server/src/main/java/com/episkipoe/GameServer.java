package com.episkipoe;

import com.episkipoe.games.CardGame;
import com.google.gson.Gson;

import javax.enterprise.context.RequestScoped;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

@Path("/games/{game}/{data}")
@RequestScoped
public class GameServer {

   /**
    * Advance the game by one round and return the new game state
    * @param gameClass the class of the game
    * @param data the game state in json format
    * @return
    */
   @GET
   @Produces("text/json")
   public String play(@PathParam("game") String gameClass, @PathParam("data") String data) throws ClassNotFoundException {
      Class clazz = Class.forName(gameClass);
      CardGame game = (CardGame) new Gson().fromJson(data, clazz);
      game.next();
      return new Gson().toJson(game);
   }
}