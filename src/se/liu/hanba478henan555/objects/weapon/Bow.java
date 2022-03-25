package se.liu.hanba478henan555.objects.weapon;

import se.liu.hanba478henan555.entity.entity_abstract.AbstractEntity;
import se.liu.hanba478henan555.entity.entity_abstract.EntityType;
import se.liu.hanba478henan555.game_director.game_managers.ZinkPanel;
import se.liu.hanba478henan555.objects.abstract_game_object.AbstractObject;
import se.liu.hanba478henan555.objects.abstract_game_object.ObjectType;


import java.awt.*;
import java.io.File;


/**
 * A weapon that allows an Entity to shoot projectiles
 *
 * for TDDD78 at LIU 2022-03-25
 * 	hanba478@student.liu.se
 * 	henan555@student.liu.se
 */
public class Bow extends AbstractObject
{
    public Bow(ZinkPanel zp){
	super(zp, ObjectType.PLAYER_BOW);
    }

    @Override public void readImage() {
	String fs = File.separator;
	image = setImage("images"+fs+"objectImages"+fs+"weapon"+fs+"bow_arrow"+fs+"bow.png");
    }

    @Override public void setCollisionArea() {
	this.collisionArea = new Rectangle(this.pos.x, this.pos.y,
					   zinkPanel.getTileSize(), zinkPanel.getTileSize());
    }

    @Override public void whenCollided(AbstractEntity entity) {
	if (!entity.getEntityType().equals(EntityType.PLAYER))
	    return;
	zinkPanel.getPlayer().getInventory().add(this);
	zinkPanel.getGameObjects().remove(this);
    }
}
