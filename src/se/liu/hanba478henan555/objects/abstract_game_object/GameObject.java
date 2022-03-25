package se.liu.hanba478henan555.objects.abstract_game_object;


import se.liu.hanba478henan555.entity.entity_abstract.AbstractEntity;

import java.awt.*;

/**
 * interface for every gameobject in game
 *
 * for TDDD78 at LIU 2022-03-25
 * 	hanba478@student.liu.se
 * 	henan555@student.liu.se
 */
public interface GameObject
{
    public void draw(Graphics2D g2);
    public boolean hasCollision(Rectangle rectangle);
    public void whenCollided(AbstractEntity entity);
    public void setCollisionArea();
    public void setValues(int x, int y);
    public void readImage();
    public default void update(){}
}
