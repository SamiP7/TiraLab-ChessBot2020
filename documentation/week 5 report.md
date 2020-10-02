* Fixed a couple bugs I found whilst running the bot.
* Implemented a heuristic evalution function based on the current positions on the board.
* Finished minmax algorithm and also made alpha-beta pruning to it.
* This all has just barely improved the bot since it loses at times to level 3 lichess bot but at times it does win against it. It can also somewhat play against level 4 although it does get crushed.
* Doing these left me no time to do any more testing or proper documentation so this is quite lacking this week. Most likely won't be implementing my own ArrayList and etc. since it isn't really relevant to this project (most likely wouldn't even have time for this). Unless it's completely necessary?
* Pruning could probably be made more efficient by sorting the order of the moves it has to test so it could go further down the tree. Currently going to depth 4 the algorithm takes over 10 secs to run most of the time so settling for depth 3 is good enough for now.
* Alpha-beta was implemented with the help of this: https://www.naftaliharris.com/blog/chess/
* Evaluation function values with the help of this: https://www.chessprogramming.org/Simplified_Evaluation_Function
* Time spent: about 12h

**Next week**

* Maybe improving the heuristic since the bot is a bit too aggressive which is most likely the reason for most of the bad moves it makes.
* Most likely what I'll end up mostly doing is just more documentation and testing since these are quite lacking in this project currently.
