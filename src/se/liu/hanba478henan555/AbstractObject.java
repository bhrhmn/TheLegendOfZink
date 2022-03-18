package se.liu.hanba478henan555;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Abstract
 */
public abstract class AbstractObject
{
    protected BufferedImage image = null;
    protected String name = null;
    protected Point pos = null;
    protected ZinkPanel zp;

    protected boolean collision = false;
    protected Rectangle collisionArea = null;

    protected AbstractObject(final ZinkPanel zp) {
        this.zp = zp;
    }

    public void draw(Graphics2D g2){
        g2.drawImage(image, pos.x * zp.getTileSize(), pos.y * zp.getTileSize(), zp.getTileSize(), zp.getTileSize(), null);
    }

    public String getName() {
        return name;
    }
}
