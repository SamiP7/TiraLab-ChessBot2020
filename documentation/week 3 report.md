* Most of the time was spent on getting the bot to understand "legal" moves, meaning that it doesn't try to move it's pawn when it's king is clearly in check. This was the main culprit behind the mysterious crashes last week while running the bot. Getting this to work took far longer than I initially thought, which is why not much progress has been made this week.
* Bot now understands special rules but still can't do them itself, so it understands when the opponent castles, but doesn't know how to do it itself. Same goes for promoting and the special *en passant* move.
* Bot can now play on either side.
* Wrote more tests for a better coverage. Still not perfect but better than it was last week. Also fixed some minor checkstyle errors whilst the major ones can't really be fixed as of yet.
* Around 12h was spent this week as well.

**Next week**

* Hopefully by then the bot can do all the possible special moves so I can start working on the algorithms which in theory, should make the bot a little less stupid.
* Work more on documentation so it's easier to understand everything that's going on the background whilst the bot is running.