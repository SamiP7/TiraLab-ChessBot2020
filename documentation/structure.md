## Structure

### Board

Board as the name suggest, gives us the chess board which is represented as a String array. From here we paste the moves on the board which other classes use to check things like moves that they can make, what the opponent can make and so on.

### Bot

This class is used to track the gamestate. Here we paste the opponents moves to our board and also send ours to the bot.

### Moves

Moves class gives all the possible moves that we, and the opponent can do, given our current board position. Here we also *translate* our moves to [UCI](https://en.wikipedia.org/wiki/Universal_Chess_Interface) so that they are valid on the server and also convert them back from UCI so that our board can read them.
This class also handles all the rules of chess which explains its large size. There's a lot of *"copypaste"* code here but this is rather unavoidable unless we want to give up on the flexibility that the methods here give us. Time complexity shouldn't be more than O(n^2) at max, since it has to check your moves and opponents at the same time to return all valid moves.

### Pieces

Currently quite redundant class so it will most likely be deleted in the future.

### MinMax

Basic algorithm which tries to maximise our score and drop the opponents as low as possible with the goal of winning the game of course. With its current status, it can comfortably win against level 3 bot, can hold it's own against level 4 but level 5 crushes it.
Currently it analyzes all the possible moves we can make which are provided by the Moves class and tries to pick the best one with the help of minmaxing and heuristics. Currently it can only reach depth 4 and it's quite slow even then. This is going to be worked on by the help of another algorithm or by trying to make the current implementation more efficient. As of now, I can't even guess the time complexity, especially with pruning, since on every node it has to fetch both your and your opponents moves and already at depth 4, this is already going through hundreds of thousands of nodes. Time could be saved if I implemented move ordering and depending how well that was made, we could go up to depth 6 with more speed than we have now. In consideration though, without any kind of pruning we would have to go through **a^n** nodes where n is depth and a is moves for each node visited. So at depth 4 this can easily go to over a million nodes. Depth might still have to sacrificed in the future for more speed since it quite literally runs at a snail's pace in situations where it's critical to make the absolute best move.

### Heuristics

Heuristic used by the program have been gained from [here](https://www.chessprogramming.org/Simplified_Evaluation_Function). This basic heuristic provides favored locations for each piece and their values so the bot knows which pieces it should try to capture and how it should try to move. Making a more complicated one is most likely out of my skill range so I'm not attempting to make my own.

### String list

My own ArrayList implementation which can only handle strings. This has most of the basic ArrayList methods and also one unique one called addUnique which only adds the string to the array, if it doesn't contain it.

### Miscellaneous

If you want more info about the classes, check out the javadoc after building the project, which can then be found from this path: */build/docs/javadoc/allclasses.html*

Other classes are provided from [this](https://github.com/TiraLabra/chess) template. They only handle the communication with the Lichess server and you can find more information about them from the given link.

As said before, code contains a lot of copypaste which could probably be avoided but given how many different moves and situations have to be considered for the bot to work, the current implementation isn't that bad. Reworking it would take far too much time so it'll have to do for now. At least it's quite flexible so making any additions to the program won't suddenly break it. Large classes could be broken down into smaller ones which is an option I will consider in the future.

Most likely won't go much in depth with O-analysis and etc. but will provide some information about speed and what not where it's necessary.
