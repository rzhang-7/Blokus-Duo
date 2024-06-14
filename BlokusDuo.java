
// Import libraries
import java.io.IOException;
import java.util.*;

/*
 * Program name: BlokusDuo
 * Programmer: Raymond Zhang
 * Last modified: 02/06/2024
 * Description: Driver code for the Blokus Duo game.
 */

public class BlokusDuo {
    // Sizes
    public static final int BOARD_SIZE = 14;
    public static final int SAVE_NAME_SIZE = 13;

    private static final String QUIT_VAL = "0";

    // Display board characters
    public static final char P1 = '@';
    public static final char P2 = 'O';
    public static final char INVALID = 'X';
    public static final char AVAIL = '*';

    // Tile controls
    private static final char UP = 'W';
    private static final char DOWN = 'S';
    private static final char LEFT = 'A';
    private static final char RIGHT = 'D';
    private static final char ROTATE_RIGHT = 'R';
    private static final char ROTATE_LEFT = 'E';
    private static final char FLIP_VERT = 'V';
    private static final char FLIP_HORZ = 'F';


    /*
     * Method name: printError
     * Paramenters: String message - The message to print out when the error is caused.
     * Description: Shortcut method for printing an error message and allowing user to press enter to continue.
     */
    public static void printError(String message) {
        // Create new scanner
        Scanner sc = IO.newScanner();

        // Print error message
        System.out.println();
        System.out.printf("ERROR: %s", message);
        System.out.println("<Press [Enter] to continue.>");
        sc.nextLine();
    }

    /*
     * Method name: printMenu
     * Parameters: int menuType - Defines the type of menu to be displayed. 0 is
     * main menu, 1 is the difficulty selection, 2 is the list of saved files
     * Description: Displays the game's main menu.
     */

    public static void printMenu(int menuType) {
        // Declare variables
        ArrayList<String> saveNames;
        String format = String.format("%%-%ds", SAVE_NAME_SIZE);

        System.out.println();

        switch (menuType) {
            case 0: // Main menu
            case 1: // Choosing difficulty
                System.out.println(String.join("\n", "[@][@]   [@][@][O][O][@][O][@]   [O][@][@]   [O][O]",
                        "[@][O][@][@][O][O][O]   [@][@][@][O][O][@][@][@][O][O]",
                        "[@][O] __________ __       __                   [@][@]",
                        "   [@] \\______   \\ | ____ |  | ____ __  ______     [@]",
                        "[@][@]  |   |  _/  |/ __ \\|  |/ /  |  \\/  ___|  [@][@]",
                        "   [@]  |   |   \\  |  |_| |    \\|  |  /\\___ \\   [O]",
                        "[@]    /________/__|\\____/| /\\__\\____/|_____/   [O][O]",
                        "[@][@]                    |/ | | \\ | | | / / \\  [@][O]",
                        "[O][@][@]                    |_|_/ \\_\\_/ \\_\\_/  [@][O]",
                        "[O]                                                [@]",
                        String.format("[O][O][O]   %-31s     [@][@]",
                                (menuType == 0 ? "[1] New game      [2] Load save" : "     <Select difficulty>")),
                        String.format("   [@]    %-35s   [O][@]",
                                (menuType == 0 ? "  [3] How to play   [" + QUIT_VAL + "] Quit"
                                        : "[1] Easy   [2] Hard   [" + QUIT_VAL + "] Main Menu")),
                        "[@][@][@]                                       [O][@]",
                        "[O][@][O][@][@][@]      [@][@][O]            [O][O][O]",
                        "[O][O][O]   [@][@][O][O][O][@]   [O][O][O][O][@][@][@]"));
                break;

            case 2: // List saved files
                saveNames = Saves.getSaveNames();

                System.out.println(String.join("\n", "[@][@]   [@][@][O][O][@][O][@]   [O][@][@]   [O][O]",
                        "[@][O][@][@][O][O][O]   [@][@][@][O][O][@][@][@][O][O]",
                        "[@][O]                                          [@][@]",
                        "   [@]    Saved games in file:                     [@]",
                        "[@][@]                                          [@][@]",
                        String.format("   [@]    [1] " + format + "  [2] " + format + "  [O]",
                                (saveNames.size() >= 1 ? saveNames.get(0) : ""),
                                (saveNames.size() >= 2 ? saveNames.get(1) : "")),
                        String.format("[@]       [3] " + format + "  [4] " + format + "  [O][O]",
                                (saveNames.size() >= 3 ? saveNames.get(2) : ""),
                                (saveNames.size() >= 4 ? saveNames.get(3) : "")),
                        String.format("[@][@]    [5] " + format + "  [6] " + format + "  [@][O]",
                                (saveNames.size() >= 5 ? saveNames.get(4) : ""),
                                (saveNames.size() >= 6 ? saveNames.get(5) : "")),
                        String.format("[O][@][@] [7] " + format + "  [8] " + format + "  [@][O]",
                                (saveNames.size() >= 7 ? saveNames.get(6) : ""),
                                (saveNames.size() >= 8 ? saveNames.get(7) : "")),
                        String.format("[O]       [9] " + format + "  [10] " + format + "    [@]",
                                (saveNames.size() >= 9 ? saveNames.get(8) : ""),
                                (saveNames.size() >= 10 ? saveNames.get(9) : "")),
                        String.format("[O][O][O] [11] " + format + " [12] " + format + " [@][@]",
                                (saveNames.size() >= 11 ? saveNames.get(10) : ""),
                                (saveNames.size() >= 12 ? saveNames.get(11) : "")),
                        "   [@]              [" + QUIT_VAL + "] Quit                    [O][@]",
                        "[@][@][@]                                       [O][@]",
                        "[O][@][O][@][@][@]      [@][@][O]            [O][O][O]",
                        "[O][O][O]   [@][@][O][O][O][@]   [O][O][O][O][@][@][@]"));
                break;

        }

        System.out.println();
    }

