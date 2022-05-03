package se.liu.hanba478henan555.entity.entity_abstract;

import java.awt.*;

/**
 * Interface for every entity character
 * Functions related to make an entity work
 * every entity has to do one or the other at one point
 *
 * for TDDD78 at LIU 2022-03-25
 * hanba478@student.liu.se
 * henan555@student.liu.se
 */
public interface Entity
{
    public void attack();
    public void takeDamage(int damage);
    public void setDefaultValues();
    public void update();
    public void moveEntity(EntityInput pi, int direction, int ammount);
    public void draw(Graphics2D g2);
    public boolean hasCollision(Rectangle rectangle);
}
