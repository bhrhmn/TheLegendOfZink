package se.liu.hanba478henan555;

import java.awt.*;

/**
 * interface for every object in game
 */
public interface GameObject
{
    public void draw(Graphics2D g2);
    public boolean hasCollision(Rectangle rectangle);
    public void whenCollided(AbstractEntity entity);
    public void setCollisionArea();
    public void setValues(int x, int y);
    public void readImage();
}
