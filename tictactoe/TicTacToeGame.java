
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
    /** 'X', 'O', ' ' */
    private char[][] board;         // a 2D array of the game board
    private boolean XtoMove;        // true if it is X's turn, false if it is O's turn
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
        resetGame();
    }

    /**
     * This function is called whenever the game needs to reset.
     */
    public void resetGame()
    {
        
    }
    
    /**
     * Returns true if the space at (x, y) on the board is empty.
     */
    public boolean canPlacePieceAt(int x, int y)
    {
        return false;
    }
    
    /**
     * If it is X's turn, this function should place an X on the board at (x, y). If it is O's turn,
     * it should place an O on the board at (x, y).
     */
    public void placePieceAt(int x, int y) 
    {
        
    }
    
    /**
     * Returns the character value for the piece at (x, y). 
     */
    public char getPieceAt(int x, int y)
    {
        
    }
    
    /**
     * Returns 'X' if it is currently X's turn, and 'O' if it is currently O's turn.
     */
    public char getCurrentPlayer()
    {
        
	}
    
    /**
     * Switches the current player from X to O or from O to X.
     */
    
    public void switchCurrentPlayer() 
    {
        
    }
    
    /**
     * Returns true if the current game is a draw, and false otherwise.
     */
    public boolean isDraw() 
    {
        return false;
    }
    
    /**
     * Returns true if there is a winner for the current game, that is, either X or O has 3 in a row.
     */
    public boolean hasWinner() 
    {
        return false;
    }
    
    /**
     * Checks if there is a winner on this board. If there is, it returns the value on the board
     * that won the game. Otherwise, it returns the space character ' '.
     */
    public char getGameWinner()
    {
        return ' ';
    }
    
    /**
     * This function is called when the game is won. It should add 1 to the score of the game's 
     * winner, if there is one.
     */
    
    public void incrementScoreOfWinner() 
    {
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
