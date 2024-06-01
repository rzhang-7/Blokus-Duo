# Blokus Duo

Blokus Duo is the two-player variant of the popular board game Blokus. It is played on a 14 x 14 grid.

Players will each receive 21 tetris-like tiles. They will take turns placing a series of tetris-like pieces on the board. Their goal is to cover as much of the grid as possible.

On the first turn, each player will place one of their pieces on the starting points indicated by the `·` symbol.

Subsequently placed pieces must touch at least one corner and no sides of previously placed pieces. In game, available spots will be indicated by `·` symbols. The following examples demonstrate valid and invalid placements respectively.

```bf
[ ][ ][ ][ ][ ][ ]       [ ][ ][ ][ ][ ][ ]
[ ][+][+][+][ ][ ]       [ ][+][+][+][ ][ ]
[ ][ ][ ][+][ ][ ]       [ ][ ][ ][+][ ][ ]
[ ][ ][ ][ ][ ][ ]   →   [ ][ ][ ][ ][+][ ]
[ ][ ][ ][ ][ ][ ]       [ ][ ][ ][+][+][ ]
[ ][ ][ ][ ][ ][ ]       [ ][ ][ ][ ][ ][ ]
```

```bf
[ ][ ][ ][ ][ ][ ]       [ ][ ][ ][ ][ ][ ]
[ ][+][+][+][ ][ ]       [ ][+][+][+][ ][ ]
[ ][ ][ ][+][ ][ ]       [ ][ ][ ][+][x][ ]
[ ][ ][ ][ ][ ][ ]   X   [ ][ ][ ][x][x][ ]
[ ][ ][ ][ ][ ][ ]       [ ][ ][ ][ ][ ][ ]
[ ][ ][ ][ ][ ][ ]       [ ][ ][ ][ ][ ][ ]
```

If a player cannot place any pieces in any valid spots, they must skip their turn. The game will continue until both players cannot place any more pieces.

The final score of each player will be the total number of grid tiles occupied by their pieces. Whoever has the higher score wins the round.

In this program, the player will be able to play against an AI opponent for the second player, with the option for variable difficulty.

A web version of this game can be found [here](https://lefun.fun/en/g/bloco).

## Requirements

The program will contain the following features:

- A welcome menu which allows  the user to choose between **starting a new game**, **loading an old game**, **showing the instructions**, or **quitting the game**.
- A game board that displays the status of the game by showing the tiles placed and available tiles
- An interface that allows the user to **choose a piece** to place a tile
  - If the user tries to make an invalid selection (e.g., a location that is not on the board, or an occupied tile) the program will print an error message and let them try again.
  - They may choose to **rotate**, **flip**, **or move** their **pieces** so long as it can make a valid placement.
  - On the player’s turn, they will be able to choose to **forfeit**, or **save** the game.
- The **board must update** after each turn and **display the current score** of each player and their placed tiles.
  - The user should only be able to see their own remaining pieces
- When the user chooses to **save the game**, the program will store the current status of the game in a text file, which can be loaded later.
  - When **saving or loading** a game, the player should be allowed to **choose the name of the file** that is saved or loaded. If they choose a filename that already exists, **that file should be overwritten**.
- The program will **indicate if a player cannot make any more valid placements**.
- The program will **indicate valid corners** that the player can place pieces depending on their remaining pieces
- The game will end when **no pieces can be placed** on the board by either player.
- After a game ends, the user should be **returned to the main menu**.
- The status of the game must be stored in **two-dimensional arrays**.
- Methods should be used to accomplish any tasks which will be repeated regularly (e.g., displaying the board, computer turns, placing pieces)
- The user should have the option to choose the game’s difficulty level:
  - If the user chooses “easy”, the computer will **place random pieces** in valid locations.
  - If the user chooses “normal”, the computer will **try to place the largest pieces first** to maximize their score.
