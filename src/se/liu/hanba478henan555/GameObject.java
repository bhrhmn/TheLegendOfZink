package se.liu.hanba478henan555;

import java.awt.*;

/**
 * interface for every object in game
 */
public interface GameObject
{
    public void draw(Graphics2D g2);
    public String getName();
    public boolean hasCollision(Rectangle rectangle);
    public void hasCollided();
    public void setCollisionArea();
    public void setValues(String name, int x, int y);
}
