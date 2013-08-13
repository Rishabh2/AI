
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

/**
 * Class TicTacToe - runs a game of TicTacToe
 * 
 * @author Keshav Saharia
 * @version 1.0
 */
public class TicTacToe extends JApplet
    implements MouseListener
{
    TicTacToeGame game;        // Local reference to the model
    
    public void init()
    {
        JRootPane rootPane = this.getRootPane();    
        rootPane.putClientProperty("defeatSystemEventQueueCheck", Boolean.TRUE);
        // provide any initialisation necessary for your JApplet
        addMouseListener(this);
        setSize(400, 400);
        
        game = new TicTacToeGame();
    }

    /**
     * Unused.
     */
    public void start()
    {
    }

    /**
     * Unused.
     */
    public void stop()
    { 
    }

    public void paint(Graphics g)
    {
        paintGrid(g);
        paintXandO(g);
        paintScore(g);
        paintMessage(g);
    }
    
    private void paintGrid(Graphics g) {
        g.setColor(Color.white);
        g.fillRect(0, 0, 400, 400);
        g.setColor(Color.black);
        g.fillRect(150, 50, 2, 300);
        g.fillRect(250, 50, 2, 300);
        g.fillRect(50, 150, 300, 2);
        g.fillRect(50, 250, 300, 2);
    }
    
    private void paintScore(Graphics g) {
        g.setFont(new Font("Helvetica", 0, 24));
        g.drawString("X - ", 100, 40);
        g.drawString("O - ", 250, 40);
        g.setColor(Color.blue);
        g.drawString("" + game.getXScore(), 140, 40);
        g.drawString("" + game.getOScore(), 290, 40);
    }
    
    private void paintXandO(Graphics g) {
        for (int i = 0 ; i < 3 ; i++) {
            for (int j = 0 ; j < 3 ; j++) {
                if (game.getPieceAt(i, j) == 'X') {
                    paintX(g, i, j);
                }
                if (game.getPieceAt(i, j) == 'O') {
                    paintO(g, i, j);
                }
            }
        }
    }
    
    private void paintMessage(Graphics g) {
        g.setColor(Color.black);
        String message;
        int indent = 0;
        if (game.hasWinner()) {
            message = game.getGameWinner() + " won! Click to restart.";
            indent = 75;
        }
        else if (game.isDraw()) {
            message = "Game is a draw. Click to restart.";
            indent = 25;
        }
        else {
            message = "It is " + game.getCurrentPlayer() + "'s turn.";
            indent = 140;
        }
        g.drawString(message, indent, 375);
    }
    
    private void paintX(Graphics g, int x, int y) {
        g.drawLine(60 + x*100, 60 + y*100, 140 + x*100, 140 + y*100);
        g.drawLine(60 + x*100, 140 + y*100, 140 + x*100, 60 + y*100);
    }
    
    private void paintO(Graphics g, int x, int y) {
        g.drawOval(60 + x * 100, 60 + y * 100, 80, 80);
    }

    public void mouseClicked(MouseEvent e) {
        if (game.hasWinner() || game.isDraw()) {
            game.resetGame();
            repaint();
            return;
        }
        if (e.getX() < 50 || e.getX() > 350 || e.getY() < 50 || e.getY() > 350) {
            return;
        }
        int x = (e.getX() - 50) / 100;
        int y = (e.getY() - 50) / 100;
        if (game.canPlacePieceAt(x, y)) {
            game.placePieceAt(x, y);
            game.switchCurrentPlayer();
        }
        if (game.hasWinner()) {
            game.incrementScoreOfWinner();
        }
        repaint();
    }
    public void mouseEntered(MouseEvent e) { }
    public void mouseExited(MouseEvent e) { }
    public void mousePressed(MouseEvent e) { }
    public void mouseReleased(MouseEvent e) { }
    
    /**
     * Unused. 
     */
    public void destroy()
    {
    }


    /**
     * @return a String representation of information about this JApplet
     */
    public String getAppletInfo()
    {
        return "Tic Tac Toe";
    }
    
    /**
     * @return a String[] representation of parameter information about this JApplet
     */
    public String[][] getParameterInfo()
    {
        return null;
    }
}
