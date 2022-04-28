package se.liu.hanba478henan555.objects.win_objects;

import se.liu.hanba478henan555.entity.entity_abstract.AbstractEntity;
import se.liu.hanba478henan555.entity.entity_abstract.EntityType;
import se.liu.hanba478henan555.game_director.game_managers.ZinkPanel;
import se.liu.hanba478henan555.objects.abstract_game_object.AbstractObject;
import se.liu.hanba478henan555.objects.abstract_game_object.ObjectType;

import java.awt.*;
import java.io.File;

/**
 * GameObject Princess
 * PLayer will win if colliding with Princess
 */
public class Princess extends AbstractObject
{
    public Princess(final ZinkPanel zp)
    {
	super(zp, ObjectType.PRINCESS, null);
    }

    @Override public void whenCollided(final AbstractEntity entity) {
	if (!entity.getEntityType().equals(EntityType.PLAYER))
	    return;
	zinkPanel.showWinScreen();
    }

    @Override public void setCollisionArea() {
	this.collisionArea = new Rectangle(this.pos.x, this.pos.y,
					   zinkPanel.getTileSize(), zinkPanel.getTileSize());
    }

    @Override public void readImage() {
	String fs = File.separator;
	image = getImage("images" + fs + "princess" + fs + "princess.png");
    }
}
