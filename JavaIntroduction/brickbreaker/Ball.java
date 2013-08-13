import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Ball is a ball in brick breaker.
 * 
 * @author Keshav Saharia
 * @version 1.0
 */
public class Ball extends Actor
{
    /**
     * A ball moves and bounces.
     */
    public void act() 
    {
        move(5);
        bounceFromEdge();
        bounceFromPaddle();
    }

    /**
     * Bounces off the edge if it senses it.
     */
    public void bounceFromEdge()
    {
        if (getX() < 10 || getX() > getWorld().getWidth() - 10)
        {
            horizontalBounce();
        }

        if (getY() < 10)
        {
            verticalBounce();
        }
    }

    /**
     * Bounces off the paddle if it senses it.
     */
    public void bounceFromPaddle()
    {
        Paddle paddle = (Paddle) getOneObjectAtOffset(0, 0, Paddle.class);
        if (paddle != null)
        {   
            // Then a paddle was detected
            verticalBounce();
            int distanceFromCenter = getX() - paddle.getX();
            turn(distanceFromCenter / 2);
            move(10);
        }
    }

    /**
     * Performs a vertical bounce by changing the direction of the ball accordingly.
     */
    public void verticalBounce()
    {
        setRotation(0 - getRotation());
    }

    /**
     * Performs a horizontal bounce by changing the direction of the ball accordingly.
     */
    public void horizontalBounce()
    {
        setRotation(180 - getRotation());    
    }
}
