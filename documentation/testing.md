## Test document

For the most part all the testing is done for the Moves class. This class handles much of the important information for the program so it's integral that it's tested a lot as well. Writing any good tests for it has proven quite difficult since simulating any games for it is really tedious. Because of this, most of the tests have been done from the neutral chess board, where any moves haven't been yet made. Given the amount of different branching paths some methods have in there, it's not really feasible to write smart tests to many of them, especially when many paths are very situational, for example *en passant* move. This can be seen in the form of poor coverage in the jacoco report, but testing many of these paths isn't that important as said before. You can find the jacoco report for this down below.

My own data structures and objects have been tested enough in my opinion. They are quite simple in structure so it's very unlikely that they are going to break with the current amount of testing.

Bot class has been tested well enough in the given template so writing any of my own tests for it is quite redundant. Board class is tested along with Moves so making any simple tests for it isn't going to help much in case of any errors. Algorithms are somewhat difficult to test without any handcrafted situations so I'm holding off on that for now.
Running tests can be done with a command `./gradlew test`. These are also run automatically when you build the project with the command `./gradlew build` or whenever you launch the bot. You can find jacoco and checkstyle from the paths given in the [README](https://github.com/SamiP7/TiraLab-ChessBot2020/blob/master/README.md).

Currently the test coverage is not great as mentioned before and seen from here (not linking the one from algorithms yet as I'm not sure how to implement tests for it):

![](https://github.com/SamiP7/TiraLab-ChessBot2020/blob/master/documentation/initTest.png)


## Aforementioned Moves class testing:

![](https://github.com/SamiP7/TiraLab-ChessBot2020/blob/master/documentation/movesTest.png)

