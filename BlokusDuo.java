
// Import libraries
import java.util.*;

/*
 * Program name: BlokusDuo
 * Programmer: Raymond Zhang
 * Last modified: 02/06/2024
 * Description: Driver code for the Blokus Duo game.
 */

public class BlokusDuo {

    public static final int BOARD_SIZE = 14;
    public static final int SAVE_NAME_SIZE = 13;

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
                System.out.println(String.join("\n", "[@][@]   [@][@][-][-][@][-][@]   [-][@][@]   [-][-]",
                        "[@][-][@][@][-][-][-]   [@][@][@][-][-][@][@][@][-][-]",
                        "[@][-] __________ __       __                   [@][@]",
                        "   [@] \\______   \\ | ____ |  | ____ __  ______     [@]",
                        "[@][@]  |   |  _/  |/ __ \\|  |/ /  |  \\/  ___|  [@][@]",
                        "   [@]  |   |   \\  |  |_| |    \\|  |  /\\___ \\   [-]",
                        "[@]    /________/__|\\____/| /\\__\\____/|_____/   [-][-]",
                        "[@][@]                    |/ | | \\ | | | / / \\  [@][-]",
                        "[-][@][@]                    |_|_/ \\_\\_/ \\_\\_/  [@][-]",
                        "[-]                                                [@]",
                        String.format("[-][-][-]   %-31s     [@][@]",
                                (menuType == 0 ? "[1] New game      [2] Load save" : "     <Select difficulty>")),
                        String.format("   [@]    %-35s   [-][@]",
                                (menuType == 0 ? "  [3] How to play   [4] Quit" : "[1] Easy   [2] Hard   [3] Main Menu")),
                        "[@][@][@]                                       [-][@]",
                        "[-][@][-][@][@][@]      [@][@][-]            [-][-][-]",
                        "[-][-][-]   [@][@][-][-][-][@]   [-][-][-][-][@][@][@]"));
                break;

            case 2: // List saved files
                saveNames = Query.getSaveNames();

                System.out.println(String.join("\n", "[@][@]   [@][@][-][-][@][-][@]   [-][@][@]   [-][-]",
                        "[@][-][@][@][-][-][-]   [@][@][@][-][-][@][@][@][-][-]",
                        "[@][-]                                          [@][@]",
                        "   [@]    Saved games in file:                     [@]",
                        "[@][@]                                          [@][@]",
                        String.format("   [@]    [1] " + format + "  [2] " + format + "  [-]",
                                (saveNames.size() >= 1 ? saveNames.get(0) : ""),
                                (saveNames.size() >= 2 ? saveNames.get(1) : "")),
                        String.format("[@]       [3] " + format + "  [4] " + format + "  [-][-]",
                                (saveNames.size() >= 3 ? saveNames.get(2) : ""),
                                (saveNames.size() >= 4 ? saveNames.get(3) : "")),
                        String.format("[@][@]    [5] " + format + "  [6] " + format + "  [@][-]",
                                (saveNames.size() >= 5 ? saveNames.get(4) : ""),
                                (saveNames.size() >= 6 ? saveNames.get(5) : "")),
                        String.format("[-][@][@] [7] " + format + "  [8] " + format + "  [@][-]",
                                (saveNames.size() >= 7 ? saveNames.get(6) : ""),
                                (saveNames.size() >= 8 ? saveNames.get(7) : "")),
                        String.format("[-]       [9] " + format + "  [10] " + format + "    [@]",
                                (saveNames.size() >= 9 ? saveNames.get(8) : ""),
                                (saveNames.size() >= 10 ? saveNames.get(9) : "")),
                        String.format("[-][-][-] [11] " + format + " [12] " + format + " [@][@]",
                                (saveNames.size() >= 11 ? saveNames.get(10) : ""),
                                (saveNames.size() >= 12 ? saveNames.get(11) : "")),
                        String.format("   [@]    [13] " + format + " [14] " + format + " [-][@]",
                                (saveNames.size() >= 13 ? saveNames.get(12) : ""),
                                (saveNames.size() >= 14 ? saveNames.get(13) : "")),
                        "[@][@][@]                                       [-][@]",
                        "[-][@][-][@][@][@]      [@][@][-]            [-][-][-]",
                        "[-][-][-]   [@][@][-][-][-][@]   [-][-][-][-][@][@][@]"));
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

    }

    /*
     * Method name: runGame
     * Parameters: boolean hard - The difficulty of the bot; false is easy mode,
     * true is hard mode
     * Descriptions: Runs the game loop for playing a round of the game
     */

    public static void runGame(boolean hard) {

    }

    /*
     * Implement game loop
     * Print main menu
     * Get options
     */
    public static void main(String[] args) {
        // Menu and selection variables
        String choiceMenu, choiceDifficulty;
        boolean running = true, validDifficulty = false;

        // Game variables
        int p1Score = 0;
        int p2Score = 0;
        Object[] p1Tiles; // Replace with custom Tile class
        Object[] p2Tiles;
        char[][] board = new char[BOARD_SIZE][BOARD_SIZE];

        Scanner sc = IO.newScanner();

        // Main loop for
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
                    printMenu(1);
                    System.out.print("Enter the difficulty of the bot: ");
                    choiceDifficulty = sc.nextLine().toLowerCase();

                    do{
                        switch(choiceDifficulty) {
                            // Easy mode
                            case "1":
                            case "[1]":
                            case "easy":
                                runGame(false);
                                validDifficulty = true;
                                break;
                            // Hard mode
                            case "2":
                            case "[2]":
                            case "hard":
                                runGame(true);
                                validDifficulty = true;
                                break;

                            // Return to main menu
                            case "3":
                            case "[3]":
                            case "menu":
                            case "main menu":
                                validDifficulty = true;
                                break;

                            // User made invalid choice
                            default:
                                System.out.println();
                                System.out.println("ERROR: Invalid selection.");
                                System.out.print("<Press [Enter] to continue>");
                                sc.nextLine();
                                break;
                        }
                    } while(!validDifficulty);
                    break;

                // Load game from save
                case "2":
                case "[2]":
                case "load":
                case "load save":
                    break;

                // Instructions
                case "3":
                case "[3]":
                case "how to play":
                    break;

                // Quit
                case "4":
                case "[4]":
                case "quit":
                    System.out.println("Thanks for playing!");
                    running = false;
                    break;

                // User made invalid choice
                default:
                    System.out.println();
                    System.out.println("ERROR: Invalid selection.");
                    System.out.print("<Press [Enter] to continue>");
                    sc.nextLine();
                    break;
            }
        }
    }
}