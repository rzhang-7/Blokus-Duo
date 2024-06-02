// Import libraries
import java.util.Scanner;

/*
 * Program name: BlokusDuo
 * Programmer: Raymond Zhang
 * Last modified: 02/06/2024
 * Description: Driver code for the Blokus Duo game.
 */

public class BlokusDuo {

    /*
     * Method name: printMenu
     * Description: Displays the game's main menu.
     */

    public static void printMenu() {
        System.out.println();
        System.out.printf(String.join("\n", "[@][@]   [@][@][-][-][@][-][@]   [-][@][@]   [-][-]",
                "[@][-][@][@][-][-][-]   [@][@][@][-][-][@][@][@][-][-]",
                "[@][-] __________ __       __                   [@][@]",
                "   [@] \\______   \\ | ____ |  | ____ __  ______     [@]",
                "[@][@]  |   |  _/  |/ __ \\|  |/ /  |  \\/  ___|  [@][@]",
                "   [@]  |   |   \\  |  |_| |    \\|  |  /\\___ \\   [-]",
                "[@]    /________/__|\\____/| /\\__\\____/|_____/   [-][-]",
                "[@][@]                    |/ | | \\ | | | / / \\  [@][-]",
                "[-][@][@]                    |_|_/ \\_\\_/ \\_\\_/  [@][-]",
                "[-]                                                [@]",
                "[-][-][-]   [1] New game      [3] How to play   [@][@]",
                "   [@]      [2] Load save     [4] Quit          [-][@]",
                "[@][@][@]                                       [-][@]",
                "[-][@][-][@][@][@]      [@][@][-]            [-][-][-]",
                "[-][-][-]   [@][@][-][-][-][@]   [-][-][-][-][@][@][@]"));
        System.out.println();
        System.out.println();
    }

    public static void printBoard(char[][] board) {

    }

    /*
     * Implement game loop
     * Print main menu
     * Get options
     */
    public static void main(String[] args) {
        // Declare variables
        String choice;
        boolean running = true;

        Scanner sc = IO.newScanner();

        // Main loop for
        while (running) {
            // Display menu
            printMenu();

            // Get choice from user
            System.out.print("Enter your selection: ");
            choice = sc.nextLine().toLowerCase(); // convert to lower for case insensitivity

            switch (choice) {
                // Start new game
                case "1":
                case "new":
                case "new game":

                    break;

                // Load game from save
                case "2":
                case "load":
                case "load save":
                    break;

                // Instructions
                case "3":
                case "how to play":
                    break;

                // Quit
                case "4":
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