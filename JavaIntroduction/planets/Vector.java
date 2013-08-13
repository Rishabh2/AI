import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.awt.Color;

/**
 * Vector represents a two dimensional vector in space.
 * 
 * @author Keshav Saharia
 * @version 1.1
 */
public class Vector
{
    private double x;
    private double y;
    
    /**
     * Constructor with no parameters, sets the vector to default < 0, 0 >
     */
    public Vector()
    {
        set(0, 0);
    }
   
    /**
     * Constructor with x, y parameters, sets the vector to < x, y >
     */
    public Vector(double x, double y)
    {
        set(x, y);
    }
    
    /**
     * Sets the x and y components of this vector.
     */
    public void set(double newX, double newY)
    {
        x = newX;
        y = newY;
    }
    
    /**
     * Sets the x and y components of this vector according to the given magnitude and direction.
     */
    public void setVector(double magnitude, double angle)
    {
        x = magnitude * Math.cos(angle);
        y = magnitude * Math.sin(angle);
    }
    
    /**
     * Adds another vector to this one.
     */
    public void add(Vector other)
    {
        x = x + other.x;
        y = y + other.y;
    }
    
    /**
     * Returns the magnitude of this vector.
     */
    public double getMagnitude()
    {
        return Math.sqrt(x * x + y * y);
    }
    
    /**
     * Returns a new vector which has been scaled by the given factor.
     */
    
    public Vector scale(double factor)
    {
        return new Vector(x / factor, y / factor);
    }
    
    /* ********************************************************************************
     * Don't edit anything below this! This is free code. I highly recommend that
     * you modify it and play around with it, but editing it will cause weird effects.
     * ********************************************************************************
     */
    
    /**
     * Returns the angle, in radians, of this vector in the Cartesian coordinate plane.
     */
    public double getAngle() {
        if (x < 0) {
            return Math.PI + Math.atan( y / x );
        }
        else if (x > 0) {
            return Math.atan( y / x );
        } else {
            if (y > 0) {
                return Math.PI / 2;
            }
            else if (y < 0) {
                return 3 * Math.PI / 2;
            }
            else {
                return 0;
            }
        }
    }
    
    /**
     * Returns the x component of this vector.
     */
    public double getX() {
        return x;
    }
    
    /**
     * Returns the y component of this vector.
     */
    public double getY() {
        return y;
    }
}
