
// Import libraries
import java.io.*;
import java.util.*;

/*
 * Program name: Saves
 * Programmer: Raymond Zhang
 * Last modified: 02/06/2024
 * Description: Class that contains methods that involve reading and writing save files.
 */

public class Saves {

    // Separates information in save files
    private static final String SAVE_DELIMITER = "~";

    /*
     * Method name: writeSave()
     * Parameters: String saveName - The name of the save file.
     * char[][] board - The current status of the game board.
     * int p1Score - The score of player 1.
     * Map<String,Tile> - The tiles used by player 1.
     * int p2Score - The score of player 2.
     * Map<String,Tile> - The tiles used by player 2.
     * boolean isHard - The difficulty of the game.
     * Description: Writes the given game information to a new save file. If the file already exists, it will be overwritten.
     */
    public static void writeSave(String saveName, char[][] board, int p1Score, Map<String, Tile> p1Tiles, int p2Score,
            Map<String, Tile> p2Tiles, boolean isHard) throws IOException {
        // Declare variables and constants
        final String FILE_NAME = "./saves" + saveName + ".txt";

        // Write file
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_NAME, false))) {
            // Write board
            for (int i = 0; i < BlokusDuo.BOARD_SIZE; i++) {
                for (int j = 0; j < BlokusDuo.BOARD_SIZE; j++) {
                    bw.write(board[i][j]);
                }
                bw.newLine();
            }
            bw.write(SAVE_DELIMITER);

            // Write score of player 1
            bw.write("Player 1 score");
            bw.newLine();
            bw.write(String.valueOf(p1Score));
            bw.write(SAVE_DELIMITER);

            // Write used tiles of player 1
            bw.write("Player 1 used");
            // Loop through all tiles
            for (Map.Entry<String, Tile> entry : p1Tiles.entrySet()) {
                // If tile is used, write its name to file
                if (entry.getValue().getUsed()) {
                    bw.write(entry.getKey());
                    bw.newLine();
                }
            }
            bw.write(SAVE_DELIMITER);

            // Write score of player 2
            bw.write("Player 2 score");
            bw.newLine();
            bw.write(String.valueOf(p2Score));
            bw.write(SAVE_DELIMITER);

            // Write used tiles of player 2
            bw.write("Player 2 used");
            // Loop through all tiles
            for (Map.Entry<String, Tile> entry : p2Tiles.entrySet()) {
                // If tile is used, write its name to file
                if (entry.getValue().getUsed()) {
                    bw.write(entry.getKey());
                    bw.newLine();
                }
            }
            bw.write(SAVE_DELIMITER);

            // Write difficulty
            bw.write((isHard ? "Hard" : "Easy"));
            bw.write(SAVE_DELIMITER);
        }
    }

    public static char[][] getSaveBoard(String saveName) throws IOException {
        // Declare variables and constants
        final String FILE_NAME = "./saves" + saveName + ".txt";
        char[][] board = new char[BlokusDuo.BOARD_SIZE][BlokusDuo.BOARD_SIZE];

        // Read file
        try (BufferedReader br = new BufferedReader(new FileReader(FILE_NAME))) {
            // Get board characters
            for (int i = 0; i < BlokusDuo.BOARD_SIZE; i++) {
                for (int j = 0; j < BlokusDuo.BOARD_SIZE; j++) {
                    board[i][j] = (char) br.read();
                }
            }
        }

        return board;
    }

    public static int getPlayerScore(String saveName, int playerNum) throws IOException {
        // Declare variables and constants
        final String FILE_NAME = "./saves" + saveName + ".txt";
        boolean reading = true;
        int score = 0;
        String line;

        // Read file
        try (BufferedReader br = new BufferedReader(new FileReader(FILE_NAME))) {
            // Read until score has been obtained
            while (reading) {
                line = br.readLine();

                // Assign score value
                if (line.equals(String.format("Player %d score", playerNum))) {
                    score = Integer.parseInt(br.readLine());
                    reading = false;
                }
            }
        }

        return score;
    }

    public static Map<String, Tile> getPlayerTiles(String saveName, int playerNum) throws IOException {
        // Declare variables and constants
        final String FILE_NAME = "./saves" + saveName + ".txt";
        Map<String, Tile> tileSet = Tile.newTileSet();
        String line;
        boolean reading = true;

        // Read file
        try (BufferedReader br = new BufferedReader(new FileReader(FILE_NAME))) {
            // Read until tiles section reached
            while (reading) {
                line = br.readLine();

                if (line.equals(String.format("Player %d used", playerNum))) {
                    // Mark tiles as used
                    while (!line.equals(SAVE_DELIMITER)) {
                        tileSet.get(br.readLine()).setUsed(true);
                    }
                    reading = false;
                }
            }
        }

        return tileSet;
    }

    public static boolean getDifficulty(String saveName) throws IOException {
        // Declare variables and constants
        final String FILE_NAME = "./saves" + saveName + ".txt";
        String line;
        boolean reading = true, isHard = false;

        // Read file
        try (BufferedReader br = new BufferedReader(new FileReader(FILE_NAME))) {
            // Read until difficulty reached
            while (reading) {
                line = br.readLine();

                // Get difficulty
                if (line.equals("Difficulty")) {
                    isHard = br.readLine().equals("Hard");
                    reading = false;
                }
            }
        }

        return isHard;
    }

    /*
     * Method name: getSaveNames()
     * Return type: ArrayList<String> - A list of all saved games in file
     * Description: Provides a list of names for saved games
     */
    public static ArrayList<String> getSaveNames() {
        // Declare variables
        ArrayList<String> saveNames = new ArrayList<>();
        File saveFolder = new File("./saves"); // saves folder directory
        File[] saveFiles = saveFolder.listFiles(); // files in saves folder directory

        // Get all save names names
        for (File f : saveFiles) {
            saveNames.add(f.getName().substring(0, f.getName().lastIndexOf(".txt")));
        }

        return saveNames;
    }
}