    /*
     * Method name: printBoard
     * Parameters: char[][] board - The game board
     * Description: Prints the current status of the board in game.
     */

    public static void printBoard(char[][] board) {
        System.out.println();
        // Print column coordinates
        System.out.print("   ");
        for (int i = 1; i <= BOARD_SIZE; i++) {
            System.out.printf(" %-2d", i);
        }
        System.out.println();

        // Print board
        for (int i = 0; i < BOARD_SIZE; i++) {
            // Print row coordinates
            System.out.printf("%2d ", i + 1);

            for (int j = 0; j < BOARD_SIZE; j++) {
                System.out.printf("[%c]", (board[i][j] == (char) 0 ? ' ' : board[i][j]));
            }
            System.out.println();
        }

        System.out.println();
    }

    /*
     * Method name: printScore
     * Parameters: int p1Score, int p2Score - The score of the two players
     * Description: Outputs the scores of each player
     */
    public static void printScore(int p1Score, int p2Score) {
        System.out.printf("P1 Score: %-2d                     P2 Score: %-2d%n", p1Score, p2Score);
        System.out.println();
    }

    /*
     * Method name: printTiles()
     * Parameters: Map<String, Tile> tiles - The tile set used by the player
     * char playerChar - The symbol representing the current player; future
     * implementations may make the game two-player rather than single player.
     * Description: Displays all the game tiles for the player
     */
    public static void printTiles(Map<String, Tile> tiles, char playerChar) {
        final String[][] TILE_ROWS = { { "I1", "I2", "I3", "V3", "O" }, { "T4", "L4", "Z4", "I4" },
                { "F", "X", "P", "W" }, { "Z5", "U", "T5", "V5" }, { "N", "Y", "L5" }, { "I5" } };
        Tile t;
        int maxRow = 0;
        boolean[][] tileSquares;

        // Print tiles on a line
        for (String[] line : TILE_ROWS) {

            // Get max number of rows needed
            for (String tileName : line) {
                t = tiles.get(tileName);
                maxRow = Math.max(maxRow, t.getRows());
            }

            // Print all rows
            for (int i = 0; i < maxRow; i++) {
                for (String tileName : line) {
                    // Get tile
                    t = tiles.get(tileName);
                    tileSquares = t.getPrintSquares();

                    // Print tile segment
                    for (int j = 0; j < t.getCols(); j++) {
                        // Only print tiles aligned to the bottom
                        if (i >= maxRow - t.getRows()) {
                            // Check if empty tile
                            if (tileSquares[i - (maxRow - t.getRows())][j]) {
                                // TODO: Use invalid character if tile cannot be placed
                                // Fill with player colour if unused
                                System.out.printf("[%c]", (t.getUsed() ? ' ' : playerChar));
                            } else {
                                System.out.print("   ");
                            }
                        }
                        // Shorter tiles will have upper rows replaced with spaces
                        else {
                            System.out.print("   ");
                        }
                    }
                    // Print separator
                    System.out.print("  ");
                }
                System.out.println();
            }

            // Reset max row value
            maxRow = 0;

            // Print tile names, centered to its corresponding tile piece
            for (String tileName : line) {
                // Get tile
                t = tiles.get(tileName);

                // Even number of columns; name goes under between two center squares
                if (t.getCols() % 2 == 0) {
                    // Print left half
                    for (int i = 0; i < t.getCols() / 2 - 1; i++) {
                        System.out.print("   ");
                    }

                    // Print tile name
                    System.out.printf("  %-2s  ", tileName);

                    // Print right half
                    for (int i = 0; i < t.getCols() / 2 - 1; i++) {
                        System.out.print("   ");
                    }
                }
                // Odd number of columns; name goes under the center squares
                else {
                    // Print left half
                    for (int i = 0; i < t.getCols() / 2; i++) {
                        System.out.print("   ");
                    }

                    // Print tile name
                    System.out.printf("%2s ", tileName);

                    // Print right half
                    for (int i = 0; i < t.getCols() / 2; i++) {
                        System.out.print("   ");
                    }
                }

                // Print separator
                System.out.print("  ");
            }
            System.out.println();
            System.out.println();
        }
    }

