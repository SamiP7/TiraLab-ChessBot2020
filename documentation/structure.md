## Structure

### Board

Board as the name suggest, gives us the chess board which is represented as a String array. From here we paste the moves on the board which other classes use to check things like moves that they can make, what the opponent can make and so on. We can also undo the latest move from here.

### Bot

This class is used to track the gamestate. Here we paste the opponents moves to our board and also send ours to the bot.

### Moves

Moves class gives all the possible moves that we, and the opponent can do, given our current board position. Here we also *translate* our moves to [UCI](https://en.wikipedia.org/wiki/Universal_Chess_Interface) so that they are valid on the server and also convert them back from UCI so that our board can read them.
This class also handles all the rules of chess which explains its large size. There's a lot of *"copypaste"* code here but this is rather unavoidable unless we want to give up on the flexibility that the methods here give us. Time complexity shouldn't be more than O(n^2) at max, since it has to check your moves and opponents at the same time to return all valid moves.

### MinMax

Basic algorithm which tries to maximise our score and drop the opponents as low as possible with the goal of winning the game of course. It tracks these with the use of heuristics which assign a value for each piece on the board whilst taking into consideration their position as well. Bot does perform quite reasonably given what depth the minimax algorithm can go to. Most of the time it wins against level 3 lichess bot and increasing the algorithms depth, it can even win against level 4.
Currently it analyzes all the possible moves we can make which are provided by the Moves class and tries to pick the best one with the help of minimaxing and heuristics. The algorithm can only reach depth 4 at a reasonable time, where every move takes about 20-30 seconds. This number can go higher or lower depending on the game situation. Minimax uses alpha-beta pruning and move ordering to help it work faster, but even these can only do so much. On the bright side, these do improve the algorithm's speed by quite a lot, since without these the moves took several minutes even at depth 3. Move ordering is especially useful with pruning since this way we can achieve the alpha-beta cutoff way faster which cut down the time at depth 4 by several minutes. If you are more interest about the performance of this algorithm, I talk more about it in the testing document.

### Heuristics

Heuristic used by the program have been gained from [here](https://www.chessprogramming.org/Simplified_Evaluation_Function). This basic heuristic provides favored locations for each piece and their values so the bot knows which pieces it should try to capture and how it should try to move. Move ordering also uses this to sort the moves with a basic insertion sort algorithm working in O(n^2). Making a more complicated heuristic is most likely out of my skill range so I'm not attempting to do that.

### String list

My own ArrayList implementation which can only handle strings. This has most of the basic ArrayList methods and also one unique one called addUnique which only adds the string to the array, if it doesn't contain it.

### Miscellaneous

If you want more info about the classes, check out the javadoc after building the project, which can then be found from this path: */build/docs/javadoc/allclasses.html*

Other classes are provided from [this](https://github.com/TiraLabra/chess) template. They only handle the communication with the Lichess server and you can find more information about them from the given link.

As said before, code contains a lot of copypaste which could probably be avoided but given how many different moves and situations have to be considered for the bot to work, the current implementation isn't that bad. Reworking it would take far too much time so it'll have to do for now. At least it's quite flexible so making any additions to the program won't suddenly break it.

The bot could use a null move heuristic or iterative deepening so the minimax could achieve greater depths with better efficiency. Either or both of these could've maybe been achieved if it didn't take me up to week 4 to finally have all the rules ready for the bot but at least I got the minimax with pruning to work reasonably well, which was my initial goal. Many of the bots games result in a draw since it doesn't have anything to check whether it's next move results in a stalemate or in a fivefold repetition. I tried to implement these a few times but sadly, without any success.

### Sources

https://www.cs.cornell.edu/boom/2004sp/ProjectArch/Chess/algorithms.html

https://www.naftaliharris.com/blog/chess/

https://www.chessprogramming.org/Simplified_Evaluation_Function

https://www.chessprogramming.org/Alpha-Beta

https://www.cs.helsinki.fi/u/ahslaaks/tirakirja/
