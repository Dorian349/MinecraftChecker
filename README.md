# Minecraft Accounts Checker

A simple java (JDA) discord bot to check Minecraft accounts.

## Getting Started

Follow the Prerequisites & Installation section.

### Prerequisites

* You need a list of Minecraft accounts, each one separated with a back-to-line. (ex: `accounts@gmail.com:password`)
* A java running environment.

### Installation

```
$ Export the project on your IDE.
$ Compile the project. (ex: `mvn clean compile assembly:single` if you are using Maven)
$ Create a text file named `config.txt` and fill it with the default version under the Additional Documentation section.
$ Put both files in the same folder and starts the jar file. `java -jar yourFile.jar`
```

## Usage

```
$ Once your bot has started, simply use `$prefix$check (accounts)` and the bot will reply with all valid accounts and their username.
```

## Additional Documentation and Acknowledgments

* Default config file.
```
token: "Your token"
ownerID: "Your discord user ID"
activityMessage: "The activity message displayed by the bot"
prefix: "-"
```
* Example accounts list string.
```
example@gmail.com:password1
example2@gmail.com:password2
example3@gmail.com:password3
```

If you find any issue, make sure to create a pull request!
