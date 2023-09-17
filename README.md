# Anagrams
Java GUI Anagrams Game. Two player game in which players must build words with the 6 random letters in order to score points. Point system is based off of the length of the word and players are given 60 seconds in order to get the most points.

# Mechanics
The 6 letters are randomly generated so each game is different. No more than two vowels will be given and the letters are weighted based on their frequency in the English language. Created words are checked in realtime with a mysql database containing all words in the English language. Players will be notified if the submitted word is correct with the letters turning green and red if the word was invalid. The game will present all correctly entered words on the gameboard so that the players remember which words they have used.

# Running the code:
## Requirements: 
  * java-8-openjdk-amd64
  * mysql: https://do.co/2IZO9wk
  * mysql-connector: https://do.co/2IZO9wk
      * add to external java library
      
## Start-up:
* 1). clone the repository
* 2). within terminal go to project directory
* 3). type sh setup.sh to set up the database
* 4). type sh makeDatabse.sh to load in the data to words database
* 5). type sh play.sh to start up the game

## MySQL Credentials:
User: root

Password: ""                 (no password)

*You should be at this window:*
![Screenshot from 2019-08-12 17-25-21](https://user-images.githubusercontent.com/47041789/62899630-38bf9f80-bd26-11e9-8c67-feb2fb7b1d86.png)
Click host or join to start a game.

The game needs one host and one client to start. Both server and client has to follow the Start-up instruction above.

As a client, you will need the IP address of the Host. As a host, you do not need to worry about anything.    
      

