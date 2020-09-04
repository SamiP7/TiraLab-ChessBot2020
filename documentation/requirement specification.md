The goal of this project is to make a somewhat competant chess bot in java, with the use of algorithms.

## Algorithms and time complexity:

* At the very least, some sort of min-max algorithm which tries to keep your score as high as possible while reducing the opponents score respectively.
* Alpha-beta pruning would work perfectly in this type of game and it's most likely going to be implemented in some way since this should make bot decide quicker in its moves.
* If time permits, some type of algorithm which tries to see possible advantages/disadvantages of a move, so for example, the bot's queen could capture a bishop but would be captured by a rook the next turn.
* Most likely the program is going to mostly rely on quadratic time complexity O(n^2), since it has to analyze every piece on the board and all their possible moves. This can be reduced by stopping the search if a better move has already been found which hopefully will reduce the time complexity. If we consider the possibility of analyzing the aftermath of a turn, then this can get bloated really quickly.

## Input and output:

* The program only uses String for both input and output of data because of Universal Chess Interface (UCI), in which moves are represented by simple Strings. In UCI the starting square comes first and the destination square latter, for example d2d3. There is an exception to this, but only when a pawn reaches the end of a board and gets a promotion, for example f7f8K.

## Sources:

[Template](https://github.com/TiraLabra/chess/)

[Ideas for different algorithms etc.](https://www.cs.cornell.edu/boom/2004sp/ProjectArch/Chess/algorithms.html)

### Etc.

* Study program: bachelor of mathematical sciences
* Documentation and code will be written in english (mostly because of the template).
