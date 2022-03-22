package se.liu.hanba478henan555;

import java.awt.*;

/**
 * Interface for every moving character
 */
public interface Entity
{
    public void attack();
    public void takeDamage();
    public void heal();
    public void setImages();
    public void setDefaultValues();
    public void update();
    public void moveEntity(EntityInput pi);
    public void draw(Graphics2D g2);
    public boolean hasCollision(Rectangle rectangle);
}
