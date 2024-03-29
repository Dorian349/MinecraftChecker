# Minecraft Accounts Checker

A simple java (JDA) Discord bot to check Minecraft accounts. Works with JDA 5 and slash commands.

## Getting Started

Follow the Prerequisites & Installation section.

### Prerequisites

* You need a list of Minecraft accounts, each one separated with a back-to-line. (ex: `accounts@gmail.com:password` or `username:password`)
* A java running environment.
* A discord bot with all intents enabled and invited with the application.commands parameter (for slash command usage).

### Installation

* Download MinecraftChecker.jar and config.txt from the Releases section. Then edit the config.txt by replacing the information.
* Put both files in the same folder and starts the jar file. `java -jar yourFile.jar`

## Usage

* Once your bot has started, simply use `/check (accounts)` and the bot will reply with all valid accounts and their username.

## Additional Documentation and Acknowledgments

* Default config.txt file.
```
token: "Your token"
ownerID: "Your discord user ID"
activityMessage: "The activity message displayed by the bot"
prefix: "-"
```
* Example command usage.
```
/check example@gmail.com:password1 example2@gmail.com:password2 example3@gmail.com:password3
```

If you find any issue, make sure to create a pull request!
