import java.util.*;
/*
 * Program name: Tile
 * Programmer: Raymond Zhang
 * Last modified: 06/07/2024
 * Description: Instance class for Blokus tiles
 */

public class Tile {
    private final int points;
    private int posR;
    private int posC;
    private int rows;
    private int cols;
    private boolean used;
    private boolean placeable;
    private boolean[][] squares;

    /*
     * Method name: Tile
     * Parameters: int points - The point value of the tile
     * int rows - The length of the grid containing the tile
     * int cols - The width of the grid containing the tile
     * boolean[][] squares - The grid representing the squares occupied by
     * the tile
     * Description: Constructor method that defines the point value, dimensions, and
     * squares occupied by the tile.
     */
    public Tile(int points, int rows, int cols, boolean[][] squares) {
        this.points = points;
        this.rows = rows;
        this.cols = cols;
        this.squares = squares;

        placeable = true;
    }

    /*
     * Method name: Tile
     * Parameters: int points - The point value of the tile
     * int rows - The length of the grid containing the tile
     * int cols - The width of the grid containing the tile
     * int posR - The row position of the tile
     * int posC - The column position of the tile
     * boolean[][] squares - The grid representing the squares occupied by
     * the tile
     * Description: Constructor method that defines the point value, dimensions, coordinates, and
     * squares occupied by the tile.
     */
    public Tile(int points, int rows, int cols, int posR, int posC, boolean[][] squares) {
        this.points = points;
        this.rows = rows;
        this.cols = cols;
        this.posR = posR;
        this.posC = posC;
        this.squares = squares;
        placeable = true;
    }

    /*
     * Method name: getPoints()
     * Return type: int - The point-value of the Tile instance
     * Description: Accessor method for the points attribute.
     */
    public int getPoints() {
        return points;
    }

    /*
     * Method name: getRows()
     * Return type: int - The length of the Tile instance
     * Description: Accessor method for the rows attribute.
     */
    public int getRows() {
        return rows;
    }

    /*
     * Method name: getCols()
     * Return type: int - The width of the Tile instance
     * Description: Accessor method for the cols attribute.
     */
    public int getCols() {
        return cols;
    }

    /*
     * Method name: getSquares()
     * Return type: boolean[][] - The square area taken up by the Tile instance
     * Description: Accessor method for the squares attribute.
     */
    public boolean[][] getSquares() {
        return squares;
    }

    /*
     * Method name: getSquaresCopy()
     * Return type: boolean[][] - A deep copy of the squares taken up by the
     * Tile instance
     * Description: Gets a deep copy of the squares array.
     */
    public boolean[][] getSquaresCopy() {
        boolean[][] squaresCopy = new boolean[getRows()][getCols()];

        // Copy the squares array
        for (int i = 0; i < getRows(); i++) {
            System.arraycopy(getSquares()[i], 0, squaresCopy[i], 0, getCols());
        }
        return squaresCopy;
    }

    /*
     * Method name: isUsed()
     * Return type: boolean - Whether or not the Tile instance has been used in
     * game.
     * Description: Accessor method for the used attribute.
     */
    public boolean isUsed() {
        return used;
    }

    /*
     * Method name: isPlaceable()
     * Return type: boolean - Whether or not the Tile instance can be placed on the
     * board.
     * Description: Accessor method for the placeable attribute.
     */
    public boolean isPlaceable() {
        return placeable;
    }

    /*
     * Method name: getPosR()
     * Return type: int - The row number of the tile instance in the game board
     * Description: Accessor method for the posR attribute.
     */
    public int getPosR() {
        return posR;
    }

    /*
     * Method name: getPosC()
     * Return type: int - The column number of the tile instance in the game board
     * Description: Accessor method for the posC attribute.
     */
    public int getPosC() {
        return posC;
    }

    /*
     * Method name: setRows()
     * Parameters: int rows - The new number of rows
     * Description: Mutator method for the rows attribute.
     */
    public void setRows(int rows) {
        this.rows = rows;
    }

    /*
     * Method name: setCols()
     * Parameters: int cols - The new number of columns
     * Description: Mutator method for the cols attribute.
     */
    public void setCols(int cols) {
        this.cols = cols;
    }

    /*
     * Method name: setSquares()
     * Parameters: boolean[][] newSquares - The new tile area
     * Description: Mutator method for the squares attribute.
     */
    public void setSquares(boolean[][] newSquares) {
        squares = newSquares;
    }

    /*
     * Method name: setUsed()
     * Parameters: boolean used - The new used state.
     * Description: Mutator method for the used attribute.
     */
    public void setUsed(boolean used) {
        this.used = used;
    }

    /*
     * Method name: setRows()
     * Parameters: boolean placeable - The new placeable state
     * Description: Mutator method for the placeable attribute.
     */
    public void setPlaceable(boolean placeable) {
        this.placeable = placeable;
    }

