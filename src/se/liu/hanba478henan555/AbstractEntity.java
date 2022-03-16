package se.liu.hanba478henan555;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * docstring
 */
public abstract class AbstractEntity implements Entity
{
    /**
     * docstring
     */
    public Point pos = null;
    /**
     * docstring
     */
    public int speed;
    /**
     * docstring
     */
    public BufferedImage up1 = null,up2 = null,
	    left1 = null,left2 = null,right1 = null,
	    	right2 = null,down1 = null,down2 = null;

    /**
     * Used to count frames
     */
    public int spriteCounter;

    /**
     * Controlls when the sprite change
     */
    public int spriteFrames;
}