    /*
     * Method name: placeTile
     * Parameters: String tileName - The name of the selected tile
     * Tile selectedTile - The Tile object of the selected tile
     * char[][] board - The game board
     * 
     */
    public static void placeTile(String tileName, Tile selectedTile, char[][] board) {
        // Declare variables
        int x, y;
        String coords;
        String[] coordPair;
        boolean validCoord = false;
        // Create new scanner
        Scanner sc = IO.newScanner();
        // System.out.printf("Tile %s is at (%d, %d)%n", tileName, x, y);

        // Get coordinates
        do {
            System.out.print("Enter the coordinates (x, y): ");
            coords = sc.nextLine();

            // Extract numbers from input string
            coordPair = coords.split("-?\\d+");

            // User entered more or less numbers than needed
            if(coordPair.length != 2) {
                printError("Please enter two separated integers.");
            }
            // Extract x and y values
            else {
                x = Integer.parseInt(coordPair[0]);
                y = Integer.parseInt(coordPair[1]);

                // Coordinates out of points
                if(x <= 0 || x > BOARD_SIZE || y <= 0 || y > BOARD_SIZE) {
                    printError(String.format("Coordinates must be between 1 and %d inclusive.", BOARD_SIZE));
                }
                // Valid coordinates were found
                else {
                    validCoord = true;
                }
            }

        } while(!validCoord);

        // TODO: Create display board

        // TODO: Allow user to shift, rotate, or flip tile until valid or they choose another tile

        // TODO: Write tile to board

        // TODO: Update available tiles
    }

    /*
     * Method name: runGame
     * Parameters: char[][] board - The state of the game board
     * int p1Score - Score of the first player
     * Map<String, Tile> p1Tiles - Tiles used by first player
     * int p2Score - Score of the second player
     * Map<String, Tile> p2Tiles - Tiles used by the second player
     * boolean isHard - The difficulty of the bot; false is easy mode,
     * true is hard mode
     * Descriptions: Runs the game loop for playing a round of the game
     */

