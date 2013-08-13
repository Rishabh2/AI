import greenfoot.*;  // (World, Actor, GreenfootImage, and Greenfoot)

import java.util.List;
import java.util.ArrayList;

/**
 * This class implements a hedgehog. Hedgehogs - in this scenario - run around in the 
 * garden and eat apples - if they find any.
 * 
 * @author Michael Kölling
 * @version 1.0
 */
public class Hedgehog extends Actor
{
    private int applesEaten;

    /**
     * Create the hedgehog, facing right (0 degrees) initially.
     */
    public Hedgehog()
    {
        applesEaten = 0;
    }

    /**
     * Do whatever the hedgehog likes to to just now.
     */
    public void act()
    {
        if(foundApple()) {
            eatApple();
        }
        else if(canMove()) {
            if (Greenfoot.getRandomNumber(100) < 20) {  // 20% chance
                turnRandom();
                if (!canMove()) {  // if we turned to face the edge, turn around
                    turn(180);
                }
            }
            else {
                move();
            }
        }
        else {
            turnLeft();
        }
    }

    /**
     * Check whether there is an apple in the same place we are. Return true if
     * there is, or false if there isn't.
     */
    public boolean foundApple()
    {
        Actor apple = getOneObjectAtOffset(0, 0, Apple.class);
        return apple != null;
    }

    /**
     * Eat an apple.
     */
    public void eatApple()
    {
        Actor apple = getOneObjectAtOffset(0, 0, Apple.class);
        if(apple != null) {
            // eat the apple...
            getWorld().removeObject(apple);
            applesEaten = applesEaten + 1; 
        }
    }

    /**
     * Move one cell forward in the current direction. Every now and then, turn randomly
     * left or right instead.
     */
    public void move()
    {
        if (!canMove()) {
            return;
        }

        //move(1);

        // === this block of code temporarily replaces the line  ===
        // === above, to work around a Greenfoot bug.            ===
        if (getRotation() == 0) {
            setLocation (getX()+1, getY());
        }
        if (getRotation() == 90) {
            setLocation (getX(), getY()+1);
        }
        if (getRotation() == 180) {
            setLocation (getX()-1, getY());
        }
        if (getRotation() == 270) {
            setLocation (getX(), getY()-1);
        }
        // ==========================================================
    }

    /**
     * Test if we can move forward. Return true if we can, false otherwise.
     */
    public boolean canMove()
    {
        int direction = getRotation();

        if ((direction == 0) && atRightEdge()) {
            return false;
        }

        if ((direction == 90) && atBottomEdge()) {
            return false;
        }

        if ((direction == 180) && atLeftEdge()) {
            return false;
        }

        if ((direction == 270) && atTopEdge()) {
            return false;
        }

        // otherwise we're fine
        return true;
    }

    /**
     * Turn 90 degrees left.
     */
    public void turnLeft()
    {
        turn(-90);
    }

    /**
     * Turn either right or left, randomly chosen.
     */
    public void turnRandom()
    {
        if (Greenfoot.getRandomNumber(2) == 0) {
            turn(90);
        }
        else {
            turn(-90);
        }
    }

    /**
     * Tell how many apples we have eaten.
     */
    public int getApplesEaten()
    {
        return applesEaten;
    }

    /**
     * Return true if we are at the right edge of the world.
     */
    private boolean atRightEdge()
    {
        return getX() == getWorld().getWidth() - 1;
    }

    /**
     * Return true if we are at the left edge of the world.
     */
    private boolean atLeftEdge()
    {
        return getX() == 0;
    }

    /**
     * Return true if we are at the top edge of the world.
     */
    private boolean atTopEdge()
    {
        return getY() == 0;
    }

    /**
     * Return true if we are at the bottom edge of the world.
     */
    private boolean atBottomEdge()
    {
        return getY() == getWorld().getHeight() - 1;
    }
}