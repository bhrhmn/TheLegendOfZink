package se.liu.hanba478henan555;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Abstract
 */
public abstract class AbstractObject implements GameObject
{
    protected BufferedImage image = null;
    protected ObjectType objectType = null;
    protected Point pos = null;
    protected ZinkPanel zinkPanel;

    protected Rectangle collisionArea = null;

    protected AbstractObject(final ZinkPanel zp) {
        this.zinkPanel = zp;
    }

    @Override public void draw(Graphics2D g2){
        g2.drawImage(image, pos.x, pos.y, zinkPanel.getTileSize(), zinkPanel.getTileSize(), null);
    }

    public ObjectType getObjectType() {
        return objectType;
    }

    @Override public void setValues(ObjectType objectType, int x, int y) {
        this.objectType = objectType;
        this.pos = new Point(x*zinkPanel.getTileSize(), y*zinkPanel.getTileSize());
        setCollisionArea();
    }

    @Override public boolean hasCollision(Rectangle rectangle) {
        return collisionArea.intersects(rectangle);
    }
}
