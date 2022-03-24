package se.liu.hanba478henan555.objects.abstract_game_object;


import se.liu.hanba478henan555.entity.entity_abstract.AbstractEntity;

import java.awt.*;

/**
 * interface for every object in game
 */
public interface GameObject
{
    public void draw(Graphics2D g2);
    public void update();
    public boolean hasCollision(Rectangle rectangle);
    public void whenCollided(AbstractEntity entity);
    public void setCollisionArea();
    public void setValues(int x, int y);
    public void readImage();
}
