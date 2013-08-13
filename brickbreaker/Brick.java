import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Brick is a brick in a brick breaker game.
 * 
 * @author Keshav Saharia
 * @version 1.0
 */
public class Brick extends Actor
{
    /**
     * Constructor for the brick.
     */
    public Brick()
    {
        GreenfootImage image = getImage();
        image.scale(40, 20);
    }
    
    /**
     * All a brick does is check if it has hit a ball.
     */
    public void act() 
    {
        checkCollisions();
    }    
    
    /**
     * If the brick has hit a ball, tell the ball to bounce vertically, then remove 
     * myself from the world.
     */
    public void checkCollisions()
    {
        Ball ball = (Ball) getOneObjectAtOffset(20, 10, Ball.class);
        if (ball != null)
        {
            // get a reference to BrickWorld
            BrickWorld world = (BrickWorld) getWorld();
            // tell the world to add score. the amount of score added is the result
            // of the getScoreValue() method.
            world.addScore(getScoreValue());
            // tell the ball to bounce
            ball.verticalBounce();
            // remove this brick
            getWorld().removeObject(this);
        }
    }
    
    /**
     * Return the score value of this brick.
     */
    public int getScoreValue()
    {
        return 0;
    }
}
