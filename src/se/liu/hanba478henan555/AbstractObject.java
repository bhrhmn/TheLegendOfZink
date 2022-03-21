package se.liu.hanba478henan555;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Abstract for the game objects.
 * Defines the functions draw, setValues, hasCollided and getters.
 */
public abstract class AbstractObject implements GameObject
{
    protected BufferedImage image = null;
    protected ObjectType objectType = null;
    protected Point pos = null;
    protected ZinkPanel zinkPanel;

    protected Rectangle collisionArea = null;
    protected int index = -1;
    protected ObjectType gameObject = null;

    protected AbstractObject(final ZinkPanel zp,final ObjectType gameObject,final int index) {
        this.zinkPanel = zp;
        this.index = index;
        this.gameObject = gameObject;
    }

    @Override public void draw(Graphics2D g2){
        g2.drawImage(image, pos.x, pos.y, zinkPanel.getTileSize(), zinkPanel.getTileSize(), null);
    }

    public ObjectType getObjectType() {
        return objectType;
    }

    /**
     * Sets startvalues such as position and collisionArea
     * @param x x-coordinate for position
     * @param y y-coordinate for position
     */
    @Override public void setValues(int x, int y) {
        this.pos = new Point(x*zinkPanel.getTileSize(), y*zinkPanel.getTileSize());
        setCollisionArea();
    }

    /**
     * Checks if collisionArea intersects with the given Rectangle
     * @param rectangle
     * @return Returns a boolean
     */
    @Override public boolean hasCollision(Rectangle rectangle) {
        return collisionArea.intersects(rectangle);
    }
}