    /*
     * Method name: rotateRight()
     * Description: Rotates the tile clockwise.
     */
    public void rotateRight() {
        // Declare variables
        int newRows = getCols(), newCols = getRows(); // Reverse rows and columns

        // Create new squares with reversed dimensions
        boolean[][] newSquares = new boolean[newRows][newCols];

        // Update rows and columns
        setCols(newCols);
        setRows(newRows);

        // Rotate squares
        for (int i = 0; i < getRows(); i++) {
            for (int j = 0; j < getCols(); j++) {
                newSquares[i][j] = getSquares()[getCols() - j - 1][i];
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
        // Declare variables
        int newRows = getCols(), newCols = getRows(); // Reverse rows and columns

        // Create new squares with reversed dimensions
        boolean[][] newSquares = new boolean[newRows][newCols];

        // Update rows and columns
        setCols(newCols);
        setRows(newRows);

        // Rotate squares
        for (int i = 0; i < getRows(); i++) {
            for (int j = 0; j < getCols(); j++) {
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
        for (int i = 0; i < getRows() / 2; i++) {
            for (int j = 0; j < getCols(); j++) {
                // Swap top rows with bottom rows
                flippedSquares[i][j] ^= flippedSquares[getRows() - i - 1][j];
                flippedSquares[getRows() - i - 1][j] ^= flippedSquares[i][j];
                flippedSquares[i][j] ^= flippedSquares[getRows() - i - 1][j];
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
     * Parameters: char[][] - The game board
     * int r - The row of the tile location. Assumed to be valid.
     * int c - The column of the tile location. Assumed to be valid.
     * char player - The character representing the current player
     * Description: Places the tile onto a valid location on the game board.
     */
    public void placeTile(char[][] board, int r, int c, char player) {
        // Loop through piece at (r,c)
        for (int i = r; i < r + getRows(); i++) {
            for (int j = c; j < c + getCols(); j++) {
                // Square in tile is not empty
                if (getSquares()[i - r][j - c]) {
                    board[i][j] = player;
                }
            }
        }
    }

    /*
     * Method name: getMoves
     * Parameters: char[][] board - The game board
     * char player - The character representing the player
     * Return type: ArrayList<Tile> - A list of Tile instances that can be placed on
     * the board
     * Description: Returns a list of valid tile arrangements for each index of the
     * board and updates the placeable attribute.
     */
    public ArrayList<Tile> getMoves(char[][] board, char player) {
        // Declare variables
        ArrayList<Tile> moves = new ArrayList<>();

        // Iterate through the board
        for (int i = 0; i < BlokusDuo.BOARD_SIZE; i++) {
            for (int j = 0; j < BlokusDuo.BOARD_SIZE; j++) {
                // Rotate to get all positions
                for (int k = 0; k < 4; k++) {
                    rotateRight();
                    // Check if tile can be placed
                    if (canPlaceAt(board, i, j, player)) {
                        moves.add(new Tile(getPoints(), getRows(), getCols(), i, j, getSquaresCopy()));
                    }
                }

                // Flip and rotate to get other potential positions
                flipVert();
                for (int k = 0; k < 4; k++) {
                    rotateRight();
                    // Check if tile can be placed
                    if (canPlaceAt(board, i, j, player)) {
                        moves.add(new Tile(getPoints(), getRows(), getCols(), i, j, getSquaresCopy()));
                    }
                }
            }
        }

        // Return to original orientation
        flipVert();

        // Update placeable state
        setPlaceable(!moves.isEmpty());

        return moves;
    }

    /*
     * Method name: canPlaceAt
     * Parameters: char[][] board - The game board
     * int r - The row of the tile
     * int c - The column of the tile
     * char player - The player to check
     * Return type: boolean - Whether or not the tile can be placed at the specified
     * row and column
     * Description: Checks whether or not a tile can be placed at a specified
     * location.
     */
    public boolean canPlaceAt(char[][] board, int r, int c, char player) {
        // Declare constants and variables
        final int[][] DIR = { { 0, 1 }, { -1, 0 }, { 0, -1 }, { 1, 0 } };
        int ni, nj;
        boolean valid = true, touchesAvail = false;
        // Tile is out of bounds
        if (r + getRows() > BlokusDuo.BOARD_SIZE || c + getCols() > BlokusDuo.BOARD_SIZE) {
            valid = false;
        } else {
            // Overlapping other tiles
            for (int i = r; i < r + getRows() && valid; i++) {
                for (int j = c; j < c + getCols() && valid; j++) {
                    if (getSquares()[i - r][j - c]) {
                        // Occupied area must be empty or an available character ('*')
                        valid &= board[i][j] == BlokusDuo.EMPTY || board[i][j] == BlokusDuo.AVAIL;

                        // Check if tile touches at least one available character
                        touchesAvail |= board[i][j] == BlokusDuo.AVAIL;

                        // Loop up/down/left/right directions
                        for (int k = 0; k < DIR.length && valid; k++) {
                            // Get new i and j values
                            ni = i + DIR[k][0];
                            nj = j + DIR[k][1];

                            // Check if new coordinates are in bounds
                            if (BlokusDuo.inRange(ni) && BlokusDuo.inRange(nj)) {
                                // Adjacent tiles should not match the player's
                                valid &= board[ni][nj] != player;
                            }
                        }
                    }
                }
            }
        }

        return valid && touchesAvail;
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