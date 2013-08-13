import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.awt.Color;

/**
 * HealthBar is an object that can be used to display the health of an Actor.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class HealthBar extends Actor
{
    // private variable, describes the percentage of health that is remaining
    private int health = 100;
    // a reference to the Actor object that we are displaying health for
    private Actor myActor;
    
    /**
     * The constructor for our HealthBar object. This method is called whenever we
     * create a HealthBar object.
     */
    public HealthBar()
    {
        // make a new image in memory, that is 60 x 30 pixels
        GreenfootImage image = new GreenfootImage(60, 30);
        // set the image of our actor to the reference called image
        setImage(image);
        // repaint the image
        paint();
    }
    
    /**
     * Repaints this health bar.
     */
    private void paint()
    {
        // get a reference to the image of this actor
        GreenfootImage image = getImage();
        image.setColor(Color.GREEN);
        image.fillRect(0, 0, 60, 30);
        image.setColor(Color.RED);
        image.fillRect(5, 5, health / 2, 20);
    }
    
    /**
     * Sets the actor that this health bar records health for. If the health is less than
     * or equal to 0, it will remove this actor from the world.
     */
    public void setActor(Actor actor)
    {
        myActor = actor;
    }
    
    /**
     * Subtracts the given amount of health from the healthbar, assuming that maximum health
     * is equal to 100.
     */
    public void subtractHealth(int amount)
    {
        health = health - amount;
        if (health <= 0) {
            getWorld().removeObject(myActor);
        }
        paint();
    }
    
    /**
     * Adds to the amount of health from the healthbar
     */
    public void addHealth(int amount)
    {
        health = health + amount;
        if (health > 100) {
            health = 100;
        }
    }
    
    public void reset()
    {
        health = 100;
    }
}
