import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Paddle is a paddle in a brick breaker game.
 * 
 * @author Keshav Saharia
 * @version 1.0
 */
public class Paddle extends Actor
{
    /**
     * Constructor for the Paddle object.
     */
    public Paddle()
    {
        GreenfootImage image = getImage();
        image.scale(100, 10);
    }

    /**
     * A paddle just checks the keys to see if it should move right or left.
     */
    public void act() 
    {
        checkKeys();
    }
    
    /**
     * Creates a new ball above this paddle.
     */
    public void spawnBall()
    {
        Ball newBall = new Ball();
        newBall.setRotation(225 + Greenfoot.getRandomNumber(90));
        getWorld().addObject(newBall, getX(), getY() - 20);
    }
    
    /**
     * Check the keys, and move accordingly.
     */
    public void checkKeys()
    {
        if (Greenfoot.isKeyDown("left"))
        {
            setLocation(getX() - 5, getY());
        }
        if (Greenfoot.isKeyDown("right"))
        {
            setLocation(getX() + 5, getY());
        }
    }
}
