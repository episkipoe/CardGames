#a dummy client, to verify the functioning of the REST API

import requests
import json

#return a new game with a get request
def newGame():
    response = requests.get('http://localhost:9090/card-games-server/games/com.episkipoe.games.war.War?data=new');
    return response.text;

#get the next state with a post request
def nextRound(state):
    payload = {'game': 'com.episkipoe.games.war.War', 'data' : state}
    response = requests.post('http://localhost:9090/card-games-server/games/com.episkipoe.games.war.War/', data=payload);
    return response.json();


gameJson = newGame();
firstRound = nextRound(gameJson)
print "Player one has " + str(len(firstRound['playerOne']['cards'])) + " cards and player two has " + str(len(firstRound['playerTwo']['cards']));

secondRound = nextRound(json.dumps(firstRound))
print "Player one has " + str(len(secondRound['playerOne']['cards'])) + " cards and player two has " + str(len(secondRound['playerTwo']['cards']));

thirdRound = nextRound(json.dumps(secondRound))
print "Player one has " + str(len(thirdRound['playerOne']['cards'])) + " cards and player two has " + str(len(thirdRound['playerTwo']['cards']));

#and so on...
