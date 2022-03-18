package se.liu.hanba478henan555;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Abstract class of Entity
 * Some fields
 */
public abstract class AbstractEntity implements Entity
{

    protected Point pos = null;
    protected int speed;

    protected BufferedImage up1 = null,up2 = null,
	    left1 = null,left2 = null,right1 = null,
	    	right2 = null,down1 = null,down2 = null;

    protected int spriteCounter;
    protected int spriteFrames;

    protected Rectangle collisionArea = null;
    protected boolean collision = false;
}
