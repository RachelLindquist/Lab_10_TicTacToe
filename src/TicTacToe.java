import java.util.Scanner;

public class TicTacToe {
    private static final int ROW = 3;
    private static final int COL = 3;
    private static String board [][] = new String[ROW][COL];

    public static void main(String[] args) {
       // 6.	Create a pseudo code outline for the program using java comments and then code the game.

       // Your program should:
	//Clear the board and set the player to X (since X always moves first)
	//get the coordinates for the move which should be 1 – 3 for the row and col
	//convert the player move coordinates to the array indices which are 0 – 2 by subtracting 1
	//loop until the converted player coordinates are a valid move
	//if appropriate check for a win or a tie (i.e. if it is possible for a win or a tie at this point in the game, check for it.)
	//If there is a win or tie announce it and then prompt the players to play again.
	//Toggle the player (i.e. X becomes O, O becomes X)
        Scanner in = new Scanner(System.in);
        boolean continueGame = true;
        String currentPlayer = "O";

        do{
            //Clear board and set starting values.
            clearBoard();
            currentPlayer = "O";
            do {
                if (currentPlayer.equals("X")){
                    currentPlayer = "O";
                } else {
                    currentPlayer = "X";
                }
                System.out.println("The current player is " + currentPlayer);
                int rowMove = -1;
                int colMove = -1;

                boolean valid = false;

                //prompt move
                do {
                    rowMove = SafeInput.getRangedInt(in, "Please enter the row for your placement", 1, 3);
                    colMove = SafeInput.getRangedInt(in, "Please enter the column for your placement", 1, 3);

                    //place move
                    rowMove--;
                    colMove--;

                    valid = isValidMove(rowMove, colMove);
                } while (!valid);
                board[rowMove][colMove] = currentPlayer;
                // display
                display();
                //check if game is over
            }while(!(isWin(currentPlayer) || isTie()));
            if (isWin(currentPlayer)){
                System.out.println("Congrats, player " + currentPlayer + " has won.");
            } else {
                System.out.println("Unfortunately it was a tie.");
            }
            continueGame =SafeInput.getYNConfirm(in, "Would you like to play another game");
            //repeat
        } while (continueGame);

    }

    /**
     * Clears the tic tac toe board
     */
    private static void clearBoard(){
        for (int i = 0; i < COL; i++){
            for (int j = 0; j < ROW; j++){
                board[j][i] = " ";
            }
        }
    }

    /**
     * Displays the tic tac toe board
     */
	private static void display(){
        for (int i = 0; i < COL; i++){
            for (int j = 0; j < ROW; j++){
                System.out.print(board[i][j] + " | ");
            }
            System.out.print("\n");
            //COL*ROW*1.25 just evens it out so the board looks nicer
            for (int k = 0; k < COL*ROW*1.25; k++){
                System.out.print("-");
            }
            System.out.print("\n");
        }
    }

    /**
     *
     * @param row row of attempted move
     * @param col column of attempted move
     * @return a boolean response for if a move is valid
     */
	private static boolean isValidMove(int row, int col){
        if (board[row][col].equals(" ")){
            return true;
        } else {
            return false;
        }
    }
    /**
     *
     * @param player player being checked
     * @return a boolean response for if the player has won
     */
    private static boolean isWin(String player){
        if (isColWin(player) || isRowWin(player) || isDiagnalWin(player)){
            return true;
        } else {
            return false;
        }
    }
    /**
     *
     * @param player player being checked
     * @return a boolean response for if the player has won in a column
     */
	private static boolean isColWin(String player){
        boolean win = true;
        for (int i = 0; i < COL; i++){
            win = true;
            for (int j = 0; j < ROW; j++){
                if(!board[j][i].equals(player)){
                    win = false;
                }
            }
            if (win){
                return true;
            }
        }
        return false;
    }
    /**
     *
     * @param player player being checked
     * @return a boolean response for if the player has won in a row
     */
    private static boolean isRowWin(String player){
        boolean win = true;
        for (int i = 0; i < ROW; i++){
            win = true;
            for (int j = 0; j < COL; j++){
                if(!board[i][j].equals(player)){
                    win = false;
                }
            }
            if (win){
                return true;
            }
        }
        return false;
    } // checks for a row win for the specified player
    /**
     *
     * @param player player being checked
     * @return a boolean response for if the player has won diagonally
     */
    private static boolean isDiagnalWin(String player){
        //Left to right down
        boolean diagWin = true;
        for (int i = 0; i < COL; i++){
            if(!board[i][i].equals(player)){
                diagWin = false;
            }
        }
        if (diagWin){
            return true;
        }

        for (int i = 0; i < COL; i++){
            if(!board[i][COL-1-i].equals(player)){
                return false;
            }
        }
        return true;

    }
    /**
     *
     * @return a boolean response for if there is a tie
     */
    private static boolean isTie(){
        boolean full = true;

        for (int i = 0; i < ROW; i++){
            for (int j = 0; j < COL; j++){
                if (board[i][j].equals(" ")){
                    full = false;
                }
            }

        }
        if (!full) {
            boolean hasX = false;
            boolean hasO = false;
            // Checking Rows
            for (int i = 0; i < ROW; i++) {
                for (int j = 0; j < COL; j++) {
                    if (board[i][j].equals("X")) {
                        hasX = true;
                    }
                    if (board[i][j].equals("O")) {
                        hasO = true;
                    }
                }
                if (!(hasX && hasO)) {
                    return false;
                }
                hasX = false;
                hasO = false;
            }

            //checking columns
            for (int i = 0; i < COL; i++) {
                for (int j = 0; j < ROW; j++) {
                    if (board[j][i].equals("X")) {
                        hasX = true;
                    }
                    if (board[j][i].equals("O")) {
                        hasO = true;
                    }
                }
                if (!(hasX && hasO)) {
                    return false;
                }
                hasX = false;
                hasO = false;
            }

            //checking diagonal

            // \
            for (int i = 0; i < COL; i++){
                if (board[i][i].equals("X")) {
                    hasX = true;
                }
                if (board[i][i].equals("O")) {
                    hasO = true;
                }
            }
            if (!(hasX && hasO)) {
                return false;
            }
             // /

            // (0, 2) , (1, 1), (2, 0)

            for (int i = 0; i < COL; i++){
                if (board[i][COL-1-i].equals("X")) {
                    hasX = true;
                }
                if (board[i][COL-1-i].equals("O")) {
                    hasO = true;
                }
            }
            if (!(hasX && hasO)) {
                return false;
            }
        }
        return true;
    }


}
