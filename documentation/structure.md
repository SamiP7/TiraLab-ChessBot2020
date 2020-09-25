## Structure

### Board

Board as the name suggest, gives us the chess board which is represented as a String array. From here we paste the moves on the board which other classes use to check things like moves that they can make, what the opponent can make and so on.

### Bot

This class is used to track the gamestate. Here we paste the opponents moves to our board and also send ours to the bot.

### Moves

Moves class gives all the possible moves that we, and the opponent can do, given our current board position. Here we also *translate* our moves to [UCI](https://en.wikipedia.org/wiki/Universal_Chess_Interface) so that they are valid on the server and also convert them back from UCI so that our board can read them.
This class also handles all the rules of chess which explains its large size. There's a lot of *"copypaste"* code here but this is rather unavoidable unless we want to give up on the flexibility that the methods here give us.

### Pieces

Currently quite redundant class so it will most likely be deleted in the future.

### MinMax

Basic algorithm which tries to maximise our score and drop the opponents as low as possible with the goal of winning the game of course. This is currently heavily under construction but it does have a slight improvement on the bots play. With its current status, it can comfortably win against level 2 bot but it does get crushed against level 3.
Currently it analyzes all the possible moves we can make which are provided by the Moves class and tries to pick the best ones by looking at the score of the move which is determined by what piece we capture and what piece can the opponent capture after our move.

### Miscellaneous

If you want more info about the classes, check the javadoc after building the project, which can then be found in this path: */build/docs/javadoc/allclasses.html*

Other classes are provided from [this](https://github.com/TiraLabra/chess) template. They only handle the communication with the Lichess server and you can find more information about them from the given link.

As said before, code contains a lot of copypaste which could probably be avoided but given how many different moves and situations have to be considered for the bot to work, the current implementation isn't that bad. Reworking it would take far too much time so it'll have to do for now. At least it's quite flexible so making any additions to the program won't suddenly break it. Large classes could be broken down into smaller ones which is an option I will consider in the future.

I probably won't do much O-analysis and etc. here since it's not really relevant to this project. This might change in the future since this document is still a work in progress.
