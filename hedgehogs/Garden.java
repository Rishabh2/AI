import greenfoot.*;  // imports Actor, World, Greenfoot, GreenfootImage

import java.util.Random;

/**
 * A garden where hedgehogs live.
 * 
 * @author Michael Kölling
 * @version 1.0
 */
public class Garden extends World
{
    /**
     * Create a new world with 8x8 cells and
     * with a cell size of 60x60 pixels
     */
    public Garden() 
    {
        super(8, 8, 60);
        setPaintOrder(Hedgehog.class, Apple.class);
    }
    
    /**
     * Place a number of apples into the world at random places.
     * The number of apples can be specified.
     */
    public void randomApples(int howMany)
    {
        for(int i=0; i<howMany; i++) {
            Apple apple = new Apple();
            int x = Greenfoot.getRandomNumber(getWidth());
            int y = Greenfoot.getRandomNumber(getHeight());
            addObject(apple, x, y);
        }
    }
}