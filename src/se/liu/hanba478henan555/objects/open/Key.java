package se.liu.hanba478henan555.objects.open;

import se.liu.hanba478henan555.entity.entity_abstract.AbstractEntity;
import se.liu.hanba478henan555.entity.entity_enum.EntityType;
import se.liu.hanba478henan555.game_director.game_managers.ZinkPanel;
import se.liu.hanba478henan555.objects.abstract_game_object.AbstractObject;
import se.liu.hanba478henan555.objects.abstract_game_object.ObjectType;

import java.awt.*;
import java.io.File;

/**
 * GameObject Key
 * Used to open GameObject Door
 */
public class Key extends AbstractObject
{
    public Key(ZinkPanel zp){
	super(zp, ObjectType.KEY);
    }

    @Override public void readImage() {
	String fs = File.separator;
	image = setImage("images"+fs+"objectImages"+fs+"door_key"+fs+"key.png");
    }

    @Override public void setCollisionArea() {
	this.collisionArea = new Rectangle(this.pos.x, this.pos.y,
					   zinkPanel.getTileSize(), zinkPanel.getTileSize());
    }

    @Override public void update() {

    }

    @Override public void whenCollided(AbstractEntity entity) {
	if (!entity.getEntityType().equals(EntityType.PLAYER))
	    return;
	zinkPanel.getScreen().showKeyMessage();
	zinkPanel.getPlayer().addAmmountOfDoorKeys();
	zinkPanel.getSound().playSoundEffect(1);
	zinkPanel.getGameObjects().remove(this);
    }
}
