## Test document

For the most part all the testing is done for the Moves class. This class handles much of the important information for the program so it's integral that it's tested a lot as well. Writing any good tests for it has proven quite difficult since simulating any games for it is really tedious. Because of this, most of the tests have been done from the neutral chess board, where any moves haven't been yet made. Given the amount of different branching paths some methods have in there, it's not really feasible to write smart tests for all of them, especially when many paths are very situational, for example *en passant* move. This can be seen in the form of poor coverage in the jacoco report, but testing many of these paths manually isn't that important as said before. Most of the testing did happen whilst running the bot since it needs almost all of the information given from that class in a single game, so this was a more reliable testing enviroment for myself. It hasn't ran to any bugs in a long time so I would assume that it's working as intended. You can find the jacoco report for this down below.

My own data structures and objects have been tested enough in my opinion. They are quite simple in structure so it's very unlikely that they are going to break with the current amount of testing.

Bot class has been tested well enough in the given template so writing any of my own tests for it is quite redundant. Board class is tested along with Moves so making any simple tests for it isn't going to help much in case of any errors. Doing and undoing moves has been tested somewhat and they should work as intended. These methods are constantly being called whilst running the bot so most of the possible bugs have been caught there. 
Algorithms are somewhat tested on a few different scenarios where they have to pick the best move. It does select the best move on these simple situations so they appear to be working. Crafting more complex situations where the best move isn't obvious or if there even is one, is being held off on this. It could be possible that the bot can't even find the best move in those, given the current depth it runs on.

Performance testing has been done by running the bot. In chess, there are about 35 possible moves for a player to make on their turn and given that we have explore many moves ahead to find the best move, this can mean that we have to explore millions of different nodes on the game tree. Already at depth 4(which is our most reasonable depth), this number can be 35 x 35 x 35 x 35 = 1,5 million nodes! We also have to generate legal moves and get the evaluation of each of these nodes so this can really take a long time. Thankfully with move ordering and some pruning, we only have to explore a few thousand nodes if even that, given what the current board situation is. If our best move is one where we don't capture anything, this can take almost up to a minute to find at times. This all depends of course on how we iniatially sort our moves but without any spesific *null move heuristic*, this really can't be improved much in this program. When increasing the depth to even 5, moves can take up to 5 minutes to find, but this does have a clear improvement on how the bot plays. It can even beat level 4 lichess bot with minimaxing at depth 5, but the match can take almost up to an hour. With depth 4, matches take about 10 minutes, where it most of the time wins against level 3 lichess bot, but many times the matches end up in a draw since it doesn't have anything to check whether it's next move results in a draw.

Running tests can be done with a command `./gradlew test`. These are also run automatically when you build the project with the command `./gradlew build` or whenever you launch the bot. You can find jacoco and checkstyle from the paths given in the [README](https://github.com/SamiP7/TiraLab-ChessBot2020/blob/master/README.md).

Test coverage is not the best as mentioned before and seen from here:

![](https://github.com/SamiP7/TiraLab-ChessBot2020/blob/master/documentation/initTest.png)


## Moves class testing jacoco:

![](https://github.com/SamiP7/TiraLab-ChessBot2020/blob/master/documentation/movesTest.png)


## MinMax class testing jacoco:

![](https://github.com/SamiP7/TiraLab-ChessBot2020/blob/master/documentation/algorithmsTest.png)

