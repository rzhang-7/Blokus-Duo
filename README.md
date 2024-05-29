# Battleship

Battleship is a game played by two players.  Each player has two boards that they play with:  The ship board, and the shots board.  Each board is a 10 x 10 grid.

At the beginning of the game, each player will place 5 ships of different sizes on the ship board.  The ships and their sizes are:
- Destroyer (2 squares long)
- Cruiser (3 squares long)
- Submarine (3 squares long)
- Battleship (4 squares long)
- Carrier (5 squares long)

The ships may be placed vertically or horizontally, but NOT diagonally.

After the ships have been placed, the players then take turns firing shots at their opponent’s ships.  A shot is fired by choosing a location by giving the number of a **ROW** and **COLUMN**. The player is then told whether it is a **hit** (if the square contains one of their opponents ships) or a **miss** (if the square does not contain a ship).  The results of the guess are then recorded on the **shots board**.

When the opponent fires at a player, the results of the **opponent’s guess** are recorded on the **player’s ship board**.  A ship is sunk if all squares on that ship have been hit.

The game is won when all of an opponent’s ships have been sunk.

Your program should allow the player to play against **an AI opponent** for the second player, with the option for variable difficulty.

If you would like to see how the game is played, you may try an example [here](https://www.battleshiponline.org/) **(There is sound.  Check your volume before you click!)**.

## Requirements

The program contains the following features:
- A welcome menu which should allow the user to choose between **starting a new game**, **loading an old game**, **showing the instructions**, or **quitting** your program.
- A game board which displays the status of the game by showing the player’s ship board and their shots board.
- The user must be able to **place their ships** on the board before the game begins. They should be able to place each ship oriented either horizontally or vertically.
  - You should ensure that any ship placement works!
- An interface that allows the user to **choose a row and column** to target on their turn.
  - If the user tries to make an invalid selection (eg., a location that is not on the board, or one that they have already tried) your program should print an error message and let them try again.
  - On the player’s turn, they should also be able to choose to **surrender**, or **Save** the game.
- The **board must update** after each turn and **display ships, hits and misses** by both the player and the computer.
- When the user chooses to **save the game**, your program will store the current status of the game **in a text file**, which can be loaded later.  The format of your text file is outlined in the [File System Format](## File System Format) section of this document.
  - When **saving or loading** a game, the player should be allowed to **choose the name of the file** that is saved or loaded.  If they choose a filename that already exists, **that file should be overwritten**.
- When a ship is sunk, the interface should display a message indicating **which player has sunk a ship**, and the **name of the ship** sunk. For example, `"Computer has sunk your Battleship!"`
- Your game should end when either **all of the computer’s ships** or **all of the player’s ships** have been sunk. The program should **indicate who has won** when a game ends.
- If the user loses the game, or they choose to surrender, your program will show the **computer’s ships board**, along with the shots you have taken.
- After a game ends, the user should be **returned to the main menu**.
- The status of the game **must be stored in two-dimensional arrays**.
- **Methods should be used** to accomplish any tasks which will be repeated regularly. (e.g., taking shots, computer turns, displaying the board, etc.)
- The user should have the option to choose the game’s difficulty:
  - If the user chooses “easy”, the computer will **always fire randomly**.
  - If the user chooses “normal”, the computer will fire randomly **until it gets a hit**, and then will aim for spots **next to the last hit** until it gets another hit, a ship is sunk or all the squares around the last hit have been tried.

## The Interface

All the input should be entered via the keyboard, and all displays are represented by characters.

The screen could look something like this.

```txt
Ships:                              Shots:
   1  2  3  4  5  6  7  8  9 10        1  2  3  4  5  6  7  8  9 10
 1 -  -  -  -  A  X  X  A  A  -      1 -  -  -  -  -  -  -  -  -  -
 2 -  D  -  -  -  -  -  -  -  -      2 -  -  -  -  -  O  -  -  -  -
 3 -  D  -  -  -  -  -  O  -  -      3 -  X  -  -  -  -  -  -  -  -
 4 -  -  -  -  B  B  B  B  -  -      4 -  X  -  -  -  -  -  -  -  -
 5 -  -  O  -  -  -  O  -  -  -      5 -  X  -  -  -  -  -  -  -  -
 6 -  -  -  -  -  -  -  -  -  -      6 -  O  -  -  -  -  O  -  -  -
 7 -  C  C  C  -  -  -  -  S  -      7 -  -  -  -  -  -  -  -  -  -
 8 -  -  -  -  -  O  -  -  S  -      8 -  -  -  -  -  -  -  -  -  -
 9 -  -  -  -  -  -  -  -  S  -      9 -  -  -  -  -  -  -  -  -  -
10 -  -  -  -  -  -  -  -  -  -     10 -  -  -  -  -  -  -  -  -  -
```

In the above example, `X`'s represent hits, `O`'s represent misses, `-` represent unused squares, and `D`, `C`, `S`, `B` and `A` represent the Destroyer, Cruiser, Submarine, Battleship and Aircraft Carrier, respectively.

The left board shows the player’s ships, and the guesses the AI has made, and the right board shows all the guesses the player has made.

As the game progresses, the board should be re-printed whenever a change occurs so that the user is always aware of the current state of the game.

Be sure to tell the user what the AI’s guess was, and whether it was a miss or a hit.

Also note that the board displays the column and row numbers from 1 through 10, but **the 2D arrays are zero-indexed**. This means the array indexes will run from 0 through 9, and **the program will have to compensate for this**.

## File System Format

The game should use a text file that contains 44 lines of text:
- The **first line** of text should be a line of text acting as the title for the player’s ships board.
- The **next ten lines** of text should each contain **ten characters**, representing the player’s ships, and the shots the computer has taken against them.
- The **next line** of text should be a line of text for the title for the computer’s ships board.
- This is followed by **another ten lines** of text of **ten characters**, representing the computer’s ships, and the shots the player has taken against it.
- The **next 11 lines** should be similar, but the board should be showing ONLY the shots the player has taken against the computer, with their ships hidden.
- Similarly, the **final 11 lines** show ONLY the shots the CPU has taken against the player, with the player’s ships hidden.

For the characters that represent the boards:
|Character|Description|
|---|---|
|`-`|Represents an unused square|
|`X`|Represents a hit|
|`D`,`C`,`S`,`B`,`A`| Represent different ship types|
|`O`| Represents a miss |

The game should be able to load the SaveExample.txt file correctly, and saved games should follow a similar file format.

When loading a game, the player SHOULD NOT be able to see the Computer's ships, they should be displayed as unused squares until they have been hit.

## Authors

- Assignment description created by Mr. Skuja for the ICS3U1 course
- Code by Raymond Zhang
