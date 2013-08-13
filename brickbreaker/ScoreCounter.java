import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.awt.Color;
import java.awt.Font;

/**
 * ScoreCounter is a Greenfoot actor that displays a score count.
 * 
 * @author Keshav Saharia
 * @version 1.0
 */
public class ScoreCounter extends Actor
{
    private int score;
    
    public ScoreCounter()
    {
        // make the image
        GreenfootImage image = new GreenfootImage(200, 50);
        // set the local reference
        setImage(image);
        // paint the image
        paint();
    }
    
    /**
     * Paint the actor.
     */
    public void paint()
    {
        // get the image and clear it
        GreenfootImage image = getImage();
        image.clear();
        // choose a font color and style before drawing text
        image.setColor(Color.WHITE);
        image.setFont(new Font("Arial", Font.PLAIN, 25));
        // draw some text
        image.drawString("Score: " + score, 0, 50);
    }
    
    /**
     * Add some score to the counter.
     */
    public void addScore(int amount)
    {
        score = score + amount;
        paint();
    }
}