    public static void runGame(char[][] board, int p1Score, Map<String, Tile> p1Tiles, int p2Score,
            Map<String, Tile> p2Tiles, boolean isHard) {
        // Declare constants and variables
        final int MAX_SAVE_FILES = 12;
        int saveIndex;
        String menuSelection, tileName, quitConfirmation, choiceSave, overwriteConfirmation;
        ArrayList<String> saveNames;
        Tile selectedTile;
        boolean p1CanMove = true, p2CanMove = true, running = true, tileSelect = false,
                validSave = false, menuSelect = false;
        Scanner sc = IO.newScanner();

        // Game loop
        while (running) {
            // If Player 1 has any valid moves
            if (p1CanMove) {
                // Give user options
                do {
                    // Print game information
                    printBoard(board);
                    printScore(p1Score, p2Score);

                    // Print selection menu
                    System.out.println("What would you like to do?");
                    System.out.println();
                    System.out.println("  [1] List tiles/Select a tile");
                    System.out.println("  [2] Save and quit game");
                    System.out.println("  [3] Quit game without saving");

                    // Get user input
                    System.out.print("Enter your selection: ");
                    menuSelection = sc.nextLine();

                    switch (menuSelection) {
                        // Show tiles and select
                        case "1":
                        case "[1]":
                            printTiles(p1Tiles, P1); // May be p2 if doing 2-player

                            do {
                                // Get user input for tile
                                System.out.printf("Select a tile to place (%s to quit): ", QUIT_VAL);
                                tileName = sc.nextLine().toUpperCase(); // convert to upper for case insensitivity

                                // User chose to quit
                                if(tileName.equals(QUIT_VAL)) {
                                    tileSelect = true;
                                }
                                // Valid tile was selected
                                else if (p1Tiles.get(tileName) != null) {
                                    selectedTile = p1Tiles.get(tileName);
                                    // Tile has already been used
                                    if (selectedTile.getUsed()) {
                                        System.out.printf("ERROR: Tile %s has already been placed%n", tileName);
                                    }
                                    // TODO: Place tile somewhere
                                    else {
                                        placeTile(tileName, selectedTile, board);

                                        // Exit loop if successfully placed tile
                                        if(selectedTile.getUsed())
                                            tileSelect = true;
                                    }
                                }
                                // Invalid tile name
                                else {
                                    System.out.println("ERROR: Please select a valid tile name.");
                                    System.out.println("[Press <Enter> to continue]");
                                    sc.nextLine();
                                }
                            } while(!tileSelect);

                            break;

                        // Save and quit game
                        case "2":
                        case "[2]":
                            // Get save file names
                            saveNames = Saves.getSaveNames();

                            // Overwrite save names if save file full
                            if (saveNames.size() == MAX_SAVE_FILES) {
                                do {
                                    // Get save file name from user
                                    printMenu(2);
                                    System.out
                                            .print("Save file is full. Please select a save file to overwrite: ");
                                    choiceSave = sc.nextLine();

                                    // User chose to quit; exit loop
                                    if (choiceSave.equals(QUIT_VAL) || choiceSave.equals("[" + QUIT_VAL + "]")
                                            || choiceSave.toLowerCase().equals("quit")) {
                                        validSave = true;
                                    }
                                    // Overwrite existing save file
                                    else {
                                        saveIndex = findSaveIndex(choiceSave, saveNames);
                                        // Found save file
                                        if (saveIndex != -1) {
                                            choiceSave = saveNames.get(saveIndex);
                                            // Overwrite save file
                                            System.out.println("Overwriting " + choiceSave + "...");
                                            try {
                                                Saves.writeSave(choiceSave, board, p1Score, p1Tiles, p2Score,
                                                        p2Tiles, isHard);
                                                System.out.println("Save successfully overwritten.");
                                            }
                                            // File overwrite was unsuccessful
                                            catch (IOException e) {
                                                System.out.println(e + " Problem writing file.");
                                            }

                                            System.out.println("Returning to main menu...");
                                            System.out.println("<Press [Enter] to continue.");
                                            sc.nextLine();

                                            // Exit loop when done and return to main menu
                                            validSave = true;
                                            running = false; // Break out of game loop
                                        }

                                        // Did not find save
                                        else {
                                            printError("Invalid selection.");
                                        }
                                    }

                                } while (!validSave);
                            }
                            // Add new save file or overwrite if already exists
                            else {
                                do {
                                    // Get save file name from user
                                    System.out.printf("Enter a name for the save file (%s to cancel): ",
                                            QUIT_VAL);
                                    choiceSave = sc.nextLine().trim();

                                    // User chose to quit; exit loop
                                    if (choiceSave.equals(QUIT_VAL)) {
                                        validSave = true;
                                    }
                                    // Process new save file name
                                    else {
                                        // Find index of save name in saves folder
                                        saveIndex = findSaveIndex(choiceSave, saveNames);

                                        // Name cannot be "quit" to avoid confusion during file selection
                                        if (choiceSave.toLowerCase().equals("quit")) {
                                            printError("Save name cannot be \"quit\".");
                                        }
                                        // Name must contain at least one character
                                        else if(choiceSave.matches("^[0-9]+$")) {
                                            printError("Save name must contain alphabetic characters");
                                        }
                                        // Save file already exists; ask to overwrite
                                        else if (saveIndex != -1) {
                                            // Get confirmation for overwriting save file
                                            System.out.printf(
                                                    "\"%s\" already exists. Would you like to overwrite it (y/n)? ",
                                                    choiceSave);
                                            overwriteConfirmation = sc.nextLine().toLowerCase();

                                            switch (overwriteConfirmation) {
                                                case "y":
                                                case "yes":
                                                    try {
                                                        Saves.writeSave(choiceSave, board, p1Score, p1Tiles, p2Score,
                                                                p2Tiles, isHard);
                                                        System.out.println("Save successfully overwritten.");
                                                    }
                                                    // File overwrite was unsuccessful
                                                    catch (IOException e) {
                                                        System.out.println(e + " Problem writing file.");
                                                    }
                                                    System.out.println("Returning to main menu...");
                                                    System.out.println("<Press [Enter] to continue>");
                                                    sc.nextLine();

                                                    // Exit loop when done and return to main menu
                                                    validSave = true;
                                                    running = false; // Break out of game loop
                                                    break;

                                                // Do nothing if anything other than "yes" is entered
                                                case "n":
                                                case "no":
                                                    break;
                                            }
                                        }

                                        // Save name is too long or too short
                                        else if (choiceSave.length() > SAVE_NAME_SIZE || choiceSave.length() == 0) {
                                            printError(String.format("File name must be less than %d characters long and contain at least 1 non-whitespace character.", SAVE_NAME_SIZE));
                                        }

                                        // Create new save file
                                        else {
                                            try {
                                                Saves.writeSave(choiceSave, board, p1Score, p1Tiles, p2Score,
                                                        p2Tiles, isHard);
                                                System.out.printf("%nSaved saved game state in %s.%n", choiceSave);

                                                // Exit loop when done and return to main menu
                                                validSave = true;
                                                tileSelect = true; // Break out of tile selection
                                                running = false; // Break out of game loop
                                            }
                                            // File overwrite was unsuccessful
                                            catch (IOException e) {
                                                System.out.println(e + " Problem writing file.");
                                            }

                                            System.out.println("Returning to main menu...");
                                            System.out.println("<Press [Enter] to continue>");
                                            sc.nextLine();
                                        }
                                    }

                                } while (!validSave);
                            }

                            // Reset looping condition
                            validSave = false;

                            break;

                        // Quit without saving
                        case "3":
                        case "[3]":
                            // Get quit confirmation
                            System.out.print(
                                    "Are you sure you want to quit? All progress will be lost (y/n): ");
                            quitConfirmation = sc.nextLine().toLowerCase();

                            switch (quitConfirmation) {
                                // Exit game loop
                                case "y":
                                case "yes":
                                    System.out.println("Returning to main menu...");

                                    // Break out of game loops
                                    menuSelect = true; // Menu selection
                                    running = false; // Game loop
                                    break;
                                // Don't exit game loop
                                case "n":
                                case "no":
                                    break;
                                // User made invalid choice
                                default:
                                    printError("Invalid selection.");
                                    break;
                            }

                            break;

                        // User made invalid choice
                        default:
                            printError("Invalid selection.");
                            break;
                    }

                } while (!menuSelect);

                // Reset looping condition
                menuSelect = false;
            }
            // Player 1 has no valid moves; skip turn
            else {
                System.out.println("Player 1 cannot move. Skipping turn...");
                System.out.println("<Press [Enter] to continue>");
                sc.nextLine();
            }

            // TODO: CPU move if game has not been quit
            if (running) {
                if (p2CanMove) {
                    // cpuMove(p2Tiles, isHard);
                    // p2CanMove = checkAvailableMoves(board, p2Tiles);
                } else {
                    System.out.println("Player 2 cannot move. Skipping turn...");
                    System.out.println("<Press [Enter] to continue>");
                    sc.nextLine();
                }
            }

            // Game is over when neither player can move
            if (!p1CanMove && !p2CanMove) {
                System.out.println();
                System.out.println("**GAME OVER**");
                // Scores are equal; tie game
                if (p1Score == p2Score) {
                    System.out.printf("Tie Game: Both players scored %d points.%n", p1Score);
                }
                // Either player 1 or player 2 wins
                else {
                    System.out.printf("Player %d wins!", (p1Score > p2Score ? 1 : 2));
                }

                // Return to main menu
                System.out.println();
                System.out.println("Returning to main menu...");
                System.out.println("<Press [Enter] to continue>");
                sc.nextLine();
            }
        }

    }

