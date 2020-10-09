* Was supposed to work on documentation and testing this week but I got way too absorbed working with the min-max algorithm as I felt I was on the verge of a breakthrough.
* So I ended up on improving the min-max algorithm. Thanks to that, the bot can now pretty easily win against level 3 lichess bot. Should also make the bot check that they don't end up in a tie or stalemate since mate is the only way to win. Not sure if I have time for this though.
* Also made my own "string list" which is pretty much just array list with the option to only add unique strings to it. Only made the absolutely necessary methods for it, since I barely used any of the cool gimmicks java's own lists and what not provide. With this my project doesn't use any of the java's own data structures. The template still does, but I don't want to touch it in case of something breaking.
* Did write some tests and little bit of structure and testing documentation. Most likely saving it till last so I won't have to go back to rewrite things.
* Still not sure on how I should write tests for the min-max algorithm because there's rarely only 1 correct move in chess unless it's a checkmate or something similar. Probably just going to try and handcraft those situations for the algorithm to solve.
* New improvements to the algorithm have slowed it down a fair bit. Tried to fix it for a long time, but other issues always came up with it. To sum it up, fast brain = stupid brain.
* Time spent this week: around 9h.


**Next week** */week after that*

* Program is pretty much done right now. Only really need to work on something to make the algorithm more efficient since it takes anywhere from 2 seconds to 2 minutes to make a move.
* Also need to try and work on test coverage and finally all the necessary documentation.