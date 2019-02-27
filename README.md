# SpectrangleGame

### Description
This project is the implementation of the board game called Spectrangle . This game can be played by 2-4 people. Within this project, the game is implemented with a client-server connection so that a game can be played by players in different computer or against a naive AI.
This project contatins the following features:
- Basic implementation of the Spectrangle game.
- A working client-server network that controls a game.


### Getting started
This game implemented in this project follow the protocols in this link [here](https://git.snt.utwente.nl/snippets/23)

##### TO START A SERVER
Inside the package “server”, you can find the class Server with the main function. You can specify the port number using configuration to start the server.

##### TO START A CLIENT
Inside the package “client”, you can find the class Client with the main function. After starting the server, you can specify the IP address and the port number using configuration to start a client. The IP address can be “localhost” if you are using the Server class inside this project.
##### TO LEAVE THE GAME
The client only has to type “leave” and everything else will be handle by the Server.
When the client is connected, it will be displayed in the server console.

### GamePlay
 To enter tile use `XXXV` with the first `X` being the left color and second `X` being the right color and the last `X` being the bottom color and `V` being the value of the tile from 1-6. 

 For a color: only use upper case and one of the following list
    
   - R: red
   - B: blue
   - G: green
   - Y: yellow
   - P: purple
   - W: white
   
 Use `placeTile <index> <tile>`  (with the <> brackets)to place a tile on the board when it is your turn.
 - throws: 403 if it is not the player's turn
 - throws: 404 if the player does not have the tile, the index is invalid or the tile may not be placed there
 
 Use `switchTile <tile>` (without the <> brackets) to switch tiles
 - throws: 403 if it is not the player's turn
 - throws: 404 if the tile is invalid, the player does not have that tile.
 
 Use `requestMove` to request a move from the other player.
 
 Use `leave` to leave the game.
 
 USe `skipMove` to skip a move.
 - throws: 403 if player may not skip a move or it is not the player's turn
### Test
##### TO USE THE TEST CASES
Every test cases can be found inside “test” package.