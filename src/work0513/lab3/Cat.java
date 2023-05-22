import java.awt.*;

/**
 * Title      : Cat.java
 * Description: This class contains the definition of a cat.
 * Copyright  : Copyright (c) 2006-2023
 * @author      Laurissa Tokarchuk
 * @version     1.0
 * @author      Paula Fonseca
 * @version     1.4
 */
public class Cat {
    // Declaration of instance variables.
    private String  name, furType;
    private boolean tail;
    private Color   colour;

    public boolean isTail() {
        return tail;
    }

    public void setTail(boolean tail) {
        this.tail = tail;
    }

    public Cat(String name, String furType, boolean tail, Color colour, int speed) {
        this.name = name;
        this.furType = furType;
        this.tail = tail;
        this.colour = colour;
        this.speed = speed;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFurType() {
        return furType;
    }

    public void setFurType(String furType) {
        this.furType = furType;
    }



    public Color getColour() {
        return colour;
    }

    public void setColour(Color colour) {
        this.colour = colour;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    private int     speed;

    /** This is the sleep method for the cat. It dictates the number of 
     *  minutes the cat sleeps.
     * @param duration  The number of minutes to sleep.
     */
    public void sleep(int duration) {
        System.out.println("I am sleeping for " + duration + " minutes.");
    }

    /** This method allows the cat to run. The distance (in a straight line)
     *  the cat runs is dependent on how long the cat runs and whether or not
     *  it is running in a zigzag.
     *  @param duration  The number of minutes to run.
     *  @param zigzag    Whether to run in a zigzag pattern.
     *  @return int	 Number of metres ran.
     */
    public int run(int duration, boolean zigzag) {
        System.out.println("I am running "
                + (zigzag? "in a zigzag" : "straight")
                + " for "
                + duration
                + " minutes.");
        int distanceRun = duration * speed; // assuming speed is metres per minute
        if (zigzag) {
	    /* When in zigzag, distance is 1/3 of what it would have been if 
	       the cat was going straight. */
            return distanceRun/3;
        }
        else return distanceRun;
    }
}            