package se.liu.hanba478henan555;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

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

    public void getImages() {
	try {

	    up1 = ImageIO.read(getClass().getResourceAsStream("/images/hello_world.png"));
	} catch (IOException e) {
	    e.printStackTrace();
	}
    }
}
