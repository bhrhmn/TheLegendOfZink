package se.liu.hanba478henan555.objects.life;

import se.liu.hanba478henan555.entity.entity_abstract.AbstractEntity;
import se.liu.hanba478henan555.game_director.game_managers.ZinkPanel;
import se.liu.hanba478henan555.objects.abstract_game_object.AbstractObject;
import se.liu.hanba478henan555.objects.abstract_game_object.ObjectType;

import java.awt.*;


/**
 * A GameObject with which nothing happens when collided
 *
 * for TDDD78 at LIU 2022-03-25
 * 	hanba478@student.liu.se
 * 	henan555@student.liu.se
 */
public class BloodPile extends AbstractObject
{

    public BloodPile(final ZinkPanel zp) {
	super(zp, ObjectType.BLOOD_PILE, null);
    }

    @Override public void whenCollided(final AbstractEntity entity) {

    }

    @Override public void setCollisionArea() {
        Rectangle rectangle = new Rectangle(pos.x, pos.y, zinkPanel.getTileSize(), zinkPanel.getTileSize());
        collisionArea = rectangle;

    }

    @Override public void readImage() {
        image = getImage("images/enemyImages/death/dead.png");
    }
}
