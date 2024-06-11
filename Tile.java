import java.util.*;
/*
 * Program name: Tile
 * Programmer: Raymond Zhang
 * Last modified: 06/07/2024
 * Description: Instance class for Blokus tiles
 */

public class Tile {
    // private int x = -1;
    // private int y = -1;
    private final int points;
    private int rows;
    private int cols;
    private boolean used;
    private boolean[][] squares;

    /*
     * Method name: Tile()
     * Parameters: int points - The point value of the tile
     * int size - The length of the square grid containing the tile
     * boolean[][] squares - The square grid representing the squares occupied by
     * the tile
     * Description: Constructor method that defines the point value, size, and
     * squares occupied by the tile.
     */
    public Tile(int points, int rows, int cols, boolean[][] squares) {
        this.points = points;
        this.rows = rows;
        this.cols = cols;
        this.squares = squares;
    }

    /*
     * Method name: getPoints()
     * Return type: int - The point-value of the Tile instance
     * Description: Accessor method for the points attribute in the Tile class.
     */
    public int getPoints() {
        return points;
    }

    /*
     * Method name: getRows()
     * Return type: int - The length of the Tile instance
     * Description: Accessor method for the rows attribute in the Tile class.
     */
    public int getRows() {
        return rows;
    }

    /*
     * Method name: getCols()
     * Return type: int - The width of the Tile instance
     * Description: Accessor method for the cols attribute in the Tile class.
     */
    public int getCols() {
        return cols;
    }

    /*
     * Method name: getSquares()
     * Return type: boolean[][] - The square area taken up by the Tile instance
     * Description: Accessor method for the squares attribute in the Tile class.
     */
    public boolean[][] getSquares() {
        return squares;
    }

    // public boolean[][] getSquaresCopy() {
    //     boolean[][] squaresCopy = new boolean[getRows()][getCols()];

    //     // Copy the squares array
    //     for (int i = 0; i < getRows(); i++) {
    //         System.arraycopy(getSquares()[i], 0, squaresCopy[i], 0, getCols());
    //     }
    //     return squaresCopy;
    // }

    /*
     * Method name: getUsed()
     * Return type: boolean - Whether or not the Tile instance has been used in game.
     * Description: Accessor method for the used attribute in the Tile class.
     */
    public boolean getUsed() {
        return used;
    }

    /*
     * Method name: setRows()
     * Parameters: int rows - The new number of rows
     * Description: Mutator method for the rows attribute in the Tile class.
     */
    public void setRows(int rows) {
        this.rows = rows;
    }

    /*
     * Method name: setCols()
     * Parameters: int cols - The new number of columns
     * Description: Mutator method for the cols attribute in the Tile class.
     */
    public void setCols(int cols) {
        this.cols = cols;
    }

    /*
     * Method name: setSquares()
     * Parameters: boolean[][] newSquares - The new tile area
     * Description: Mutator method for the squares attribute in the Tile class.
     */
    public void setSquares(boolean[][] newSquares) {
        squares = newSquares;
    }

    /*
     * Method name: setUsed()
     * Parameters: boolean used - The new used state.
     * Description: Mutator method for the used attribute in the Tile class.
     */
    public void setUsed(boolean used) {
        this.used = used;
    }

    /*
     * Method name: rotateRight()
     * Description: Rotates the tile clockwise.
     */
    public void rotateRight() {
        // Create new squares with reversed dimensions
        boolean[][] newSquares = new boolean[getCols()][getRows()];

        for (int i = 0; i < getCols(); i++) {
            for (int j = 0; j < getRows(); j++) {
                newSquares[i][j] = getSquares()[getRows() - j - 1][i];
            }
        }

        // Update squares
        setSquares(newSquares);
    }

    /*
     * Method name: rotateLeft()
     * Description: Rotates the tile counterclockwise.
     */
    public void rotateLeft() {
        // Create new squares with reversed dimensions

        boolean[][] newSquares = new boolean[getCols()][getRows()];
        for (int i = 0; i < getCols(); i++) {
            for (int j = 0; j < getRows(); j++) {
                newSquares[i][j] = getSquares()[j][getRows() - i - 1];
            }
        }

        // Update squares
        setSquares(newSquares);
    }

    /*
     * Method name: flipVert()
     * Description: Flips the tile vertically
     */
    public void flipVert() {
        // Get reference to squares
        boolean[][] flippedSquares = getSquares();

        // Loop throw tile's columns
        for (int i = 0; i < getRows(); i++) {
            for (int j = 0; j < getCols() / 2; j++) {
                // Swap top rows with bottom rows
                flippedSquares[j][i] ^= flippedSquares[getCols() - j - 1][i];
                flippedSquares[getCols() - j - 1][i] ^= flippedSquares[j][i];
                flippedSquares[j][i] ^= flippedSquares[getCols() - j - 1][i];
            }
        }
    }

