# User Manual for Scrabble APP
This app is a word game played by a single player for practicing Scrabble.
## Getting started 
The following instructions will give you a guidance on the content of this app, the required environmnet, how it builts and the details on how to play.

## System requirements
- The application requires minimum API level is API 23 Android 6.0 (Marshmallow)
- The application is development for Android Phone with touch screen

## Build with
- This app is built by Android Studio 3.5

---
## How to play

### 1. Applucation menu
At the main application menu, there are four actions that player could make:

##### PLAY
The player is able to start the game by pressing *PLAY* button at the menu.

##### ADJUST SETTING
If a player would like to adjust the defalt game settings, the *ADJUST SETTING* button allows the change of maximum turns per game, letter frequecies and their corresponding points.

##### VIEW STATISTICS
Player is able to view the previous game histories by clicking *VIEW* button, it includes the game scores and game settings in each game, letters that have been played and their usage rate and the times of words that have been played.



![visual][manual1]

### 2. Play interface

If it's the first time player plays or the previous game was completed, then a new game will be initatated, otherwise, the previous game will be loaded. Here player can see sections of *Your Score*, *Word*, *Board* and *Rack*, also options on acting *SWAP* or *LEAVE*, and here're the descriptions on each part:

##### Your Score
Here reflects the score the player earning until now, which will be updated after a work is played. After reaching to the set maximum turns per game, the final score will pop up. 

##### Word
Player could enter the word by touching the letter shown in the Board and Rack sections. Only one letter could be chosed in Board while the numbers of Rack are unlimited. After entering the word that player wants to play, then click *ENTER* button to submit the word and the corresponding score will be reflected and updated on *Your Score*. The word must contain only valid letters and may not be repeated within a single game.    

After a word is played, that letter on the board will be replaced by a different random letter from the word that was just played, and the tiles used from the rack are replaced from the pool of letters.

##### Board
At the beginning, there are 4 words in Board randomly selected from the pool. As mentioned above, only one letter could be chosen per turn or an error message will pop up. The letter that was chosen for the word will be replaced by one of the random letters played from the Rack.

##### Rack
At the beginning, there are 7 words in Board randomly selected from the pool. The tiles used from the rack are replaced from the pool of letters. 

##### SWAP
If player is not happy with the letters in Rack, they could swap 1-7 letters from their the rack with letters from the pool of letters. This is the only time letters are returned to the pool during a game. To pexecute this, just click on the letters and click *SWAP* button.

##### LEAVE
If player wanna leave the game, click *LEAVE* button. Then the game is saved, and player goes back to main application menu.

###### Game 

![visual][manual2]
### 3. Setting interface
At the setting interaface, player can adjust some game settings as described below:
##### Max Turn
The maximum turn per game can be set here. Enter the numebr of max turns player would like to play, and click *save* button to save the setting.
##### Set letter freuquency
The letter frequency in the pool per game can be set here. The default setting is set following [English Scrabble Disutribution](https://en.wikipedia.org/wiki/Scrabble_letter_distributions). First, player could chose the letter from A-Z next to *SetFreq*, then insert desired numbers of this letter per game, then click *save* to save the changed settings.
##### Set letter point
Same as letter frequency, the points corresponding to each letter can also be set. The default setting is also set following [English Scrabble Disutribution](https://en.wikipedia.org/wiki/Scrabble_letter_distributions). First, player could chose the letter from A-Z next to *SetPoints*, then insert desired points corresponding to this letter, then click *save* to save the changed settings.

####  It should be noted that player could press the *back* button on Android to back to ther previous interface.

![visual][manual3]
### 4. View interface
As mentioned before, the all game information will be stored and can view in different ways, including game score statstics, letter statistics and word bank. After clicking *VIEW* button at application menu, player will be directed to **VIEW** interface as shown in picture below, there are four options to chose:
#### GAME SCORE STATS
By clicking *GAME SCORE STATS* button, player could view the list of scores, in descending order by final game score, displaying the final game scores, the number of turns in that game and the average score per turn. Moreover, the player could select the game scores to view the settings for that game's maximum number of turns, letter distribution and the letter points.
#### LETTER STATS
By clicking *LETTER STATS* button, player could view the list of letters, in ascending order by number of times played, displaying the total number of times that letter has been played in a word, the total number of times that letter has been tracked into pool. and the percentages of times that the letter is used in a word, out of total numbers of times it has been drawn.
##### WORDBANK
By clicking *WORDBANK* button, player could view the list of words used, starting from the most recently played, displaying the word and the number of times that the word has been played.

![visual][manual4]

## Authors 
CS6300 Team119









[manual1]: manual1.png
[manual2]: manual2.png
[manual3]: manual3.png
[manual4]: manual4.png


# Supplementary Requirements

**Author**: Team119

## List of Supplementary Requirments

The following items list any additional requirements that are not included in the requirements from Design documents.  

* The device should provide enough memory for running the app smoothly, and sufficient storage for saving game information in local database.
* The user interface must be intuitive and responsive.
* The default game setting matching the ​ [English Scrabble Disutribution](https://en.wikipedia.org/wiki/Scrabble_letter_distributions)(12 E’s worth 1 point each, 4 D’s worth 2 points each, etc).
* The performance of the game should be such that players do not experience any
considerable lag between their actions and the response of the application.
* If the app crashes, the score and game information is lost
* Users don’t need login to play game
* This app works only locally, no capable of connecting to server or with any other player.
* The app cannot check whether the word input is valid or not, user should make sure the input word is acceptable.

