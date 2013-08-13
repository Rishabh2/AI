import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Asteroid is a moving target in the world that you have to shoot with projectiles
 * from your rocket, and avoid getting hit by.
 * 
 * @author (your name) 
 * @version 1.0
 */
public class Asteroid extends Actor
{
    /**
     * An Asteroid can move around in as simple or as complex a way as you want.
     */
    public void act() 
    {
        
    }
    
    /**
     * This method takes the image of the Asteroid object, and shrinks it by scaling
     * it down by a certain amount. If the Asteroid is too small to shrink any further, 
     * it should call the destroy() method.
     */
    public void shrink()
    {
        
    }
    
    /**
     * Destroys this asteroid by removing it from the world.
     */
    public void destroy()
    {
        World space = getWorld();
        space.removeObject(this);
    }
}