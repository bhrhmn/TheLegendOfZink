package se.liu.hanba478henan555.entity.entity_abstract;

import se.liu.hanba478henan555.entity.entity_enum.EntityInput;

import java.awt.*;

/**
 * Interface for every moving character
 */
public interface Entity
{
    public void attack();
    public void takeDamage(int damage);
    public void heal();
    public void setImages();
    public void setDefaultValues();
    public void update();
    public void moveEntity(EntityInput pi, int direction, int ammount);
    public void draw(Graphics2D g2);
    public boolean hasCollision(Rectangle rectangle);
}
