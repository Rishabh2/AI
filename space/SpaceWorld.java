import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class SpaceWorld here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class SpaceWorld extends World
{

    /**
     * Constructor for objects of class SpaceWorld.
     * 
     */
    public SpaceWorld()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(600, 600, 1);
        prepare();
    }
    
    public void prepare()
    {
        Rocket rocket = new Rocket();
        addObject(rocket, 300, 300);
        HealthBar healthbar = new HealthBar();
        addObject(healthbar, 40, 40);
        
        // very important!
        rocket.setHealthBar(healthbar);
        healthbar.setActor(rocket);
    }
}