    /*
     * Method name: findSave
     * Parameters: String saveName, ArrayList<String> saveNames - The save file name
     * to search for and the list to search.
     * Return type: int - The index of saveName in saveNames, or -1 if not found
     * ArrayList<String> saveNames - The list of name to search
     * Description: Finds the index of the name of a save file in an array while
     * ignoring case sensitivity.
     */

    public static int findSaveIndex(String saveName, ArrayList<String> saveNames) {
        // Declare variables
        int idx = -1;

        // Match by index with square brackets
        if (saveName.matches("^\\[1?[0-9]\\]$")) {
            idx = Integer.parseInt(saveName.substring(1, saveName.indexOf(']'))) - 1;

            // Set index to be invalid if out of range
            if (idx >= saveNames.size()) {
                idx = -1;
            }
        }
        // Match by index
        else if (saveName.matches("^1?[0-9]$")) {
            idx = Integer.parseInt(saveName) - 1;

            // Set index to be invalid if out of range
            if (idx >= saveNames.size()) {
                idx = -1;
            }
        }

        // Assume user entered the name of the file
        else {
            for (int i = 0; i < saveNames.size() && idx == -1; i++) {
                if (saveName.equalsIgnoreCase(saveNames.get(i)))
                    idx = i;
            }
        }

        return idx;
    }