    /*
     * Method name: flipHorz()
     * Description: Flips the tile horizontally
     */
    public void flipHorz() {
        // Get reference to squares
        boolean[][] flippedSquares = getSquares();

        // Loop through tile's rows
        for (int i = 0; i < getRows(); i++) {
            for (int j = 0; j < getCols() / 2; j++) {
                // Swap left columns with right columns
                flippedSquares[i][j] ^= flippedSquares[i][getCols() - j - 1];
                flippedSquares[i][getCols() - j - 1] ^= flippedSquares[i][j];
                flippedSquares[i][j] ^= flippedSquares[i][getCols() - j - 1];
            }
        }
    }

    /*
     * Method name: placeTile()
     * Parameters: int r - The row of the tile location. Assumed to be valid.
     * int c - The column of the tile location. Assumed to be valid.
     * char currentPlayer - The character representing the current player
     * char[][] - The game board
     * Description: Places a tile onto a valid location on the game board.
     */
    public void placeTile(int r, int c, char currentPlayer, char[][] board) {
        // Loop through piece at (r,c)
        for (int i = r; i < r + getRows(); i++) {
            for (int j = c; j < c + getCols(); j++) {
                // Square in tile is not empty
                if (getSquares()[i - r][j - c]) {
                    board[i][j] = currentPlayer;
                }
            }
        }
    }

    /*
     * Method name: newTileSet()
     * Return type: Map<String,Tile> - A set of 21 key-value pairs, each
     * representing a unique Blokus tile. The String represents its identifier name,
     * while Tile represents the data contained within the tile.
     * Description: Returns a new hard-coded 21-tile Blokus set.
     */
    public static Map<String, Tile> newTileSet() {
        Map<String, Tile> newTiles = new HashMap<>();

        newTiles.put("I1", new Tile(1, 1, 1, new boolean[][] { { true } }));
        newTiles.put("I2", new Tile(2, 1, 2, new boolean[][] { { true, true } }));
        newTiles.put("V3", new Tile(3, 2, 2, new boolean[][] { { true, false }, { true, true } }));
        newTiles.put("I3", new Tile(3, 1, 3,
                new boolean[][] { { true, true, true, } }));
        newTiles.put("O", new Tile(4, 2, 2, new boolean[][] { { true, true }, { true, true } }));
        newTiles.put("T4", new Tile(4, 2, 3,
                new boolean[][] { { true, true, true }, { false, true, false } }));
        newTiles.put("L4", new Tile(4, 2, 3,
                new boolean[][] { { true, false, false }, { true, true, true } }));
        newTiles.put("Z4", new Tile(4, 2, 3,
                new boolean[][] { { true, true, false }, { false, true, true } }));
        newTiles.put("I4", new Tile(4, 1, 4,
                new boolean[][] { { true, true, true, true } }));
        newTiles.put("F", new Tile(5, 3, 3,
                new boolean[][] { { false, true, false }, { true, true, true }, { true, false, false } }));
        newTiles.put("X", new Tile(5, 3, 3,
                new boolean[][] { { false, true, false }, { true, true, true }, { false, true, false } }));
        newTiles.put("P", new Tile(5, 3, 2,
                new boolean[][] { { true, true }, { true, true }, { true, false } }));
        newTiles.put("W", new Tile(5, 3, 3,
                new boolean[][] { { true, false, false }, { true, true, false }, { false, true, true } }));
        newTiles.put("Z5", new Tile(5, 3, 3,
                new boolean[][] { { true, true, false }, { false, true, false }, { false, true, true } }));
        newTiles.put("U", new Tile(5, 2, 3,
                new boolean[][] { { true, false, true }, { true, true, true } }));
        newTiles.put("T5", new Tile(5, 3, 3,
                new boolean[][] { { true, true, true }, { false, true, false }, { false, true, false } }));
        newTiles.put("V5", new Tile(5, 3, 3,
                new boolean[][] { { true, false, false }, { true, false, false }, { true, true, true } }));
        newTiles.put("Y", new Tile(5, 2, 4,
                new boolean[][] { { false, true, false, false },
                        { true, true, true, true } }));
        newTiles.put("L5", new Tile(5, 2, 4,
                new boolean[][] { { true, false, false, false },
                        { true, true, true, true } }));
        newTiles.put("N", new Tile(5, 2, 4,
                new boolean[][] { { false, true, true, true },
                        { true, true, false, false } }));
        newTiles.put("I5", new Tile(5, 1, 5,
                new boolean[][] { { true, true, true, true, true } }));

        return newTiles;
    }

}