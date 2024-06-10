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
    private final int size;
    private boolean used;
    private boolean[][] squares;

    /*
     * Method name: Tile()
     * Parameters: int points - The point value of the tile
     *             int size - The length of the square grid containing the tile
     *             boolean[][] squares - The square grid representing the squares occupied by the tile
     * Description: Constructor method that defines the point value, size, and squares occupied by the tile.
     */
    public Tile(int points, int size, boolean[][] squares) {
        this.points = points;
        this.size = size;
        this.squares = squares;
    }

    // Accessor methods
    /*
     * 
     */
    public boolean getUsed() {
        return used;
    }

    public int getPoints() {
        return points;
    }

    public int getSize() {
        return size;
    }

    public boolean[][] getSquares() {
        return squares;
    }

    public boolean[][] getSquaresCopy() {
        boolean[][] squaresCopy = new boolean[getSize()][getSize()];

        // Copy the squares array
        for (int i = 0; i < getSize(); i++) {
            System.arraycopy(getSquares()[i], 0, squaresCopy[i], 0, getSize());
        }
        return squaresCopy;
    }

    /*
     * Method name: getSquaresRemoveEmpty()
     * Return type: boolean[][] - A new grid of squares representing the tile with empty rows/columns removed
     * Description: Returns a copy of the squares array with empty rows and columns removed.
     */
    public boolean[][] getSquaresRemoveEmpty() {
        // Declare variables
        int rowCount = 0, colCount = 0, maxRow = 0, maxCol = 0, rowIdx = getSize(), colIdx = getSize();
        boolean[][] newSquares;

        // Find the empty rows or columns
        for(int i = 0; i<getSize(); i++) {
            for(int j = 0; j<getSize();j++) {
                // Square found in row j
                if(getSquares()[j][i]) {
                    // Index of first row that is not empty
                    rowIdx = Math.min(rowIdx, j);

                    rowCount++;
                }
                // Square found in column j
                if(getSquares()[i][j]) {
                    // Index of first column that is not empty
                    colIdx = Math.min(colIdx, j);

                    colCount++;
                }
            }
            // Get the new maximum amount of rows needed and reset counter
            maxRow = Math.max(maxRow, rowCount);
            rowCount = 0;

            // Get the new maximum amount of columns needed and reset counter
            maxCol = Math.max(maxCol, colCount);
            colCount = 0;
        }

        // Initialize new array
        newSquares = new boolean[maxRow][maxCol];

        for(int i = rowIdx; i<rowIdx + maxRow; i++) {
            for(int j = colIdx; j<colIdx + maxCol; j++) {
                newSquares[i-rowIdx][j-colIdx] = getSquares()[i][j];
            }
        }

        return newSquares;
    }

    /*
     * Method name: setSquares()
     * Parameters: boolean[][] newSquares
     * Description: Mutator method for squares.
     */
    // Precondition: newSquares is the same size as squares
    public void setSquares(boolean[][] newSquares) {
        squares = newSquares;
    }

    /*
     * Method name: setUsed()
     * Parameters: boolean used - The new boolean variable to assign.
     * Description: Mutator method for the used condition.
     */
    public void setUsed(boolean used) {
        this.used = used;
    }

    /*
     * Method name: rotateRight()
     * Description: Rotates the tile clockwise.
     */
    public void rotateRight() {
        boolean[][] newSquares = new boolean[getSize()][getSize()];
        for (int i = 0; i < getSize(); i++) {
            for (int j = 0; j < getSize(); j++) {
                newSquares[i][j] = getSquares()[getSize() - j - 1][i];
            }
        }
        setSquares(newSquares);
    }

    /*
     * Method name: rotateLeft()
     * Description: Rotates the tile counterclockwise.
     */
    public void rotateLeft() {
        boolean[][] newSquares = new boolean[getSize()][getSize()];
        for (int i = 0; i < getSize(); i++) {
            for (int j = 0; j < getSize(); j++) {
                newSquares[i][j] = getSquares()[j][getSize() - i - 1];
            }
        }
        setSquares(newSquares);
    }

    /*
     * Method name: flipVert()
     * Method type: Non-static
     * Description: Flips the tile vertically
     */
    public void flipVert() {
        // Get reference to squares
        boolean[][] flippedSquares = getSquares();

        // Loop throw tile's columns
        for (int i = 0; i < getSize(); i++) {
            for (int j = 0; j < getSize() / 2; j++) {
                // Swap top rows with bottom rows
                flippedSquares[j][i] ^= flippedSquares[getSize() - j - 1][i];
                flippedSquares[getSize() - j - 1][i] ^= flippedSquares[j][i];
                flippedSquares[j][i] ^= flippedSquares[getSize() - j - 1][i];
            }
        }
    }

    /*
     * Method name: flipHorz()
     * Method type: Non-static
     * Description: Flips the tile horizontally
     */
    public void flipHorz() {
        // Get reference to squares
        boolean[][] flippedSquares = getSquares();

        // Loop through tile's rows
        for (int i = 0; i < getSize(); i++) {
            for (int j = 0; j < getSize() / 2; j++) {
                // Swap left columns with right columns
                flippedSquares[i][j] ^= flippedSquares[i][getSize() - j - 1];
                flippedSquares[i][getSize() - j - 1] ^= flippedSquares[i][j];
                flippedSquares[i][j] ^= flippedSquares[i][getSize() - j - 1];
            }
        }
    }

    /*
     * Method name: placeTile()
     * Method type: non-static
     * Parameters: int r - The row of the tile location. Assumed to be valid.
     *             int c - The column of the tile location. Assumed to be valid.
     *             char currentPlayer - The character representing the current player
     *             char[][] - The game board
     * Description: Places a tile onto a valid location on the game board.
     */
    public void placeTile(int r, int c, char currentPlayer, char[][] board) {
        // Loop through piece at (r,c)
        for (int i = r; i < r + getSize(); i++) {
            for (int j = c; j < c + getSize(); j++) {
                // Square in tile is not empty
                if (getSquares()[i-r][j-c]) {
                    board[i][j] = currentPlayer;
                }
            }
        }
    }

    /*
     * Method name: newTileSet()
     * Method type: static
     * Return type: Map<String,Tile> - A set of 21 key-value pairs, each
     * representing a unique Blokus tile. The String represents its identifier name,
     * while Tile represents the data contained within the tile.
     * Description: Returns a new hard-coded 21-tile Blokus set.
     */
    public static Map<String, Tile> newTileSet() {
        Map<String, Tile> newTiles = new HashMap<>();

        newTiles.put("I1", new Tile(1, 1, new boolean[][] { { true } }));
        newTiles.put("I2", new Tile(2, 2, new boolean[][] { { true, true }, { false, false } }));
        newTiles.put("V3", new Tile(3, 2, new boolean[][] { { true, false }, { true, true } }));
        newTiles.put("I3", new Tile(3, 3,
                new boolean[][] { { false, false, false }, { true, true, true, }, { false, false, false } }));
        newTiles.put("O", new Tile(4, 2, new boolean[][] { { true, true }, { true, true } }));
        newTiles.put("T4", new Tile(4, 3,
                new boolean[][] { { false, false, false }, { true, true, true }, { false, true, false } }));
        newTiles.put("L4", new Tile(4, 3,
                new boolean[][] { { true, false, false }, { true, true, true }, { false, false, false } }));
        newTiles.put("Z4", new Tile(4, 3,
                new boolean[][] { { false, false, false }, { true, true, false }, { false, true, true } }));
        newTiles.put("I4", new Tile(4, 4,
                new boolean[][] { { false, false, false, false }, { true, true, true, true },
                        { false, false, false, false }, { false, false, false, false } }));
        newTiles.put("F", new Tile(5, 3,
                new boolean[][] { { false, true, false }, { true, true, true }, { true, false, false } }));
        newTiles.put("X", new Tile(5, 3,
                new boolean[][] { { false, true, false }, { true, true, true }, { false, true, false } }));
        newTiles.put("P", new Tile(5, 3,
                new boolean[][] { { true, true, false }, { true, true, false }, { true, false, false } }));
        newTiles.put("W", new Tile(5, 3,
                new boolean[][] { { true, false, false }, { true, true, false }, { false, true, true } }));
        newTiles.put("Z5", new Tile(5, 3,
                new boolean[][] { { true, true, false }, { false, true, false }, { false, true, true } }));
        newTiles.put("U", new Tile(5, 3,
                new boolean[][] { { true, false, true }, { true, true, true }, { false, false, false } }));
        newTiles.put("T5", new Tile(5, 3,
                new boolean[][] { { true, true, true }, { false, true, false }, { false, true, false } }));
        newTiles.put("V5", new Tile(5, 3,
                new boolean[][] { { true, false, false }, { true, false, false }, { true, true, true } }));
        newTiles.put("Y", new Tile(5, 4,
                new boolean[][] { { false, false, false, false }, { false, true, false, false },
                        { true, true, true, true }, { false, false, false, false } }));
        newTiles.put("L5", new Tile(5, 4,
                new boolean[][] { { false, false, false, false }, { true, false, false, false },
                        { true, true, true, true }, { false, false, false, false } }));
        newTiles.put("N", new Tile(5, 4,
                new boolean[][] { { false, false, false, false }, { false, true, true, true },
                        { true, true, false, false }, { false, false, false, false } }));
        newTiles.put("I5", new Tile(5, 5,
                new boolean[][] { { false, false, false, false, false }, { false, false, false, false, false },
                        { true, true, true, true, true }, { false, false, false, false, false },
                        { false, false, false, false, false } }));

        return newTiles;
    }

}