    /*
     * Implement game loop
     * Print main menu
     * Get options
     */
    public static void main(String[] args) {
        final int START_1 = 4, START_2 = 9;

        // Menu and selection variables
        String choiceMenu, choiceDifficulty, choiceSave;
        boolean running = true, validDifficulty = false, validSave = false;
        int saveIndex;
        ArrayList<String> saveNames;

        // Game variables
        int p1Score;
        int p2Score;
        Map<String, Tile> p1Tiles;
        Map<String, Tile> p2Tiles;
        char[][] board = new char[BOARD_SIZE][BOARD_SIZE];
        boolean isHard;

        // Set starting positions for board; will be overwritten if loading save
        board[START_1][START_1] = AVAIL;
        board[START_2][START_2] = AVAIL;

        Scanner sc = IO.newScanner();

        // Main loop
        while (running) {
            // Display menu
            printMenu(0);

            // Get choice from user
            System.out.print("Enter your selection: ");
            choiceMenu = sc.nextLine().toLowerCase(); // convert to lower for case insensitivity

            switch (choiceMenu) {
                // Start new game
                case "1":
                case "[1]":
                case "new":
                case "new game":
                    // Choose difficulty
                    do {
                        printMenu(1);
                        System.out.print("Enter the difficulty of the bot: ");
                        choiceDifficulty = sc.nextLine().toLowerCase();
                        switch (choiceDifficulty) {
                            // Easy mode
                            case "1":
                            case "[1]":
                            case "easy":
                                // Reset game variables
                                board = new char[BOARD_SIZE][BOARD_SIZE];
                                board[START_1][START_1] = AVAIL;
                                board[START_2][START_2] = AVAIL;
                                p1Score = 0;
                                p2Score = 0;
                                p1Tiles = Tile.newTileSet();
                                p2Tiles = Tile.newTileSet();

                                // Start new game on easy mode
                                isHard = false;
                                runGame(board, p1Score, p1Tiles, p2Score, p2Tiles, isHard);
                                validDifficulty = true;
                                break;
                            // Hard mode
                            case "2":
                            case "[2]":
                            case "hard":
                                // Reset game variables
                                board = new char[BOARD_SIZE][BOARD_SIZE];
                                board[START_1][START_1] = AVAIL;
                                board[START_2][START_2] = AVAIL;
                                p1Score = 0;
                                p2Score = 0;
                                p1Tiles = Tile.newTileSet();
                                p2Tiles = Tile.newTileSet();

                                // Start new game on hard mode
                                isHard = true;
                                runGame(board, p1Score, p1Tiles, p2Score, p2Tiles, isHard);
                                validDifficulty = true;
                                break;

                            // Return to main menu
                            case QUIT_VAL:
                            case "[0]":
                            case "menu":
                            case "main menu":
                                validDifficulty = true;
                                break;

                            // User made invalid choice
                            default:
                                pirntError("Invalid selection.");
                                break;
                        }
                    } while (!validDifficulty);

                    // Reset looping condition
                    validDifficulty = false;

                    break;

                // Load game from save
                case "2":
                case "[2]":
                case "load":
                case "load save":
                    // Get save file names
                    saveNames = Saves.getSaveNames();

                    do {
                        // Get save file name from user
                        printMenu(2);
                        System.out.print("Enter a save file to load: ");
                        choiceSave = sc.nextLine().trim();

                        // User chose to quit
                        if (choiceSave.equals(QUIT_VAL) || choiceSave.equals("[" + QUIT_VAL + "]")
                                || choiceSave.toLowerCase().equals("quit")) {
                            validSave = true;
                        } else {
                            saveIndex = findSaveIndex(choiceSave, saveNames);
                            // Found save file
                            if (saveIndex != -1) {
                                choiceSave = saveNames.get(saveIndex);
                                // Read save information file and start game
                                try {
                                    board = Saves.getSaveBoard(choiceSave);
                                    p1Score = Saves.getPlayerScore(choiceSave, 1);
                                    p1Tiles = Saves.getPlayerTiles(choiceSave, 1);
                                    p2Score = Saves.getPlayerScore(choiceSave, 2);
                                    p2Tiles = Saves.getPlayerTiles(choiceSave, 2);
                                    isHard = Saves.getDifficulty(choiceSave);
                                    runGame(board, p1Score, p1Tiles, p2Score, p2Tiles, isHard);
                                }
                                // Could not read file
                                catch (IOException e) {
                                    System.out.println(e + " Problem reading file.");
                                }

                                // Exit to main menu when done
                                validSave = true;
                            }

                            // Did not find save
                            else {
                                printError("Invalid selection.");
                            }
                        }

                    } while (!validSave);

                    // Reset looping condition
                    validSave = false;

                    break;

                // Instructions
                case "3":
                case "[3]":
                case "how to play":
                    break;

                // Quit
                case QUIT_VAL:
                case "[0]":
                case "quit":
                    System.out.println("Thanks for playing!");
                    running = false;
                    break;

                // User made invalid choice
                default:
                    printError("Invalid selection.");
                    break;
            }
        }
    }
}