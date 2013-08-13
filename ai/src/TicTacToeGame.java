
/**
 * TicTacToeGame is the underlying model behind a game of tic tac toe.
 * 
 * @author Keshav Saharia
 * @version 1.4
 */

/**
 * Notes (please read!)
 *  - When I say "at (x, y)", I mean "at the location on the board given by the coordinates x and y".
 *    Simply put, this is the value given by board[x][y].
 *  - Remember, for this lesson, you are only modifying this file. I encourage you to look at the
 *    TicTacToe class, but avoid the urge to edit it
 */

public class TicTacToeGame
{
    /** 'x', 'o', ' ' */
    private char[][] board;         // a 2D array of the game board
    private boolean XtoMove;        // true if it is X's turn, false if it is O's turn
    private boolean XtoStart;       // true if X is starting the current game, false otherwise
    private int Xscore, Oscore;     // stores the score for X and O
    
    /**
     * This is the constructor, and is run whenever we create a TicTacToeGame object with
     *    game = new TicTacToeGame();
     * In this function, you want to add all the steps needed to initialize the game. This includes
     * - giving pieces of memory to variables that need it
     * - giving initial values to your variables
     * - anything you need to do to start up
     */
    
    public TicTacToeGame() 
    {
        board = new char[3][3];      // creates a new 3 x 3 grid in memory.
        resetGame();
    }

    /**
     * This function is called whenever the game needs to reset.
     */
    public void resetGame()
    {
        for (int x = 0 ; x < 3 ; x++)
            for (int y = 0 ; y < 3 ; y++)
                board[x][y] = ' ';
    }
    
    /**
     * Returns true if the space at (x, y) on the board is empty.
     */
    public boolean canPlacePieceAt(int x, int y)
    {
		return board[x][y] == ' ';
    }
    
    /**
     * If it is X's turn, this function should place an X on the board at (x, y). If it is O's turn,
     * it should place an O on the board at (x, y).
     */
    public void placePieceAt(int x, int y) 
    {
		board[x][y] = (XtoMove) ? 'X' : 'O';
    }
    
    /**
     * Returns the character value for the piece at (x, y). 
     */
    public char getPieceAt(int x, int y)
    {
        return board[x][y];
    }
    
    /**
     * Returns 'X' if it is currently X's turn, and 'O' if it is currently O's turn.
     */
    public char getCurrentPlayer()
    {
		return (XtoMove) ? 'X' : 'O';
	}
    
    /**
     * Switches the current player from X to O or from O to X.
     */
    
    public void switchCurrentPlayer() 
    {
        XtoMove = !XtoMove;
    }
    
    /**
     * Returns true if the current game is a draw, and false otherwise.
     */
    public boolean isDraw() 
    {
        return spacesFull() == 9 && getGameWinner() == ' ';
    }
    
    public int spacesFull()
    {
        int amount = 0;
        for (int i = 0 ; i < 3 ; i++) {
            for (int j = 0 ; j < 3 ; j++) {
                if (board[i][j] != ' ')
                    amount++;
            }
        }
        return amount;
    }
    
    /**
     * Returns true if there is a winner for the current game, that is, either X or O has 3 in a row.
     */
    public boolean hasWinner() 
    {
        return getGameWinner() != ' ';
    }
    
    /**
     * Checks if there is a winner on this board. If there is, it returns the value on the board
     * that won the game. Otherwise, it returns the space character ' '.
     */
    public char getGameWinner()
    {
        for (int row = 0 ; row < 3 ; row++) {
            if (board[row][0] != ' ' && 
                board[row][0] == board[row][1] && 
                board[row][1] == board[row][2])
            {
                return board[row][0];
            }
        }
        for (int col = 0 ; col < 3 ; col++) {
            if (board[0][col] != ' ' && 
                board[0][col] == board[1][col] && 
                board[1][col] == board[2][col])
            {
                return board[0][col];
            }
        }
        if (board[0][0] != ' ' && 
            board[0][0] == board[1][1] && 
            board[1][1] == board[2][2]) {
                return board[0][0];
        }
        if (board[2][0] != ' ' && 
            board[2][0] == board[1][1] && 
            board[1][1] == board[0][2]) {
                return board[2][0];
        }
        return ' ';
    }
    
    /**
     * This function is called when the game is won. It should add 1 to the score of the game's 
     * winner, if there is one.
     */
    
    public void incrementScoreOfWinner() 
    {
        Xscore += (getGameWinner() == 'X') ? 1 : 0;
		Oscore += (getGameWinner() == 'O') ? 1 : 0;
    }
    
    /*************************************************************************************
     * Don't worry about any of the functions below - they are freebies.                 *
     *************************************************************************************/
    
    /**
     * Clears all the X's and O's off the board.
     */
    public void clearBoard() {
        for (int i = 0 ; i < 3 ; i++) {
            for (int j = 0 ; j < 3 ; j++) {
                board[i][j] = ' ';
            }
        }
    }

    /**
     * Returns the score for X and O
     */
    public int getXScore() {
        return Xscore;
    }
    
    public int getOScore() {
        return Oscore;
    }
}
