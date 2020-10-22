Getting the bot work is quite simple. First download code and extract it to your desired location. After that Register to [Lichess](https://lichess.org/signup).
Then you'll have create your own [personal API access token](https://lichess.org/account/oauth/token/create) and choose all the scopes. If you have ever played as a human, you'll have to delete your token, close your account to register it as a bot and then finally create a new token. After that, go to the folder where you extracted the program with your favorite command-line (for example: Git Bash), then run this command to upgrade your account to a bot: `curl -d '' https://lichess.org/api/bot/account/upgrade -H "Authorization: Bearer INSERT YOUR TOKEN HERE"`

Now you should be able to try the bot. To do that, go to [lichess](https://lichess.org/), select "**play with computer**", or against a friend, both work. Type `./gradlew build`, if you haven't built the program already, and finally type `./gradlew run --args="--lichess --token=put_token_here"`, to start up the bot.

You can run the tests seperately with the command `./gradlew test`

To close the program use CTRL+C (if you happen to run into any errors, usually just closing the bot and activating it again solves the problem).

Jacoco, Checkstyle and Javadoc can be found in the paths given in the [README](https://github.com/SamiP7/TiraLab-ChessBot2020/blob/master/README.md)
