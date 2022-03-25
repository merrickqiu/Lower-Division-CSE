/**
 * TODO: Add your file header
 * Name:
 * ID:
 * Email:
 * Sources used: Put "None" if you did not have any external help
 * Some example of sources used would be Tutors, Zybooks, and Lecture Slides
 * 
 * 2-4 sentence file description here
 */

import java.util.Scanner;

/**
 * TODO: Add your class header (purpose and capabilities of the class)
 */
public class RPS extends RPSAbstract {
    
    /**
     * TODO: Add method header
     */
    public RPS(String[] moves) {
        this.possibleMoves = moves;
        this.playerMoves = new String[MAX_GAMES];
        this.cpuMoves = new String[MAX_GAMES];
    }

    /**
     * Takes the user move, the CPU move, and determines who wins.
     * @param playerMove - move of the player
     * @param cpuMove - move of the CPU
     * @return -1 for invalid move, 0 for tie, 1 for player win, 2 for cpu win
     */
    public int determineWinner(String playerMove, String cpuMove)
    {
        // TODO
        int playerIndex = -1;
        for(int i = 0; i < possibleMoves.length; i++) {
            if (playerMove.equals(possibleMoves[i])) {
                playerIndex = i;
            }
        }
        if (playerIndex == -1 || !isValidMove(cpuMove)) {
            return INVALID_INPUT_OUTCOME;
        }
        else if (possibleMoves[Math.floorMod(playerIndex+1, possibleMoves.length)].equals(cpuMove)) {
            return PLAYER_WIN_OUTCOME;
        }
        else if (possibleMoves[Math.floorMod(playerIndex-1, possibleMoves.length)].equals(cpuMove)) {
            return CPU_WIN_OUTCOME;
        }
        else {
            return TIE_OUTCOME;
        }
    }

    /**
     * Main method that reads user input, generates cpu move, and plays game
     * 
     * @param args - arguments passed in from command line in String form
     */
    public static void main(String[] args) {
        // If command line args are provided use those as the possible moves
        String[] moves = new String[args.length];
        if (args.length >= MIN_POSSIBLE_MOVES) {
            for (int i = 0; i < args.length; i++) {
                moves[i] = args[i];
            }
        } else {
            moves = RPS.DEFAULT_MOVES;
        }
        // Create new game and scanner
        RPS game = new RPS(moves);
        Scanner in = new Scanner(System.in);

        // While user does not input "q", play game       
        // TODO: Insert the code to play the game. 
        // See the writeup for an example of the game play.
        // Hint: call the methods we/you have already written 
        // to do most of the work!
        while(true) {
            System.out.println(PROMPT_MOVE);
            String playerMove = in.nextLine();
            if (playerMove.equals("q")){
                break;
            }
            game.play(playerMove, game.genCPUMove());
        }
        game.end();
        
        in.close();
    }
}
