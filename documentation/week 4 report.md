* Bot can now make all the special moves in chess, of course including all the normal moves which were implemented last week.
* Made an early version of minmax-algorithm. This has made the bot a bit smarter but there's still a long way to go. Currently the minmax only looks at what it can capture during its turn and what can be captured from it the next. So it doesn't consider things like board positions when it makes its move.
* Wrote an early version of the structure and testing documents.
* A bit more testing for both regular- and special moves.
* Time spent: about 9h of which many were running the bot to track down any possible errors.

** Next week**

* Work on algorithms for the bot and make the current ones go to more depth so it can *plan ahead*.
* Try to get the bot understand "favorable" board positions for its pieces so it tries to prioritize them.
* Possible start to work on some pruning, especially if minmaxing starts to go several turns ahead.
* Testing testing testing.