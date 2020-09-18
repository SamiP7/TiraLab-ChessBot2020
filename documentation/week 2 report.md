* Made the layout for the board which was more difficult as it should have been since I couldn't decide on the way I wanted to implement it. Current version works, but there still might be room for improvement. Came up with some kind of a system for the bot to differentiate the different pieces and their colours(mostly if it's hostile or its own piece).
* Wrote most of the rules of chess for the bot. Most of the time was spent here as it might be pretty obvious comparing it to other classes. It's still missing the rule for castling and the information when it's in a check.
* Bot somewhat works in lichess but only for white side but that will be fixed next week.
* Little bit testing. Would have done way more if I had enough time but more will come next week.
* Code is a bit of of a mess as of now but it should be cleaned in the near future.
* Had a little trouble with getting the bot understand which are its pieces in lichess but I think have it covered for next week without any hiccups.
* Jacoco and much more have already been added to the template and these can found after building the project with the command `./gradlew build`.
* Time spent: around 12h.

**Next week**

* Finish making the rules so that it's fully functionable in lichess.
* More testing.
* Start with a min-max algorithm so the bot won't make as dumb moves.
